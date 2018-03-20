<jsp:include page="/views/new-layout/header.jsp" />
	<% String tableBody = request.getAttribute("tableBody").toString(); %>
	<!-- Page wrapper  -->
	<div class="page-wrapper">
		<!-- Bread crumb -->
		<div class="row page-titles">
			<div class="col-md-5 align-self-center">
           		<h3 class="text-primary">View Structure</h3>
			</div>
       		<div class="col-md-7 align-self-center">
           		<ol class="breadcrumb">
               		<li class="breadcrumb-item"><a href="javascript:void(0)">Home</a></li>
               		<li class="breadcrumb-item">Data Set</li>
               		<li class="breadcrumb-item active">Create</li>
           		</ol>
       		</div>
   		</div>
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
			<div class="row">
				<div class="col-lg-12">
					<div class="card" id="structureListDiv">
						<div class="card-title">
							<h4>Structure List</h4>
						</div>
						<div class="card-body">
							<div class="table-responsive">
								<table class="table table-hover ">
									<thead>
										<tr>
											<th>#</th>
											<th>Structure Name</th>
											<th style='text-align:left'>Option</th>
										</tr>
									</thead>
									<%= tableBody %>
								</table>
							</div>
						</div>
					</div>
					<div class="card" id="structureStringDiv" style="display:none;">
						<div class="card-title">
							<h4 class="structureName">{{ structure name }}</h4>
						</div>
						<div class="card-body">
							<span class="structureString">{{ structure string }}</span>
							<button class="btn btn-block btn-danger my-3" onclick="closeDiv()">Close</button>
						</div>
					</div>
					<div class="card" id="selectStructureDiv" style="display:none;">
						<div class="card-title">
							<h4 class="structureName">{{ structure name }}</h4>
						</div>
						<div class="card-body">
							<form action="DatasetInsertForm" method="POST">
								<div class="form-group"> 
									<label for="structureName">Data Set Name:</label> 
									<input type="text" class="form-control input-default" id="datasetName" name="datasetName" placeholder="Input Data Set Name" required/>
								</div>
								<input type="hidden" name="structure_id" id="insert_structure_id" value="" />
								<input type="hidden" name="structure_string" class="structureString" value="" />
								<input type="submit" name="submitButton" value="Manual Entry" class="btn btn-block btn-info mt-3"/>
								<input type="submit" name="submitButton" value="Upload CSV" class="btn btn-block btn-primary mt-3"/>
							</form>
							<button class="btn btn-block btn-danger my-3" onclick="closeDiv()">Close</button>
						</div>
					</div>
				</div>
			</div>
		</div>     
	</div>
   <!-- End Container fluid  -->
<jsp:include page="/views/new-layout/footer.jsp" />
<script>
function viewStructure(structure_id) {
	$.get("http://localhost:8080/weka-tutorial/StructureData", {'structure_id' : structure_id}, function (data) {
		$("#structureListDiv").hide();
		$("#structureStringDiv").show();
		$(".structureString").html(data.structure_string);
		$("#structureName").html(data.structure_name);
	}, "json");
}

function closeDiv() {
	$("#structureListDiv").show();
	$("#structureStringDiv").hide();
	$("#selectStructureDiv").hide();
	$(".structureString").html("");
	$("#structureName").html("");
}

function selectStructure(structure_id) {
	$.get("http://localhost:8080/weka-tutorial/StructureData", {'structure_id' : structure_id}, function (data) {
		console.log(data);
		$("#insert_structure_id").val(structure_id);
		$("#selectStructureDiv").show();
		$("#structureListDiv").hide();
		$(".structureString").val(data.structure_string);
		$(".structureName").html(data.structure_name + " Selected");
	}, "json");
}
</script>