<jsp:include page="layout/header.jsp" />
<div class="container">
	<div class="jumbotron">
		<h1>UTB Data Analytic</h1>
	</div>
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
						out.print("<th>" + attributeNames[i] + "</th>");
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
		</div>
	</form>
</div>
<jsp:include page="layout/footer.jsp" />