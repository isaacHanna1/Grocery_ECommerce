<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link rel="stylesheet" href="<c:url value="../resources/css/all.min.css"/>" />
    <link rel="stylesheet" href="<c:url value="../resources/css/normalize.css" />" />
    <link rel="stylesheet" href="<c:url value="../resources/css/master.css" />" />
    <link rel="stylesheet" href="<c:url value="../resources/css/itemImage.css" />" />
	
    <title>WATAD|حاجتك اونلاين</title>
  </head>
  <body>
   <!-- start simple ad -->
  		<jsp:include page="template/headerAds.jsp"/>
  <!-- end simple ad -->
  
  <!-- start warning 
   
  	<div class="warning">
  		 <img src="<c:url value="../resources/imgs/header.png" />"/> 
  	</div>
  
   end warning -->
   
  	<!-- stat header -->
    <jsp:include page="template/header.jsp"/>
    <!-- End header -->

  <!-- Start section (nav menu) -->
    <jsp:include page="template/userNav.jsp"/>
    <!-- End nav menu -->

 
 <div class="ads-board">
 	<!-- <img  src="<c:url value="../resources/imgs/header.jpg" />"> -->
 </div>
 
 		

    <!-- Start item in section -->
    <div class="container">
    
	    <!-- path of category and sub category -->
	    <div class="container">
	      <div class="path">
	      </div>
	    </div>
	    <div class="sectionHeader">
	    	 <h2>وصل حديثاً</h2>
	    	 <p>تفقد كل ما هو جديد لدينا</p>
	    </div>
<div class="section_items">
 <c:forEach items="${newArrival}" var="newItem">
    <div id="${newItem.getId()}" class="card">
        <div class="card-top">
        <c:if test="${newItem.getDiscountPercentageCustomer() != 0}">
        	<span class="discount">${newItem.getDiscountPercentageCustomer()}</span>
        </c:if>
            <div class="img" title=""><img src="data:image/png;base64,${newItem.getImageAsBase64()}"></div>
            <div class="dis-flex flex-wrap">
                <button class="description" id="addToCart" title="${newItem.getItemDescription() }">
                    اضف الي العربة
                   <i class="fa-solid fa-cart-arrow-down description" title="${newItem.getItemDescription()}"></i>
                </button>
                <button  id="buyNow" title="${newItem.getItemDescription() }">
                    شراء الأن
					<i class="fa-solid fa-money-bill-1"></i>
                </button>
            </div>
        </div>
        <div class="card-bottom">
            <div class="price">
                <div id="after-discount">${newItem.calcPriceAfterDiscount()}</div>
                <c:if test="${newItem.discountPercentageCustomer != 0}">
                <div id="befor-discount">${newItem.calcPrice()}</div>
                </c:if>
            </div>
            <div class="item-name">${newItem.getItemName()}</div>
            <div class="unit">${newItem.getUnit().unitName}</div>
        </div>
    </div>
  </c:forEach>
  	<div class="sectionHeader">
		<h2 class="textCenter">خصم %</h2>
		<p>الاصناف ذات خصومات الاعلي اليوم</p>
	</div>
	<br/>
<div class="section_items_discount">
 <c:forEach items="${highDiscount}" var="newItem">
    <div id="${newItem.getId()}" class="card">
        <div class="card-top">
        <c:if test="${newItem.getDiscountPercentageCustomer() != 0}">
        	<span class="discount">${newItem.getDiscountPercentageCustomer()}</span>
        </c:if>
            <div class="img" title=""><img src="data:image/png;base64,${newItem.getImageAsBase64()}"></div>
            <div class="dis-flex flex-wrap">
                <button class="description" id="addToCart" title="${newItem.getItemDescription() }">
                    اضف الي العربة
                   <i class="fa-solid fa-cart-arrow-down description" title="${newItem.getItemDescription()}"></i>
                </button>
                <button  id="buyNow" title="${newItem.getItemDescription() }">
                    شراء الأن
					<i class="fa-solid fa-money-bill-1"></i>
                </button>
            </div>
        </div>
        <div class="card-bottom">
            <div class="price">
                <div id="after-discount">${newItem.calcPriceAfterDiscount()}</div>
                <c:if test="${newItem.discountPercentageCustomer != 0}">
                <div id="befor-discount">${newItem.calcPrice()}</div>
                </c:if>
            </div>
            <div class="item-name">${newItem.getItemName()}</div>
            <div class="unit">${newItem.getUnit().unitName}</div>
        </div>
    </div>
  </c:forEach>
</div>
</div>



    </div>
    <!-- End item in section -->
			<div class="page">
	        	<div>صفحة: </div>
    		</div>
	<!-- start footer -->
    <jsp:include page="template/footer.jsp"/>
    
    <div id="notify_id" class="notify dis-none">تم اضافة العنصر <i class="fa-solid fa-cart-arrow-down"></i></div>
    <!-- End footer -->
	   <script src="../resources/js/marketPlace.js"></script>
	   <script src="../resources/js/master.js"></script>
  </body>
</html>
