<jsp:include page="views/layout/header.jsp" />
	<%
		String message = "";
		if (session.getAttribute("message") != null) {
			message = "<div class='alert alert-success'><strong>Success! </strong>"+session.getAttribute("message").toString()+"</div>";
			session.removeAttribute("message");
		}
	%>
	<% out.println(message); %>
	<div class="row">
		<div class="col-md-12 my-2">
			<h2>Home</h2>
		</div>
		<div class="col-md-12 my-2">
			<a href="ServletSelectDatasets">
				<button class="btn btn-block btn-danger">View [Not Yet]</button>
			</a>
		</div>
		<div class="col-md-12 my-2">
			<a href="views/structure/create.jsp">	
				<button class="btn btn-block btn-success">Create Structure</button>
			</a>
		</div>
		<div class="col-md-12 my-2">
			<a href="views/structure/select.jsp">	
				<button class="btn btn-block btn-success">Create Data Set From Existing Structure</button>
			</a>
		</div>
		
		<!-- div class="col-md-12 my-2">
			<a href="#">	
				<button class="btn btn-block btn-danger">Train and Test Existing Data Sets [Not Yet]</button>
			</a>
		</div>
		<div class="col-md-12 my-2">
			<a href="views/prediction/upload.jsp">	
				<button class="btn btn-block btn-success">Upload Train and Test CSV</button>
			</a>
		</div>
		<hr>
		<div class="col-md-12 my-2">
			<a href="views/student-grade/multiple.jsp">	
				<button class="btn btn-block btn-warning">Insert Instance Manually (OLD)</button>
			</a>
		</div>
		<div class="col-md-12 my-2">
			<a href="views/student-grade/insert.jsp">	
				<button class="btn btn-block btn-warning">Get Single Prediction (OLD)</button>
			</a>
		</div>
		<div class="col-md-12 my-2">
			<a href="views/dataset/create.jsp">	
				<button class="btn btn-block btn-warning">Create Data Set From Non Existing Structure (OLD)</button>
			</a>
		</div-->
	</div>
</div>
<jsp:include page="views/layout/footer.jsp" />