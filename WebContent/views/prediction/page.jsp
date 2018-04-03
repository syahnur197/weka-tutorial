<jsp:include page="../new-layout/header.jsp" />
	<%
		String table = request.getAttribute("tableString").toString();
		String percentage = request.getAttribute("percentageString").toString();
		int trainDataset_id = (int)request.getAttribute("trainDataset_id");
		int testDataset_id = (int)request.getAttribute("testDataset_id");
	%>;
	<!-- Page wrapper  -->
	<div class="page-wrapper">
		<!-- Bread crumb -->
		<div class="row page-titles">
			<div class="col-md-5 align-self-center">
           		<h3 class="text-primary">Create Task</h3>
			</div>
       		<div class="col-md-7 align-self-center">
           		<ol class="breadcrumb">
               		<li class="breadcrumb-item"><a href="javascript:void(0)">Home</a></li>
               		<li class="breadcrumb-item">Task</li>
               		<li class="breadcrumb-item active">Prediction</li>
           		</ol>
       		</div>
   		</div>
		<div class="container-fluid">
			<div class="row">
				<div class="col-lg-12">
					<div class="card">
						<form id="saveTaskForm" action="/weka-tutorial/TaskSave" method="POST">
							<h3 class="card-title">Prediction Result</h3>
							<div class='table-responsive'>
								<table class='table table-striped table-hover' id="predictionTable" style='text-align: center'>
									<tr>
										<th>Student No</th><th>Actual Grade</th><th>Predicted Grade (J48)</th><th>J48 Matches</th><th>Predicted Grade (BN)</th><th>BN Matches</th>
									</tr>
									<%= table %>
								</table>
							</div>
							<%= percentage %>
							<hr>
							<button type="button" class='btn btn-primary mx-1' onclick="drawJ48Chart();">Show J48 Pie Chart</button>
							<button type="button" class='btn btn-primary mx-1' onclick="drawNBChart();">Show BN Pie Chart</button>
							<input type="hidden" name="trainDataset_id" value="<%= trainDataset_id %>" />
							<input type="hidden" name="testDataset_id" value="<%= testDataset_id %>" />
							<input type="text" name="taskName" id="taskName" value="" placeholder="Enter Task Name" class="form-control mt-3" required/>
							<input type="submit" class="btn btn-block btn-primary mt-3" value="Save Task"/>
						</form>
					</div>
				</div>
			</div>
		</div>
		<div class="container-fluid" id="piechartDiv" style="display:none;">
			<div class="row">
				<div class="col-lg-12">
					<button class='btn btn-danger' onclick="closePieChart()">Back</button>
				</div>
				<div class="col-lg-12">
					<div id="piechart"></div>
				</div>
			</div>
		</div>
	</div>
<jsp:include page="../new-layout/footer.jsp" />
<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
<script type="text/javascript">
	google.charts.load('current', {'packages':['corechart']});
	google.charts.setOnLoadCallback(drawChart);

	function drawJ48Chart() {
		var j48Matches = Number.parseInt($("#j48Matches").html());
		var j48Unmatches = Number.parseInt($("#j48Unmatches").html());
	
		var data = google.visualization.arrayToDataTable([
			['Label', 'Numbers'],
			['J48 Matches',     j48Matches],
			['J48 Unmatches',      j48Unmatches]
		]);
		
		var options = {
			title: 'J48 Prediction Result Pie Chart',
			width: $(window).width(),
			height: $(window).height()*0.75
		};
		
		$("#piechartDiv").show();
		$('html, body').animate({
	        scrollTop: $("#piechartDiv").offset().top
	    }, 1000);
		$("#task-result").hide();
		var chart = new google.visualization.PieChart(document.getElementById('piechart'));
		
		chart.draw(data, options);
	}
	
	function drawNBChart() {
		var NBMatches = Number.parseInt($("#nbMatches").html());
		var NBUnmatches = Number.parseInt($("#nbUnmatches").html());
	
		var data = google.visualization.arrayToDataTable([
			['Label', 'Numbers'],
			['BN Matches',     NBMatches],
			['BN Unmatches',      NBUnmatches]
		]);
		
		var options = {
			title: 'BN Prediction Result Pie Chart',
			width: $(window).width(),
			height: $(window).height()*0.75
		};
		
		$("#piechartDiv").show();
		$('html, body').animate({
	        scrollTop: $("#piechartDiv").offset().top
	    }, 1000);
		var chart = new google.visualization.PieChart(document.getElementById('piechart'));
		
		chart.draw(data, options);
	}
</script>
<script>

	function closePieChart() {
		$("#piechartDiv").hide();
	}
	function changeValue(i) {
		$('#actualClass_'+i).hide();
		$('#editActualClass_'+i).show();
	}
	
	function valueChanged(i) {
		var input = document.getElementById('editActualClass_'+i);

	  	var newVal = $('#editActualClass_'+i).val();
	  	var j48 = $("#j48_"+i+" :input").val();
	  	var nb = $("#nb_"+i+" :input").val();
	  	var j48Color = $("#j48Color_"+i);
	  	var nbColor = $("#nbColor_"+i);
	  	var actualClass = $('#actualClass_'+i);
	  	actualClass.html(newVal);
	  	actualClass.show();
		$('#editActualClass_'+i).hide();
				
		if (j48 == newVal) {
			j48Color.html("matched <input type='hidden' name='j48ClassMatch' value='matched'>");
			j48Color.css("color", "green");
		} else {
			j48Color.html("unmatched <input type='hidden' name='j48ClassMatch' value='unmatched'>");
			j48Color.css("color", "red");
		}
		
		if(nb == newVal) {
			nbColor.html("matched <input type='hidden' name='nbClassMatch' value='matched'>");
			nbColor.css("color", "green");
		} else {
			nbColor.html("unmatched <input type='hidden' name='nbClassMatch' value='unmatched'>");
			nbColor.css("color", "red");
		}
		
		var rowCount = $('#predictionTable tr').length;
		
		var j48Matches = 0;
		var j48Unmatches = 0;
		var nbMatches = 0;
		var nbUnmatches = 0;
		for(var j = 0; j < rowCount - 1; j++) {
			if($('#actualClass_'+j).html() == $('#j48_'+j+" :input").val()) {
				j48Matches++;
			} else {
				j48Unmatches++;
			}
			
			if($('#actualClass_'+j).html() == $('#nb_'+j+" :input").val()) {
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
</script>