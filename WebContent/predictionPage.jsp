<jsp:include page="layout/header.jsp" />
	<%
		String table = request.getAttribute("tableString").toString();
		String percentage = request.getAttribute("percentageString").toString();
	%>
	<div class='table-responsive'>
		<table class='table table-striped table-hover' id="predictionTable" style='text-align: center'>
			<tr>
				<th>Student No</th><th>Actual Grade</th><th>Predicted Grade (J48)</th><th>J48 Matches</th><th>Predicted Grade (NB)</th><th>NB Matches</th>
			</tr>
			<%= table %>
		</table>
	</div>
	<%= percentage %>
<jsp:include page="layout/footer.jsp" />
<script>
	function changeValue(i) {
		$('#actualClass_'+i).hide();
		$('#editActualClass_'+i).show();
		var input = document.getElementById('editActualClass_'+i);

		// Execute a function when the user releases a key on the keyboard
		input.addEventListener("keyup", function(event) {
		  // Cancel the default action, if needed
		  event.preventDefault();
		  // Number 13 is the "Enter" key on the keyboard
		  if (event.keyCode === 13) {
			  	var newVal = $('#editActualClass_'+i).val();
			  	var j48 = $("#j48_"+i);
			  	var nb = $("#nb_"+i);
			  	var j48Color = $("#j48Color_"+i);
			  	var nbColor = $("#nbColor_"+i);
			  	var actualClass = $('#actualClass_'+i);
			  	actualClass.html(newVal);
			  	actualClass.show();
				$('#editActualClass_'+i).hide();
				
				if (j48.html() == newVal) {
					j48Color.html("matched");
					j48Color.css("color", "green");
				} else {
					j48Color.html("unmatched");
					j48Color.css("color", "red");
				}
				
				if(nb.html() == newVal) {
					nbColor.html("matched");
					nbColor.css("color", "green");
				} else {
					nbColor.html("unmatched");
					nbColor.css("color", "red");
				}
				
				var rowCount = $('#predictionTable tr').length;
				
				var j48Matches = 0;
				var j48Unmatches = 0;
				var nbMatches = 0;
				var nbUnmatches = 0;
				for(var j = 0; j < rowCount - 1; j++) {
					if($('#actualClass_'+j).html() == $('#j48_'+j).html()) {
						j48Matches++;
					} else {
						j48Unmatches++;
					}
					
					if($('#actualClass_'+j).html() == $('#nb_'+j).html()) {
						nbMatches++;
					} else {
						nbUnmatches++;
					}
				}
				
				var j48Percent = ((j48Matches/(j48Matches+j48Unmatches))*100).toFixed(2);
				var nbPercent = ((nbMatches/(nbMatches+nbUnmatches))*100).toFixed(2);
				
				$("#j48Matches").html(j48Matches);
				$("#j48Unmatches").html(j48Unmatches);
				$("#nbMatches").html(nbMatches);
				$("#nbUnmatches").html(nbUnmatches);
				$("#j48Percent").html(j48Percent);
				$("#nbPercent").html(nbPercent);
				
		  }
		});
	}
</script>