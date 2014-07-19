<%@ page contentType="text/html;charset=utf-8" %>
<%@ page contentType="text/html;charset=utf-8" language="java" %>
<!DOCTYPE html>
<html>
  <head>
  
  <title>Simple jsp page</title>
  </head>
  <body>
  
<img src="${pathToImg}" />

  <form action="${serverAdress}/loading" method="post" enctype="multipart/form-data">
        <input name="description" type="text"><br>
		<input name="data" type="file"><br>
		<input name="send_it" type="submit"><br>
  </form>
  
  ${serverTime}
	form LOAD1!
	
  </body>
</html>