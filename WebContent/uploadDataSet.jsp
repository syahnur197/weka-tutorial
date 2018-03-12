<jsp:include page="layout/header.jsp" />
	<form action="ServletUploadDataSet" method="post">
		<% 
			String structureString = request.getAttribute("structureString").toString();
		%>
		<div class="form-group">
			<label for="dataSet">Upload Data Set:</label>
			<input type="file" class="form-control" id="dataSet" name="dataSet"/>
		</div>
		<div class="form-group">
			<p>Due to security reason modern browser does not include the full path to file:</p>
			<p>Please enter the path to the files above:</p>
			<input type="text" class="form-control" id="full_path" name="full_path"/>
		</div>
		<input type="hidden" name="structureString" value="<% out.print(structureString); %>" />
		<button type="submit" class="btn btn-block btn-success">Submit</button>
	</form>
<jsp:include page="layout/footer.jsp" />