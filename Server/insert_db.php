<?php

include("db_info.php");

$insertcat = $_POST["insert"];



if ($insertcat == "register"){
    //getting values from POST request
    $username = $_POST["username"];
    $name = $_POST["name"];
    $email = $_POST["email"];
    $password = $_POST["password"];
    $password = hash("sha256", $password); //hashing the password

    $query = $conn->prepare("INSERT INTO users (name, username, email, password) VALUES (?, ?, ?, ?)");
    $query->bind_param("ssss", $name, $username, $email, $password);
}
else if ($insertcat == "item"){
    //getting values from POST request
    $name = $_POST["name"];
    $category = $_POST["category"];
    $picture = $_POST['picture'];
    $price = $_POST['price'];

    $query = $conn->prepare("INSERT INTO users (name, category, picture, price) VALUES (?, ?, ?, ?)");
    $query->bind_param("sssd", $name, $category, $picture, $price);
}


$query->execute();

?>