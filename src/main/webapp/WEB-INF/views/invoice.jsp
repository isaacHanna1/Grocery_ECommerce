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
  
  	<!-- stat header -->
    	<jsp:include page="template/header.jsp"/>
    <!-- End header -->
  
    
    <div class="container">
    	<div class="content">
    	    <h2>الفاتورة</h2>
    	</div>
    	<div class="finishReceipt">
    		<div class="total">
    			<span class="span-bold">الإجمالي : </span>
    			<span id="totalOfReceipt" >0</span>
    		</div>
    		<button>ارسال</button>
    	</div>
    </div>
    
    
    <!-- start footer -->
    	<jsp:include page="template/footer.jsp"/>
    <!-- End footer -->
    
    
	<script src="<c:url value="../resources/js/invoice.js"/>"></script>
  </body>
</html>
