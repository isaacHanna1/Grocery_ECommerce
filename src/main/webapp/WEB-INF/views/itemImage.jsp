<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link rel="stylesheet" href="<c:url value="../resources/css/all.min.css"/>" />
    <link rel="stylesheet" href="<c:url value="../resources/css/normalize.css" />" />
    <link rel="stylesheet" href="<c:url value="../resources/css/master.css" />" />
    <link rel="stylesheet" href="<c:url value="../resources/css/itemImage.css" />" />
    <title>صورة صنف</title>
</head>
<body>
<!-- stat header -->
    <jsp:include page="template/header.jsp"/>
    <!-- End header -->

  <!-- Start section (nav menu) -->
    <jsp:include page="template/nav.jsp"/>
    <!-- End nav menu -->
    
    
    <!-- Start edit image -->
    		<div class ="container ">
    		<form action ="/updateImageItem/${itemDto.id}"class="updateImage" method="POST" enctype="multipart/form-data" >
    				<input type="file" name ="image" required="required" >
    				<button class="btn update-img">تعديل الصورة</button>
    			</form>
    			<div class="item-data">
    			
    				<div class="img">
    					<img src="data:image/jpeg;base64,${base64Encoded}" 
    					     alt="Image Description">
    				</div>
    				<div class="data ">
    					<div class="row col-2">
    						<span>الاسم:</span>
    						<span>${itemDto.itemName }</span>
    					</div>
    					<div class="row col-2">
    						<span>قسم:</span>
    						<span>${itemDto.categoryName }</span>
    					</div>
    					<div class="row col-2">
    						<span>فرعي</span>
    						<span>${itemDto.subCategoryName }</span>
    					</div>
    					<div class="row col-2">
    						<span>اسم الوحدة:</span>
    						<span>${itemDto.unitName }</span>
    					</div>
    						<div class="row col-2">
    						<span>سعر الشراء:</span>
    						<span>${itemDto.purchasePrice }</span>
    						</div>
    						<div class="row col-2">
    						<span>ربح عميل:</span>
    						<span>${itemDto.profitMarginCustomer } %</span>
    						</div>
    						<div class="row col-2">
    						<span> الربح تاجر  :</span>
    						<span>${itemDto.profitMarginTrader } %</span>
    						</div>
    						<div class="row col-2">
    						<span> الخصم العميل:</span>
    						<span>${itemDto.discountPercentageCustomer } %</span>
    						</div>
    						<div class="row col-2">
    						<span> الخصم التاجر:</span>
    						<span>${itemDto.discountPercentageTrader } %</span>
    						</div>
    						<div class="row col-2">
    						<span> البيع للعميل  :</span>
    						<span>${itemDto.sellingPriceCustomer}</span>
    						</div>
    						<div class="row col-2">
    						<span> البيع للتاجر  :</span>
    						<span>${itemDto.sellingPriceTrader }</span>
    						</div>
    						<div class="row col-2">
    						<span>تاريخ الاضافة :</span>
    						<span>${itemDto.addingDate }</span>
    						</div>
    						<div class="row col-2">
    						<span>وصف المنتج:</span>
    						<span>${itemDto.itemDescription }</span>
    						</div>
    						<div class="row col-2">
    						<span>متاح ؟  :</span>
    						<span>${itemDto.avability}</span>
    						</div>
    						
    					</div>
    				
    				
    				</div>
    			</div>
    		</div>
    
        
    <!-- End   edit image  -->
    
    
    
	<!-- start footer -->
    <jsp:include page="template/footer.jsp"/>
    <!-- End footer -->
       <script src="<c:url value="../resources/js/master.js"/>"></script>
    
</body>
</html>