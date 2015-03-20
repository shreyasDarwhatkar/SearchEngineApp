<html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<body>
	<h1>Search Engine123</h1>
<c:forEach items="${results}" var="result">
${result.url}<br/>
</c:forEach>

</body>
</html>