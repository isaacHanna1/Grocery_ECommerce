<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%@ page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>


</head>
<body>
<div class="section-box">
      <div class="menu-icon" id="memu">
        <span></span>
        <span></span>
        <span></span>
      </div>
      <ul>
        <li>
          <i class="fa-solid fa-leaf"></i
          ><a href="../views/receipts.html"> الفواتير المستلمة </a>
        </li>
        <li>
          <i class="fa-solid fa-leaf"></i
          ><a href="<c:url value="/categoryPage" />">اضافة قسم</a>
        </li>
          <li>
          <i class="fa-solid fa-leaf"></i
          ><a href="/subCategory">اضافة قسم فرعي</a>
        </li>
        <li>
          <i class="fa-solid fa-leaf"></i
          ><a href="../views/units.html">اضافة وحدة</a>
        </li>
        <li>
          <i class="fa-solid fa-leaf"></i
          ><a href="../views/additem.html">اضافة صنف </a>
        </li>
        <li>
          <i class="fa-solid fa-leaf"></i
          ><a href="../views/index.html"> وجة المستخدم </a>
        </li>
      </ul>
    </div>

</body>
</html>