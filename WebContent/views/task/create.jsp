<jsp:include page="/views/new-layout/header.jsp" />
	<!-- Page wrapper  -->
	<div class="page-wrapper">
		<!-- Bread crumb -->
		<div class="row page-titles">
			<div class="col-md-5 align-self-center">
           		<h3 class="text-primary">Create Task</h3>
			</div>
       		<div class="col-md-7 align-self-center">
           		<ol class="breadcrumb">
               		<li class="breadcrumb-item"><a href="javascript:void(0)">Home</a></li>
               		<li class="breadcrumb-item">Task</li>
               		<li class="breadcrumb-item active">Create</li>
           		</ol>
       		</div>
   		</div>
		<div class="container-fluid">
			<div class="row">
				<div  class="float-right">
					<form action="https://weka-tutorial.azurewebsites.net/PredictionCreate" method="POST" id="createTaskForm">
						<input type="hidden" name="trainingData" id="trainingData" value="" />
						<input type="hidden" name="testingData" id="testingData" value="" />
						<input type="submit" name="submitButton" id="getPredictionButton" value="Get Prediction" class="btn btn-warning" disabled/>
					</form>
				</div>
			</div>
			<div class="row">
				<div class="col-lg-6">
					<div class="card">
						<h3> Training Data </h3>
						<p id="trainSelected" style="display:none"></p>
					</div>
				</div>
				<div class="col-lg-6">
					<div class="card">
						<h3> Testing Data </h3>
						<p id="testSelected" style="display:none"></p>
					</div>
				</div>
				<div class="col-lg-6">
					<div class="card" id="trainCard">
						<h3 class="card-title">Select Train Data</h3>
							<ul>
							<% 
								String list = request.getAttribute("list").toString();
								out.print(list);
							%>
							</ul>
					</div>
				</div>
				<div class="col-lg-6">
					<div class="card" id="testCard">
							<h3 class="card-title">Select Test Data</h3>
							<ul id="testList">
							</ul>
					</div>
				</div>
			</div>
		</div>     
	</div>
   <!-- End Container fluid  -->
<jsp:include page="/views/new-layout/footer.jsp" />
<script>
	function selectTrain(e, structure_id, dataset_id) {
		$("#testList").empty();
		var val = $(e).find(".train_dataset_name").html();
		$("#trainSelected").show().html(val);
		$("#testSelected").hide().empty();
		$("#testingData").val();
		$("#trainingData").val(dataset_id);
		$("#getPredictionButton").prop("disabled", true);
		$.get("https://weka-tutorial.azurewebsites.net/ServletTestList", {"structure_id" : structure_id, "dataset_id" : dataset_id}, function(data) {
			console.log(data);
			var list = "";
			data.dataset.forEach( function(ds) {
				list += "<li><a href='#' onclick='selectTest(this, " + ds.id+ ")'><i class='fa fa-check text-info'></i><span class='test_dataset_name'>" + ds.name + "</span></li>";
			});
			$("#testList").html(list);
		}, "json");
	}
	
	function selectTest(e, dataset_id) {
		var val = $(e).find(".test_dataset_name").html();
		$("#testSelected").show().html(val);
		$("#testingData").val(dataset_id);
		$("#getPredictionButton").prop("disabled", false);
		
	}
</script>

