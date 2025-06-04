package com.watad.Dao;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.annotation.PostConstruct;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.watad.Dto.ItemDTOSearch;
import com.watad.Dto.ItemDto;
import com.watad.controllers.MarketplaceController;
import com.watad.model.Item;
import com.watad.model.Observer;
import com.watad.model.User;
import com.watad.model.itemImages;
import com.watad.model.Subject;
import com.watad.notification.strategy.EmailNotifications;
import com.watad.notification.strategy.NotificationStrategy;


@Component
public class itemDaoImp implements itemDao  ,Subject{


	@Autowired
	private SessionFactory mySessionFactory;
	
	@Autowired
	private UserDao userDao;
	
	
    private static final Logger logger = LogManager.getLogger(MarketplaceController.class);
	private final List<Observer> observers = new CopyOnWriteArrayList<>();
	private final NotificationStrategy notificationStrategy;
    private final HttpServletRequest request;

	
	
	
	 	@Autowired
	    public itemDaoImp(EmailNotifications emailNotifications , HttpServletRequest request) {
	        this.notificationStrategy = emailNotifications;
	        this.request   = request;
	    }



	public void setMySessionFactory(SessionFactory mySessionFactory) {
		this.mySessionFactory = mySessionFactory;
	}
	
	 	@PostConstruct
	    public void init() {
	        loadObserversFromDatabase();
	    }
	@Override
	public Item addingNewItem(Item item) {
		Session session = this.mySessionFactory.getCurrentSession();
		item.setItemName(item.getItemName().toLowerCase());
		session.save(item);
		notifyUser(item);
		return item;
	}

	@Override
	public List<ItemDto> getAllItems() {
		Session session = this.mySessionFactory.getCurrentSession();
		String hql = "SELECT NEW com.watad.Dto.ItemDto(I.id, I.itemName, I.purchasePrice, I.sellingPriceCustomer, I.sellingPriceTrader) FROM Item I";
		Query<ItemDto> query = session.createQuery(hql, ItemDto.class);
		List<ItemDto> results = query.list();
		return results;
	}
	
	
	@Override
	public Item deleteItem(long id) {
		 Session session = this.mySessionFactory.getCurrentSession();
		 Item item = session.get(Item.class, id);
		 if(item != null) {
		 session.delete(item);
		 return item;
		 }
		 return null;
	}

	@Override
	public List<ItemDto> getSpecificItems(int pageNumber) {
		int start = (pageNumber - 1) * 10;
		Session session = this.mySessionFactory.getCurrentSession();
		String hql = "SELECT NEW com.watad.Dto.ItemDto(I.id, I.itemName, I.purchasePrice, I.sellingPriceCustomer, I.sellingPriceTrader) FROM Item I";
		Query<ItemDto> query = session.createQuery(hql, ItemDto.class);
		query.setFirstResult(start);
		query.setMaxResults(10);
		List<ItemDto> results = query.list();
		return results;	
	}

	@Override
	public List<ItemDto> getSpecificItemsForCustomers(int pageNumber, long categoryID, long subCategoryId , String userRole) {

		int start = (pageNumber - 1) * 16;
		Session session = this.mySessionFactory.getCurrentSession();
		String hql =null;
		if(userRole.equals("ADMIN") || userRole.equals("USER")) {

		hql = "SELECT NEW com.watad.Dto.ItemDto"
				+ "(i.id , i.itemName , i.image ,"
				+ "u.unitName, i.discountPercentageCustomer, "
				+ "i.sellingPriceCustomer , i.addingDate ,"
				+ " i.itemDescription , i.avability)"
				+ " from Item i "
				+ "join i.unit u "
				+ "join i.category c "
				+ "Join i.subCategory s "
				+ "where c.id = :categoryId and s.id = :subcategoryId";
		}else if(userRole.equals("TRADER")){
			hql = "SELECT NEW com.watad.Dto.ItemDto"
					+ "(i.id , i.itemName , i.image ,"
					+ "u.unitName, i.discountPercentageTrader, "
					+ "i.sellingPriceTrader , i.addingDate ,"
					+ " i.itemDescription , i.avability)"
					+ " from Item i "
					+ "join i.unit u "
					+ "join i.category c "
					+ "Join i.subCategory s "
					+ "where c.id = :categoryId and s.id = :subcategoryId";
		}else {
			hql="";
		}		
		System.out.println(hql);
		Query<ItemDto> query = session.createQuery(hql, ItemDto.class);
		query.setParameter("categoryId",    categoryID);
		query.setParameter("subcategoryId", subCategoryId);
		query.setFirstResult(start);
		query.setMaxResults(16);
		List<ItemDto> results = query.list();
		return results;		
	}

