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
      <ul>
        <li>
          <i class="fa-solid fa-caret-down"></i>
          <a href="<c:url value="/allOrders" />"> الفواتير المستلمة </a>
        </li>
        <li>
          <i class="fa-solid fa-caret-down"></i>
          <a href="<c:url value="/categoryPage" />">اضافة قسم</a>
        </li>
          <li>
          <i class="fa-solid fa-caret-down"></i>
          <a href="/subCategory">اضافة قسم فرعي</a>
        </li>
        <li>
          <i class="fa-solid fa-caret-down"></i>
          <a href="/unitPage">اضافة وحدة</a>
        </li>
        <li>
          <i class="fa-solid fa-caret-down"></i>
          <a href="/itemPage">اضافة صنف </a>
        </li>
        <li>
          <i class="fa-solid fa-caret-down"></i>
          <a href="/marketPlace/user"> وجة المستخدم </a>
        </li>
        
        <li>
          <i class="fa-solid fa-caret-down"></i>
          <a href="/logout"> تسجيل الخروج </a>
        </li>
        
      </ul>
    </div>

</body>
</html>