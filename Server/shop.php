<?php

include("db_info.php");



$query = $conn->prepare('SELECT NAME, CATEGORY, PICTURE, PRICE FROM items WHERE CATEGORY = "Hair Gel & Wax" and CATEGORY = "Oils" and CATEGORY = "Accessories"');
$query->execute();

$array = $query->get_result();

$response = [];

while ($arr = $array->fetch_assoc()){
    $response[] = $arr;
}

echo json_encode($response);


?>