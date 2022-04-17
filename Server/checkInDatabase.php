<?php

include("db_info.php");

/*
$username = $_POST["username"];
$password = $_POST["password"];
*/

$str = 'SELECT username FROM users WHERE username = "' + $username + '"';

$query = $conn->prepare($str);
$query->execute();

$array = $query->get_result();

$response = [];

while ($arr = $array->fetch_assoc()){
    $response[] = $arr;
}

$isEmpty = empty($response);
if ($isEmpty){
    echo "false";
}
else{
    echo "true";
}

?>