<jsp:include page="views/new-layout/header.jsp" />
<!-- Page wrapper  -->
<div class="page-wrapper">
	<!-- Bread crumb -->
	<div class="row page-titles">
		<div class="col-md-5 align-self-center">
			<h3 class="text-primary">Dashboard</h3> 
		</div>
		<div class="col-md-7 align-self-center">
			<ol class="breadcrumb">
				<li class="breadcrumb-item"><a href="javascript:void(0)">Home</a></li>
				<li class="breadcrumb-item active">Dashboard</li>
			</ol>
		</div>
	</div>
	<!-- End Bread crumb -->
	<!-- Container fluid  -->
	<div class="container-fluid">
		<%
			String message = "";
			if (session.getAttribute("message") != null && session.getAttribute("success") != null) {
				boolean success = (boolean)session.getAttribute("success");
				if (success) {
					message = "<div class='alert alert-success'><strong>Success! </strong>"+session.getAttribute("message").toString()+"</div>";
				} else {
					message = "<div class='alert alert-danger'><strong>Warning! </strong>"+session.getAttribute("message").toString()+"</div>";
				}
				session.removeAttribute("message");
				out.println(message);
			}
		%>
		<!-- Start Page Content -->
		<div class="row" id="entity-counter">
			<div class="col-md-4">
				<div class="card bg-primary p-20">
					<div class="media widget-ten">
						<div class="media-left meida media-middle">
							<span><i class="fa fa-tasks f-s-40"></i></span>
						</div>
						<div class="media-body media-text-right">
							<h2 class="color-white" id="noOfTask">0</h2>
							<p class="m-b-0">Tasks</p>
						</div>
					</div>
				</div>
			</div>
			<div class="col-md-4">
				<div class="card bg-pink p-20">
					<div class="media widget-ten">
						<div class="media-left meida media-middle">
							<span><i class="fa fa-building f-s-40"></i></span>
						</div>
						<div class="media-body media-text-right">
							<h2 class="color-white" id="noOfStructure">0</h2>
							<p class="m-b-0">Structures</p>
						</div>
					</div>
				</div>
			</div>
			<div class="col-md-4">
				<div class="card bg-success p-20">
					<div class="media widget-ten">
						<div class="media-left meida media-middle">
							<span><i class="fa fa-database f-s-40"></i></span>
						</div>
						<div class="media-body media-text-right">
							<h2 class="color-white" id="noOfDataset">0</h2>
							<p class="m-b-0">Datasets</p>
						</div>
					</div>
				</div>
			</div>
		</div>

		<div class="row" id="task-list-row">
			<!-- /# column -->
			<div class="col-lg-12">
				<div class="card">
					<div class="card-title">
						<h4>Tasks</h4>
						<button class="btn btn-primary float-right" onclick="newTask();">New Task</button>
					</div>
					<div class="card-body">
						<div class="table-responsive">
							<table class="table table-hover ">
								<thead>
									<tr>
										<th>#</th>
										<th>Task Name</th>
										<th>Training Data</th>
										<th style='text-align: left;'>Testing Data</th>
									</tr>
								</thead>
								<tbody id="allTask">
								</tbody>
							</table>
						</div>
					</div>
				</div>
				<!-- /# card -->
			</div>
			<!-- /# column -->
		</div>
		<div class="row" id="create-task-workflow" style="display:none;">
		
			<div class="col-lg-4">
				<div class="card">
					<div class="card-title">
						<h4>Task Creation Work Flow</h4>
					</div>
					<div class="card-body">
						<ul class="list-group">
							<li class="list-group-item active workflow-list" id="structure-workflow-list">1. Select or Create Structure</li>
							<li class="list-group-item workflow-list" id="training-workflow-list">2. Select or Create Training Data Set</li>
							<li class="list-group-item workflow-list" id="testing-workflow-list">3. Select or Create Testing Data Set</li>
							<li class="list-group-item workflow-list" id="create-task-workflow-list">4. Submit Create Task</li>
						</ul>
					</div>
				</div>
			</div>
		
			
			<div class="col-lg-8">
				<div class="card">
					<!-- Nav tabs -->
					<ul class="nav nav-tabs" id="dashboardTab">
						<li class="nav-item">
							<a class="nav-link active" data-toggle="tab" href="#selectStructure">Select Structure</a>
						</li>
						<li class="nav-item">
							<a class="nav-link" data-toggle="tab" href="#selectTrain" onclick='this.preventDefault()'>Select Train Data Set</a>
						</li>
						<li class="nav-item">
							<a class="nav-link" data-toggle="tab" href="#selectTest">Select Test Data Set</a>
						</li>
					</ul>
					
					<!-- Tab panes -->
					<div class="tab-content">
						<div class="tab-pane active container card" id="selectStructure">
							<div class="card-body">
								<h4>Please select the structure</h4>
								<a href="http://localhost:8080/weka-tutorial/views/structure/create.jsp"><button class='btn btn-primary my-1 create'>Create Structure</button></a>	
								<ul class="list-group" id="structure-list-group"></ul>
							</div>
						</div>
						<div class="tab-pane container card" id="selectTrain">
							<div class="card-body">
								<h4 class="selected-structure-name">{{ structure is not yet selected }}</h4>
								<a href="http://localhost:8080/weka-tutorial/DatasetCreate"><button class='btn btn-primary my-1 create' >Create Data Set</button></a>
								<ul class="list-group" id="all-dataset-train-list-group" class="training-ul"></ul>
							</div>
						</div>
						<div class="tab-pane container card" id="selectTest">
							<div class="card-body">
								<h4 class="selected-structure-name">{{ structure is not yet selected }}</h4>
								<a href="http://localhost:8080/weka-tutorial/DatasetCreate"><button class='btn btn-primary my-1 create' >Create Data Set</button></a>
								<ul class="list-group" id="all-dataset-test-list-group" class='testing-ul'></ul>
								<form method="post" action="http://localhost:8080/weka-tutorial/PredictionCreate" style="display:none;" id="create-task-form" class="my-2">
									<input type="hidden" name="trainingData" id="trainingData" value="" />
									<input type="hidden" name="testingData" id="testingData" value="" />
									<input type="submit" value="Submit" class="btn btn-success btn-block"/>
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="card" id="structureStringDiv" style="display:none;">
			<div class="card-title">
				<h4 id="structureName">{{ structure name }}</h4>
			</div>
			<div class="card-body">
				<span id="structureString">{{ structure string }}</span>
				<button class="btn btn-block btn-danger my-3" onclick="closeDiv()">Close</button>
			</div>
		</div>
		<!-- End PAge Content -->
	</div>
	<!-- End Container fluid  -->
