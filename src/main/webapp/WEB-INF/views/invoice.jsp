<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link rel="stylesheet" href="<c:url value="../resources/css/all.min.css"/>" />
    <link rel="stylesheet" href="<c:url value="../resources/css/normalize.css" />" />
    <link rel="stylesheet" href="<c:url value="../resources/css/invoice.css" />" />
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
    	<div class="content">
    	    <h2>الفاتورة</h2>
    	</div>
    	<div>
    	<!-- 
    	<div class="orderInfo">
    	<h3>تأكيد البيانات </h3>
    	 <input type="checkBox" class="changeAddress">
    	 <label>تغير العنوان</label>
    	<div class="address">
    		<h4>العنوان:</h4>
    		<input id="goverment" value="${userData.getGovernment()}" disabled ="disabled">
    		<input id="city"      value="${userData.getCity()}"       disabled ="disabled">
    		<input id="address"   value="${userData.userAddress}"     disabled ="disabled">
    	</div>
    	<textarea class="userNeeds" placeholder="هل تريد اضافة تفاصيل اضافية علي الطلب ؟"></textarea>
    	</div>
    	 -->
    	<div class="finishReceipt">
    		<div class="total">
    			<span class="span-bold">الإجمالي : </span>
    			<span id="totalOfReceipt" >0</span>
    		</div>
    		<button id="send">أطلب</button>
    	</div>
    </div>
    </div>
    
    <!-- start footer -->
    	<jsp:include page="template/footer.jsp"/>
    <!-- End footer -->
    
    
    <script src="<c:url value="resources/js/master.js"/>"></script>
	<script src="<c:url value="../resources/js/invoice.js"/>"></script>
	
	
			<input type ="hidden" id="goverment" value="${userData.getGovernment()}" disabled ="disabled">
    		<input type ="hidden" id="city"      value="${userData.getCity()}"       disabled ="disabled">
    		<input type ="hidden" id="address"   value="${userData.userAddress}"     disabled ="disabled">
    		<input type ="hidden" id="phone"   value="${userData.userPhone}"     disabled ="disabled">
  </body>
</html>


