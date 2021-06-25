<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Вход в систему - Гео Система ИНПРИС</title>
    <link rel="stylesheet" type="text/css" href="/resources/css/inpris.css" />
</head>
<body>
  <div style="width: 400px; height: 400px;">
    <div align="center" style="text-align: center; vertical-align: middle">
      <form id="login" method="post" action="j_security_check">
        <table columns="2" cellpadding="5">
          <tr>
            <td>Имя пользователя:</td>
            <td>
              <input name="j_username" type="text" size="15"  autocomplete="username">
            </td>
          </tr>
          <tr>
            <td>Пароль:</td>
            <td>
              <input name="j_password" type="password" size="15" autocomplete="current-passsword" />
            </td>
          </tr>
          <tr>
              <td colspan="2">
                <input type="submit" value="Войти" size="7" />
              </td>
          </tr>
        </table>
      </form>
    </div>
  </div>


</body>
</html>
