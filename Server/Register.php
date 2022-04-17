<?php

include("db_info.php");

//getting values from POST request
$username = $_POST["username"];
$name = $_POST["name"];
$email = $_POST["email"];
$password = $_POST["password"];


$password = hash("sha256", $password); //hashing the password

//check if username exists in database
$query = $conn->prepare("SELECT username FROM users");
$query->execute();

$array = $query->get_result();

$response = [];
while ($arr = $array->fetch_assoc()){
    $response[] = $arr;
}

$res = 1;
for ( $i = 0 ; $i < sizeof($response); $i++){
    if ($response[$i]['username'] == $username){
        $res = 0;
        break;
    }
}

if ($res == 1){
    $query = $conn->prepare("INSERT INTO users (name, username, email, password) VALUES (?, ?, ?, ?)");
    $query->bind_param("ssss", $name, $username, $email, $password);
    $query->execute();


    //get id of new user from database
    $query2 = $conn->prepare("SELECT username FROM users where username = ?");
    $query2->bind_param ("s",$username);
    $query2->execute();
    $array2 = $query2->get_result();
    $response2 = [];
    while ($arr = $array2->fetch_assoc()){
        $response2[] = $arr;
    }

    session_start();
    $_SESSION["username"] = $username;
    $_SESSION["id"] = $response[0]['id'];

    echo "true";
}
else{
    echo "false";
}
?>