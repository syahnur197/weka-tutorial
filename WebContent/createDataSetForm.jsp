<jsp:include page="layout/header.jsp" />
	<button onclick='addInstance()' class='btn btn-block btn-primary'>Add Instance</button><br>
	<form action="#" method="post">
		<div class="table-responsive">
			<table class="table table-striped" id="instances_table" style="table-layout:fixed;">
				<tr>
				<% 
					String[] attributeNames = request.getParameterValues("attributeName");
 					int attCount = attributeNames.length;
 					String string = "";
					for(int i = 0; i < attCount; i++) {
						out.print("<th class='attributeName'>" + attributeNames[i] + "</th>");
						string += "<td style='width:150px'>";
						int index = i+1;
						String attType = request.getParameter("attributeType_"+index).toString();
						if(attType.equals("Numeric")) {
							string += "<input type='text' name='"+attributeNames[i]+"' class='form-control'/>";
						} else if (attType.equals("Nominal")) {
							String[] options = request.getParameterValues("option_"+index);
							int optionCount = options.length;
							string += "<select class='form-control' name='"+attributeNames[i]+"'>";
							for (int j = 0; j < optionCount; j++) {
								string += "<option>"+options[j]+"</option>";
							}
							string += "</select>";
						}
						string += "</td>";
					}
					string += "<td style='width:150px'><input type='button' value='Delete Row' class='btn btn-block btn-danger'/></td>";
					out.print("<th>Option</th>");
				
				%>
				</tr>
				<% 
					int studentCount = Integer.parseInt(request.getParameter("noOfStudents")); 
					for(int i = 0; i < studentCount; i++) {
				%>
				<tr>
					<% out.println(string); %>	
				</tr>
				<% } %>
			</table>
			<input type="button" value="Download as CSV" class="btn btn-block btn-success" onclick="downloadCSV()"/>
		</div>
	</form>
<jsp:include page="layout/footer.jsp" />
<script>
function addInstance() {
	$("#instances_table").append("<tr><%= string%></tr>");
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
    //  Modified from: http://stackoverflow.com/questions/17836273/
    //  export-javascript-data-to-csv-file-without-server-interaction
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