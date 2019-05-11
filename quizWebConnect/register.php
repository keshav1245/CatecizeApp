<?php include('prof_regis.php');?>
<!DOCTYPE html>
<html>
<head>
	<title>Register Account</title>
	<link rel="stylesheet" type="text/css" href="css/style.css">
	<link href="https://fonts.googleapis.com/css?family=Indie+Flower|Kalam|Patrick+Hand" rel="stylesheet">
</head>
<body>
	<div class="row">	
		<div class="header">
			<h1>C@TECHIZE - An Online Quiz Portal</h1>
		</div>
	</div>
	<div class="row">
		<div class="nbar">
			<ul>
				<li id="log" style="float: left;"><a href="#">LOGO </a></li>
				<li><a href="index.html">HOME </a></li>
				<li class="active"><a href="register.php">REGISTER </a></li>
				<li><a href="login.php">LOGIN </a></li>
				<li><a href="index.html#about_app">ABOUT </a></li>
			</ul>
		</div>
	</div>
<div class="container">
	
<form method="POST" action="register.php" id="frm">
	<!-- display validation errors here !-->
<?php
	include('errors.php');
?>
	<div class="row">
		<div class="col-25">
			<label for="name">Name : </label>
		</div>
		<div class="col-75">
			<input type="text" name="name" id="name" placeholder="Your Name !">
		</div>
	</div>
	<div class="row">
		<div class="col-25">
			<label for="username">Username : </label>
		</div>
		<div class="col-75">
			<input type="text" name="username" id="username" placeholder="Enter Username !">
		</div>
	</div>
	<div class="row">
		<div class="col-25">
			<label for="email">Email : </label>
		</div>
		<div class="col-75">
			<input type="email" name="email" placeholder="Your Email !">
		</div>
	</div>
	<div class="row">
		<div class="col-25">
			<label for="password">Password : </label>
		</div>
		<div class="col-75">
			<input type="password" name="password" id="password" placeholder="Your Password !">
		</div>
	</div>
	<div class="row">
		<div class="col-25">
			<label for="password">Confirm Password : </label>
		</div>
		<div class="col-75">
			<input type="password" name="password2" id="password2" placeholder="Confirm Your Password !">
		</div>
	</div>
	<div class="row">
		<div class="col-25">
			<label for="designation">Designation : </label>
		</div>
		<div class="col-75">
			<input type="text" name="designation" id="designation" placeholder="Your Designation !">
		</div>
	</div>
	<div class="row">
		<div class="col-25">
			<label for="department">Department : </label>
		</div>
		<div class="col-75">
			<input type="text" name="department" id="department" placeholder="Department !">
		</div>
	</div>
	<div class="row">
		<div class="col-25">
			<label for="phone">Contact : </label>
		</div>
		<div class="col-75">
			<input type="int" name="phone" id="phone" placeholder="Your Phone Number...">
		</div>
	</div>
	<div class="row">
		<input type="submit" value="Register" name="register">
		<p style="text-align: left;">Already  Registered :<a href="login.php">Sign In</a>></p>
	</div>
</form>
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


</body>
</html>