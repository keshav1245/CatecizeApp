<?php
$response = array();
$con = mysqli_connect("localhost","root","","QUESTION");
$idarray = array();
if(mysqli_connect_errno()){
	echo "FAILED TO CONNECT WITH DATABASE !".mysqli_connect_err();
}else{
	
// $randomNumber = range(1,15); 
// 	shuffle($randomNumber);
	if(isset($_POST['sub']) ){
		$sub = $_POST['sub'];
		$sql = "SELECT `id` FROM `allques` WHERE `subject`= '$sub'";
		$resultq = mysqli_query($con,$sql);
		while ($row = $resultq->fetch_assoc()) {
			array_push($idarray, $row['id']);
		}
		shuffle($idarray);
		// switch ($sub) {
		// 	case '1':
		// 		$tbl = 'c_questions';
		// 		break;
		// 	case '2':
		// 		$tbl ='cpp_questions';
		// 		break;
		// 	case '3':
		// 		$tbl = 'dis_questions';
		// 		break;
		// 	case '4':
		// 		$tbl = 'ds_questions';
		// 		break;
				
		// }


	for ( $i = 1; $i <=10; $i++ ){

	$stmt = $con->prepare("SELECT `question`,`imagequestion`, `option1`, `option2`, `option3`, `option4`, `correct` FROM `allques`WHERE id = ?");
	$stmt->bind_param("i",$idarray[$i-1]);
	$stmt->execute();
	$result = $stmt->get_result();
	$response[$i] = $result->fetch_assoc();
	//echo json_encode($response[$i]);
	}
}else{
	$err['message'] = "Na bhai nahi chal raha";
	echo json_encode($err);
}
}
echo json_encode($response);