<?php 
$con = new mysqli("localhost:3306","koffinyo_mawuli","mawuli1989.","koffinyonouglo_salesweb");

$st_check = $con->prepare("select * from users where mobile=? and password=?");
$st_check->bind_param("ss",$_GET["mobile"],$_GET["password"]);
$st_check->execute();
$rs=$st_check->get_result();
if($rs->num_rows==0)
     echo "0";
     else
     echo "1";                                                        


?>