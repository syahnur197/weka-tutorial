<jsp:include page="../layout/header.jsp" />
	<%
		String tableString = request.getAttribute("tableString").toString();
		int dataset_id = (int) request.getAttribute("dataset_id");
	%>
	<form id='updateDataset' method="post" action="ServletUpdateDataSet">
		<input type='button' value='Edit Cells' id='edit_cells' class='btn btn-block btn-warning my-2'/>
		<div class='table-responsive'>
			<table class='table table-striped table-bordered table-hover' style="table-layout:fixed;">
				<%= tableString %>
			</table>
		</div>
		<input type='hidden' name='dataset_id' value='<%= dataset_id %>' />
		<input type='submit' name='submit' value='Update' class='btn btn-block btn-success my-3'/>
	</form>
	<hr>
<jsp:include page="../layout/footer.jsp" />
<script>
	$(function() {
		$("#edit_cells").on('click', function() {
			$(this).hide();
			$('.valueCell').hide();
			$('.valueCell').next().show();
		});
	});
</script>