</div>
<jsp:include page="views/new-layout/footer.jsp" />
<script>

	function Structure(id, name, string) {
		this.id = id;
		this.name = name;
		this.string = string;
	}
	
	Structure.prototype.getId = function() {
		return this.id;
	}
	
	Structure.prototype.getName = function() {
		return this.name;
	}
	
	Structure.prototype.getString = function() {
		return this.string;
	}
	
	var structure = {};
	
	function Dataset(id, name, structure_id) {
		this.id = id;
		this.name = name;
		this.structure_id = structure_id;
	}
	
	Dataset.prototype.getId = function() {
		return this.id;
	}
	
	Dataset.prototype.getName = function() {
		return this.name;
	}
	
	Dataset.prototype.getStructureId = function() {
		return this.structure_id;
	}
	
	var dataset = {};
	
	function getDataset(dataset_id) {
		var count = Object.keys(dataset).length;
		console.log(count);
		for(var i = 0; i < count; i++) {
			if(dataset[i].getId() == dataset_id) {
				return dataset[i];
			}
		}
	}

	function newTask() {
		$("#entity-counter").hide();
		$("#task-list-row").hide();
		$("#create-task-workflow").show();
	}
	
	function viewStructureString(count) {
		var structureString = structure[count].getString();
		var structureName = structure[count].getName();
		$("#create-task-workflow").hide();
		$("#structureStringDiv").show();
		$("#structureName").html(structureName);
		$("#structureString").html(structureString);
	}
	
	function closeDiv() {
		$("#create-task-workflow").show();
		$("#structureStringDiv").hide();
		$("#structureName").empty();
		$("#structureString").empty();
	}
	
	function selectStructure(count) {
		$(".workflow-list").removeClass('active');
		$("#training-workflow-list").addClass('active');
		$('#dashboardTab a[href="#selectTrain"]').tab('show');
		$("#create-task-form").hide();
		var structureName = structure[count].getName();
		var structureId = structure[count].getId();
		$(".selected-structure-name").html(structureName);
		var string = "";
		for(var i = 0; i < Object.keys(dataset).length; i++) {
			if(dataset[i].getStructureId() == structureId) {
				string += "<li class='list-group-item'> <a href='http://localhost:8080/weka-tutorial/DatasetView?dataset_id="+dataset[i].getId()+"'>"+dataset[i].getName()
				+ "</a><button onclick='selectTrain("+dataset[i].getId()+", "+count+")' class='btn btn-warning float-right'>Select Train Data</button>"
				+ "</li>";
			}
		}
		 if (string == "") {
			string = "<li class='list-group-item' style='color:red'>No Dataset Found</li>";
		}
		$("#all-dataset-train-list-group").html(string);
	}
	
	function selectTrain(train_id, count) {
		$(".workflow-list").removeClass('active');
		$("#testing-workflow-list").addClass('active');
		$('#dashboardTab a[href="#selectTest"]').tab('show');
		$("#create-task-form").hide();
		$("#trainingData").val(train_id);
		var structureName = structure[count].getName();
		var structureId = structure[count].getId();
		var trainDatasetName = getDataset(train_id).getName();
		var string = "";
		for(var i = 0; i < Object.keys(dataset).length; i++) {
			if(dataset[i].getStructureId() == structureId && dataset[i].getId() != train_id) {
				string += "<li class='list-group-item'><a href='http://localhost:8080/weka-tutorial/DatasetView?dataset_id="+dataset[i].getId()+"'>"+dataset[i].getName()
				+ "</a><button onclick='selectTest("+dataset[i].getId()+")' class='btn btn-success float-right'>Select Test Data</button>"
				+ "</li>";
			}
		}
		
		 if (string == "") {
			string = "<li class='list-group-item' style='color:red'>No Dataset Found</li>";
		}
		$("#all-dataset-test-list-group").html(string);
	}
	
	function selectTest(test_id) {
		$(".workflow-list").removeClass('active');
		$("#create-task-workflow-list").addClass('active');
		$("#create-task-form").show();
		$("#testingData").val(test_id);
	}


	$(function() {
		$.get("http://localhost:8080/weka-tutorial/ServletDashboard", {}, function(data) {
			var string = "";
			var count = 1;
			var taskCount = data.task.length;
			data.task.forEach(function(d) {
				string += "<tr>"
					+ "<td>"+count+"</td>"
					+ "<td><a href='http://localhost:8080/weka-tutorial/TaskView?task_id="+d.id+"'>"+d.name+"</a></td>"
					+ "<td><a href='http://localhost:8080/weka-tutorial/DatasetView?dataset_id="+d.train_id+"'>"+d.train+"</a></td>"
					+ "<td style='text-align: left'><a href='http://localhost:8080/weka-tutorial/DatasetView?dataset_id="+d.test_id+"'>"+d.test+"</a></td>"
					+ "</tr>";
					count++;
			});
			$("#allTask").html(string);
			$("#noOfTask").html(taskCount);
		}, "json");		
		
		$.get("http://localhost:8080/weka-tutorial/ServletDataset", {}, function(data) {
			var allDataset = "";
			var datasetCount = data.dataset.length;
			var count = 0;
			data.dataset.forEach(function(d) {
				// allDataset += "<li class='list-group-item'>"+d.name+"</li>";
				dataset[count] = new Dataset(d.id, d.name, d.structure_id);
				count++;
			});
			console.log(dataset);
			$("#all-dataset-list-group").html(allDataset);
			$("#noOfDataset").html(datasetCount);
		}, "json");
		
		$.get("http://localhost:8080/weka-tutorial/ServletStructuresList", {}, function(data) {
			var structureCount = data.structure.length;
			var string = "";
			count = 0;
			data.structure.forEach(function(s) {
				structure[count] = new Structure(s.ID, s.name, s.string);
				string += "<li class='list-group-item'>"
					+ structure[count].getName()
					+ "<button class='float-right btn btn-success mx-1' onclick='selectStructure("+count+")'>Select Structure</button>"
					+ "<button class='float-right btn btn-warning mx-1' onclick='viewStructureString("+count+")'>View Structure String</button>"
					+ "</li>";
				count++;
			});
			$("#noOfStructure").html(structureCount);
			$("#structure-list-group").html(string);
		}, "json");
	});

</script>