<jsp:include page="layout/header.jsp"/>
	<div class="container">
		<div class="jumbotron">
			<a href='index.jsp'>
				<h1>UTB Data Analytic</h1>
			</a>
		</div>
		<form action="ServletInstancePrediction" method="POST">
		
			<div class="form-group">
				<label for="gender">Gender:</label>
				<select name="gender" id="gender" id="form-control">
					<option>M</option>
					<option>F</option>
				</select>
			</div>
			<div class="form-group">
				<label for="NationalITy">Nationality:</label>
				<select name="NationalITy" id="NationalITy" id="form-control">
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
			</div>
			<div class="form-group">
				<label for="PlaceofBirth">Place of Birth:</label>
				<select name="PlaceofBirth" id="PlaceofBirth" id="form-control">
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
			</div>
			<div class="form-group">
				<label for="StageID">Stage ID:</label>
				<select name="StageID" id="StageID" id="form-control">
					<option>lowerlevel</option>
					<option>MiddleSchool</option>
					<option>HighSchool</option>
				</select>
			</div>
			<div class="form-group">
				<label for="GradeID">Grade ID:</label>
				<select name="GradeID" id="GradeID" id="form-control">
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
			</div>
			<div class="form-group">
				<label for="SectionID">Section ID:</label>
				<select name="SectionID" id="SectionID" id="form-control">
					<option>A</option>
					<option>B</option>
					<option>C</option>
				</select>
			</div>
			<div class="form-group">
				<label for="Topic">Topic:</label>
				<select name="Topic" id="Topic" id="form-control">
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
			</div>
			<div class="form-group">
				<label for="Semester">Semester:</label>
				<select name="Semester" id="Semester" id="form-control">
					<option>F</option>
					<option>S</option>
				</select>
			</div>
			<div class="form-group">
				<label for="Relation">Relation:</label>
				<select name="Relation" id="Relation" id="form-control">
					<option>Father</option>
					<option>Mum</option>
				</select>
			</div>
			<div class="form-group">
				<label for="raisehands">Raise Hands:</label>
				<input type="number" id="raisedhands" name="raisedhands"/>
			</div>
			<div class="form-group">
				<label for="VisITedResources">Visited Resources:</label>
				<input type="number" id="VisITedResources" name="VisITedResources"/>
			</div>
			<div class="form-group">
				<label for="AnnouncementsView">Announcements View:</label>
				<input type="number" id="AnnouncementsView" name="AnnouncementsView"/>
			</div>
			<div class="form-group">
				<label for="Discussion">Discussion:</label>
				<input type="number" id="Discussion" name="Discussion"/>
			</div>
			<div class="form-group">
				<label for="ParentAnsweringSurvey">Parent Answering Survey:</label>
				<select id="ParentAnsweringSurvey" name="ParentAnsweringSurvey">
					<option>Yes</option>
					<option>No</option>
				</select>
			</div>
			<div class="form-group">
				<label for="ParentschoolSatisfaction">Parent School Satisfaction:</label>
				<select id="ParentschoolSatisfaction" name="ParentschoolSatisfaction">
					<option>Good</option>
					<option>Bad</option>
				</select>
			</div>
			<div class="form-group">
				<label for="StudentAbsenceDays">StudentAbsenceDays:</label>
				<select id="StudentAbsenceDays" name="StudentAbsenceDays">
					<option>Above-7</option>
					<option>Under-7</option>
				</select>
			</div>
			<div class="form-group">
				<label for="train">Training Data:</label>
				<input type="file" class="form-control" id="train" name="train"/>
			</div>
			<button type="submit" class="btn btn-default">Submit</button>
		</form>
	</div>
<jsp:include page="layout/footer.jsp"/>