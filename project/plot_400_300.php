<?php
    header('Access-Control-Allow-Origin: *'); 

    error_reporting(E_ALL);
    ini_set("display_errors", 1);


    /**** connect to the mySQL database ****/
    $link = mysql_connect('localhost', 'root', '');
    if (!$link) {
        die('Could not connect: ' . mysql_error());
    }
    
    $num = 70; /**** THE NUMBER OF DOTS ****/

    $db_selected = mysql_select_db('stock', $link); // database: stock
    if (!$db_selected) {
        die ('Can\'t use the selected database: ' . mysql_error());
    }

    /**** select the chosen table ****/
    $symbol = $_GET['symbol']; ///************** the symbol of the stock
    //$str = (string)$symbol;
    // echo gettype($str);
    
    $sym = (string)$symbol."_NASDAQ";  /////********** the symbol corresponds to the table
    
    //echo $sym;
   
    $result = mysql_query("SELECT Date, Open FROM $sym"); // from: stock.AAPL_NASDAQ
    
    // create an array named $price
    while($row = mysql_fetch_row($result)) {
        $price[$row[0]]=$row[1];
    }

    /**** get the latest $num prices ****/
    $price = array_slice($price,count($price)-$num);

    /**** start to draw pictures ****/
    // create canvas
    $im=imagecreatetruecolor(500,250);
    // define colors
    $black  =imagecolorallocate($im,0,0,0);
    $white  =imagecolorallocate($im,255,255,255);

    $blue   =imagecolorallocate($im,0,128,255);     /*      OPEN     */
    $red    =imagecolorallocate($im,255,0,0);       /*      HIGH     */
    $green  =imagecolorallocate($im,0,255,0);       /*      LOW      */
    $yellow =imagecolorallocate($im,255,153,51);    /*      CLOSE    */

    imagefill($im,0,0,$white);

    $step = 500/$num;///// step on X axis
    $i = 0;
    foreach($price as $key=>$value){ // get the points on the graph
        $point[]=array($step*$i,200-4*$value); ////// 4 times the price
        $i++;
    }
    /****
    for($i=0;$i<10;$i++) {
        echo $point[$i][0];

        echo "<br />";
    }
    ****/

    
    /* draw the X-axis label */
    $j=0;
    foreach ($price as $key => $value) {
        imagestringup($im, 1, $j*$step, 250, $key, $black);
        $j++;
    }
    /* draw the Y-axis label */
    $step_y = 10;
    for($i=0;$i<40;$i++){  
        //imageline($im,0,$i*$step_y,0,$point[$i+1][1],$blue);
        imagestring($im, 1, 0, 200-4*$i*$step_y, $i*$step_y, $black);
    }
    /* the notation of colors:
    *  OPEN: blue   HIGH: red   LOW: green   CLOSE: yellow
    */
    
    imageline($im, 0, 200, 500, 200, $black); // X axis
    imageline($im, 0, 0, 0, 200, $black);   // Y axis

    for($i=0;$i<$num-1;$i++){  
        imagesetpixel($im,$point[$i][0],$point[$i][1],$red);
        imageline($im,$point[$i][0],$point[$i][1],$point[$i+1][0],$point[$i+1][1],$blue);
    }
    

    //header('Content-type:image/jpeg');
    $image_path = "/Users/Kevin/Documents/myEclipseWorkspace/StockRecommendation/WebContent/".$sym.".jpg";
    ////////"/Users/Kevin/Documents/myEclipseWorkspace/StockRecommendation/WebContent/".$sym.".jpg";
    ////////"/Users/Kevin/Desktop/".$sym.".jpg"
    imagejpeg($im,$image_path); ///////******** the place the image is stored
    imagedestroy($im);    
    
    mysql_close($link);
   
?>