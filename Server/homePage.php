<?php

include("db_info.php");



$query = $conn->prepare('SELECT NAME, CATEGORY, PICTURE, PRICE FROM items WHERE CATEGORY = "Haircuts" and CATEGORY = "Treatments"');
$query->execute();

$array = $query->get_result();

$response = [];

while ($arr = $array->fetch_assoc()){
    $response[] = $arr;
}

echo json_encode($response);


?>