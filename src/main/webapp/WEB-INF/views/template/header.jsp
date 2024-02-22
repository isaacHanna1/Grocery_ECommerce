<%@ page pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
  </head>
<body>
<!-- stat header -->
    <div class="header-area">
      <div class="container">
        <div class="logo">WATAD</div>
        <div class="search-box">
          <form action="">
            <input
              type="text"
              name="search_box"
              placeholder="اكتب ما انت تبحث عنه ؟"
            />
          </form>
        </div>
        <div class="location">
          <i class="fa-solid fa-location-dot"></i>
          <span>${userData.getGovernment()},${userData.getCity()}</span>
          <span id="userId">${userData.getId()}</span>
        </div>
        <a href="/login" class="btn">تسجيل الدخول</a>

        <div class="active-user">${userData.getUserName()}</div>
        <a href="/invoice">
        <div class="cart-icon">
          <i class="fa-solid fa-cart-arrow-down"></i>
        		<div class="beforeCart">0</div>
        </div>
        </a>
      </div>
    </div>
    <!-- End header -->
</body>
</html>