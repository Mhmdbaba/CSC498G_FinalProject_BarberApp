<?php

include("db_info.php");
session_start();

$insercate = $_GET["insertcate"];

if ($insertcat == "item"){
    //getting values from POST request
    $name = $_GET["name"];
    $category = $_GET["category"];
    $picture = $_GET['picture'];
    $price = $_GET['price'];

    $query = $conn->prepare("INSERT INTO users (name, category, picture, price) VALUES (?, ?, ?, ?)");
    $query->bind_param("sssd", $name, $category, $picture, $price);
}
else if ($insercate == "appointment"){
    $what = $_GET["what"];
    $timeslots_id =$_GET["timeslots_id"];
    $date = $_GET["date"];
    $id = $_SESSION["id"];
    
    $query = $conn->prepare("INSERT INTO appointments (what, timeslots_id, date, user_id, active) VALUES (?, ?, ?, ?, ?)");
    $query->bind_param("sdsdd", $name, $category, $picture, $price, 1);
}

?>