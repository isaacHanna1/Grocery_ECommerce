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
    <title>الاصناف</title>
    
  </head>
  <body>
  <!-- stat header -->
    <jsp:include page="template/header.jsp"/>
    <!-- End header -->

  <!-- Start section (nav menu) -->
    <jsp:include page="template/nav.jsp"/>
    <!-- End nav menu -->

    <!-- Start add item  -->
    <div class="add-item">
      <h2>اضافة صنف</h2>
      <div class="container">
        <a href="">الاصناف</a>
        <form action="/addItem" method="post" enctype="application/json" id="" >
          <div class="data-row">
          <label>اسم صنف : </label>
          <input id= "itemName" name="itemName" type="text" placeholder="ادخل اسم  الصنف" required="required" />
        </div>
        <div class="data-row">
          <label>صورة الصنف : </label>
          <input id= "image" name ="image" type="file" placeholder="ادخل اسم  القسم" required="required" />
        </div>
        <div class="data-row">
          <label>اسم القسم : </label>
          <select name="category.id" id="category "required="required" >
          <option value="" selected disabled>اختار القسم</option>
          <c:forEach var="category" items="${category}">
            <option value="${category.id}">${category.categoryName}</option>
            </c:forEach>
          </select>
        </div>
        <div class="data-row">
          <label>  الفرعي: </label>
          <select name="subCategory.id" id="subCategory" >
          	<c:forEach var="subCategory" items="${subCategory }">
            	<option value="${subCategory.id}">${subCategory.subCategoryName}</option>
            </c:forEach>
          </select>
        </div>
        <div class="data-row">
          <label>اسم الوحدة : </label>
          <select name="unit.id" id="unit" required="required" >
            <c:forEach var="unit" items="${unit}">
            	<option value="${unit.id}">${unit.unitName}</option>
            </c:forEach>
          </select>
        </div>
        <div class="data-row">
          <label>سعر الشراء : </label>
          <input id ="purchasePrice" name="purchasePrice" type="number" placeholder="ادخل سعر الشراء" required="required" />
        </div>
        <div class="data-row">
          <label>نسبة الربح من العميل: </label>
          <input id="profitMarginCustomer" name="profitMarginCustomer" type="number" placeholder="نسبة الربح العميل" required="required"  />
        </div>
        <div class="data-row">
          <label>نسبة الربح من التاجر : </label>
          <input id="profitMarginTrader" name="profitMarginTrader" type="number" placeholder="نسبة الربح تاجر" required="required"  />
        </div>
        <div class="data-row">
          <label>  خصم للعميل : </label>
          <input id="discountPercentageCustomer" name="discountPercentageCustomer" type="number" placeholder="0%" required="required"  />
        </div>
        <div class="data-row">
          <label> خصم للتاجر: </label>
          <input id="discountPercentageTrader" name="discountPercentageTrader" type="number" placeholder="0%" required="required" />
        </div>
        <div class="data-row">
          <label> البيع للعميل : </label>
          <input id="sellingPriceCustomer" name="sellingPriceCustomer" type="number" placeholder=" سعر البيع" required="required" />
        </div>
        <div class="data-row">
          <label> البيع للتاجر: </label>
          <input id="sellingPriceTrader" name="sellingPriceTrader" type="number" placeholder=" سعر للتاجر" required="required" />
        </div>
        <div class="data-row ">
          <label>تاريخ الاضافة : </label>
          <input id="addingDate" name="addingDate" type="date"  placeholder="تاريخ الاضافة" required="required" ></input>
        </div>
        <div class="data-row ">
          <label>وصف المنتج : </label>
          <textarea id="itemDescription" name="itemDescription" placeholder="ادخل  الوصف" required="required" ></textarea>
        </div>
        <div class="data-row ">
          <label>متاح : </label>
          <select name="avability" id="avability" required="required" >
            <option value="true">نعم</option>
            <option value="false">لا</option>
          </select>
        </div>
        <div class="btn-container">
          <button class="btn">اضافة</button>
        </div>
        </form>
      </div>
      </div>
    <!-- End add item  -->
<!-- start footer -->
    <jsp:include page="template/footer.jsp"/>
    <!-- End footer -->
   <script src="<c:url value="resources/js/master.js"/>"></script>
   <script src="<c:url value="resources/js/items.js"/>"></script>
  </body>
</html>
