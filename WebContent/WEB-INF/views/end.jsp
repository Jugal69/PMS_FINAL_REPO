<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>End</title>
<script type="text/javascript">
window.addEventListener("load",display,false);
    function display()
    {
    
    var two=sessionStorage.getItem("1");
    console.log('Username='+two);
    //rightbox.innerHTML="Username:" +two;
    }
   
   
    </script>
</head>

<body>
	<h1>Hello User !!!!</h1>
</body>
</html>