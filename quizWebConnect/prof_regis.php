<?php
session_start();
$response =array();
	$user = array();
	$count = array();
	$tbl = "";
$con = mysqli_connect("localhost","root","","PROFESSORS");
$conn = mysqli_connect("localhost","root","","QUESTION");

if(isset($_POST['register'])){

if(mysqli_connect_errno()){
$response['message']="FAILED TO CONNECT WITH DATABASE !".mysqli_connect_err();
}else{
	$name =mysqli_real_escape_string($con,$_POST['name']);
	$pass = mysqli_real_escape_string($con,md5($_POST['password']));
	$confirmpass = mysqli_real_escape_string($con,md5($_POST['password2']));
	$email = mysqli_real_escape_string($con,$_POST['email']);
	$department = mysqli_real_escape_string($con,$_POST['department']);
	$designation = mysqli_real_escape_string($con,$_POST['designation']);
	$username =mysqli_real_escape_string($con,$_POST['username']);
	if(empty($name) OR empty($pass)OR empty($email)OR empty($designation)OR empty($department)OR empty($username)){
		array_push($response, "One or more fields are missing !");
	}
	if($pass != $confirmpass){
		array_push($response, "Passwords doesn't match !");
	}
	if(count($response)==0){
	$stmt = $con->prepare("INSERT INTO `prof_details`(`name`, `username`, `email`, `password`, `designation`, `department`, `phone`) VALUES (?,?,?,?,?,?,?)");
	$stmt->bind_param("ssssssi",$name,$username,$email,$pass,$designation,$department,$_POST['phone']);
	if($stmt->execute()){
		// $_SESSION['name'] = $user['name'];
		// $_SESSION['username'] = $user['username'];
		// $_SESSION['email'] = $user['email'];
		// $_SESSION['designation'] = $user['designation'];
		// $_SESSION['department'] = $user['department'];
		// $_SESSION['phone'] = $user['phone'];
		// $_SESSION['success'] = "You are now logged in !";
		// header('location: register.php');
		array_push($response, "User Registration Successful !");
	}else{
		array_push($response, "User Registration Unsuccessful !");
	}
}
}
}
	//login page login
	if(isset($_POST['login'])){
	$password = mysqli_real_escape_string($con,md5($_POST['password']));
	$username =mysqli_real_escape_string($con,$_POST['username']);
	if(empty($_POST['password'])OR empty($username)){
		array_push($response, "One or more fields are missing !");
	}
	if(count($response)==0){
		// $con = mysqli_connect("localhost","root","","PROFESSORS");
	$stmt=$con->prepare("SELECT * FROM prof_details WHERE username = ? AND password = ?");
	$stmt->bind_param("ss",$username,$password);
	$stmt->execute();
	if($stmt->execute()){
	$user = $stmt->get_result()->fetch_assoc();
	// $results = mysqli_query($con,$query);
	if( ($user['password'])==$password) {
		$stmt->close();
		$_SESSION['name'] = $user['name'];
		$_SESSION['username'] = $user['username'];
		$_SESSION['email'] = $user['email'];
		$_SESSION['designation'] = $user['designation'];
		$_SESSION['department'] = $user['department'];
		$_SESSION['phone'] = $user['phone'];
		$_SESSION['success'] = "You are now logged in !";
		header('location: profile.php');
	}else{
		array_push($response, "Username or Password is incorrect !");
	}
	}
	}
}

	if (isset($_POST['upload'])) {
		
		if(mysqli_connect_errno()){
			array_push($response, "Connection to Database Failed :(");
		}else{
		$tblname = $_POST['subject'];
		$ques = mysqli_real_escape_string($conn,$_POST['question']);
		$option1 = mysqli_real_escape_string($conn,$_POST['option1']);
		$option2 = mysqli_real_escape_string($conn,$_POST['option2']);
		$option3 = mysqli_real_escape_string($conn,$_POST['option3']);
		$option4 = mysqli_real_escape_string($conn,$_POST['option4']);
		$correct = mysqli_real_escape_string($conn,$_POST['correct']);

		if(empty($ques)OR empty($option1)OR empty($option2)OR empty($option3)OR empty($option4) OR empty($correct)){
			array_push($response, "One or more fields Missing :(");
		}else{
			
			// switch ($tblname) {
			// 	case "1":
			// 		$tbl ='c_questions';
			// 		break;
			// 	case "2":
			// 		$tbl ='cpp_questions';
			// 		break;
			// 	case "3":
			// 		$tbl = 'ds_questions';
			// 		break;
			// 	case "4":
			// 		$tbl = 'dis_questions';
			// 		break;

			// }
			$stmt = $conn->prepare("INSERT INTO `allques`( `question`, `option1`, `option2`, `option3`, `option4`, `correct`, `subject`) VALUES (?,?,?,?,?,?,?)");

				$stmt->bind_param("sssssis",$ques,$option1,$option2,$option3,$option4,$correct,$tblname);
			
			// $stmt->execute();
			if($stmt->execute()){
				array_push($response, "KHUSH HO JAA :D");
			}else{
				array_push($response, "ITNI JALDI HAI KHUSH ONE KI :(");
			}
			
		}
		}
	}
	if(isset($_POST['feed'])){
		$connfeed = mysqli_connect("localhost","root","","FEEDBACK");	
		if(mysqli_connect_errno()){
			$response['message']="FAILED TO CONNECT WITH DATABASE !".mysqli_connect_err();
		}else{
			$message = mysqli_real_escape_string($connfeed,$_POST['feedback']);
			if (empty($message)) {
				array_push($response, "Please Enter something before Submitting !");
			}else{
			$stmt =$connfeed->prepare("INSERT INTO `feedback`(`feedback`, `prof_name`) VALUES (?,?)");
			$stmt->bind_param("ss",$message,$_SESSION['name']);
			if($stmt->execute()){
				array_push($response, "Thank You for your valuable Feedback :D");
			}else{
				array_push($response, "Some error occured. Check your network and try again !");
			}	
			}
			
		}
	}

	if (isset($_POST['download'])) {
		if (mysqli_connect_errno()) {
			array_push($response, "FAILED TO CONNECT WITH DATABASE !".mysqli_connect_err());
		}else{
			$tblname = $_POST['subject'];
			// switch ($tblname) {
			// 	case "1":
			// 		$tbl ='c_questions';
			// 		break;
			// 	case "2":
			// 		$tbl ='cpp_questions';
			// 		break;
			// 	case "3":
			// 		$tbl = 'ds_questions';
			// 		break;
			// 	case "4":
			// 		$tbl = 'dis_questions';
			// 		break;

			// }

			header('Content-Type: text/csv; charset=utf-8');  
      		header('Content-Disposition: attachment; filename=data.csv');  
      		$output = fopen("php://output", "w");  
      		fputcsv($output, array('question', 'option1', 'option2', 'option3','option4','correct'));
			$query = "SELECT `question`, `option1`, `option2`, `option3`, `option4`, `correct`FROM `allques` WHERE `subject` = '$tblname'";  
      		$result = mysqli_query($conn, $query);  
		    while($row = mysqli_fetch_assoc($result))  
		      {  
		           fputcsv($output, $row);  
		      }  
		    fclose($output);       		



		}
	}
	if (isset($_POST['downloadres'])) {
		if (mysqli_connect_errno()) {
			array_push($response, "FAILED TO CONNECT WITH DATABASE !".mysqli_connect_err());
		}else{
			$tblname = $_POST['subject'];
			switch ($tblname) {
				case "1":
					$tbl ='c_result';
					break;
				case "2":
					$tbl ='cpp_result';
					break;
				case "3":
					$tbl = 'ds_result';
					break;
				case "4":
					$tbl = 'del_result';
					break;
				case "5":
					$tbl = 'cao_result';

			}
			header('Content-Type: text/csv; charset=utf-8');  
      		header('Content-Disposition: attachment; filename=data.csv');  
      		$output = fopen("php://output", "w");  
      		fputcsv($output, array('roll', 'name', 'username', 'email', 'branch','marks1','marks2'));
			$query = "SELECT * from $tbl";  
      		$result = mysqli_query($conn, $query);  
		    while($row = mysqli_fetch_assoc($result))  
		      {  
		           fputcsv($output, $row);  
		      }  
		    fclose($output);       		



		}
	}
	if(isset($_POST['pass_chg'])){
	if(mysqli_connect_errno()){
	array_push($response, "Connect with Database Failed !");
}else{
	if(empty($_POST['old'])OR empty($_POST['new'])OR empty($_POST['connew'])){
		array_push($response, "One or more fields are Missing :(");
	}else{
		$old= md5(mysqli_real_escape_string($con,$_POST['old']));
		$new= md5(mysqli_real_escape_string($con,$_POST['new']));
		$conf= md5(mysqli_real_escape_string($con,$_POST['connew']));
		$user = $_SESSION['username'];
		if($new != $conf){
			array_push($response, "Passwords doesnt match !");
		}else{

		$stmt = $con->prepare("UPDATE `prof_details` SET `password`= ? WHERE username = ? AND password = ?");
		$stmt->bind_param("sss",$new,$user,$old);
		if($stmt->execute()){
			echo "<script>
			alert('Password change successful !');
			window.location.href='profile.php?logout=1';
			</script>";
		}else{
			array_push($response, "Some error Occured !");
		}
		}
	}
}
}
if(isset($_POST['phone_chg'])){
	if(mysqli_connect_errno()){
	array_push($response, "Connect with Database Failed !");
}else{
	if(empty($_POST['newu'])OR empty($_POST['connewu'])){
		array_push($response, "One or more fields are Missing :(");
	}else{
		$new= (mysqli_real_escape_string($con,$_POST['newu']));
		$conf= (mysqli_real_escape_string($con,$_POST['connewu']));
		$pass = $_SESSION['password'];
		$user = $_SESSION['username'];
		if($new != $conf){
			array_push($response, "Contacts doesnt match !");
		}else{

		$stmt = $con->prepare("UPDATE `prof_details` SET `phone`= ? WHERE username = ?");
		$stmt->bind_param("ss",$new,$user);
		if($stmt->execute()){
			echo "<script>
			alert('Contact change successful !');
			window.location.href='profile.php?logout=1';
			</script>";
		}else{
			array_push($response, "Some error Occured !");
		}
		}
	}
}
}
	

	if (isset($_POST['import'])) {
		if(mysqli_connect_errno()){
			array_push($response, "Connection with Database Failed !");
		}else{
			$tblname = $_POST['subject'];
			// switch ($tblname) {
			// 	case "1":
			// 		$tbl ='c_questions';
			// 		break;
			// 	case "2":
			// 		$tbl ='cpp_questions';
			// 		break;
			// 	case "3":
			// 		$tbl = 'ds_questions';
			// 		break;
			// 	case "4":
			// 		$tbl = 'dis_questions';
			// 		break;

			// }
			if ($_FILES['file']['name']) {
				
				$filename = explode('.',$_FILES['file']['name']);
				if($filename[1]=='csv'){
				$handle = fopen($_FILES['file']['tmp_name'],"r");
				while (($column = fgetcsv($handle))) {
				$question = mysqli_real_escape_string($conn,$column[1]);
				$option1 = mysqli_real_escape_string($conn,$column[3]);
				$option2 = mysqli_real_escape_string($conn,$column[4]);
				$option3 = mysqli_real_escape_string($conn,$column[5]);
				$option4 = mysqli_real_escape_string($conn,$column[6]);
				$correct = $column[7];

				$sqlimsert = "INSERT INTO `allques`(`question`, `option1`, `option2`, `option3`, `option4`, `correct`, `subject`) VALUES ('$question','$option1','$option2','$option3','$option4','$correct','$tblname')";
				$result = mysqli_query($conn,$sqlimsert);
					
				}
				if (! empty($result)) {
		                echo "<script>
			alert('CSV Data Imported into the Database');
			window.location.href='#';
			</script>";
		             
		            } else {
		            	
		                array_push($response,"Unable to import data into the Database");
		            	
		            }
				fclose($handle);
			}
		}
		}
		}



		if (isset($_POST['imageupload'])) {
			if(mysqli_connect_errno()){
				array_push($response, "Connection with DB Failed !");
			}else{
				$subject = $_POST['subject'];
				$option1 = $_POST['option1'];
				$option2 = $_POST['option2'];
				$option3 = $_POST['option3'];
				$option4 = $_POST['option4'];
				$correct = $_POST['correct'];
				$filename = $_FILES['question']['name'];
				$filetmpname = $_FILES['question']['tmp_name'];
				$folder = 'quesimages/';
				move_uploaded_file($filetmpname,$folder.$filename);

				$stmt = $conn->prepare("INSERT INTO `allques`(`imagequestion`, `option1`, `option2`, `option3`, `option4`, `correct`, `subject`) VALUES (?,?,?,?,?,?,?)");
				$stmt->bind_param("sssssis",$filename,$option1,$option2,$option3,$option4,$correct,$subject);


				if($stmt->execute()){
					 echo "<script>
					alert('Image Data Uploaded into the Database');
					window.location.href='importdata.php';
					</script>";	
				}else{
					 echo "<script>
			alert('Unable to upload into the Database');
			window.location.href='importdata.php';
			</script>";
				}
					
			
			}
			}
				


		//logging out script
  	if(isset($_GET['logout'])){
  		session_destroy();
  		unset($_SESSION['username']);
  		header('location: login.php');	
  	}

?>
