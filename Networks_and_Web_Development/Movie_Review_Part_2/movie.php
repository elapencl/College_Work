<!doctype html>
<html>
    
    <head>
        <title>Rancid Tomatoes</title>

        <meta charset="utf-8" />
        <link href="movie.css" type="text/css" rel="stylesheet" />
        <link rel="icon" type="image/gif" href="https://watzek.lclark.edu/cs/293spr2021/ex2/rotten.gif" />

    </head>
    
    <?php

    if (!isset($_REQUEST["film"]) || !is_dir($_REQUEST["film"])) {
        die("Illegal movie");
    }

    $movie = $_REQUEST["film"];
    $raw_info = file_get_contents("$movie/info.txt");
    $raw_overview = file_get_contents("$movie/overview.txt");
    $info = explode("\n", $raw_info);
    $overview = explode("\n", $raw_overview);
    $count = 0;
    $freshness = "";
    $freshAlt = "";

    if (intval($info[2]) >= 60){
        $freshness = "https://watzek.lclark.edu/cs/293spr2021/ex2/freshlarge.png";
        $freshAlt = "Fresh";
    }

    else{
        $freshness = "http://watzek.lclark.edu/cs/293spr2021/ex2/rottenbig.png";
        $freshAlt = "Rotten";
    }

    function sidebar(){
        global $overview;
        foreach($overview as $row){
            $row = explode(":", $row);
            echo "<dt>{$row[0]}</dt><dd>{$row[1]}</dd>";
        }
    }

    function getReviews(){
        global $movie;
        global $count;
        $raw_review = array();
        foreach (glob("$movie/review*.txt") as $filename){
            $raw_review[$count] = file_get_contents("$filename");
            $count++;
        }
        for ($i=0;$i<$count;$i++){
            $review = explode("\n", $raw_review[$i]);
            displayReview($review,$i);
        }
    }

    function displayReview($review,$num){
        global $count;
        $num++;
        $review[1] = strtolower($review[1]);
        echo "<p class='review'>
        <img src='{$review[1]}.gif' alt='{$review[1]}' />
        <q>{$review[0]}</q>
        </p>
        <p class='reviewer'>
        <img src='http://watzek.lclark.edu/cs/293spr2021/ex2/critic.gif' alt='Critic' />
        {$review[2]}<br />
        {$review[3]}
        </p>";

        if($num == ceil($count/2)){
            echo "</div>
            <div class='column'>";
        }
    }           
    ?>

    <body >
        <div  id="repeat">
            <img src="https://watzek.lclark.edu/cs/293spr2021/ex2/rancidbanner.png" alt = "Rancid Banner" height="50">
        </div>


        <h1 id="title"><?echo "$info[0] ($info[1])";?></h1>
        
        <div id="content"> 
        
            <div id="outter"> 
                <div>
                    <img id="overview_img" src="<?=$movie?>/overview.png" alt="general overview" />
                </div>

                <dl>
                    <?php sidebar();?>
                </dl>

            </div> 

            <div id="inner"> 
                <div id="rate33">
                    <img id="rotten" src="<?=$freshness?>" alt="<?=$freshAlt?>" />
                    <?=$info[2]?>%
                </div>


                
                <div class="column"> 
                    <?php getReviews();?>               
                </div> 
                <div id="aftercolumns"></div>


            </div> 

                <p id="footer">(1- <?=$count?>) of <?=$count?></p>

                <div id="rate">
                    <img id="rotten" src="<?=$freshness?>" alt="<?=$freshAlt?>" />
                    <?=$info[2]?>%
                </div>
        </div> 



    </body>
        <div id="validate">
            <p>
                <a href="http://validator.w3.org/#validate_by_upload"><img src="http://watzek.lclark.edu/cs/293spr2021/ex2/w3c-html.png" alt="HTML Validator" /></a><br />
                <a href="http://jigsaw.w3.org/css-validator/#validate_by_upload"><img src="http://watzek.lclark.edu/cs/293spr2021/ex2/w3c-css.png" alt="CSS Validator" /></a>
            </p>

        </div>

        <div  id="repeat1">
            <img src="https://watzek.lclark.edu/cs/293spr2021/ex2/rancidbanner.png" alt = "Rancid Banner" >
        </div>



        
</html>