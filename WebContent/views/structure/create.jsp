<jsp:include page="../new-layout/header.jsp"/>
<div class="page-wrapper">
   	<div class="row page-titles">
      	<div class="col-md-5 align-self-center">
         	<h3 class="text-primary">Create Structure</h3>
      	</div>
      	<div class="col-md-7 align-self-center">
         	<ol class="breadcrumb">
            	<li class="breadcrumb-item"><a href="javascript:void(0)">Home</a></li>
            	<li class="breadcrumb-item">Structure</li>
            	<li class="breadcrumb-item active">Create</li>
         	</ol>
      	</div>
   	</div>
   	<div class="container-fluid">
      	<div class="row">
         	<div class="col-lg-12">
               	<form action="https://weka-tutorial.azurewebsites.net/StructureCreate" method="post">
            	<div class="card">
	               	<div class="card-title">
	                  	<h4>Create New Data Set</h4>
	               	</div>
               		<div class="card-body">
                  		<div class="basic-form">
							<div class="form-group"> 
								<label for="structureName">Structure Name (No Spaces):</label> 
								<input type="text" class="form-control input-default" id="structureName" name="structureName" placeholder="Input Structure Name"/>
							</div>
							<div class="form-group"> 
								<label for="structureString">Enter Structure String:</label> 
								<input type="text" class="form-control input-default" name="structureString" id="structureString" onchange="enterStructureString()" placeholder="Enter Structure String"/>
							</div>
						</div>
					</div>
				</div>
				<div class="card">
					<div class="card-title">
						<h4>Structure Builder</h4>
					</div>
					<div class="card-body">
						<div class="basic-form">
							<p>Click Add Attribute to use Structure Builder</p>
							<div id="structureBuilder"></div>
							<button type="button" class="btn btn-block btn-primary" id="addAttribute" onclick="addAttributeName()">Add Attribute</button>
							<input type="submit" name="submitButton" class="btn btn-block btn-warning my-2 submitButton" style="display:none;" value="Insert Structure"/> 	
                  		</div>
               		</div>
            	</div>
            </form>
        </div>
	</div>
</div>
<jsp:include page="../new-layout/footer.jsp"/>
<script>
	var count=0; function addAttributeName() {
		var regex=/^\S*$/; 
		if (!regex.test($("#structureName").val()) || $("#structureName").val()=="") {
			alert("Do not leave the data set name empty and remove any spaces in the data set name!")
		} else {
			$(".submitButton").show(); 
			$(".submitButton").prop("disabled", true); 
			count++; 
			var string = '<hr><div class="form-group"><strong>Attribute Name No ' + count + ': </strong><input type="text" class="form-control" name="attributeName" onchange="insertName(this)")/></div>'; string +='<div class="form-group" id="attribute_'+count+'">' 
				+ '<label for="attributeType_' + count + '">Type</label>' 
				+ '<select class="form-control" name="attributeType_' +count + '" onchange="attributeSelect($(this).val(), ' + count + ');">' 
					+ '<option>Please Select</option>' 
					+ '<option>Nominal</option>' 
					+ '<option>Numeric</option>' 
				+ '</select>' 
				+ '</div><div class="form-group" id="attOption_'+count+'"></div>'; 
			$("#structureBuilder").append(string);
		}
	}

	function attributeSelect(value, index) {
		if(value=="Nominal") {
			var string = '<div class="form-group"><label for="option_'+count+'">Options</label>' 
				+ '<div id="insertOption_'+count+'">' 
				+ '<div class="form-group">' 
				+ '<input type="text" name="option_' + count + '" class="form-control">' 
				+'</div>' 
				+'</div>' 
				+'<button class="btn btn-block btn-success" type="button" onclick="addOption(' + index + ')">Add Option</button>' 
				+'</div>'; 
			$("#attOption_"+index).html(string);
		} else if(value="Numeric") {
			var string='<div class="form-group"><input type="hidden" name="option_'+count+'" value=""/></div>'; 
			$("#attOption_"+index).html(string);
		}
	}
	
	function insertName(a) {
		if(a.value==null || a.value=="" ) { 
			$(".submitButton").prop("disabled", true);
		} else { 
			$(".submitButton").prop("disabled", false);
		}
	}
	
	function addOption(index) { 
		var string = '<div class="form-group"><input type="text" name="option_'+count+'" class="form-control"></div>'; 
		$("#insertOption_"+index).append(string);
	}
	
	function enterStructureString() { 
		$(".submitButton").show(); 
		if($("#structureString").val()=="" || $("#structureString").val()==null) { 
			$(".submitButton").prop("disabled", true); 
			$("#addAttribute").prop("disabled", false);
		} else {
			$(".submitButton").prop("disabled", false); 
			$("#addAttribute").prop("disabled", true);
		}
	}
</script>