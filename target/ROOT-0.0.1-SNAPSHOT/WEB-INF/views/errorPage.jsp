<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%@ page isErrorPage = "true" %>
<!DOCTYPE html>
<head>
  <meta charset="UTF-8">
   <title>WATAD| خطأ</title>
  <meta name="viewport" content="width=device-width, initial-scale=1"><link rel="stylesheet" href="./style.css">
  <link rel="stylesheet" href="<c:url value="../resources/css/errorPage.css" />" />

</head>
<body>
<div class="browser">
  <div class="controls">
    <i></i>
    <i></i>
    <i></i>
  </div>
  
  <div class="eye"></div>
  <div class="eye"></div>
  <div class="mouth">
    <div class="lips"></div>
    <div class="lips"></div>
    <div class="lips"></div>
    <div class="lips"></div>
    <div class="lips"></div>
    <div class="lips"></div>
    <div class="lips"></div>
    <div class="lips"></div>
  </div>
</div>

<h1>خطأ</h1>
<p>${errorMessage}</p>
</body>
</html>
