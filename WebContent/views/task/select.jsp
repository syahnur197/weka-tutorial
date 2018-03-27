<jsp:include page="../new-layout/header.jsp" />
	<%
		String listString = request.getAttribute("listString").toString();
		String message = "";
		if (session.getAttribute("message") != null) {
			message = "<div class='alert alert-success'><strong>Success! </strong>"+session.getAttribute("message").toString()+"</div>";
			session.removeAttribute("message");
		}
		out.println(message);
	%>
	<!-- Page wrapper  -->
	<div class="page-wrapper">
		<!-- Bread crumb -->
		<div class="row page-titles">
			<div class="col-md-5 align-self-center">
           		<h3 class="text-primary">View Task</h3>
			</div>
       		<div class="col-md-7 align-self-center">
           		<ol class="breadcrumb">
               		<li class="breadcrumb-item"><a href="javascript:void(0)">Home</a></li>
               		<li class="breadcrumb-item">Task</li>
               		<li class="breadcrumb-item active">View</li>
           		</ol>
       		</div>
   		</div>
   		<div class="container-fluid">
			<div class="row">
				<div class="col-lg-12">
					<div class="card">
						<div class="card-title">
							<h4>Task List</h4>
						</div>
						<div class="card-body">
							<ul class="list-icons">
								<%= listString %>
							</ul>
						</div>
					</div>
					<div class="card" style='display:none;' id="viewDatasetDiv">
						<h4><span id="viewDataset"></span> is selected to be viewed</h4>
						<form method="post" action="/weka-tutorial/ServletGetBlob">
							<div class="form-group">
								<input type="hidden" name="dataset_id" id="dataset_id" value=""/>
								<input type="submit" value="View" class="btn btn-primary btn-block"/>
							</div>
						</form>
					</div>
					<div class="card" style='display:none;' id="trainTestDiv">
						<h4 style='display:none;'>Train Data: <span id="trainDataset"></span></h4>
						<h4 style='display:none;'>Test Data: <span id="testDataset"></span></h4>
						<form method="post" action="/weka-tutorial/ServletTrainTest">
							<div class="form-group">
								<input type="hidden" name="trainDataset_id" id="trainDataset_id" value=""/>
								<input type="hidden" name="testDataset_id" id="testDataset_id" value=""/>
								<input type="submit" value="Submit" class="btn btn-primary btn-block"/>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
<jsp:include page="../new-layout/footer.jsp" />

<script>
	$(function() {
		$(".selectBlobButton").on('click', function() {
			var id = $(this).val();
			var datasetName = $(this).next().next().next().html();
			$("#trainTestDiv").hide();
			$("li").css('background-color', "");
			$(this).parent().css('background-color', "#f5f5f5");
			$("#viewDatasetDiv").show();
			$("#viewDataset").html(datasetName);
			$("#dataset_id").val(id);
		})
		
		$(".trainButton").on("click", function() {
			$("#viewDatasetDiv").hide();
			$(".structure").hide();
			var value = $(this).val();
			$(".structure_"+value).show();
			$("#trainTestDiv").show();
			$("#trainDataset").parent().show();
			$("#trainDataset").html($(this).next().next().html());
			$("#trainDataset_id").val($(this).prev().val());
		})
		
		$(".testButton").on("click", function() {
			$("#viewDatasetDiv").hide();
			$(".structure").hide();
			var value = $(this).val();
			$(".structure_"+value).show();
			$("#trainTestDiv").show();
			$("#testDataset").parent().show();
			$("#testDataset").html($(this).next().html());
			$("#testDataset_id").val($(this).prev().prev().val());
		})
	})
</script>