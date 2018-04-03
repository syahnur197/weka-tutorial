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
               		<li class="breadcrumb-item">Structure</li>
               		<li class="breadcrumb-item active">View</li>
           		</ol>
       		</div>
   		</div>
		<div class="container-fluid">
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
							<h4 id="structureName">{{ structure name }}</h4>
						</div>
						<div class="card-body">
							<span id="structureString">{{ structure string }}</span>
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
	$.get("https://weka-tutorial.azurewebsites.net/StructureData", {'structure_id' : structure_id}, function (data) {
		$("#structureListDiv").hide();
		$("#structureStringDiv").show();
		$("#structureString").html(data.structure_string);
		$("#structureName").html(data.structure_name);
	}, "json");
}

function closeDiv() {
	$("#structureListDiv").show();
	$("#structureStringDiv").hide();
	$("#structureString").html("");
	$("#structureName").html("");
}
</script>
