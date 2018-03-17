<jsp:include page="../layout/header.jsp" />
	<%
		String tableString = request.getAttribute("tableString").toString();
	%>
	<%= tableString %>
	<hr>
<jsp:include page="../layout/footer.jsp" />

<script>
	$(function() {
		$(".selectBlobButton").on('click', function() {
			var id = $(this).val();
			var datasetName = $(this).next().html();
			$("li").css('background-color', "");
			$(this).parent().css('background-color', "#f5f5f5");
			$("#chosenDatasetDiv").show();
			$("#chosenDataset").html(datasetName);
			$("#dataset_id").val(id);
		})
	})
</script>