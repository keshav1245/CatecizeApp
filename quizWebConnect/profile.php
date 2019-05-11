<?php include('prof_regis.php');?>
<!DOCTYPE html>
<html>
<head>
	<title>Profile</title>
<meta charset="utf-8">
	<title>C@TECHIZE - An Online Quiz portal</title>
	<link href="https://fonts.googleapis.com/css?family=Indie+Flower|Kalam|Patrick+Hand" rel="stylesheet">
	<link rel="stylesheet" type="text/css" href="css/profile.css">
<link rel="stylesheet" type="text/css" href="css/styleindex.css">
<!-- <link rel="stylesheet" type="text/css" href="css/style.css"> -->
</head>
<body>
<div class="container">
		<!-- display validation errors here !-->
<?php
	include('errors.php');
?>
	<div class="row">
		<?php if(!isset($_SESSION['success'])){
			header("location: login.php");
			echo "Enter Login credentials first to login to your profile !";
			die;
		}?>
		<?php if(isset($_SESSION['success'])):?>
			<div class="success">
				<h3 style="color: white;text-align: center;"><?php
					
				?></h3>
			</div>	
		<?php endif?>		
	</div>
	<div class="row">	
		<div class="header">
			<h1>C@TECHIZE - An Online Quiz Portal</h1>
		</div>
	</div>
	<div class="row">
		<div class="nbar">
			<ul>
				<li id="log" style="float: left;"><a href="#">Logo </a></li>
				<li class="active"><a href="profile.php">Home</a></li>
				<li><a href="manage.php">Manage Profile</a></li>
				<li><a href="profile.php?logout=1">Logout </a></li>
				<!-- <li><a href="#about_app">ABOUT </a></li> -->
			</ul>
		</div>
	</div>
	<div class="row">
		<div class="personal">
			<h1><span>Personal Details...</span></h1>
			<div id="col-50-left">
				<p><strong>Username :</strong> <?php echo $_SESSION['username']?></p>
				<p><strong>Name :</strong> <?php echo $_SESSION['name']?></p>
				<p><strong>Email :</strong> <?php echo $_SESSION['email']?></p>
			</div>
			<div id="col-50-right">
				<p><strong>Department :</strong> <?php echo $_SESSION['department']?></p>
				<p><strong>Designation :</strong> <?php echo $_SESSION['designation']?></p>
				<p><strong>Contact :</strong> <?php echo $_SESSION['phone']?></p>
				
			</div>
			</div>
	</div>
	<div class="row">
		<div class="addques">
			<h1><span>Add Questions...</span></h1>
<div class="quesform">
			<form method="POST" action="profile.php">

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
			<label for="question">Question : </label>
		</div>
		<div class="col-70">
			<input type="text" name="question" placeholder="Enter Question">
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
		<input type="submit" value="Upload" name="upload">
	</div>
</form>
</div>	
	<p>Or Import your .csv file OR image file by Clicking <a href="importdata.php">here</a></p>

</div>
</div>
<div class="row">
	<div class="download">
		<h1><span>Download Question Table...</span></h1>
		<div class="dlques">
			<form method="POST" action="profile.php">
				<div class="row">
					<div class="col-30">
						<label for="subject">Subject : </label>
					</div>
					<div class="col-70">
						<select name="subject">
							<option value='C++'>C++</option>
							<option value='DEL'>Digital Electronics</option>
							<option value="CAO">Computer Architecture and Organisation</option>
						</select>
					</div>
				</div>
				<div class="row">
					<input type="submit" name="download" value="Download Questions !">
				</div>
			</form>
		</div>
	</div>
</div>
<div class="row">
	<div class="download2">
		<h1><span>Download Result Table...</span></h1>
		<div class="dlques2">
			<form method="POST" action="profile.php">
				<div class="row">
					<div class="col-30">
						<label for="subject">Subject : </label>
					</div>
					<div class="col-70">
						<select name="subject">
							<option value='2'>C++</option>
							<option value='4'>Digital Electronics</option>
							<option value="">Computer Architecture and Organisation</option>
						</select>
					</div>
				</div>
				<div class="row">
					<input type="submit" name="downloadres" value="Download Result !">
				</div>
			</form>
		</div>
	</div>
</div>
<div class="row">
	<div class="feedback">
		<h1><span>Feedback...</span></h1>
		<div class="fbform">
			
			<form method="POST" action="profile.php">
				<div class="row">
					<div class="col-30">
						<label for="feedback">Feedback : </label>
					</div>
					<div class="col-70">
						<textarea name="feedback" placeholder="Suggestions are Welcome !"></textarea>
					</div>
				</div>
				<div class="row">
					<input type="submit" name="feed" value="Send Feedback">			
				</div>
				</div>
			</form>
		</div>
	</div>
</div>

	<div class="row">
		<div class="footer">
			<div class="col-60">
				<div class="inner">
					<h1>CONTACT DETAILS</h1>
					<p>Email : tangri57@gmail.com</p>
					<p>Phone : +91-9041693272</p>
					<p>Whatsapp : +91-7009957782</p>
					<h5>Copyright : Keshav Tangri</h5>
				</div>
			</div>
			<div class="col-40">
				<div class="inner">
					<h1>Find Me On</h1>
					<ul>
						<li><a target="_blank" href="https://www.linkedin.com/in/keshav-t-7ab782104/"><img src="images/linked.png"></a></li>
						<li><a target="_blank" href="https://www.facebook.com/keshav.tangri.775"><img src="images/fb.png"></a></li>
						<li><a target="_blank" href="https://www.instagram.com/apprentice_fotografo/?hl=en"><img src="images/insta.png"></a></li>
					</ul>
				</div>
			</div>
		</div>
	</div>

</div>

</body>
</html>