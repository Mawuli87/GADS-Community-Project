<?php
$con = new mysqli("localhost:3306","koffinyo_mawuli","mawuli1989.","koffinyonouglo_salesweb");
$st = $con->prepare("select price,qty from items inner join bill_det on items.id=bill_det.itemId where bill_det.bill_no=?");
$st->bind_param("i",$_GET["bill_no"]);
$st->execute();
$rs=$st->get_result();

$total=0;

while ($row=$rs->fetch_assoc())
{
   
    $total += ($row['price'] * $row['qty']);
}
if($total != 0){

    $result['success'] = "1";
    $result['message'] =  "$total";
    echo json_encode($result);
    

}else{

    $result['success'] = "0";
    $result['message'] = "Failed";
    echo json_encode($result);
}

?>



