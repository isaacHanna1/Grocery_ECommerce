<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link rel="stylesheet" href="<c:url value="resources/css/all.min.css"/>" />
    <link rel="stylesheet" href="<c:url value="resources/css/normalize.css" />" />
    <link rel="stylesheet" href="<c:url value="resources/css/master.css" />" />

    <title>اضافة قسم</title>
  </head>
  <body>
    <!-- stat header -->
    <jsp:include page="template/header.jsp"/>
    <!-- End header -->

    <!-- Start section (nav menu) -->
    <jsp:include page="template/nav.jsp"/>
    <!-- End nav menu -->

    <div class="add-section">
      <h2>اضافة قسم</h2>
      <div class="container">
        <form method="post" action="/addCategory" accept-charset="UTF-8" id="categoryForm">
          <label for="categoryName">اسم القسم : </label>
          <input type="text" id="categoryName" name="categoryName" placeholder="ادخل اسم  القسم" required="required" th:field="*{categoryName}" />
			 <div class="errMessage" id="errMessage">
				${param.errMessage}
			</div>           
          <button class="btn" id="sendBtn">اضافة</button>
        </form>
      </div>
      <div class="table-container section-table">
        <h2>الاقسام</h2>
        <table>
          <thead>
            <tr>
              <td>م</td>
              <td>اسم القسم</td>
              <td>Action</td>
            </tr>
          </thead>
          <tbody>
          <c:forEach var="CategorySet" items="${allCategories}">
            <tr>
              <td>${CategorySet.id}</td>
              <td id="categoryNameCol">${CategorySet.categoryName}</td>
              <td>
                <a class="btn edit-btn">تعديل</a>
                <a href ="/deleteCategory/${CategorySet.id}" class="btn delete-btn">مسح</a>
              </td>
            </tr>
            </c:forEach>
          </tbody>
        </table>
      </div>
    </div>
<!-- stat footer -->
    <jsp:include page="template/footer.jsp"/>
    <!-- End footer -->
    <script src="<c:url value="resources/js/master.js"/>"></script>
  </body>
</html>
