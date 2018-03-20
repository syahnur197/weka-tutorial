<jsp:include page="../layout/header.jsp" />
	<%
		String tableString = request.getAttribute("tableString").toString();
	%>
	<%= tableString %>
	<hr>
<jsp:include page="../layout/footer.jsp" />