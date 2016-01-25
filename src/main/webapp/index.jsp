<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
</head>
</head>
<body>
	<h3>
		<spring:message code="label.title" />
	</h3>
	<span style="float: right"> <a href="welcome/user?lang=en&theme=blue">User</a> | <a
		href="welcome/admin?lang=de&theme=black">Admin</a>
	</span>
</body>
</html>