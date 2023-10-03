<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link rel="stylesheet" href="../css/all.min.css" />
    <link rel="stylesheet" href="../css/normalize.css" />
    <link rel="stylesheet" href="../css/master.css" />

    <title>تعديل الوحدات</title>
  </head>
  <body>
    <!-- stat header -->
    <div class="header-area">
      <div class="container">
        <div class="logo">WATAD</div>
        <div class="active-user">زائر</div>
      </div>
    </div>
    <!-- End header -->

    <!-- Start section (nav menu) -->
    <jsp:include page="template/nav.jsp"/>
    <!-- End nav menu -->

    <div class="add-section units">
      <h2>تعديل الوحدات</h2>
      <div class="container">
        <form action="">
          <label>اسم الوحدة : </label>
          <input type="hidden" />
          <input type="text" placeholder="ادخل اسم  الوحدة" />
          <button class="btn">تعديل</button>
        </form>
      </div>
    </div>

    <script src="../js/master.js" />
  </body>
</html>
