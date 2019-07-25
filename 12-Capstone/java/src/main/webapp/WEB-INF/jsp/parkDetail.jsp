<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:set var="pageTitle" value="Home Page" />
<%@include file="common/header.jsp"%>
<div class="details-total">

	<div id="temp-choice">
		<form action="parkDetail?parkCode=${park.parkCode}"
			modelAttribute="temperatureFahrenheit" method="POST">
			<p>Choose your temperature type</p>
			<input type="radio" name="temperatureFahrenheit" value="true">
			&#8457 <input type="radio" name="temperatureFahrenheit" value="false">
			&#8451 <input type="submit" value="submit" />
		</form>
	</div>
	<img id="img" src="<c:url value="img/parks/${park.parkCode}.jpg" />" />
	<h3>
		<c:out value="${park.parkName}" />
	</h3>
</div>
<br>
<div id="body">
	<p>${park.description}</p>
	<br>
	<div id=quote>
		<p>"${park.quote}"</p>
		<p>-${park.quoteSource}</p>
	</div>
	<br>
	<div id=stats>
		<h4>Key Park Stats:</h4>
		<ul>
			<li>State: <c:out value="${park.state}" />
			<li>Acreage: <c:out value="${park.acreage}" /> acres
			<li>Elevation: <c:out value="${park.elevation}" /> feet
			<li>Miles of Trail: <c:out value="${park.milesOfTrail}" />
				miles
			<li># of Campsites: <c:out value="${park.numberOfCampsites}" />
			<li>Climate: <c:out value="${park.climate}" />
			<li>Year Founded: <c:out value="${park.yearFounded}" /> A.D.
			<li>Visitor Count: <c:out value="${park.visitorCount}" />
			<li>Entry Fee: $<c:out value="${park.entryFee}" />
			<li># of Animal Species: <c:out
					value="${park.numberOfAnimalSpecies}" />
		</ul>
		<br>
	</div>
</div>
<div id=forecast>
	<h4>5-Day Weather Forecast</h4>
	<p id="today-forecast">
		<b>Today</b>
	</p>
	<div id="weatherprint">

		<c:forEach items="${forecast}" var="forecast">
			<div id="matches">
				<img id="weather-image"
					src="<c:url value="img/weather/${forecast.forecast}.png" />" />

				<div id="c-or-f">
					<c:choose>
						<c:when test="${temperatureFahrenheit == false}">
							<c:out value="High: ${forecast.highTemperatureCelcius} " /> &#8451
					<c:out value="Low: ${forecast.lowTemperatureCelcius} " /> &#8451
				</c:when>
						<c:otherwise>
							<c:out value="High: ${forecast.highTemperatureFahrenheit} " /> &#8457
					<c:out value="Low: ${forecast.lowTemperatureFahrenheit} " /> &#8457
				</c:otherwise>
					</c:choose>
					<div id="message">
						<c:out value="${gear}" />
						<br>
						<c:out value="${weatherGear}" />
					</div>
				</div>
			</div>
		</c:forEach>
	</div>

</div>

<%@include file="common/footer.jsp"%>