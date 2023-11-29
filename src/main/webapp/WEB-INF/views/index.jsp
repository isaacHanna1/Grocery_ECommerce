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
  	<!-- stat header -->
    <jsp:include page="template/header.jsp"/>
    <!-- End header -->

  <!-- Start section (nav menu) -->
    <jsp:include page="template/userNav.jsp"/>
    <!-- End nav menu -->

 
 
 		

    <!-- Start item in section -->
    <div class="container">
    
	    <!-- path of category and sub category -->
	    <div class="container">
	      <div class="path">
	      </div>
	    </div>
      <div class="section_items">
      
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
