<?php
$response =array();

if(isset($_POST['name'])and isset($_POST['email']) and isset($_POST['roll'])
		and isset($_POST['branch']) and isset($_POST['username']) and isset($_POST['marks1'])){
	$con = mysqli_connect("localhost","root","","QUESTION");
	if(mysqli_connect_errno()){

		$response['message']="FAILED TO CONNECT WITH DATABASE !".mysqli_connect_err();
	}else{

		$sub = (int)$_POST['sub'];
		switch ($sub) {
			case '1':
				$tbl = 'cpp_result';
				break;
			case '2':
				$tbl ='del_result';
				break;
			case '3':
				$tbl ='cao_result';
				break;
		}
		$stmt = $con->prepare("INSERT INTO `$tbl`(`roll`, `name`, `username`, `email`, `branch`, `marks1`) VALUES (?,?,?,?,?,?);");
		$stmt->bind_param("sssssd",$_POST['roll'],$_POST['name'],$_POST['username'],$_POST['email'],$_POST['branch'],$_POST['marks1']);
		if($stmt->execute()){
		$response['error']=false;
		$response['message']="HAVE A GOOD DAY !";

		}else{
			$response['error']=true;
				$response['message']="SOME ERROR OCCURED !";
		}

	}

	}

	echo json_encode($response);