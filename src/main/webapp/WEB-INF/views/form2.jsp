<%@page pageEncoding="utf-8"%>
<%request.setCharacterEncoding("utf-8");%>
<%@page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ page language="java" contentType="text/html; charset=utf-8" %>
<!DOCTYPE html>
<html>
<!--2фывф-->
    <head>    
        <title></title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="stylesheet" type="text/css" href="../css/styles.css">
				<!--Проверка-->
		<script type="text/javascript" src="../js/jquery.js"></script>
		<script type="text/javascript" src="../js/jquery.validation_form.js"></script>
    </head>
	<script>
	function getXmlHttp() {
    var xmlhttp;
    try {
      xmlhttp = new ActiveXObject("Msxml2.XMLHTTP");
    } catch (e) {
    try {
      xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
    } catch (E) {
      xmlhttp = false;
    }
    }
    if (!xmlhttp && typeof XMLHttpRequest!='undefined') {
      xmlhttp = new XMLHttpRequest();
    }
    return xmlhttp;
  }
   function send(){
	var a=document.getElementById('login').value;
	flag=true;
	if(a!=''){
	//document.sender.submit();
	var xmlhttp = getXmlHttp(); // ??? ?? XMLHTTP
    xmlhttp.open('POST', 'http://localhost:8080/SpringA/exeA', true); // ?????????????
    xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded'); // ???????
    xmlhttp.send("login=" + encodeURIComponent(a)); // ????POST-???
    xmlhttp.onreadystatechange = function() { // G? ?? ????
      if (xmlhttp.readyState == 4) { // ?? ???
        if(xmlhttp.status == 200) { // ??? ?? ? 200 (??????
		if(xmlhttp.responseText=='Логин свободен')
		{
          document.getElementById("place").innerHTML = xmlhttp.responseText; // u???????
		  change_status();
		}
		else
		{
		  document.getElementById("place").innerHTML = 'Логин занят'; // u???????
		  var f=document.send;
		  f.save.disabled=true;
		}
        }
      }
    };
	}
}
function proverka(){
//сделай ещё под логином блок для вывода, пока его значение пусто кнопка сохранить неактивна
	if(document.getElementById('login').value.length!=0){
	//отправляем ajax пост запрос и сервер проверит по базе и вернёт значение если значени false кнопка не активна
		send();
	}
	else{
	//ничего не делаем
	//send();
		change_status();
		document.getElementById("place").innerHTML='';
		//alert("Пустой логин");
	//	return false;
	}
}
	 $(document).ready(function()
    {

         var options = {
         	   important_marker: "*",	 				 // этим будут помечены поля, обязательные для заполнения
			   min_pass: 8,								 // минимальная длина пароля
			   container_valid_pass_width: 300,          // ширина (в пикселях) контейнера индикатора паролей
			   validPassMarker_dx: 20,                   // шаг индикатора паролей
			   validation_form: ".send",      // класс проверяемой формы
			   text: ".text",     						 // класс полей, подлежащих проверке
			   password1: ".password1",    				 // класс поля пароля, в котором проверяется длина пароля
			   password2: ".password2",    				 // класс поля подтверждения пароля
			   important: ".important",    				 // класс поля, обязательного для заполнения
  			   submit: ".submit"
		}
        $().validateThis(options);

    }
);

	</script>
	<body>
	  <div id='addNewCl'>
	  <fieldset>
			<legend>Ввод нового сотрудника</legend>
			<form action='#' method='' id='send' name='send' class='send' onsubmit='return checkForm()'>
			<table align='center' width='100%'>
			<tr><th>Фамилия сотрудника:</th><th>Логин:</th></tr>
			<tr><td><input type='text' id='name' name='name' placeholder='Введите фамилию' onkeyup='change_status()' onchange='change_status()'/></td><td><input type='text' id='login' name='login' placeholder='Придумайте логин' onkeyup='proverka()' onchange='proverka()' onBlur='proverka()'/><br/><div id='place' name='place'></div></td></tr>
			<tr><th>Имя сотрудника:</th>
			<th rowspan='4'>
			Пароль:<br/>
			<input type='password' class='text password1' id='pwd' name='pwd' placeholder="Придумайте пароль" onkeyup='change_status()' onchange='change_status()'/><br/>
			Повторите пароль:<br/>
			<input type='password' class='text password2' id='pwdrep' name='pwdrep' placeholder="Повторите пароль" onkeyup='change_status()' onchange='change_status()'/>
			</th>
			</tr>
			<tr><td><input type='text' id='sname' name='sname' placeholder='Введите имя' onkeyup='change_status()' onchange='change_status()'/></td></tr>
			<tr><th>Телефон в формате xxx-xxx-xx-xx</th></tr>
			<tr><td><input type='tel' pattern="[0-9]{3}-[0-9]{3}-[0-9]{2}-[0-9]{2}" id='tel' name='tel' placeholder='xxx-xxx-xx-xx' onkeyup='change_status()' onchange='change_status()'/></td></tr>
			<tr><th>E-MAIL:</th><th>Должность</th></tr>
			<tr><td><input type='email' id='mail' name='mail' placeholder="Ваш e-mail" onkeyup='change_status()' onchange='change_status()'/></td>
			<td>
			<select name='status' id='status'>
							<option value='admin'>Администратор</option>
							<option value='manager'>Менеджер</option>
							<option value='security'>Охранник</option>
						</select>
			</td></tr>
			<tr><th colspan='2'>Права:</th></tr>
			<tr><td><input type='radio' name='status' value='1' checked>Активен</td>
			<td><input type='radio' name='status' value='0'>Заблокирован</td></tr>
			<tr><td><input type='submit' id='save' name='save' value='Добавить'/></td><td><input type='button' name='cancel' value='Отменить' /></td></tr>
			</table>
			</form>
	  </fieldset>
	  </div>
		<script>
			var f=document.send;
			function change_status(){
				f.save.disabled=((f.sname.value.length!=0) &&(f.name.value.length!=0)&&(f.login.value.length!=0)&&(f.tel.value.length!=0)&&(f.pwd.value.length!=0)&&(f.pwdrep.value.length!=0)&&(f.mail.value.length!=0)&&(isNaN(f.name.value))&&(isNaN(f.sname.value))&&(f.pwd.value.toString()==f.pwdrep.value.toString()))?false:true;
			}
			change_status();
		</script>

    </body>
    <!-- end -->
</html>