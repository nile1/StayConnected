<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>StayConnect</title>
<link rel="stylesheet" href="/WebChat/common/css/style.css">
<link rel="shortcut icon" sizes="140x140" href="/WebChat/common/css/icon1.ico" type="image/x-icon" />
</head>

<body  background="/WebChat/common/css/wallpaper2.jpg">
<form method ="get" action ="Chat">

<script>
  setTimeout(function() {
      document.location = "http://10.6.14.45:8082/WebChat/Chat";
  }, 25000); // <-- this is the delay in milliseconds
</script>

<%
String  s1  = (String) request.getAttribute("disp");
String  s2  = (String) request.getAttribute("disp1");
//response.setIntHeader("Refresh", 25);
%>

<script type="text/javascript">
window.onload = function () {
 setCursor(document.getElementById('txt'), 13, 13)
}
function setCursor(el, st, end) {
 if (el.setSelectionRange) {
     el.focus();
     el.setSelectionRange(st, end);
 } else {
     if (el.createTextRange) {
         range = el.createTextRange();
         range.collapse(true);
         range.moveEnd('character', end);
         range.moveStart('character', st);
         range.select();
     }}}
</script>


<script type="text/javascript">
 function SayHi() {
   if (event.keyCode == 13)
   {
 var btnSubmitTags = document.getElementById( "button");
       btnSubmitTags.click();
   }
 }
</script>
<div id="zoom">
<h2>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="http://10.6.14.45:8082/WebChat/Chat" title="Developed By Nilesh Gupta & vyom Saxena" style="text-decoration:none"><font color="white">Welcome To StayConnect</font></a></h2>
<br><b style="color:white;">&nbsp;RECEIVED MESSAGE:<br></b>
<textarea style="font-weight: bold; border:none; color:black; background-image:url('/WebChat/common/css/wallpaper.JPG');" id="alltext" name="display" rows="28" cols="160"  readonly >
<%=s1 %>
</textarea><br><br>\
<b style="color:white;">WRITE MESSAGE:<br></b>
<textarea onkeypress="SayHi()" style="border:none;" id = "txt"  rows="2" cols="160" name="message"></textarea><br><br>
<input type="submit" class="button" id="button" value="Send"  />

<script>document.getElementById('alltext').scrollTop=document.getElementById('alltext').scrollHeight;</script>
</div>
</form>
</body>
</html>