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
 <!-- start simple ad -->
  		<jsp:include page="template/headerAds.jsp"/>
  <!-- end simple ad -->
<!-- stat header -->
    <jsp:include page="template/header.jsp"/>
    <!-- End header -->

  <!-- Start section (nav menu) -->
    <jsp:include page="template/nav.jsp"/>
    <!-- End nav menu -->
    
    
    <!-- Start edit image -->

    		<div class ="container ">
    		    		<h1>${itemDto.itemName }</h1>
    			<div class="item-data">
    		    			
    				<div class="img">
    					<img src="data:image/jpeg;base64,${base64Encoded}" 
    					     alt="Image Description">
    					     <div class="subImage">
    					     	    <c:forEach items="${subImages}" var="image">
    					     	      <div class="imageContainer">
    								    <img  src="data:image/jpeg;base64,${image.getBase64Data()}" alt="Uploaded Image">
    								    <button id="${image.getImageId()}" class="deleteIcon" ><i class="fa-solid fa-xmark"></i></button>
    								    
    								   </div>
    								</c:forEach>
    					     </div>
    				</div>
    				
    				<div class="data ">
    				<div class="row">
    					<div class=" col-2">
    						<span>الاسم:</span>
    						<span>${itemDto.itemName }</span>
    					</div>
    					<div class=" col-2">
    						<span>قسم:</span>
    						<span>${itemDto.categoryName }</span>
    					</div>
    				</div>
    				<div class="row">
    					<div class="col-2">
    						<span>فرعي:</span>
    						<span>${itemDto.subCategoryName }</span>
    					</div>
    					<div class="col-2">
    						<span>الوحدة:</span>
    						<span>${itemDto.unitName }</span>
    					</div>
    					
    				</div>
    				<div class="row">
    						<div class="col-2">
    						<span>سعر الشراء:</span>
    						<span>${itemDto.purchasePrice }</span>
    						</div>
    						<div class="col-2">
    						<span>ربح عميل:</span>
    						<span>${itemDto.profitMarginCustomer } %</span>
    						</div>
    				</div>
    				<div class="row">
    						<div class="col-2">
    						<span> الربح تاجر  :</span>
    						<span>${itemDto.profitMarginTrader } %</span>
    						</div>
    						<div class="col-2">
    						<span> الخصم العميل:</span>
    						<span>${itemDto.discountPercentageCustomer } %</span>
    						</div>
    				</div>
    				<div class="row">
    						<div class="col-2">
    						<span> الخصم التاجر:</span>
    						<span>${itemDto.discountPercentageTrader } %</span>
    						</div>
    						<div class="col-2">
    						<span> البيع للعميل  :</span>
    						<span>${itemDto.sellingPriceCustomer}</span>
    						</div>
    			    </div>
    			    <div class="row">
    						<div class="col-2">
    						<span> البيع للتاجر  :</span>
    						<span>${itemDto.sellingPriceTrader }</span>
    						</div>
    						<div class="col-2">
    						<span>تاريخ الاضافة :</span>
    						<span>${itemDto.addingDate }</span>
    						</div>
					</div>    
					<div class="row">						
    						<div class="col-2">
    						<span>وصف المنتج:</span>
    						<span>${itemDto.itemDescription }</span>
    						</div>
    						<div class="col-2">
    						<span>متاح ؟  :</span>
    						<span>${itemDto.avability}</span>
    						</div>
    				</div>	
    				
    				<div class="forms">
    				<label>تعديل الصورة الرئيسية </label>
    					<form action ="/updateImageItem/${itemDto.id}"class="updateImage" method="POST" enctype="multipart/form-data" >
    						<input type="file" name ="imageFile" required="required" placeholder="اختر الصور الرئيسية" >
    						<button class="btn update-img"> تعديل</button>
    					</form>
    			    <label>تعديل الصورة الفرعية</label>
    					<form action ="/addItemImages/${itemDto.id}" class="updateImage" method="POST" enctype="multipart/form-data" >
    						<input type="file" name ="images" required="required"  multiple placeholder="اختر الصور الفرعية">
    						<button class="btn update-img"> تعديل</button>
    					</form>
    			
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
       <script src="<c:url value="../resources/js/itemImage.js"/>"></script>
    
</body>
</html>