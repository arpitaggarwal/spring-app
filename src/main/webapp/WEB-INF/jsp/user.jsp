<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<html>
<head>
<link rel="stylesheet" href="../<spring:theme code="css"/>"
	type="text/css" />
</head>
<body>
	User
	<h3>
		<spring:message code="label.title" />
	</h3>
</body>
</html>