<jsp:include page="/views/new-layout/header.jsp" />
	<!-- Page wrapper  -->
	<div class="page-wrapper">
		<!-- Bread crumb -->
		<div class="row page-titles">
			<div class="col-md-5 align-self-center">
           		<h3 class="text-primary">Create Data Set</h3>
			</div>
       		<div class="col-md-7 align-self-center">
           		<ol class="breadcrumb">
               		<li class="breadcrumb-item"><a href="javascript:void(0)">Home</a></li>
               		<li class="breadcrumb-item">Data Set</li>
               		<li class="breadcrumb-item active">Manual Entry</li>
           		</ol>
       		</div>
   		</div>
		<div class="container-fluid">
			<div class="row">
				<div class="col-lg-12">
					<div class="card" id="structureListDiv"> 
						<div class="row" id="noOfStudentsDiv">
							<div class="col-lg-6">
								<input type="text" class="form-control input-default" id="noOfStudents" val="" placeholder="Enter Number of Students"/>
							</div>	
							<div class="col-lg-6">
								<button class="btn btn-block btn-info" onclick="addRows($('#noOfStudents').val());" >Add Student Rows</button>
							</div>
						</div>
						<button onclick='addInstance()' class='btn btn-block btn-primary mt-2'>Add Instance</button><br>
						<form action="/weka-tutorial/DatasetUpload" method="post" id="instances_table_form">
							<div class="table-responsive">
								<table class="table table-striped" id="instances_table" style="table-layout:fixed;">
									<% 
										String tableHeader = request.getAttribute("tableHeader").toString();
										String tableContent = request.getAttribute("tableContent").toString();
										String datasetName = request.getAttribute("datasetName").toString();
										int structureId = (int)request.getAttribute("structureId");
										out.print(tableHeader);
									
									%>
								</table>
							</div>
							<button onclick='addInstance()' type="button" class='btn btn-block btn-primary mt-2'>Add Instance</button>
							<input type="hidden" name="structureId" value="<% out.print(structureId); %>" />
							<input type="hidden" name="datasetName" value="<% out.print(datasetName); %>" />
							<input type="submit" name="submitButton" value="Upload as CSV" class="btn btn-block btn-success mt-2" />
						</form>
					</div>
				</div>
			</div>
		</div>     
	</div>
   <!-- End Container fluid  -->
<jsp:include page="/views/new-layout/footer.jsp" />
<script>
function addInstance() {
	$("#instances_table").append("<tr><%= tableContent%></tr>");
}

function addRows(rows) {
	for(var i = 0; i < rows; i++) {
		$("#instances_table").append("<tr><%= tableContent%></tr>");
		$("#noOfStudentsDiv").hide();
	}
}

function downloadCSV() {
	var attribute = []
	var attName = $(".attributeName").map(function() {
		return $(this).html();
	}).get();
	var attCount = attName.length;
	var rowCount = $("#instances_table tr").length;
	var vars = {};
	for(var i = 0; i < attCount; i++) {
		var attValue = [];
		$("[name='"+attName[i]+"']").map(function() {
			attValue.push($(this).val());
		});
		vars[attName[i]] = attValue;
	}
	attribute.push(attName);
	for(var j = 0; j < rowCount-1; j++) {
		var array = [];
		for(var k = 0; k < attCount; k++) {
			array.push(vars[attName[k]][j]);
		}
		attribute.push(array);
	}
	arrayToCSV(attribute);
}

function arrayToCSV (twoDiArray) {
    var csvRows = [];
    for (var i = 0; i < twoDiArray.length; ++i) {
        for (var j = 0; j < twoDiArray[i].length; ++j) {
            twoDiArray[i][j] = '\"' + twoDiArray[i][j] + '\"';  // Handle elements that contain commas
        }
        csvRows.push(twoDiArray[i].join(','));
    }

    var csvString = csvRows.join('\r\n');
    var a         = document.createElement('a');
    a.href        = 'data:attachment/csv,' + csvString;
    a.target      = '_blank';
    a.download    = '<%= request.getParameter("dataSetName") %>.csv';

    document.body.appendChild(a);
    a.click();
    // Optional: Remove <a> from <body> after done
}

$(document).ready(function() {
	$('table').on('click', 'input[type="button"]', function(e){
		var confirmation = confirm("Are you sure you want to delete this row and its content?");
		if(confirmation) {
			   $(this).closest('tr').remove();
		} else {
			alert("Cancel deleting row.")
		}
	});
});
</script>

