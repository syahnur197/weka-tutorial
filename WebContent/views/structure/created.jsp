<jsp:include page="../layout/header.jsp" />
	<%
		String message = request.getAttribute("message").toString();
		boolean success = (boolean) request.getAttribute("success");
		String url = "";
		String urlString = "";
	%>
	<div class="row">
		<div class="col-md-12 my-2">
			<h2><% out.println(message); %></h2>
		</div>
		<% if(success) {
			url = "createStructure.jsp";
			urlString = "Back to Create Structure";
		} else {
			url = "/weka-tutorial/index.jsp";
			urlString = "Home";
		}%>
		<div class="col-md-12">
			<a href="<% out.println(url); %>">
				<button class="btn btn-block btn-success"><% out.println(urlString);%></button>
			</a>
		</div>
	</div>
<jsp:include page="../layout/footer.jsp" />