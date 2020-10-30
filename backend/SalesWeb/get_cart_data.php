<?php 
$con = new mysqli("localhost:3306","koffinyo_mawuli","mawuli1989.","koffinyonouglo_salesweb");
   $st_check = $con->prepare("select id,name,price,qty,image,mobile from cart inner join items on items.id=cart.itemId where mobile=?");
   $st_check->bind_param("s",$_GET["mobile"]);
   $st_check->execute();
   $rs=$st_check->get_result();

   $arr = array();

    while ($row=$rs->fetch_assoc()){
   
     array_push($arr,$row);
 }

     echo json_encode($arr);

?>