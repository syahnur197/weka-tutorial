<jsp:include page="../new-layout/header.jsp" />
	<%
		String tableString = request.getAttribute("tableString").toString();
		int dataset_id = (int) request.getAttribute("dataset_id");
		String elapsed = request.getAttribute("elapsed").toString();
	%>
	<!-- Page wrapper  -->
	<div class="page-wrapper">
		<!-- Bread crumb -->
		<div class="row page-titles">
			<div class="col-md-5 align-self-center">
           		<h3 class="text-primary">View Structure</h3>
           		<p><%= elapsed %> second response time</p>
			</div>
       		<div class="col-md-7 align-self-center">
           		<ol class="breadcrumb">
               		<li class="breadcrumb-item"><a href="javascript:void(0)">Home</a></li>
               		<li class="breadcrumb-item">Data Set</li>
               		<li class="breadcrumb-item active">View</li>
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
					<div class="card">
						<div class="card-title">
							<h4>Dataset</h4>
						</div>
						<div class="card-body">
							<form id='updateDataset' method="post" action="DatasetUpdate">
								<input type='button' value='Edit Cells' id='edit_cells' class='btn btn-block btn-warning my-2'/>
								<div class="table-responsive">
									<table class='table table-striped table-bordered table-hover' style="table-layout:fixed;">
										<%= tableString %>
									</table>
								</div>
								<input type='hidden' name='dataset_id' value='<%= dataset_id %>' />
								<input type='submit' id="submitButton" name='submit' value='Update' class='btn btn-block btn-success my-3' style="display:none;"/>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div> 
   	</div>
<jsp:include page="../new-layout/footer.jsp" />
<script>
	$(function() {
		$("#edit_cells").on('click', function() {
			$(this).hide();
			/*$.map($('.valueCell'), function (el) { 
				el.nextElementSibling.value = el.textContent;
			});*/
			$('.valueCell').hide();
			$('.valueCell').next().show();
			$("#submitButton").show();
		});
	});
</script>