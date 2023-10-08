<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link rel="stylesheet" href="<c:url value="resources/css/all.min.css"/>" />
    <link rel="stylesheet" href="<c:url value="resources/css/normalize.css" />" />
    <link rel="stylesheet" href="<c:url value="resources/css/master.css" />" />
    
    <title>تعديل الوحدات</title>
  </head>
  <body>
   <!-- stat header -->
    <jsp:include page="template/header.jsp"/>
    <!-- End header -->

    <!-- Start section (nav menu) -->
    <jsp:include page="template/nav.jsp"/>
    <!-- End nav menu -->


    <div class="add-section units">
      <h2>اضافة الوحدات </h2>
      <div class="container">
        <form method="POST" action="/addUnit">
        <div>
	          <label>اسم الوحدة : </label>
	          <input type="text" name ="unitName" placeholder=" اسم  الوحدة" />
	          <div class="errMessage" id="errMessage">
				${param.errMessage}
			</div>
          </div>
          <div>
	          <label>وصف الوحدة : </label>
	          <input type="text" name ="unitDecription" placeholder=" وصف  الوحدة" />
          </div>
          <button class="btn">اضافة</button>
        </form>
        <div class="table-container col-4">
        <h2>الوحدات</h2>
        <table>
          <thead>
            <tr>
              <td>م</td>
              <td>الوحدة</td>
              <td>الوصف</td>
              <td>Action</td>
            </tr>
          </thead>
          <tbody>
          <c:forEach var="unit" items="${allUnits}">
            <tr>
              <td>${unit.id}</td>
              <td id="unitName">${unit.unitName}</td>
              <td id="unitDescription">${unit.unitDecription}</td>
              <td>
                <a class="btn edit-btn">تعديل</a>
                <a class="btn delete-btn">مسح</a>
              </td>
            </tr>
            </c:forEach>
          </tbody>
        </table>
      </div>
      </div>
    </div>
<!-- stat footer -->
    <jsp:include page="template/footer.jsp"/>
    <!-- End footer -->
	 <script src="<c:url value="resources/js/master.js"/>"></script>
	 <script src="<c:url value="resources/js/unit.js"/>"></script>
  </body>
</html>
