<?php

include("db_info.php");

//getting variables using POST
$username = $_POST["username"];
$password = $_POST["password"];
$password = hash("sha256", $password); //hashing the password

//query
$str = 'SELECT id, username FROM users WHERE username = "' . $username . '" and password = "' . '"' . $password . '"';

$query = $conn->prepare($str);
$query->execute();

$array = $query->get_result();

$response = [];

while ($arr = $array->fetch_assoc()){
    $response[] = $arr;
}

$isEmpty = empty($response);
if ($isEmpty){
    echo "false"; //username and password are incorrect
}
else{
    session_start();
    $_SESSION["username"] = $username;
    $_SESSION["id"] = $response[0]['id'];

    echo "true"; //username and password found in database
}
?>