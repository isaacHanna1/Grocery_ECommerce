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
	
    <title>WATAD|حاجتك اونلاين</title>
  </head>
  <body>
  <!-- start simple ad -->
  		<jsp:include page="template/headerAds.jsp"/>
  <!-- end simple ad -->
      <!-- Start section (nav menu) -->
    <jsp:include page="template/userNav.jsp"/>
    <!-- End nav menu -->
  <!-- start header -->
    <jsp:include page="template/header.jsp"/>
    <!-- End header -->
  
    <div class="container">
      <div class="login">
      
        <form action="/login" method="POST">
          <span class="login-title">تسجبل الدخول</span>
          <div class="wrap-input">
            <input type="number" placeholder="ادخل رقم التليفون" name="username" id="username"/>
          </div>
          <div class="wrap-input">
            <input type="password" placeholder="الرقم السري" name="password"/>
          </div>
          <div class="option">
            <div class="forget" onclick="handle_forgetPassword()">
            </div>
          </div>
          <div class="message">
          			<p id="messageAlert"></p>
              <c:if test="${param.status eq 'checked'}">
       	          <div class="messageSucessful">
      		        <p class="sucessful">${message}</p>
      		       </div>
      	      </c:if>
      		  <c:if test="${param.errorType eq 'notfound'}">
      		 	  <p class="error"> رقم الهاتف أو- الرقم السري خطأ </p>
      		  </c:if>
      		   <c:if test="${param.errorType eq 'inactive'}">
      		 	  <p class="error">قم بتفعيل الحساب من البريد الالكتروني </p>
      		  </c:if>
      	  </div>
          
          <div class="send">
            <button>دخول</button>
          </div>
          
          <div class='forget acc-container' >
            <a href="#" id="forget-password" >نسيت كلمة السر ؟</a>
          </div>
           <div class="create acc-container">
              <a href="/signUp"> هل تريد إنشاء حساب جديد ؟</a>
           </div>
           
        </form>
        
      </div>
    </div>
    
    <!-- start footer -->
    <jsp:include page="template/footer.jsp"/>
    <!-- End footer -->
    
    <script src="<c:url value="../resources/js/master.js"/>"></script>  
    <script src="<c:url value="resources/js/login.js"/>"></script>
  </body>
</html>
