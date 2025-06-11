<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link rel="stylesheet" href="<c:url value="../resources/css/all.min.css"/>" />
    <link rel="stylesheet" href="<c:url value="../resources/css/normalize.css" />" />
    <link rel="stylesheet" href="<c:url value="../resources/css/master.css" />" />
    <link rel="stylesheet" href="<c:url value="../resources/css/tableDetails.css" />" />
    <title>WATAD|حاجتك اونلاين</title>
  </head>
  <body>
  <!-- start simple ad -->
  		<jsp:include page="template/headerAds.jsp"/>
  <!-- end simple ad -->
  	<!-- stat header -->
    <jsp:include page="template/header.jsp"/>
    <!-- End header -->

 
  <!-- Start section (nav menu) -->
    <jsp:include page="template/nav.jsp"/>
    <!-- End nav menu -->

	<div class="container">
	    <div class="orderDetails user_details">
    	<div class="row">
			<label>اسم العميل :</label>
			<label class="value"><a href="/userData/${order.getUser().getId()}">${order.getUser().getUserName()}</a></label>		
    	</div>
    	<div class="row">
			<label>رقم العميل :</label>
			<label class="value">${order.getUser().getUserPhone()}  </label>		
    	</div>
    	<div class="row">
			<label>العنوان:</label>
			<label class="value">${order.getUser().getGovernment()},${order.getUser().getCity()},
				                 ${order.getUser().getUserAddress()} </label>		
    	</div>
    	<div class="row">
			<label>عنوان الاوردر:</label>
			<label class="value">${order.details}</label>		
    	</div>
    	
    	<div class="row">
			<label>التاريخ:</label>
			<label class="value">${orderDateFormat}</label>		
    	</div>
    	<div class="row">
			<label>حالة الطلب:</label>
			<label class="value status">${order.getStatus()}</label>		
    	</div>
    	<div class="row">
			<label>اجمالي الفاتورة:</label>
        	<fmt:formatNumber value="${totalPrice}" pattern="00.00" />
    	</div>
			<div class="table-container col-4 ">
        <h2>الفاتورة</h2>
        <table id="orderTable">
            <thead>
                <tr>
                    <td> ID</td>
                    <td>اسم الصنف</td>
                    <td>الكمية</td>
                    <td>السعر</td>
                    <td>الاجمالي</td>
                </tr>
            </thead>
            <tbody>
            	<c:forEach var="items" items="${allItems}">
                <tr>
					<td>${items.item.getId()}</td>
					<td>${items.item.getItemName()}</td>
					<td>${items.getQuantity()}</td>
					<td>${items.getPrice()}</td>
					<td>${items.getPrice()* items.getQuantity()}</td>
                </tr>
            </c:forEach>
          
            </tbody>
        </table>
        </div>		
    	</div>
		<form action="/updateOrderStatus" class="orderDetails">
		<input type="hidden" name="orderId" value="${order.id}">
			<div class="row">
				<label>الحالة:</label>
				<select name="orderStatus" >
						 <option value="">حدد الحالة</option>
        				 <option value="wait" ${status == 'wait' ? 'selected' : ''}>انتظار</option>
        				 <option value="hesitate" ${status == 'hesitate' ? 'selected' : ''}>متردد</option>
                         <option value="done" ${status == 'done' ? 'selected' : ''}>اكتمل</option>
                         <option value="cancel" ${status == 'cancel' ? 'selected' : ''}>الغاء</option>
					</select>
			</div>
			<div class="row">
				<label>تعليق الحالة:</label>
				<textarea name="orderComment">${order.getStatusComment()}</textarea>
			</div>
			
			<input type="submit" value="حفظ">
		</form>
     </div>
     
     <!-- End item in section -->
			
     
	<!-- start footer -->
    <jsp:include page="template/footer.jsp"/>
    <!-- End footer -->
       <script src="<c:url value="../resources/js/master.js"/>"></script>
  </body>
</html>
     