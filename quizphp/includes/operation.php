<?php

	/**
	 * 
	 */
	class operationdb
	{
		private $con;
		
		function __construct()
		{
			# code...
			require_once dirname(__FILE__).'/connectionwithdb.php';
			$db = new connectiondb();
			$this->con = $db->connect();
		}


		public function getUserByID($id){
			$stmt = $this->con->prepare("SELECT `question`, `option1`, `option2`, `option3`, `option4`, `correct` FROM `c_questions` WHERE id = ?");
			$stmt->bind_param("i",$id);
			$stmt->execute();
			
			return $stmt->get_result()->fetch_assoc();
		}


	}





	