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
	
    <title>WATAD|إنشاء حساب</title>
  </head>
  <body>
  
  <!-- start header -->
    <jsp:include page="template/header.jsp"/>
    <!-- End header -->
  
   <div class="container">
      <div class="login">
         <div class="error">
   <div class="row">*${errorOf}</div>
      <c:forEach var="error" items="${fieldErrors}">
      	<div class="row">*${error.field}</div>
      </c:forEach>
      </div>
        <form action="/add-user" method="POST">
          <span class="login-title">إنشاء حساب</span>
          <div class="wrap-input">
            <input required="required" title ="* مطلوب" type="text" placeholder="ادخل اسمك *" name="userName" value="${register.userName}"/>
				 <p class="shown">اكتب اسمك بالكامل مثل : اسحق حنا</p>            
          </div>
          <div class="wrap-input">
          	<select required="required" title ="* مطلوب" id="governments"  name="government">
            	<option disabled="disabled" selected>اسم المحافظة</option>
            </select>
          </div>
          <div class="wrap-input">
          	<select required="required" title ="* مطلوب" id="cities"  name="city">
            	<option disabled="disabled" selected>اسم المركز</option>
            </select>
          </div>  
          <div class="wrap-input">
            <input required="required" title ="* مطلوب" type="number" placeholder="ادخل رقم التليفون *" name="phone" value="${register.phone}"/>
			<p class="shown" >ادخل رقم تليفون مكو من 11 رقم </p>
          </div>
          <div class="wrap-input">
            <input required="required" title ="* مطلوب" type="email" placeholder="ادخل البريد الالكتروني " name="email" value="${register.email}"/>
			<p class="shown">اكتب بريد الالكتروني مثل :  seha.abdo@gmail.com</p>            
          </div>

          <div class="wrap-input">
            <input required="required" title ="* مطلوب" id="password" type="password" placeholder="ادخل الرقم السري * " name="password" />
            <p class="shown">لابد ان لا يقل عن 6 احرف</p>
          </div>
          
          <div class="wrap-input">
            <input required="required" title ="Custom message:* مطلوب" id="confirmPassword" type="password" placeholder="تأكيد الرقم السري *" name="conformPassword"/>
			<p class="shown"></p>            
          </div>
          
          <div class="wrap-input">
            <textarea  placeholder="ادخل عنوانك" name="address" >${register.address}</textarea>
          </div>
          
          <div class="send">
            <button>إنشاء</button>
          </div>
          
        </form>
      </div>
    </div>
    <!-- start footer -->
    <jsp:include page="template/footer.jsp"/>
    <!-- End footer -->
    
      <script src="<c:url value="resources/js/signUp.js"/>"></script>   
  </body>
</html>
