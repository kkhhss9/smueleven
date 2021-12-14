<?php
    $con = mysqli_connect('localhost', 'stock5424', 'hs5424!!', 'stock5424');

     $userID = $_POST["userID"];//POST로 값 받기 editText와 연동
     $userPassword = $_POST["userPassword"];

     $statement = mysqli_prepare($con, "SELECT * FROM USER WHERE userID = ? AND userPassword = ?");//register한 id,password 찾기
     mysqli_stmt_bind_param($statement, "ss", $userID, $userPassword);
     mysqli_stmt_execute($statement);
     mysqli_stmt_store_result($statement);
     mysqli_stmt_bind_result($statement, $userID, $userPassword, $userName, $userAge);
     $response = array();
     $response["success"] = true;

     while(mysqli_stmt_fetch($statement)){
      $response["success"] = true;//success라는 인덱스에 true값을 넣어줌
      $response["userID"] = $userID;
      $response["userPassword"] = $userPassword;
      $response["userName"] = $userName;
      $response["userAge"] = $userAge;
     }

     echo json_encode($response);
?>