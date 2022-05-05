<?php

include("db_info.php");



$query = $conn->prepare('SELECT USERNAME, NAME, DATE, TIME FROM USERSAPPOINTMENTS');
$query->execute();

$result = $query->get_result();

$response = array();

while ($row = $result->fetch_assoc()){
    $response[] = $row;
}


//get username from first array
//echo $response[0]["username"];

// print_r ($response);

echo json_encode($response);

?>