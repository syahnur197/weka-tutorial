<jsp:include page="layout/header.jsp"/>
<div class="container">
	<div class="jumbotron">
		<a href='index.jsp'>
			<h1>UTB Data Analytic</h1>
		</a>
	</div>
	<button onclick='addInstance()' class='btn btn-block btn-primary'>Add Instance</button><br>
	<form action="ServletPrediction" method="post">
		<div class="table-responsive">
			<table class="table table-striped" id="instances_table" style="table-layout:fixed;">
				<tr>
					<th style='width:150px'>Gender</th>
					<th style='width:150px'>Nationality</th>
					<th style='width:150px'>Place of Birth</th>
					<th style='width:150px'>Stage ID</th>
					<th style='width:150px'>Grade ID</th>
					<th style='width:150px'>Section ID</th>
					<th style='width:150px'>Topic</th>
					<th style='width:150px'>Semester</th>
					<th style='width:150px'>Relation</th>
					<th style='width:150px'>Raised Hands</th>
					<th style='width:150px'>Visited Resources</th>
					<th style='width:150px'>Announcement Views</th>
					<th style='width:150px'>Discussion</th>
					<th style='width:150px'>Parent Answering Survey</th>
					<th style='width:150px'>Parent School Satisfaction</th>
					<th style='width:150px'>Student Absence Days</th>
					<th style='width:150px'>Class</th>
				</tr>
			<% 
				int noOfInstances = Integer.parseInt(request.getParameter("noOfInstances")); 
				for(int i = 1; i <= noOfInstances; i++) {
			%>
				<tr>
					<td style='width:150px'>
						<select name="gender" id="gender" class="form-control">
							<option>M</option>
							<option>F</option>
						</select>	
					</td>
					<td style='width:150px'>
						<select name="NationalITy" id="NationalITy" class="form-control">
							<option>KW</option>
							<option>SaudiArabia</option>
							<option>Jordan</option>
							<option>USA</option>
							<option>lebanon</option>
							<option>Iran</option>
							<option>venzuela</option>
							<option>Egypt</option>
							<option>Tunis</option>
							<option>Morocco</option>
							<option>Syria</option>
							<option>Palestine</option>
							<option>Iraq</option>
							<option>Lybia</option>
						</select>
					</td>
					<td style='width:150px'>
						<select name="PlaceofBirth" id="PlaceofBirth" class="form-control">
							<option>KuwaIT</option>
							<option>SaudiArabia</option>
							<option>Jordan</option>
							<option>USA</option>
							<option>lebanon</option>
							<option>Iran</option>
							<option>venzuela</option>
							<option>Egypt</option>
							<option>Tunis</option>
							<option>Morocco</option>
							<option>Syria</option>
							<option>Palestine</option>
							<option>Iraq</option>
							<option>Lybia</option>
						</select>
					</td>
					<td style='width:150px'>
						<select name="StageID" id="StageID" class="form-control">
							<option>lowerlevel</option>
							<option>MiddleSchool</option>
							<option>HighSchool</option>
						</select>
					</td>
					<td style='width:150px'>
						<select name="GradeID" id="GradeID" class="form-control">
							<option>G-02</option>
							<option>G-04</option>
							<option>G-05</option>
							<option>G-06</option>
							<option>G-07</option>
							<option>G-08</option>
							<option>G-09</option>
							<option>G-10</option>
							<option>G-11</option>
							<option>G-12</option>
						</select>
					</td>
					<td style='width:150px'>
						<select name="SectionID" id="SectionID" class="form-control">
							<option>A</option>
							<option>B</option>
							<option>C</option>
						</select>
					</td>
					<td style='width:150px'>
						<select name="Topic" id="Topic" class="form-control">
							<option>IT</option>
							<option>Math</option>
							<option>Arabic</option>
							<option>English</option>
							<option>Quran</option>
							<option>Science</option>
							<option>French</option>
							<option>Spanish</option>
							<option>History</option>
							<option>Biology</option>
							<option>Chemistry</option>
							<option>Geology</option>
						</select>
					</td>
					<td style='width:150px'>
						<select name="Semester" id="Semester" class="form-control">
							<option>F</option>
							<option>S</option>
						</select>
					</td>
					<td style='width:150px'>
						<select name="Relation" id="Relation" class="form-control">
							<option>Father</option>
							<option>Mum</option>
						</select>
					</td>
					<td style='width:150px'>
						<input type="number" id="raisedhands" name="raisedhands" class="form-control"/>
					</td>
					<td style='width:150px'>
						<input type="number" id="VisITedResources" name="VisITedResources" class="form-control"/>
					</td>
					<td style='width:150px'>
						<input type="number" id="AnnouncementsView" name="AnnouncementsView" class="form-control"/>
					</td>
					<td style='width:150px'>
						<input type="number" id="Discussion" name="Discussion" class="form-control"/>
					</td>
					<td style='width:150px'>
						<select id="ParentAnsweringSurvey" name="ParentAnsweringSurvey" class="form-control">
							<option>Yes</option>
							<option>No</option>
						</select>
					</td>
					<td style='width:150px'>
						<select id="ParentschoolSatisfaction" name="ParentschoolSatisfaction" class="form-control">
							<option>Good</option>
							<option>Bad</option>
						</select>
					</td>
					<td style='width:150px'>
						<select id="StudentAbsenceDays" name="StudentAbsenceDays" class="form-control">
							<option>Above-7</option>
							<option>Under-7</option>
						</select>
					</td>
					<td style='width:150px'>
						<input type="text" class="form-control" name="class" id="class" class="form-control"/>
					</td>
				</tr>
			<% } %>
			</table>
		</div>
		<div class="form-group">
			<label for="train">Training Data:</label>
			<input type="file" class="form-control" id="train" name="train"/>
		</div>
		<div class="form-group">
			<label for="test_file">Create a new csv file and select it:</label>
			<input type="file" class="form-control" id="test_file" name="test_file"/>
			<input type="hidden" name="test" id="test" value=""/>
		</div>
		<div class="form-group">
			<p>Due to security reason modern browser does not include the full path to file:</p>
			<p>Please enter the path to the files above:</p>
			<input type="text" class="form-control" id="full_path" name="full_path"/>
		</div>
		<div class="form-group">
			<button type="submit" class="btn btn-success btn-block">Submit</button>
		</div>
	</form>
</div>
<jsp:include page="layout/footer.jsp"/>