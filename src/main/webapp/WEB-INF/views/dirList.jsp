<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head><title>Просто jspDir </title></head>
  <body>
go home: ${serverAdress}
<br/>
		<form action='${uplAdress}' method='post' id='send' name='send' enctype="multipart/form-data">
			<table width='200%' align='center'>
				<input name="description" type="text" value="${path}"><br/>
				<input name="data" type="file"><br/>
				<input name="UpLoad" type="submit" value="LoadIt!"><br/>
			</table>
		</form>
<br/>

hello. ${curFolder}: <br/>  <br/> 
${dirItems}
 
  </body>
  <!--indexx-->
</html>