<jsp:include page="layout/header.jsp" />
	<button onclick='addInstance()' class='btn btn-block btn-primary'>Add Instance</button><br>
	<form action="#" method="post">
		<div class="table-responsive">
			<table class="table table-striped" id="instances_table" style="table-layout:fixed;">
				<tr>
				<% 
					String tableHeader = request.getAttribute("tableHeader").toString();
					String tableContent = request.getAttribute("tableContent").toString();
					String structureString = request.getAttribute("structureString").toString();
					out.print(tableHeader);
				
				%>
				</tr>
				<% 
					int studentCount = Integer.parseInt(request.getParameter("noOfStudents")); 
					for(int i = 0; i < studentCount; i++) {
				%>
				<tr>
					<% out.print(tableContent); %>	
				</tr>
				<% } %>
			</table>
		</div>
		<input type="hidden" name="structureString" value="<% out.print(structureString); %>" />
		<input type="button" value="Download as CSV" class="btn btn-block btn-success" onclick="downloadCSV()"/>
	</form>
<jsp:include page="layout/footer.jsp" />
<script>
function addInstance() {
	$("#instances_table").append("<tr><%= tableContent%></tr>");
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