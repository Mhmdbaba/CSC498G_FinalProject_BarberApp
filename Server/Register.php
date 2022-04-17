<?php

include("db_info.php");

//getting values from POST request
$username = $_POST["username"];
$name = $_POST["name"];
$email = $_POST["email"];
$password = $_POST["password"];
$password = hash("sha256", $password); //hashing the password

//check if suername exists in database
$str = 'SELECT FROM users username where username = "' . $username . '"';
$query = $conn->prepare($str);

$array = $query->get_result();

$response = [];

while ($arr = $array->fetch_assoc()){
    $response[] = $arr;
}
$isEmpty = empty($response);
if ($isEmpty){ //not found in database

    //register the username with its credentials
    $query = $conn->prepare("INSERT INTO users (name, username, email, password) VALUES (?, ?, ?, ?)");
    $query->bind_param("ssss", $name, $username, $email, $password);
    $query->execute();
    echo "true";
}
else{
    echo "false";
}




?>