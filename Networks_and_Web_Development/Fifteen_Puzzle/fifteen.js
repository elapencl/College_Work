
var spaceY;
var spaceX;

var gamePiece; 

var timer;

var notify;

//what happend when we load the page --> we specify the puzzle pieces
 window.onload = function ()

{
	//let's get our puzzle piece
	var puzzleArea = document.getElementById('puzzlearea');
	gamePiece = puzzleArea.getElementsByTagName('div'); 

	for (var i=0; i<gamePiece.length; i++) 

	{

		gamePiece[i].className = 'puzzlepiece'; 
		gamePiece[i].style.left = (i%4*100)+'px'; 
		gamePiece[i].style.top = (parseInt(i/4)*100) + 'px'; 
		gamePiece[i].style.backgroundPosition= '-' + gamePiece[i].style.left + ' ' + '-' + gamePiece[i].style.top; 
		gamePiece[i].onmouseover = function()
		{
			//add the red borders
			if (checkMove(parseInt(this.innerHTML))) 

			{
				this.style.border = "3px solid red"; 
				this.style.color = "#006600"; 
				this.style.textDecoration = "underline"; 
                this.style.backgroundImage="url('https://topofthelist.net/wp-content/uploads/2016/01/Hydrangeas.jpg')"; 
			}
		};

		//what happens when we are not pressing the puzzle piece
		gamePiece[i].onmouseout = function()

		{

			this.style.border = "2px solid black"; 
			this.style.color = "#000000"; 
			this.style.textDecoration = "none"; 

		};


		//what happens when we click a puzzle piece
		gamePiece[i].onclick = function() 
		{
			if (checkMove(parseInt(this.innerHTML))) 

			{
				swap(this.innerHTML-1); 
				if (finish()) 

				{
					win(); 
				}

				return;

			}
		};
	}


	var shuffle = document.getElementById('shufflebutton'); 
	spaceX = '300px'; 
	spaceY = '300px';

	//what happens when we click the shuffle button
	shuffle.onclick = function() 

	{
		for (var i=0; i<300; i++) 
		{

			var rand = parseInt(Math.random()* 100) %4; 
			if (rand == 0)

			{

				var temp = up(spaceX, spaceY); 
				if ( temp != -1)
				{
					swap(temp);
				}
			}

			if (rand == 1)
			{

				var temp = down(spaceX, spaceY);
				if ( temp != -1) 
				{
					swap(temp);
				}
			}

			if (rand == 2)
			{
				var temp = left(spaceX, spaceY);
				if ( temp != -1)
				{
					swap(temp);
				}
			}

			if (rand == 3)
			{
				var temp = right(spaceX, spaceY);
				if (temp != -1)
				{
					swap(temp);
				}
			}
		}
	};
};



function checkMove(position) // returns true whenever a piece can be moved into an empty space

{

	if (left(spaceX, spaceY) == (position-1))

	{
		return true;
	}



	if (down(spaceX, spaceY) == (position-1))

	{
		return true;
	}



	if (up(spaceX, spaceY) == (position-1))

	{
		return true;
	}



	if (right(spaceX, spaceY) == (position-1))

	{
		return true;
	}

}


//function to check if we are done with the game
function finish()

{
	var flag = true;

	for (var i = 0; i < gamePiece.length; i++)  
	{

		var top = parseInt(gamePiece[i].style.top);
		var left = parseInt(gamePiece[i].style.left);

		if (left != (i%4*100) || top != parseInt(i/4)*100) 

		{
			flag = false;
			break;
		}

	}

	return flag;
}


//calculate the puzzlepiece movement to the left
function left(x, y) 

{

	var cordX = parseInt(x);
	var cordY = parseInt(y);



	if (cordX > 0)

	{
		for (var i = 0; i < gamePiece.length; i++) 
		{
			if (parseInt(gamePiece[i].style.left) + 100 == cordX && parseInt(gamePiece[i].style.top) == cordY)
			{
				return i;
			} 

		}

	}

	else 

	{
		return -1;
	}

}


//calculate the puzzlepiece movement to the right
function right (x, y)
{
	var cordX = parseInt(x);
	var cordY = parseInt(y);

	if (cordX < 300)
	{
		for (var i =0; i<gamePiece.length; i++){
			if (parseInt(gamePiece[i].style.left) - 100 == cordX && parseInt(gamePiece[i].style.top) == cordY) 
			{
				return i;
			}
		}
	}

	else
	{
		return -1;
	} 
}

//calculate the puzzlepiece movement to the top
function up(x, y) 
{

	var cordX = parseInt(x);
	var cordY = parseInt(y);
	if (cordY > 0)

	{
		for (var i=0; i<gamePiece.length; i++)
		{
			if (parseInt(gamePiece[i].style.top) + 100 == cordY && parseInt(gamePiece[i].style.left) == cordX) 
			{
				return i;
			}
		} 
	}

	else 

	{
		return -1;
	}
}

//calculate the puzzlepiece movement to the bottom
function down (x, y)
{

	var cordX = parseInt(x);
	var cordY = parseInt(y);

	if (cordY < 300)

	{

		for (var i=0; i<gamePiece.length; i++)
		{
			if (parseInt(gamePiece[i].style.top) - 100 == cordY && parseInt(gamePiece[i].style.left) == cordX) 

			{
				return i;
			}
		}
	}

	else

	{
		return -1;
	} 

}

//swap puzzle piece with empty spot
function swap (position) 
{
	var temp = gamePiece[position].style.top;
	gamePiece[position].style.top = spaceY;
	spaceY = temp;
	temp = gamePiece[position].style.left;
	gamePiece[position].style.left = spaceX;
	spaceX = temp;

}

