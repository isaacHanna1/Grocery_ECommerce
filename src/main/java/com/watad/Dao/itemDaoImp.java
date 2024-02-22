package com.watad.Dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.watad.Dto.ItemDto;
import com.watad.model.Item;


@Component
public class itemDaoImp implements itemDao {


	@Autowired
	private SessionFactory mySessionFactory;
	
	public void setMySessionFactory(SessionFactory mySessionFactory) {
		this.mySessionFactory = mySessionFactory;
	}
	
	
	@Override
	public Item addingNewItem(Item item) {
		Session session = this.mySessionFactory.getCurrentSession();
		session.save(item);
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
	public List<ItemDto> getSpecificItemsForCustomers(int pageNumber, long categoryID, long subCategoryId) {

		int start = (pageNumber - 1) * 15;
		Session session = this.mySessionFactory.getCurrentSession();
		String hql = "SELECT NEW com.watad.Dto.ItemDto"
				+ "(i.id , i.itemName , i.image ,"
				+ "u.unitName, i.discountPercentageCustomer, "
				+ "i.sellingPriceCustomer , i.addingDate ,"
				+ " i.itemDescription , i.avability)"
				+ " from Item i "
				+ "join i.unit u "
				+ "join i.category c "
				+ "Join i.subCategory s "
				+ "where c.id = :categoryId and s.id = :subcategoryId";
		System.out.println(hql);
		Query<ItemDto> query = session.createQuery(hql, ItemDto.class);
		query.setParameter("categoryId",    categoryID);
		query.setParameter("subcategoryId", subCategoryId);
		query.setFirstResult(start);
		query.setMaxResults(15);
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
}

