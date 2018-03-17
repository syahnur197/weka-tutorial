<jsp:include page="../layout/header.jsp" />
	<form action="/weka-tutorial/ServletUploadDataSet" method="post" enctype="multipart/form-data">
		<% 
			String structureString = request.getAttribute("structureString").toString();
			String datasetName = request.getAttribute("datasetName").toString();
			int structureId = (int)request.getAttribute("structureId");
		%>
		<div class="form-group">
			<label for="dataset">Upload Data Set:</label>
			<input type="file" class="form-control" id="dataset" name="dataset"/>
		</div>
		<!-- div class="form-group">
			<p>Due to security reason modern browser does not include the full path to file:</p>
			<p>Please enter the path to the files above:</p>
			<input type="text" class="form-control" id="full_path" name="full_path"/>
		</div-->
		<input type="hidden" name="structureString" value="<% out.print(structureString); %>" />
		<input type="hidden" name="structureId" value="<% out.print(structureId); %>" />
		<input type="hidden" name="datasetName" value="<% out.print(datasetName); %>" />
		<input type="submit" name="submit" value="Upload CSV" class="btn btn-block btn-success" />
	</form>
<jsp:include page="../layout/footer.jsp" />