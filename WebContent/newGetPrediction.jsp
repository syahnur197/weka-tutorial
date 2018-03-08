<jsp:include page="layout/header.jsp" />
<div class="container">
	<div class="jumbotron">
		<h1>UTB Data Analytic</h1>
	</div>
	<form action="ServletPrediction" method="post">
		<div class="form-group">
			<label for="train">Training Data:</label>
			<input type="file" class="form-control" id="train" name="train"/>
		</div>
		<div class="form-group">
			<label for="test">Testing Data:</label>
			<input type="file" class="form-control" id="test" name="test"/>
		</div><div class="form-group">
			<p>Due to security reason modern browser does not include the full path to file:</p>
			<p>Please enter the path to the files above:</p>
			<input type="text" class="form-control" id="full_path" name="full_path"/>
		</div>
		<button type="submit" class="btn btn-default">Submit</button>
	</form>
</div>
<jsp:include page="layout/footer.jsp" />