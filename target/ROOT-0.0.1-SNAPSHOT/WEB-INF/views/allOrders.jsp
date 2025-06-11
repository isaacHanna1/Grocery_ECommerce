<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link rel="stylesheet" href="<c:url value="../resources/css/all.min.css"/>" />
    <link rel="stylesheet" href="<c:url value="../resources/css/normalize.css" />" />
    <link rel="stylesheet" href="<c:url value="../resources/css/master.css" />" />
    <link rel="stylesheet" href="<c:url value="../resources/css/allOrders.css" />" />
	
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
		<div class="search-filter">
		<label id="filter">فلتر البحث :</label>
				<form action="/orderWhere">
					<div class="item">
						<label  for="from">من:</label>
						<input type="Date" id="form" name="from" value="${dateFrom}"/>
					</div>
					<div class="item">
						<label for="to">إلي:</label>
						<input type="Date" id="To" name="to" value="${dateTo}"/>
					</div>
					<div class="item">
						<label>الحالة:</label>
					<select name="status">
						 <option value="">حدد الحالة</option>
        				 <option value="wait" ${status == 'wait' ? 'selected' : ''}>انتظار</option>
        				 <option value="hesitate" ${status == 'hesitate' ? 'selected' : ''}>متردد</option>
                         <option value="done" ${status == 'done' ? 'selected' : ''}>اكتمل</option>
                         <option value="cancel" ${status == 'cancel' ? 'selected' : ''}>الغاء</option>
					</select>
					</div>
					<div class="item">
					<input type="submit" value ="بحث" id="search">
					</div>
				</form>
		</div>
       <div class="table-container col-4 ">
        <h2>جميع الطلبات</h2>
        <table id="orderTable">
            <thead>
                <tr>
                    <td> ID</td>
                    <td>التاريخ</td>
                    <td>اسم العميل</td>
                    <td>الحالة</td>
                </tr>
            </thead>
            <tbody>
            
            <c:forEach var="order" items="${allOrder}">
                <tr>
					<td><a href="/orderDetails?orderId=${order.id}&from=${dateFrom}&to=${dateTo}">${order.id}</a></td>
        			<td><fmt:formatDate value="${order.orderDate}" pattern="yyyy-MM-dd"/></td>
                    <td>${order.user.getUserName()}</td>
                    <td>${order.status}</td>
                    <!-- Add more columns as needed -->
                </tr>
            </c:forEach>
            </tbody>
        </table>
        </div>
    </div>
       <div class="page">
	        <div>صفحة: </div>
	    	<c:forEach  var="i" begin= "1" end="${orderCount/10+1}" >
	    		<span><a href="/${endPoint}?pageNumber=${i}">${i}</a></span>
	    	</c:forEach>
    	</div>
	<!-- start footer -->
    <jsp:include page="template/footer.jsp"/>
    <!-- End footer -->
       <script src="<c:url value="../resources/js/master.js"/>"></script>
  </body>
</html>
