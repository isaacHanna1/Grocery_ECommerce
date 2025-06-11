<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link rel="stylesheet" href="<c:url value="../resources/css/all.min.css"/>" />
    <link rel="stylesheet" href="<c:url value="../resources/css/normalize.css" />" />
    <link rel="stylesheet" href="<c:url value="../resources/css/master.css" />" />
<title>كل الاصناف</title>
</head>
<body>
   <!-- start simple ad -->
  		<jsp:include page="template/headerAds.jsp"/>
  <!-- end simple ad -->
  	<!-- stat header -->
 <!-- stat header -->
    <jsp:include page="template/header.jsp"/>
    <!-- End header -->

    <!-- Start section (nav menu) -->
    <jsp:include page="template/nav.jsp"/>
    <!-- End nav menu -->
    	
    	
    <div class ="container">	
    	<div class="table-container col-6 show-items">
    	 	
        <h2>الاصناف</h2>
        <input id="searchInput_admin" placeholder="ابحث بإسم الصنف">
        <a class="add" href ="/itemPage">اضافة</a>
       <div class="customContainer"> 
        <table class="allItems">
          <thead>
            <tr>
              <td>م</td>
              <td>اسم الصنف</td>
              <td> س ش</td>
              <td>  س زبون</td>
              <td>  س تاجر</td>
              <td>Action</td>
            </tr>
          </thead>
          <tbody>
          <c:forEach var="all" items="${allItem}">
            <tr>
	              <td>${all.id}</td>
	              <td class="item-name"><span class="textContainer">${all.itemName}</span>
	              	<button class="see-more">المزيد</button>
	              	</td>
	              <td>${all.purchasePrice}</td>
	              <td>${all.sellingPriceCustomer}</td>
	              <td>${all.sellingPriceTrader}</td>
	              <td>
	                <a href="/showItem/${all.getId()}" class="btn edit-btn">تعديل</a>
	                <a href ="#" class="btn delete-btn">مسح</a>
	                <a href ="/image/${all.getId()}" class="btn update-img">الصورة</a>
	              </td>
            </tr>
            </c:forEach>
          </tbody>
        </table>
        </div>
         <div class="page">
	        <div>صفحة: </div>
	    	<c:forEach  var="i" begin= "1" end="${recordCount/10+1}" >
	    		<span><a href="/allItems/${i}">${i}</a></span>
	    	</c:forEach>
    	</div>
      </div>
    	
    </div>
    	
    <!-- stat footer -->
    <jsp:include page="template/footer.jsp"/>
    <!-- End footer -->
    <script src="<c:url value="../resources/js/master.js"/>"></script>
    <script src="<c:url value="../resources/js/showItems.js"/>"></script>
</body>
</html>