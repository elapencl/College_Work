<?php
require_once("common.php");

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
		
		<?php TheHeader(); ?>


			<div id="main">
				<h1>The One Degree of Kevin Bacon</h1>
				<p>Type in an actor's name to see if he/she was ever in a movie with Kevin Bacon!</p>
				<p><img src="https://cs293.watzekdi.net/images/kb/kevin_bacon.jpg" alt="Kevin Bacon" /></p>

				<?php TheTables();?>

			</div> 
		
			<?php TheFooter(); ?>
		</div> 
	</body>
</html>