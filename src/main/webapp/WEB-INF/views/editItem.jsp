<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link rel="stylesheet" href="<c:url value="../resources/css/all.min.css"/>" />
    <link rel="stylesheet" href="<c:url value="../resources/css/normalize.css" />" />
    <link rel="stylesheet" href="<c:url value="../resources/css/master.css" />" />
    <title>تعديل صنف</title>
    
  </head>
  <body>
  
     <!-- start simple ad -->
  		<jsp:include page="template/headerAds.jsp"/>
  <!-- end simple ad -->
  	<!-- stat header -->
  <!-- stat header -->
    <jsp:include page="template/header.jsp"/>
    <!-- End header -->

  <!-- Start section (nav menu) -->
    <jsp:include page="template/nav.jsp"/>
    <!-- End nav menu -->

    <!-- Start add item  -->
    <div class="add-item">
      <h2>تعديل صنف</h2>
      <div class="container">
        <a href="/allItems/1">الاصناف</a>
        <form action="/editItem/${item.id}" method="POST" enctype="multipart/form-data">
          <div class="data-row">
          <label>اسم صنف : </label>
          <input id= "itemName" name="itemName" type="text" placeholder="ادخل اسم  الصنف" required="required" value="${item.itemName}" />
        </div>
        <div class="data-row">
          <label>صورة الصنف : </label>
          <input id= "image" name ="image" type="file" placeholder="ادخل اسم  القسم" disabled/>
        </div>
        <div class="data-row">
          <label>اسم القسم : </label>
          <select  name="category.id" id="category "required="required" >
          <option value="" selected disabled>اختار القسم</option>
          <c:forEach var="category" items="${category}">
              <c:set var="selected" value="${category.categoryName eq item.categoryName}" />
            <option value="${category.id}"<c:if test="${selected}">selected</c:if>>${category.categoryName} </option>
            </c:forEach>
          </select>
        </div>
        <div class="data-row">
          <label>  الفرعي: </label>
          <select name="subCategory.id" id="subCategory" >
          	<c:forEach var="subCategory" items="${subCategory}">
          		<c:set var="selected" value="${subCategory.subCategoryName eq item.subCategoryName}" />
            	<option value="${subCategory.id}"<c:if test="${selected}">selected</c:if> >${subCategory.subCategoryName}</option>
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
          <input id ="purchasePrice" step="0.01" name="purchasePrice" type="number" placeholder="ادخل سعر الشراء" required="required" value="${item.purchasePrice}"/>
        </div>
        <div class="data-row">
          <label>نسبة الربح من العميل: </label>
          <input id="profitMarginCustomer" step="0.01" name="profitMarginCustomer" type="number" placeholder="نسبة الربح العميل" required="required" value="${item.profitMarginCustomer}" />
        </div>
        <div class="data-row">
          <label>نسبة الربح من التاجر : </label>
          <input id="profitMarginTrader" step="0.01" name="profitMarginTrader" type="number" placeholder="نسبة الربح تاجر" required="required" value="${item.profitMarginTrader}" />
        </div>
        <div class="data-row">
          <label>  خصم للعميل : </label>
          <input id="discountPercentageCustomer" step="0.01" name="discountPercentageCustomer" type="number" placeholder="0%" required="required" value="${item.discountPercentageCustomer}" />
        </div>
        <div class="data-row">
          <label> خصم للتاجر: </label>
          <input id="discountPercentageTrader" step="0.01" name="discountPercentageTrader" type="number" placeholder="0%" required="required" value="${item.discountPercentageTrader}" />
        </div>
        <div class="data-row">
          <label> البيع للعميل : </label>
          <input id="sellingPriceCustomer" step="0.01" name="sellingPriceCustomer" type="number" placeholder=" سعر البيع" required="required" value="${item.sellingPriceCustomer}" />
        </div>
        <div class="data-row">
          <label> البيع للتاجر: </label>
          <input id="sellingPriceTrader" step="0.01" name="sellingPriceTrader" type="number" placeholder=" سعر للتاجر" required="required" value="${item.sellingPriceTrader}" />
        </div>
        <div class="data-row ">
          <label>تاريخ الاضافة : </label>
          <input id="addingDate"  name="addingDate" type="date"  placeholder="تاريخ الاضافة" required="required" value="${item.addingDate}"/>
        </div>
        <div class="data-row ">
          <label>وصف المنتج : </label>
          <textarea id="itemDescription" name="itemDescription" placeholder="ادخل  الوصف" required="required" >${item.itemDescription}</textarea>
        </div>
        <div class="data-row ">
          <label>متاح : </label>
          <select name="avability" id="avability" required="required" >
          	<c:set value="${item.avability}" var="avability"/>
          	<c:choose>
          	<c:when test="${avability==true}">
            	<option value="true" selected="selected">نعم</option>
            	<option value="false">لا</option>
            </c:when>
            <c:otherwise>
            	<option value="true">نعم</option>
            	<option value="false" selected="selected">لا</option>
            </c:otherwise>
            </c:choose>
          </select>
        </div>
        <div class="btn-container">
          <button class="btn">تعديل</button>
        </div>
        </form>
      </div>
      </div>
    <!-- End add item  -->
<!-- start footer -->
    <jsp:include page="template/footer.jsp"/>
    <!-- End footer -->
   <script src="<c:url value="../resources/js/master.js"/>"></script>
   <script src="<c:url value="../resources/js/items.js"/>"></script>
  </body>
</html>
