<jsp:include page="../layout/header.jsp"/>
		<form action="../../ServletInstancePrediction" method="POST">
		
			<div class="form-group">
				<label for="gender">Gender:</label>
				<select name="gender" id="gender" class="form-control">
					<option>M</option>
					<option>F</option>
				</select>
			</div>
			<div class="form-group">
				<label for="NationalITy">Nationality:</label>
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
			</div>
			<div class="form-group">
				<label for="PlaceofBirth">Place of Birth:</label>
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
			</div>
			<div class="form-group">
				<label for="StageID">Stage ID:</label>
				<select name="StageID" id="StageID" class="form-control">
					<option>lowerlevel</option>
					<option>MiddleSchool</option>
					<option>HighSchool</option>
				</select>
			</div>
			<div class="form-group">
				<label for="GradeID">Grade ID:</label>
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
			</div>
			<div class="form-group">
				<label for="SectionID">Section ID:</label>
				<select name="SectionID" id="SectionID" class="form-control">
					<option>A</option>
					<option>B</option>
					<option>C</option>
				</select>
			</div>
			<div class="form-group">
				<label for="Topic">Topic:</label>
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
			</div>
			<div class="form-group">
				<label for="Semester">Semester:</label>
				<select name="Semester" id="Semester" class="form-control">
					<option>F</option>
					<option>S</option>
				</select>
			</div>
			<div class="form-group">
				<label for="Relation">Relation:</label>
				<select name="Relation" id="Relation" class="form-control">
					<option>Father</option>
					<option>Mum</option>
				</select>
			</div>
			<div class="form-group">
				<label for="raisehands">Raise Hands:</label>
				<input type="number" id="raisedhands" name="raisedhands" class="form-control"/>
			</div>
			<div class="form-group">
				<label for="VisITedResources">Visited Resources:</label>
				<input type="number" id="VisITedResources" name="VisITedResources" class="form-control"/>
			</div>
			<div class="form-group">
				<label for="AnnouncementsView">Announcements View:</label>
				<input type="number" id="AnnouncementsView" name="AnnouncementsView" class="form-control"/>
			</div>
			<div class="form-group">
				<label for="Discussion">Discussion:</label>
				<input type="number" id="Discussion" name="Discussion" class="form-control"/>
			</div>
			<div class="form-group">
				<label for="ParentAnsweringSurvey">Parent Answering Survey:</label>
				<select id="ParentAnsweringSurvey" name="ParentAnsweringSurvey" class="form-control">
					<option>Yes</option>
					<option>No</option>
				</select>
			</div>
			<div class="form-group">
				<label for="ParentschoolSatisfaction">Parent School Satisfaction:</label>
				<select id="ParentschoolSatisfaction" name="ParentschoolSatisfaction" class="form-control">
					<option>Good</option>
					<option>Bad</option>
				</select>
			</div>
			<div class="form-group">
				<label for="StudentAbsenceDays">StudentAbsenceDays:</label>
				<select id="StudentAbsenceDays" name="StudentAbsenceDays" class="form-control">
					<option>Above-7</option>
					<option>Under-7</option>
				</select>
			</div>
			<div class="form-group">
				<label for="train">Training Data:</label>
				<input type="file" class="form-control" id="train" name="train"/>
			</div>
			<button type="submit" class="btn btn-success btn-block">Submit</button>
		</form>
<jsp:include page="../layout/footer.jsp"/>