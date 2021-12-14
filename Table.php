<?php

$con = mysqli_connect('localhost', 'stock5424', 'hs5424!!', 'stock5424');

$result = mysqli_query($con, "SELECT * FROM LIST;");
$response = array();
$result2 = mysqli_query($con, "SELECT * FROM SELL;");
$response2 = array();
while($row2 = mysqli_fetch_array($result2)){
array_push($response2, array("productNum2"=>$row2[0], "productName2"=>$row2[1], "productCount2"=>(int)$row2[2], "productDate2"=>$row2[3]));
}
while($row = mysqli_fetch_array($result)){
//response["userID"]=$row[0] ....이런식으로 됨.
array_push($response, array("productNum"=>$row[0], "productName"=>$row[1], "productCount"=>(int)$row[2], "productDate"=>$row[3]));
$value = $row[2] - $row2[2];
	if($value<0){//판매할 개수가 더많다는 뜻
		mysqli_close($con);
	}else{}
	array_pop($response);
	array_pop($response);
	array_pop($response);
	array_pop($response);
	array_pop($response);
	array_pop($response);
	array_pop($response);//다시 값을 입력시키기 위해 배열을 다 지워준다
	array_push($response, array("productNum"=>$row[0], "productName"=>$row[1], "productCount"=>$value, "productDate"=>$row[3]));
}
echo json_encode(array("response"=>$response));
mysqli_close($con);
?>