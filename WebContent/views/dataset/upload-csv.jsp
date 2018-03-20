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
               		<li class="breadcrumb-item active">Upload CSV</li>
           		</ol>
       		</div>
   		</div>
		<div class="container-fluid">
			<div class="row">
				<div class="col-lg-12">
					<div class="card" id="structureListDiv">
						<form action="/weka-tutorial/DatasetUpload" method="post" id="upload_csv_form" enctype="multipart/form-data">
							<% 
								String datasetName = request.getAttribute("datasetName").toString();
								int structureId = (int)request.getAttribute("structureId");							
							%>
                            <input name="dataset" type="file" class="form-control"/>
							<input type="hidden" name="structureId" value="<% out.print(structureId); %>" />
							<input type="hidden" name="datasetName" value="<% out.print(datasetName); %>" />
							<input type="submit" name="submitButton" value="Upload CSV" class="btn btn-block btn-success mt-2" />
						</form>
					</div>
				</div>
			</div>
		</div>     
	</div>
   <!-- End Container fluid  -->
<jsp:include page="/views/new-layout/footer.jsp" />
