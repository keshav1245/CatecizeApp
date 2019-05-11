<?php

	require_once '../includes/operation.php';
	$response =array();

	if($_SERVER['REQUEST_METHOD']=='POST'){

			$db = new operationdb();

			for ( $i = 1; $i <=8; $i++ ){

				$user = $db->getUserByID($i);
				$response[$i] = $user;

			}
			// for ( $i = 9; $i <=15; $i++ ){

			// 	$user = $db->getUserByID($i);
			// 	$response[$i] = $user;

			// }
		// for ( $i = 11; $i <=15; $i++ ){

		// 		$user = $db->getUserByID($i);
		// 		$response[$i] = $user;

		// 	}

			
	}

	echo json_encode($response);