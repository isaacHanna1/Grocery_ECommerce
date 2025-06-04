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
      <img alt="logo watad" src="<c:url value="/resources/imgs/logo.png"/>"> 
      </div>

      <div class="menu-icon" id="memu">
        <span></span>
        <span></span>
        <span></span>
      </div>
      
      <ul class="mainMenu">
      
      	<c:forEach var="category" items="${allCategories}">
      	<li id="${category.id}"><i class="fa-solid fa-plus"></i>
      	<a href="#">${category.categoryName}</a></li>
      	</c:forEach>
      	<li>
      	<i class="fa-solid fa-house"></i>
      	<a href="/marketPlace">الصفحة الرئيسية</a>
      	</li>
		<c:choose>
		  <c:when test="${loggedIn}">
		    <li>
		    	<i class="fa-solid fa-arrow-right-from-bracket"></i>
		        <a href="/logout">تسجيل الخروج</a>
		    </li>
		  </c:when>
		   <c:otherwise>
		   	<li >
		   		<i class="fa-solid fa-arrow-right-from-bracket"></i>
		        <a href="/login">تسحيل الدخول</a>
		    </li>    
		   </c:otherwise>
		</c:choose>
      </ul>
    </div>
		
		<!-- End Subcategory Menu -->
<script src="<c:url value="/resources/js/userNav.js"/>"></script>
</body>
</html>