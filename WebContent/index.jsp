<jsp:include page="views/new-layout/header.jsp" />
<!-- Page wrapper  -->
<div class="page-wrapper">
	<!-- Bread crumb -->
	<div class="row page-titles">
		<div class="col-md-5 align-self-center">
			<h3 class="text-primary">Dashboard</h3> 
		</div>
		<div class="col-md-7 align-self-center">
			<ol class="breadcrumb">
				<li class="breadcrumb-item"><a href="javascript:void(0)">Home</a></li>
				<li class="breadcrumb-item active">Dashboard</li>
			</ol>
		</div>
	</div>
	<!-- End Bread crumb -->
	<!-- Container fluid  -->
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
		<!-- Start Page Content -->
		<div class="row">
			<div class="col-md-4">
				<div class="card bg-primary p-20">
					<div class="media widget-ten">
						<div class="media-left meida media-middle">
							<span><i class="fa fa-tasks f-s-40"></i></span>
						</div>
						<div class="media-body media-text-right">
							<h2 class="color-white">{{ No Of Task }}</h2>
							<p class="m-b-0">Tasks</p>
						</div>
					</div>
				</div>
			</div>
			<div class="col-md-4">
				<div class="card bg-pink p-20">
					<div class="media widget-ten">
						<div class="media-left meida media-middle">
							<span><i class="fa fa-building f-s-40"></i></span>
						</div>
						<div class="media-body media-text-right">
							<h2 class="color-white">{{ No Of Structure }}</h2>
							<p class="m-b-0">Structures</p>
						</div>
					</div>
				</div>
			</div>
			<div class="col-md-4">
				<div class="card bg-success p-20">
					<div class="media widget-ten">
						<div class="media-left meida media-middle">
							<span><i class="fa fa-database f-s-40"></i></span>
						</div>
						<div class="media-body media-text-right">
							<h2 class="color-white">{{ No Of Dataset }}</h2>
							<p class="m-b-0">Datasets</p>
						</div>
					</div>
				</div>
			</div>
		</div>

		<div class="row">
			<!-- /# column -->
			<div class="col-lg-12">
				<div class="card">
					<div class="card-title">
						<h4>Tasks </h4>
					</div>
					<div class="card-body">
						<div class="table-responsive">
							<table class="table table-hover ">
								<thead>
									<tr>
										<th>#</th>
										<th>Training Data</th>
										<th>Testing Data</th>
										<th>Date</th>
										<th>Price</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<th scope="row">1</th>
										<td>Kolor Tea Shirt For Man</td>
										<td><span class="badge badge-primary">Sale</span></td>
										<td>January 22</td>
										<td class="color-primary">$21.56</td>
									</tr>
									<tr>
										<th scope="row">2</th>
										<td>Kolor Tea Shirt For Women</td>
										<td><span class="badge badge-success">Tax</span></td>
										<td>January 30</td>
										<td class="color-success">$55.32</td>
									</tr>
									<tr>
										<th scope="row">3</th>
										<td>Blue Backpack For Baby</td>
										<td><span class="badge badge-danger">Extended</span></td>
										<td>January 25</td>
										<td class="color-danger">$14.85</td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
				</div>
				<!-- /# card -->
			</div>
			<!-- /# column -->
		</div>
		<!-- End PAge Content -->
	</div>
	<!-- End Container fluid  -->
</div>
<jsp:include page="views/new-layout/footer.jsp" />