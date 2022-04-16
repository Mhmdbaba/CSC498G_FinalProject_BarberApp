<?php

include("db_info.php");


if ($conn){
    $query = "select * from users where";
}
else{
    echo "Not connected....!";
}

?>