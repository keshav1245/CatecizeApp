<?php 
$response = array();
$con = mysqli_connect("localhost","root","","QUESTION");
$idarray = array();
if(mysqli_connect_errno()){
	echo "Failed to connect with database !";
}else{

	if(isset($_POST['sub'])){
		$sub = $_POST['sub'];
		$roll = $_POST['roll'];
		switch ($sub) {
				case "1":
					$tbl ='cpp_result';
					break;
				case "2":
					$tbl = 'del_result';
					break;
				case '3':
					$tbl = 'cao_result';
					break;

			}
		$sql = "SELECT `roll`, `marks1`FROM $tbl WHERE `roll`= '$roll' ";
		if($resultq = mysqli_query($con,$sql)){
		$row = $resultq->fetch_assoc();
		$response['roll'] = $row['roll'];
		$response['marks'] = $row['marks1']; 
	}else{
		$response['message'] = "Not Attempted Yet !";
	}
		
	}else{
		$response['message'] = "Na bhai nahi chal raha";
	
	}
}

echo json_encode($response);
?>