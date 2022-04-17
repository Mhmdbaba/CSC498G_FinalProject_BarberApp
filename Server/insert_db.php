<?php

include("db_info.php");

    //getting values from POST request
$username = $_POST["username"];
$name = $_POST["name"];
$email = $_POST["email"];
$password = $_POST["password"];
$password = hash("sha256", $password); //hashing the password

$query = $conn->prepare("INSERT INTO users (name, username, email, password) VALUES (?, ?, ?, ?)");
$query->bind_param("ssss", $name, $username, $email, $password);


$query->execute();

?>