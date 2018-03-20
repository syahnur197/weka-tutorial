<jsp:include page="../layout/header.jsp" />
	<%
		// String tableString = request.getAttribute("tableString").toString();
		String fileName = request.getAttribute("fileName").toString();
		int dataset_id = (int) request.getAttribute("dataset_id");
	%>
	<form id='updateDataset' method="post" action="ServletUpdateDataSet">
		<input type='button' value='Edit Cells' id='edit_cells' class='btn btn-block btn-warning my-2'/>
		<div class='table-responsive' id="table-container">
		</div>
		<input type='hidden' name='dataset_id' value='<%= dataset_id %>' />
		<input type='submit' name='submit' value='Update' class='btn btn-block btn-success my-3'/>
	</form>
	<hr>
<jsp:include page="../layout/footer.jsp" />
<script>
CsvToHtmlTable.init({
    csv_path: "<%= fileName %>",
    element: 'table-container', 
    allow_download: true,
    csv_options: {separator: ',', delimiter: '"'},
    datatables_options: {"paging": false}
  });
	
    
</script>