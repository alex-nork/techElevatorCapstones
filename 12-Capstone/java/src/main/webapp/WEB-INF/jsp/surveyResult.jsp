<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:set var="pageTitle" value="Home Page"/>
<%@include file="common/header.jsp" %>
<br>
<br>
<h2>National Park Survey Results</h2>

<c:forEach items = "${results}" var = "result">
	<hr>
	<c:out value = "${result.parkName}: ${result.count}"/>
</c:forEach>


<%@include file="common/footer.jsp" %>