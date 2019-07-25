<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle" value="Home Page" />
<%@include file="common/header.jsp"%>


<c:forEach items="${parks}" var="park">
	<div class="park">
		<a class="park-image" href="parkDetail?parkCode=${park.parkCode}">
			<img src="<c:url value="img/parks/${park.parkCode}.jpg"/>" />
		</a> <br>
		<div id=park-info>
			<b><a class="park-name" href="parkDetail?parkCode=${park.parkCode}"> 
			<c:out value="${park.parkName}" /></a></b>
			<br>
			<c:out value=" - ${park.state}" />
			<br> <br>
			<c:out value="${park.description}" />
		</div>
	</div>
</c:forEach>



<%@include file="common/footer.jsp"%>

