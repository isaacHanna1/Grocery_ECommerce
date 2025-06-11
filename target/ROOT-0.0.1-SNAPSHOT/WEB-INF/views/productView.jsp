<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link rel="stylesheet" href="<c:url value="../resources/css/all.min.css"/>" />
    <link rel="stylesheet" href="<c:url value="../resources/css/normalize.css" />" />
    <link rel="stylesheet" href="<c:url value="../resources/css/productViewStyle.css" />" />
    <link rel="stylesheet" href="<c:url value="../resources/css/master.css" />" />
    
	
    <title>WATAD|حاجتك اونلاين</title>
  </head>
  <body>
   <!-- start simple ad -->
  		<jsp:include page="template/headerAds.jsp"/>
  <!-- end simple ad -->
  	<!-- stat header -->
    	<jsp:include page="template/header.jsp"/>
    <!-- End header -->
    <!-- Start section (nav menu) -->
    <jsp:include page="template/userNav.jsp"/>
    <!-- End nav menu -->
  <div class="container">
  	<div class="product " id="${item.id}">
	<div class="product__photo">
		<div class="photo-container">
			<div class="photo-main <c:if test="${!item.avability}">sold-out</c:if>">
    				<img id="mainImgId" src="data:image/jpeg;base64,${base64Encoded}" alt="green apple slice">
			</div>
			<div class="photo-album">
				<ul>
					<c:forEach items="${subImages}" var="image">
    					<li>
    					   <img  src="data:image/jpeg;base64,${image.getBase64Data()}" alt="Uploaded Image">
    					</li>
    				</c:forEach>					
				</ul>
			</div>
		</div>
	</div>
	<div class="product__info">
		<div class="title">
			<h3 id="itemName">${item.getItemName()}</h3>
			<span class="code">COD: <span id="ItemId">${item.getId()}</span></span>
		</div>
		<div >
		 <span class="price" id="itemPrice">${item.getSellingPriceCustomer()}</span>
		 <span>${item.getUnitName()}</span>
		</div>
		
		<div class="description">
			<h3>المميزات</h3>
			<p>
			  ${item.getItemDescription()}
			</p>
		</div>
		<c:if test="${item.avability}">
		    <div>
		        <button id="btnPlus" class="change">+</button>
		        <input id="itemQuantity" type="number" value="1" class="quantity">
		        <button id="btnMinus" class="change">-</button>
		        <button id="btnAdd" class="add">اضف <i class="fa-solid fa-cart-arrow-down"></i></button>
		        <button id="buyNow" class="buy">اشتري الأن  <i class="fa-solid fa-money-bill-1"></i></button>
		    </div>
		</c:if>	
		</div>
</div>
</div>
  
   <!-- start footer -->
    	<jsp:include page="template/footer.jsp"/>
    <!-- End footer -->
    
   <div id="notify_id" class="notify dis-none">تم اضافة العنصر <i class="fa-solid fa-cart-arrow-down"></i></div>
   <script src="<c:url value="../resources/js/master.js"/>"></script>
   <script src="<c:url value="../resources/js/productView.js"/>"></script>
   
  </body>
</html>
