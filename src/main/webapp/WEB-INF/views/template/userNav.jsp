<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%@ page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>


</head>
<body>
<div class="section-box">
	<div class="logo">
      		<img alt="logo watad" src="<c:url value="../resources/imgs/logoCompany.png"/>">
      </div>

      <div class="menu-icon" id="memu">
        <span></span>
        <span></span>
        <span></span>
      </div>
      
      <ul class="mainMenu">
      
      	<c:forEach var="category" items="${allCategories}">
      	<li id="${category.id}"><i class="fa-solid fa-caret-down"></i><a href="#">${category.categoryName}</a></li>
      	</c:forEach>
      </ul>
    </div>

<script src="<c:url value="../resources/js/userNav.js"/>"></script>
</body>
</html>