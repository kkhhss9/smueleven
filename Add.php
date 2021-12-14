<?php

   $con = mysqli_connect('localhost', 'stock5424', 'hs5424!!', 'stock5424');
   if (!$con)
  {
     echo "MySQL 접속 에러 : ";
     echo mysqli_connect_error();
     exit();
  }

   //post방식으로 데이터를 받는다.
	$productNum = $_POST["productNum"];
	$productName = $_POST["productName"];
	$productCount = $_POST["productCount"];
	$productDate = $_POST["productDate"];
    //데이터베이스에 값을 넣는부분
    $statement = mysqli_prepare($con, "INSERT INTO LIST VALUES(?, ?, ?, ?)");
    mysqli_stmt_bind_param($statement, "ssis", $productNum, $productName, $productCount, $productDate);
    mysqli_stmt_execute($statement);
    //배열 선언후
    $response = array();
    $response["success"] = true;//success라는 인덱스에 true값을 넣어줌

    echo json_encode($response);//JSON형식으로 출력

  ?>