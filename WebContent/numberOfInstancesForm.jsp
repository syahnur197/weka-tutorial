<jsp:include page="layout/header.jsp"/>
<div class="container">
	<div class="jumbotron">
		<a href='index.jsp'>
			<h1>UTB Data Analytic</h1>
		</a>
	</div>
	<form action="instancesDataEntry.jsp" method="GET">
		<div class="form-group">
			<label for="noOfInstances">How Many Instances:</label>
			<input type="number" class="form-control" name="noOfInstances" id="noOfInstances"/>
		</div>
		<div class="form-group">
			<input type="submit" class="btn btn-primary btn-block" value="submit"/>
		</div>
	</form>
</div>
<jsp:include page="layout/footer.jsp"/>