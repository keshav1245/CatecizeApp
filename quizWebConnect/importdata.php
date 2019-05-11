<?php include('prof_regis.php');?>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>Import csv</title>
	<link href="https://fonts.googleapis.com/css?family=Indie+Flower|Kalam|Patrick+Hand" rel="stylesheet">
	<link href="css/styleindex.css?v=<?php echo time(); ?>" rel="stylesheet" type="text/css" />
	
	<link rel="stylesheet" type="text/css" href="css/styleindex.css">
	<link href="css/importdata.css?v=<?php echo time(); ?>" rel="stylesheet" type="text/css" />
	
	<link rel="stylesheet" type="text/css" href="css/importdata.css">
</head>
<body>
	<div class="container">
		<?php
	include('errors.php');
?>
		<div class="row">	
			<div class="header">
				<h1>C@TECHIZE - An Online Quiz Portal</h1>
			</div>
		</div>
		<div class="row">
			<div class="nbar">
				<ul>
					<li id="log" style="float: left;"><a href="#">Logo </a></li>
					<li><a href="profile.php">Home</a></li>
					<li><a href="manage.php">Manage Profile</a></li>
					<li><a href="profile.php?logout=1">Logout </a></li>
					<!-- <li><a href="#about_app">ABOUT </a></li> -->
				</ul>
			</div>
		</div>
		<div class="row">
		<div class="importques">
			<h1><span>Import Questions...</span></h1>
<div class="importform">
			<form enctype='multipart/form-data' method="POST" action="importdata.php">

	<div class="row">
		<div class="col-30">
			<label for="subject">Subject : </label>
		</div>
		<div class="col-70">
			<select name="subject">
				<option value='C++'>C++</option>
				<option value="DEL">Digital Electronics</option>
				<option value="CAO">Computer Architecture and Organisation</option>
			</select>
		</div>
	</div>
	<div class="row">
		<div class="col-30">
			<label for="file">Choose File : </label>
		</div>
		<div class="col-70">
			<input type="file" id="file" name="file" accept=".csv" placeholder="Choose file">
		</div>
	</div>
	<div class="row">
		<input type="submit" value="Import" name="import">
	</div>
</form>
</div>	
</div>
</div>
<div class="row">
	<div class="addimgques">
		<h1><span>Add Image Question...</span></h1>
		<div class="imgquesform">
			<form enctype="multipart/form-data" method="POST" action="importdata.php">
				<div class="row">
		<div class="col-30">
			<label for="subject">Subject : </label>
		</div>
		<div class="col-70">
			<select name="subject">
				<option value='C++'>C++</option>
				<option value="DEL">Digital Electronics</option>
				<option value="CAO">Computer Architecture and Organisation</option>
			</select>
		</div>
	</div>
	<div class="row">
		<div class="col-30">
			<label for="question">Question Image : </label>
		</div>
		<div class="col-70">
			<input type="file" id="imgfile" name="question" placeholder="Enter Question">
		</div>
	</div>
	<div class="row">
		<div class="col-30">
			<label for="option1">Option 1 : </label>
		</div>
		<div class="col-70">
			<input type="text" name="option1" placeholder="Enter Option 1">
		</div>
	</div>
	<div class="row">
		<div class="col-30">
			<label for="option2">Option 2 : </label>
		</div>
		<div class="col-70">
			<input type="text" name="option2" placeholder="Enter option 2">
		</div>
	</div>
	<div class="row">
		<div class="col-30">
			<label for="option3">Option 3 : </label>
		</div>
		<div class="col-70">
			<input type="text" name="option3" placeholder="Enter option 3">
		</div>
	</div>
	<div class="row">
		<div class="col-30">
			<label for="option4">Option 4 : </label>
		</div>
		<div class="col-70">
			<input type="text" name="option4" placeholder="Enter option 4">
		</div>
	</div>
	<div class="row">
		<div class="col-30">
			<label for="correct">Correct option : </label>
		</div>
		<div class="col-70">
			<input type="text" name="correct" placeholder="Enter Correct option">
		</div>
	</div>
	<div class="row">
		<input type="submit" value="Upload" name="imageupload">
	</div>
			</form>
		</div>
	</div>
</div>
	</div>


	
</body>
</html>