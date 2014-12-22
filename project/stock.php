<?php
header("Access-Control-Allow-Origin: *");
header("Content-Type: application/json; charset=UTF-8");

$q = $_GET['q'];
    

$t = $_GET['t'];
    

$f = $_GET['f'];
     

$conn = new mysqli("localhost","root","35fpud","stock");

$result = $conn->query("SELECT * FROM stock where Market_Category='".$q."' AND (Test_Issue='".$t."' AND Financial_Status='".$f."' ) LIMIT 0, 10");

$outp = "[";
while($rs = $result->fetch_array(MYSQLI_ASSOC)) {
    if ($outp != "[") {$outp .= ",";}
    $outp .= '{"Symbol":"'  . $rs["Symbol"] . '",';
    $outp .= '"Security_Name":"'   . $rs["Security_Name"]        . '",';
    $outp .= '"Market_Category":"'. $rs["Market_Category"]     . '",'; 
    $outp .= '"Test_Issue":"'. $rs["Test_Issue"]     . '",'; 
    $outp .= '"Financial_Status":"'. $rs["Financial_Status"]     . '",'; 
    $outp .= '"Round_Lot_Size":"'. $rs["Round_Lot_Size"]     . '"}'; 
}
$outp .="]";

$conn->close();

echo($outp);
?>