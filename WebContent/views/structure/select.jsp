<jsp:include page="../layout/header.jsp" />
	<%
		String message = "";
		if (session.getAttribute("message") != null) {
			message = "<div class='alert alert-danger'><strong>Warning</strong>"+session.getAttribute("message").toString()+"</div>";
			session.removeAttribute("message");
		}
	%>
	<% out.println(message); %>
	<div class="row">
		<div class="col-md-12">
			<h2>Select Available Structure</h2>
		</div>
	</div>
	<hr>
	<ul class="list-group" id="structureList">
	</ul>

	<!-- Structure String Modal -->
	<div class="modal fade" id="structure-string-modal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLongTitle" aria-hidden="true">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title" id="exampleModalLongTitle">Structure String</h5>
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
	          <span aria-hidden="true">&times;</span>
	        </button>
	      </div>
	      <div class="modal-body" id="structure-string">
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
	      </div>
	    </div>
	  </div>
	</div>
	
	
	<!-- Add Data Set Modal -->
	<div class="modal fade" id="add-dataset-modal" tabindex="-1" role="dialog" aria-labelledby="addDatasetTitle" aria-hidden="true">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <form action="https://weka-tutorial.azurewebsites.net/ServletCreateDataSet" method="POST">
		      <div class="modal-header">
		        <h5 class="modal-title" id="addDatasetTitle">Add Dataset</h5>
		        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
		          <span aria-hidden="true">&times;</span>
		        </button>
		      </div>
		      <div class="modal-body" id="structure-string">
		      	<div class="from-group">
		      		<label for="noOfStudents">No Of Students</label>
	      			<input type="number" name="noOfStudents" value="" class='form-control'/>
		      	</div>
		      	<div class="form-group">
					<label for="datasetName">Data Set Name (No Spaces):</label>
					<input type="text" class="form-control" id="dataSetName" name="datasetName" id="datasetName"/>
				</div>
		      </div>
		      <div class="modal-footer">
	      		<input type="hidden" name="structureString" value="" id="structureStringInput"/>
	      		<input type="hidden" name="structureId" value="" id="structureIdInput"/>
		        <input type="submit" name='submitButton' value="Manual Entry" class="btn btn-secondary"/>
		        <input type="submit" name='submitButton' value="Upload CSV" class="btn btn-secondary" />
		      </div>
	      </form>
	    </div>
	  </div>
	</div>
<jsp:include page="../layout/footer.jsp" />

<script>
	$(function() {
		$.get("https://weka-tutorial.azurewebsites.net/ServletStructuresList", {}, function(data) {
			for(var i = 0; i < data.structure.length; i++) {
			    var string = "<li class='list-group-item'>"
			    +"<button class='btn btn-warning mx-2' data-toggle='modal' data-target='#structure-string-modal' onclick='viewString(\"" + data.structure[i].string + "\")'>View String</button>"
				+"<button class='btn btn-success mx-2' data-toggle='modal' data-target='#add-dataset-modal' onclick='addDataset(\"" + data.structure[i].string + "\", "+data.structure[i].ID+")'>Add Data Set</button>"
		    	+ data.structure[i].name 
		    	+ "</li>";
			    $("#structureList").append(string);
			}
		}, 'json');
	})
</script>
<script>
	function viewString(string) {
		$("#structure-string").html(string);
	}
	
	function addDataset(string, id) {
		$("#structureStringInput").val(string);
		$("#structureIdInput").val(id);
	}
</script>