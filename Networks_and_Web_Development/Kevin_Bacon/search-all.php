<?php
require_once("common.php");

$firstname=$_REQUEST["firstname"];
$lastname=$_REQUEST["lastname"];


$db=dbConnect();
if ($id=getActorId($firstname, $lastname, $db)){
  
  $rows=getMoviesByActorID($db, $id);
  $table=formatTable($rows);
  $h1="Results for $firstname $lastname";
  $h2= count($rows);
  $result=$id;

}
else{

  $h1="Error 404: $firstname $lastname not found";

}
getActorByName($db, $firstname, $lastname);


?>

<!DOCTYPE html>
<html>
  <head>
    <title>My Movie Database (MyMDb)</title>
    <meta charset="utf-8" />
    <link href="https://cs293.watzekdi.net/images/kb/favicon.png" type="image/png" rel="shortcut icon" />

    <link href="bacon.css" type="text/css" rel="stylesheet" />
  </head>

  <body>
    <div id="frame">
      <?php TheHeader();?>

      <div id="main">
        <h1><?= $h1?></h1>
        <h2><?= $h2?></h2>
        
        <?php echo $table;?>
        
        
        <?php TheTables();?>
      </div> 
      <?php TheFooter(); ?>
    </div> 
  </body>
</html>