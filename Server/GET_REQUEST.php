<?php

include("db_info.php");
session_start();

$getcate = $_GET["getcate"];
//insert item into database
if ($getcate == "item"){
    //getting values from POST request
    $name = $_GET["name"];
    $category = $_GET["category"];
    $picture = $_GET["picture"];
    $price = $_GET["price"];

    $decodedImage = base64_decode("$image");
    file_put_contents("pictures/" . $name . ".JPEG" . $decodedImage);

    $query = $conn->prepare("INSERT INTO users (name, category, picture, price) VALUES (?, ?, ?, ?)");
    $query->bind_param("sssd", $name, $category, $picture, $price);
}
//insert appointment into database
else if ($getcate == "appointment"){
    $what = $_GET["what"];
    $timeslots_id =$_GET["timeslots_id"];
    $date = $_GET["date"];
    $id = $_SESSION["id"];
    
    $query = $conn->prepare("INSERT INTO appointments (what, timeslots_id, date, user_id, active) VALUES (?, ?, ?, ?, ?)");
    $query->bind_param("sdsdd", $name, $category, $picture, $price, 1);
}
//get the clients actuve appointments
else if ($getcate == "admin"){
    $query = $conn->prepare("SELECT NAME, TIME, ACTIVE FROM usersappointments");
    $query->execute();

    $array = $query->get_result();

    $response = [];

    while ($arr = $array->fetch_assoc()){
            $response[] = $arr;
        }
        echo json_encode($response);
}

?>