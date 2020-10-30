<?php 
$con = new mysqli("localhost:3306","koffinyo_mawuli","mawuli1989.","koffinyonouglo_salesweb");
   $st_check = $con->prepare("delete from cart where mobile=?");
   $st_check->bind_param("s",$_GET["mobile"]);
   $st_check->execute();
   $rs=$st_check->get_result();
  if($rs==0){
    echo "1";
  }else{
    echo "0";
  }
    
   
                                                            


?>