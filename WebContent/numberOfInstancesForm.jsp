<jsp:include page="layout/header.jsp"/>
	<form action="instancesDataEntry.jsp" method="GET">
		<div class="form-group">
			<label for="noOfInstances">How Many Instances:</label>
			<input type="number" class="form-control" name="noOfInstances" id="noOfInstances"/>
		</div>
		<div class="form-group">
			<input type="submit" class="btn btn-primary btn-block" value="submit"/>
		</div>
	</form>
<jsp:include page="layout/footer.jsp"/>