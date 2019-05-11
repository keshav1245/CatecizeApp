<?php include('prof_regis.php');?>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>Manage -Profile	</title>
	<link href="https://fonts.googleapis.com/css?family=Indie+Flower|Kalam|Patrick+Hand" rel="stylesheet">
	<link rel="stylesheet" type="text/css" href="css/styleindex.css">
	<link rel="stylesheet" type="text/css" href="css/manage.css">
</head>
<body>
<div class="container">
			<!--  display validation errors here ! -->
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
				<li class="active"><a href="manage.php">Manage Profile</a></li>
				<li><a href="profile.php?logout='1">Logout </a></li>
				<!-- <li><a href="#about_app">ABOUT </a></li> -->
			</ul>
		</div>
	</div>
	<div class="row">
		<div class="passchange">
			<h1><span>Change Password</span></h1>
			<div class="passform">
				<form method="POST" action="manage.php">
					<div class="row">
						<div class="col-30">
							<label for="old">Enter Old Password : </label>	
						</div>
						<div class="col-70">
							<input type="password" name="old" placeholder="Enter Old Password">
						</div>
					</div>
					<div class="row">
						<div class="col-30">
							<label for="new">Enter New Password : </label>	
						</div>
						<div class="col-70">
							<input type="password" name="new" placeholder="Enter New Password">
						</div>
					</div>
					<div class="row">
						<div class="col-30">
							<label for="connew">Confirm Old Password : </label>	
						</div>
						<div class="col-70">
							<input type="password" name="connew" placeholder="Confirm New Password">
						</div>
					</div>
					<div class="row">
						<input type="submit" name="pass_chg" value="Update Password">
					</div>
				</form>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="userchange">
			<h1><span>Change Phone</span></h1>
			<div class="userform">
				<form method="POST" action="manage.php">
					
					<div class="row">
						<div class="col-30">
							<label for="newu">Enter New Contact : </label>	
						</div>
						<div class="col-70">
							<input type="int" name="newu" placeholder="Enter New Contact">
						</div>
					</div>
					<div class="row">
						<div class="col-30">
							<label for="connewu">Confirm New Conttact : </label>	
						</div>
						<div class="col-70">
							<input type="int" name="connewu" placeholder="Confirm New Contact">
						</div>
					</div>
					<div class="row">
						<input type="submit" name="phone_chg" value="Update Contact">
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