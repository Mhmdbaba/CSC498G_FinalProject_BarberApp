<?php

include("db_info.php");



$query = $conn->prepare("SELECT NAME, TIME, ACTIVE FROM usersappointments");
$query->execute();

$array = $query->get_result();

$response = [];

while ($arr = $array->fetch_assoc()){
    $response[] = $arr;
}

echo json_encode($response);


?>