	@Override
	public long getCountOfRecordOfItems() {
		Session session = this.mySessionFactory.getCurrentSession();
		String HQl = "SELECT COUNT(*) FROM Item";
		Query<Long> query = session.createQuery(HQl, Long.class);
		long count = query.uniqueResult();
		System.out.println("the count is "+count);
		if(count%2 == 0) {
		return count;
		}
		return count+1;
	}


	@Override
	public long getCountOfRecOfItems(long categoryID , long subCategoryID) {
		Session session = this.mySessionFactory.getCurrentSession();
		String HQl = "SELECT COUNT(*) FROM Item i where "
				+ "i.category.id     = :categoryId "+""
				+ "and i.subCategory.id = :subCategoryId";
		Query<Long> query = session.createQuery(HQl, Long.class);
		query.setParameter("categoryId", categoryID);
        query.setParameter("subCategoryId", subCategoryID);
		long count = query.uniqueResult();
		System.out.println("the count is "+count);
		if(count%2 == 0) {
		return count;
		}
		return count+1;
		
	}

	@Override
	public ItemDto getItemById(long id) {
		Session session = this.mySessionFactory.getCurrentSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<ItemDto> criteriaQuery = builder.createQuery(ItemDto.class);
		Root<Item> itemRoot = criteriaQuery.from(Item.class);
		itemRoot.join("category", JoinType.LEFT);
		itemRoot.join("subCategory", JoinType.LEFT);
		itemRoot.join("unit", JoinType.LEFT);

		criteriaQuery.select(builder.construct(
			    ItemDto.class,
			    itemRoot.get("id"),
			    itemRoot.get("itemName"),
			    itemRoot.get("image"),
			    itemRoot.get("category").get("categoryName"),
			    itemRoot.get("subCategory").get("subCategoryName"),
			    itemRoot.get("unit").get("unitName"),
			    itemRoot.get("purchasePrice"),
			    itemRoot.get("profitMarginCustomer"),
			    itemRoot.get("profitMarginTrader"),
			    itemRoot.get("discountPercentageCustomer"),
			    itemRoot.get("discountPercentageTrader"),
			    itemRoot.get("sellingPriceCustomer"),
			    itemRoot.get("sellingPriceTrader"),
			    itemRoot.get("addingDate"),
			    itemRoot.get("itemDescription"),
			    itemRoot.get("avability")
			));

			// Add a WHERE clause to filter by item ID
			criteriaQuery.where(builder.equal(itemRoot.get("id"), id));
		    ItemDto itemDto = session.createQuery(criteriaQuery).getSingleResult();
		    return itemDto;
	}


	@Override
	public Item getItem(long id) {
		Session session = this.mySessionFactory.getCurrentSession();
		Item item = session.get(Item.class, id);
		return item;
	}
	@Override
	public byte[] getImageDataById(long itemId) {	
		Item item = getItem(itemId);
		if(item != null) {
			return item.getImage();
		}
		return null;
	}



	@Override
	public void updateImage(byte[] image , long itemID) {
		Item item = getItem(itemID);
		item.setImage(image);
		Session session = this.mySessionFactory.getCurrentSession();
		session.update(item);
	}


	@Override
	public Item updateItem(long id , Item item) {
		Item Olditem = getItem(id);
		item.setImage(Olditem.getImage());
		item.setId(Olditem.getId());
		Session session = this.mySessionFactory.getCurrentSession();
		session.merge(item);
		return item;
	}


	@Override
	public void uploadImages(long itemID, MultipartFile[] images) throws IOException {
		Session session = this.mySessionFactory.getCurrentSession();
		 Item item = session.get(Item.class, itemID);
	        if (item == null) {
	            throw new RuntimeException("Item not found with ID: "+itemID);
	        }
	        System.out.println("im called multi images");
	        System.err.println("the size of images is "+images.length);
		for (MultipartFile image : images) {
            byte[] imageBytes = image.getBytes();
            itemImages itemImage = new itemImages();
            itemImage.setItem(item);
            itemImage.setImage(imageBytes);
            item.getItemImage().add(itemImage);

        }
		session.merge(item);
		
	}


	@Override
	public List<itemImages> getSubImages(long itemId) {
		Item item = getItem(itemId);
		if(item != null) {
			return item.getItemImage();
		}
		return null;
	}


