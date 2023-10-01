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

    <title>اضافة قسم فرعي</title>
  </head>
  <body>
    <!-- stat header -->
    <jsp:include page="template/header.jsp"/>
    <!-- End header -->

    <!-- Start section (nav menu) -->
    <jsp:include page="template/nav.jsp"/>
    <!-- End nav menu -->

    <div class="add-section">
      <h2>اضافة قسم فرعي</h2>
      <div class="container">
        <form  accept-charset="UTF-8" id="categoryForm">
       
          <label for="categoryName">اسم القسم الفرعي: </label>
          <input type="text" id="subCategoryName" name="subCategoryName" placeholder="ادخل اسم  القسم" required="required" " />
			 <div class="errMessage" id="errMessage">
				${param.errMessage}
			</div>  
			 <label for="categoriesOption">اسم القسم</label>
        	<select id="categoriesOption" name="categoyId">
        	 <c:forEach var="category" items="${allCategories}">
        	 	<option value="${category.id }">${category.categoryName}</option>
        	 </c:forEach>
        	</select>         
          <button class="btn" id="sendBtn">اضافة</button>
        </form>
      </div>
      <div class="table-container col-4 ">
        <h2>الاقسام الفرعية</h2>
        <table>
          <thead>
            <tr>
              <td>م</td>
              <td>الفرعي</td>
              <td>القسم</td>
              <td>Action</td>
            </tr>
          </thead>
          <tbody>
          <c:forEach var="subCategory" items="${allSubCategories}">
            <tr>
              <td>${subCategory.id}</td>
              <td id="">${subCategory.subCategoryName}</td>
              <td id="">${subCategory.categoryName}</td>
              <td>
                <a class="btn edit-btn">تعديل</a>
                <a href ="" class="btn delete-btn">مسح</a>
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
    <script src="<c:url value="resources/js/subCategory.js"/>"></script>
  </body>
</html>
