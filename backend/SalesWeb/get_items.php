<?php 
$con = new mysqli("localhost:3306","koffinyo_mawuli","mawuli1989.","koffinyonouglo_salesweb");
 $st= $con->prepare("select * from items where category=?");
 $st->bind_param("s",$_GET["category"]);
 $st->execute();
 $rs=$st->get_result();
 $arr = array();

 while ($row=$rs->fetch_assoc()){
   
     array_push($arr,$row);
 }

 echo json_encode($arr);


 ?>