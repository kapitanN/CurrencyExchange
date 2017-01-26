<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Second Page</title>
</head>
<body>
Введенное имя: ${loginUser.email} ${registrationUser.email};
<br/>
Введенный пароль: ${loginUser.password} ${registrationUser.password};
<br/>
Курс доллара: ${api};
</body>
</html>