	@Override
	public boolean deleteItemImage(long id) {
		Session session = this.mySessionFactory.getCurrentSession();
		itemImages itemImage = session.get(itemImages.class, id); // id of image
		System.out.println("im here");
		if(itemImage == null) {
			return false ;
		}
		session.delete(itemImage);
		return true;
	}

	
	public List<Item> getNewArrival(){
		Session session = this.mySessionFactory.getCurrentSession();
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<Item> cr = cb.createQuery(Item.class);
		Root<Item> root = cr.from(Item.class);
		// Calculate the date 15 days ago from today
		//LocalDate fromDate = LocalDate.now().minusDays(15);

		// Convert LocalDate to java.util.Date
		//Date fromDateUtil = Date.from(fromDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

		//Predicate datePredicate = cb.between(root.get("addingDate"),fromDateUtil,new Date());

		Predicate availabilityPredicate = cb.isTrue(root.get("avability"));
		//Predicate finalPredicate = cb(availabilityPredicate);
		
		cr.select(root).where(availabilityPredicate);
	    cr.orderBy(cb.desc(root.get("addingDate")));
		int maxResult = 20 ;
	    TypedQuery<Item> query = session.createQuery(cr);
	    query.setMaxResults(maxResult); // Set maximum results
	    List<Item> results = query.getResultList();

		System.out.println("the size is :"+results.size());
		return results;
	}


		@Override
	public List<Item> getHighDiscounts() {
			Session session = this.mySessionFactory.getCurrentSession();
			CriteriaBuilder cb = session.getCriteriaBuilder();
			CriteriaQuery<Item> cr = cb.createQuery(Item.class);
			Root<Item> root = cr.from(Item.class);
			Predicate discountPredicate = cb.gt(root.get("discountPercentageCustomer"),0);
			
		    cr.orderBy(cb.desc(root.get("discountPercentageCustomer")));
		    Predicate availabilityPredicate = cb.isTrue(root.get("avability"));
		    Predicate finalPredicate = cb.and(discountPredicate, availabilityPredicate);
		    cr.select(root).where(finalPredicate);
			int maxResult = 20 ;
			session.createQuery(cr).setMaxResults(maxResult);
			List<Item> results = session.createQuery(cr).getResultList();
			return results;
		}
		

		
		
	@Override
	public List<Item> getHighDiscounts_trader() {
			Session session = this.mySessionFactory.getCurrentSession();
			CriteriaBuilder cb = session.getCriteriaBuilder();
			CriteriaQuery<Item> cr = cb.createQuery(Item.class);
			Root<Item> root = cr.from(Item.class);
			Predicate discountPredicate = cb.gt(root.get("discountPercentageTrader"),0);
			
		    cr.orderBy(cb.desc(root.get("discountPercentageTrader")));
		    Predicate availabilityPredicate = cb.isTrue(root.get("avability"));
		    Predicate finalPredicate = cb.and(discountPredicate, availabilityPredicate);
		    cr.select(root).where(finalPredicate);
			int maxResult = 20 ;
			session.createQuery(cr).setMaxResults(maxResult);
			List<Item> results = session.createQuery(cr).getResultList();
			return results;
		}

	    public List<ItemDTOSearch> getItemsAsIDAndName(String partOfItemName) {
	        Session session = this.mySessionFactory.getCurrentSession();
	        String hql = "SELECT NEW com.watad.Dto.ItemDTOSearch(i.id, i.itemName) " +
	                     "FROM Item i WHERE i.itemName LIKE :partOfItemName";
	        Query<ItemDTOSearch> query = session.createQuery(hql, ItemDTOSearch.class);
	        query.setParameter("partOfItemName", "%" + partOfItemName + "%");
	        query.setMaxResults(20);
	        List<ItemDTOSearch> item = query.list();
	        System.out.println("the size of item is : "+item.size());
	        return item;
	    }


		@Override
		public void registerObserver(Observer observer) {
			if (!observers.contains(observer)) {
				observers.add(observer);
			}
		}


		@Override
		public void unregisterObserver(Observer observer) {
			if (observers.contains(observer)) {
				observers.remove(observer);
			}
		}


	@Override
	public void notifyUser(Item item) {
		String baseUrl = request.getRequestURL().toString().replace(request.getRequestURI(), "");
		String itemUrl = baseUrl + "/getItemPage/" + item.getId();
		if (observers.isEmpty()) {
				logger.warn("No observers to notify.");
		}
		for (Observer observer : observers) {
			try {
			 if (observer instanceof User) { // Ensure it's a User instance
				User user = (User) observer;
				 String message = "مرحبا يا " + user.getUserName() + "، قمنا بإضافة عنصر جديد باسم "+ item.getItemName() + ". يمكنك مشاهدته هنا: " + itemUrl;
				 String subject = "إضافة صنف جديد";
				 observer.notify(notificationStrategy, subject, message);
					}
			} catch (Exception e) {
				logger.error("Failed to notify observer: " + observer, e);
			}
			}
		}

		private void loadObserversFromDatabase() {
			List<User> users = userDao.getAllUser();
			for(User user : users) {
				logger.info("the user email is "+user.getUserEmail());
			registerObserver(user);
			}
		}

}

