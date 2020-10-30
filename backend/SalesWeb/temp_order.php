<?php 
$con = new mysqli("localhost:3306","koffinyo_mawuli","mawuli1989.","koffinyonouglo_salesweb");
   $st_check = $con->prepare("insert into cart values(?,?,?,?)");
   $st_check->bind_param("siii",$_GET["mobile"],$_GET["itemId"],$_GET["qty"],$_GET["image"]);
   $st_check->execute();

?>