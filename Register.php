<?php

   $con = mysqli_connect('localhost', 'stock5424', 'hs5424!!', 'stock5424');
   if (!$con)
  {
     echo "MySQL 접속 에러 : ";
     echo mysqli_connect_error();
     exit();
  }

   //post방식으로 데이터를 받는다.
    $userID = $_POST["userID"];
    $userPassword = $_POST["userPassword"];
    $userName = $_POST["userName"];
    $userAge = $_POST["userAge"];

    //데이터베이스에 값을 넣는부분
    $statement = mysqli_prepare($con, "INSERT INTO USER VALUES(?, ?, ?, ?)");
    mysqli_stmt_bind_param($statement, "sssi", $userID, $userPassword, $userName, $userAge);//상품개수는 int값이므로 i 나머지는 String형식이므로 s로 받아준다
    mysqli_stmt_execute($statement);

    //배열 선언후
    $response = array();
    $response["success"] = true;//success라는 인덱스에 true값을 넣어줌

    echo json_encode($response);//JSON형식으로 출력

  ?>