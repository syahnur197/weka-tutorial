<jsp:include page="layout/header.jsp" />
<div class="container">
	<div class="jumbotron">
		<h1>UTB Data Analytic</h1>
	</div>
	<div class="row">
		<div class="col-md-12 my-2">
			<h2>Create New Data Set</h2>
		</div>
	</div>
	<form action="createDataSetForm.jsp" method="post">
		<div class="form-group">
			<label for="dataSetName">Data Set Name:</label>
			<input type="text" class="form-control" id="dataSetName" name="dataSetName"/>
		</div>
		<div class="form-group">
			<label for="noOfStudents">Number of Students Students:</label>
			<input type="number" class="form-control" name="noOfStudents" id="noOfStudents"/>
		</div>
		<h3>Structure</h3>
		<div id="structureBuilder"></div>
		<button type="button" class="btn btn-block btn-primary" onclick="addAttribute()">Add Attribute</button>
		<button type="submit" class="btn btn-block btn-warning">Submit</button>
	</form>
</div>
<jsp:include page="layout/footer.jsp" />

<script>
	var count = 0;
	function addAttribute() {
		count++;
		var string = '<div class="form-group"><label for="attributeName">Attribute Name:</label><input type="text" class="form-control" name="attributeName"/></div>';
		string += '<div class="form-group" id="attribute_'+count+'">'
			+'<label for="attributeType_'+count+'">Type</label>'
			+'<select class="form-control" name="attributeType_'+count+'" onchange="attributeSelect($(this).val(), '+count+');">'
				+'<option>Please Select</option>'
				+'<option>Nominal</option>'
				+'<option>Numeric</option>'
			+'</select>'
		+'</div><div class="form-group" id="attOption_'+count+'"></div>';
		$("#structureBuilder").append(string);
	}
	
	function attributeSelect(value, index) {
		if(value == "Nominal") {
			var string = '<div class="form-group"><label for="option_'+count+'">Options</label>'
				+'<div id="insertOption_'+count+'">'
					+'<div class="form-group">'
						+'<input type="text" name="option_'+count+'" class="form-control">'
					+'</div>'
				+'</div>'
				+'<button class="btn btn-block btn-success" type="button" onclick="addOption('+index+')">Add Option</button>'
			+'</div>';
			$("#attOption_"+index).html(string);
		} else if(value = "Numeric") {
			var string = '<div class="form-group"><input type="hidden" name="option_'+count+'" value=""/></div>';
			$("#attOption_"+index).html(string);
		}
	}
	
	function addOption(index) {
		var string = '<div class="form-group"><input type="text" name="option_'+count+'" class="form-control"></div>';
		$("#insertOption_"+index).append(string);
	}
</script>

