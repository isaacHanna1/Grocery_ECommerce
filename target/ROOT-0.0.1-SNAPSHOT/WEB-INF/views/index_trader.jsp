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
	
    <title>تاجر|WATAD|حاجتك ؟اونلاين</title>
  </head>
  <body>
  <!-- start simple ad -->
  		<jsp:include page="template/headerAds.jsp"/>
  <!-- end simple ad -->
  
  <!-- start warning  -->
   
  	<div class="warning">
  		<img src="<c:url value="../resources/imgs/groceryStore.jpg" />"/>
  	</div>
  
  <!-- end warning -->
   
  	<!-- stat header -->
    <jsp:include page="template/header.jsp"/>
    <!-- End header -->

  <!-- Start section (nav menu) -->
    <jsp:include page="template/userNav.jsp"/>
    <!-- End nav menu -->

 
 <div class="ads-board">
 	<!-- <img  src="<c:url value="../resources/imgs/mainCover.jpg" />"> -->
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
        <c:if test="${newItem.getDiscountPercentageTrader() != 0}">
        	<span class="discount">${newItem.getDiscountPercentageTrader()}</span>
        </c:if>
            <div class="img" title=""><img src="data:image/png;base64,${newItem.getImageAsBase64()}"></div>
            <div class="dis-flex">
                <button class="description" id="addToCart" title="${newItem.getItemDescription() }">
                    <i class="fa-solid fa-plus description" title="${newItem.getItemDescription()}"></i>
                </button>
                <a title="${newItem.getItemDescription()}" class="descriptionLink description">about</a>
            </div>
        </div>
        <div class="card-bottom">
            <div class="price">
                <div id="after-discount">${newItem.calcPriceAfterDiscount_trader()}</div>
                <c:if test="${newItem.discountPercentageTrader != 0}">
                <div id="befor-discount">${newItem.calcPrice_trader()}</div>
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
<div class="section_items">
 <c:forEach items="${highDiscount}" var="newItem">
    <div id="${newItem.getId()}" class="card">
        <div class="card-top">
        <c:if test="${newItem.getDiscountPercentageTrader() != 0}">
        	<span class="discount">${newItem.getDiscountPercentageTrader()}</span>
        </c:if>
            <div class="img" title=""><img src="data:image/png;base64,${newItem.getImageAsBase64()}"></div>
            <div class="dis-flex">
                <button class="description" id="addToCart" title="${newItem.getItemDescription() }">
                    <i class="fa-solid fa-plus description" title="${newItem.getItemDescription()}"></i>
                </button>
                <a title="${newItem.getItemDescription()}" class="descriptionLink description">about</a>
            </div>
        </div>
        <div class="card-bottom">
            <div class="price">
                <div id="after-discount">${newItem.calcPriceAfterDiscount_trader()}</div>
                <c:if test="${newItem.discountPercentageTrader != 0}">
                <div id="befor-discount">${newItem.calcPrice_trader()}</div>
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
    <!-- End footer -->
       <script src="<c:url value="../resources/js/master.js"/>"></script>
       <script src="<c:url value="../resources/js/marketPlace.js"/>"></script>
  </body>
</html>
