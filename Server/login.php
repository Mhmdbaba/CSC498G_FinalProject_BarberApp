<?php

include("db_info.php");

/*
$username = $_POST["username"];
$password = $_POST["password"];
*/

$username = "mhmdbaba";

$str = 'SELECT id, username FROM users WHERE username = "' . $username . '"';

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
    session_start();
    $_SESSION["username"] = $username;
    $_SESSION["id"] = $response[0]['id'];

    echo "true";
}

?>