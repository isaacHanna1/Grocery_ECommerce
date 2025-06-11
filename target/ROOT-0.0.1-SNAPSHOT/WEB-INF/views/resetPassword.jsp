<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link rel="stylesheet" href="<c:url value="../resources/css/all.min.css"/>" />
    <link rel="stylesheet" href="<c:url value="../resources/css/normalize.css" />" />
    <link rel="stylesheet" href="<c:url value="../resources/css/master.css" />" />
    <link rel="stylesheet" href="<c:url value="../resources/css/signInUp.css" />" />
    
    <title>تغير الرقم السري</title>
  </head>
  <body>
  
    <!-- start simple ad -->
    <jsp:include page="template/headerAds.jsp"/>
    <!-- end simple ad -->
    <!-- start header -->
    <jsp:include page="template/header.jsp"/>
    <!-- End header -->

	<div class="container">
		<div class="changePassword">
			<h2>تغير كلمة السر</h2>
			<form action="/updatePassword" method="POST"
				onsubmit="return validatePassword()">
				<div class="wrap-input">
					<input type="password" id="newPassword" placeholder="الرقم السري"
						name="newPassword">
				</div>
				<div class="wrap-input">
					<input type="password" id="confirmPassword"
						placeholder="تأكيد الرقم السري" name="confirmPassword">
				</div>
				<input type="hidden" name="userName" value="${username}" />
				<div class="send">
					<button type="submit">تأكيد</button>
				</div>
				<div id="errorMessage" style="color: red; display: none;">الرقم
					السري غير متطابق</div>
			</form>
		</div>
	</div>
	<!-- start footer -->
    <jsp:include page="template/footer.jsp"/>
    <!-- End footer -->
    
    <script>
      function validatePassword() {
        const password = document.getElementById('newPassword').value;
        const confirmPassword = document.getElementById('confirmPassword').value;
        const errorMessage = document.getElementById('errorMessage');
        
        if (password !== confirmPassword) {
          errorMessage.style.display = 'block';
          return false;  
        }
        
        errorMessage.style.display = 'none';
        return true;  
      }
    </script>
    <script src="<c:url value="../resources/js/login.js"/>"></script>
  </body>
</html>
