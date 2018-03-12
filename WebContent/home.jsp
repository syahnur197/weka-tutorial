<jsp:include page="layout/header.jsp" />
	<div class="row">
		<div class="col-md-12 my-2">
			<h2>Home</h2>
		</div>
		<div class="col-md-12 my-2">
			<button class="btn btn-block btn-success">View</button>
		</div>
		<div class="col-md-12 my-2">
			<a href="createStrcture.jsp">	
				<button class="btn btn-block btn-success">Create Structure</button>
			</a>
		</div>
		<div class="col-md-12 my-2">
			<a href="createDataSet.jsp">	
				<button class="btn btn-block btn-success">Create Data Set From Non Existing Structure</button>
			</a>
		</div>
		<div class="col-md-12 my-2">
			<a href="selectStructure.jsp">	
				<button class="btn btn-block btn-success">Create Data Set From Existing Structure</button>
			</a>
		</div>
		
		<div class="col-md-12 my-2">
			<a href="#">	
				<button class="btn btn-block btn-success">Train and Test Existing Data Sets</button>
			</a>
		</div>
		<div class="col-md-12 my-2">
			<a href="newGetPrediction.jsp">	
				<button class="btn btn-block btn-warning">Insert Train and Test CSV (OLD)</button>
			</a>
		</div>
		<div class="col-md-12 my-2">
			<a href="numberOfInstancesForm.jsp">	
				<button class="btn btn-block btn-warning">Insert Instance Manually (OLD)</button>
			</a>
		</div>
		<div class="col-md-12 my-2">
			<a href="getPrediction.jsp">	
				<button class="btn btn-block btn-warning">Get Single Prediction (OLD)</button>
			</a>
		</div>
	</div>
</div>
<jsp:include page="layout/footer.jsp" />