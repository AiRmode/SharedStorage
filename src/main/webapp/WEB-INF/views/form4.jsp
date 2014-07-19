<%@page pageEncoding="utf-8"%>
<%request.setCharacterEncoding("utf-8");%>
<%@page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ page language="java" contentType="text/html; charset=utf-8" %>
<!DOCTYPE html>
<html>
<!--4-->
<!--41-->
    <head>
        <title></title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="stylesheet" type="text/css" href="../css/styles.css">
    </head>
		
	<body>
	  <div id='addNewCl' class="wrapper">
	  <fieldset>
	  <legend>Добавление нового посетителя</legend>
	  <h3 align='center'>Заполните все поля и выберите фотографию</h3>
		<form action='http://localhost:8080/SpringA/loading' method='post' id='send' name='send' enctype="multipart/form-data">
		<table width='100%' align='center'>
			<tr><th colspan="2">Штрих-код:</th><td rowspan='8' colspan='2'><img id="previewImg" src='../images/nofoto.jpg' width='35%' height='40%' alt=''/></td></tr>
			<tr><td colspan="2"><input type='code' id='code' name='code' size='30' autofocus='autofocus' onkeyup='change_status()' onchange='change_status()'/></td></tr>
			<tr><th colspan="2">Фамилия:</th></tr>
			<tr><td colspan="2"><input type='text' id='sname' name='sname' size='30' onkeyup='change_status()' onchange='change_status()'/></td></tr>
			<tr><th colspan="2">Имя:</th></tr>
			<tr><td colspan="2"><input type='text' id='name' name='name' size='30' onkeyup='change_status()' onchange='change_status()'/></td></tr>
			<tr><th colspan="2">Телефон в формате xxx-xxx-xx-xx</th></tr>
			<tr><td colspan="2"><input type='tel' id='tel' name='tel' pattern="[0-9]{3}-[0-9]{3}-[0-9]{2}-[0-9]{2}" size='30' placeholder='xxx-xxx-xx-xx' onkeyup='change_status()' onchange='change_status()'/></td></tr>
			<tr><th colspan="2">E-mail:</th><td colspan='2'><input type='file' accept="image/*" id='pic' name='pic' onkeyup='change_status()' onchange='change_status();preview(this.value);'/></td></tr>
			<tr><td colspan="2"><input type='email' id='mail' name='mail' size='30' placeholder='e-mail' onkeyup='change_status()' onchange='change_status()'/></td><td colspan='2'></td></tr>
			<tr><th colspan="2">Права</th></tr>
			<tr><td>Активен: <input type='radio' id='active' name='active' value='1' checked='checked'/></td><td>Заблокирован: <input type='radio' id='noactive' name='active' value='0'/></td></tr>
			<tr><td colspan='2'><input type="submit" id='save' name='save' value="Сохранить"/></td><td colspan='2'><input type="submit" id='back' name='back' value="Отмена"/></td></tr>
			</table>
			
		</form>
		</fieldset>
	</div>
<script>

				var f=document.send;
				function change_status(){
				var arr=new Array();
				var picname=f.pic.value;
				arr=picname.split(".");
				var count=(arr.length)-1;
				f.save.disabled=((f.mail.value.length>=5)&&(f.name.value.length>=2)&&(f.sname.value.length>=2)&&(f.tel.value.length>=10)&&(isNaN(f.name.value))&&(isNaN(f.sname.value))&&(f.code.value.length>0)&&(f.pic.value.length!=0)&&((arr[count].toString()=='jpg')||(arr[count].toString()=='jpeg')||(arr[count].toString()=='bmp')||(arr[count].toString()=='gif')||(arr[count].toString()=='png')||(arr[count].toString()=='pjpeg')||(arr[count].toString()=='JPG')||(arr[count].toString()=='PNG')||(arr[count].toString()=='JPEG')||(arr[count].toString()=='GIF')))?false:true;
			}
			
			change_status();
		</script>
		<script>
		//превью
		function onFileSelect(e) {
  var f = e.target.files[0], // Первый выбранный файл
    reader = new FileReader,
    place = document.getElementById("previewImg") // Сюда покажем картинку;
  reader.readAsDataURL(f);
  reader.onload = function(e) { // Как только картинка загрузится
    place.src = e.target.result;
  }
}
if(window.File && window.FileReader && window.FileList && window.Blob) {
  document.querySelector("input[type=file]").addEventListener("change", onFileSelect, false);
} else {
  console.warn( "Ваш браузер не поддерживает FileAPI")
}
</script>
    </body>
</html>