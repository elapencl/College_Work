function changeBackground(color) {
	document.body.style.background = color;
}

function drawDot() {
  interval = setInterval(function(){
    var graph = document.getElementById('graph');
	var newDiv = document.createElement("div");
	graph.appendChild(newDiv);
	newDiv.className += "point";

	//I used this id to solve the coloring class..
	//newDiv.id = "pointColor";
	
	var height = newDiv.offsetHeight + 580;
	var width = newDiv.offsetWidth + 580;
	var randomY = Math.round(Math.random() * height)  + 'px';
	var randomX = Math.round(Math.random() * width) + 'px';

	newDiv.style.left = randomX;
	newDiv.style.bottom = randomY;

	/* I used this to solve the coloring task, but didn't succeed..

	if(randomY < 250 +'px'){
		newDiv.setAttribute("style", "background-color: red;");
		//document.getElementById("pointColor").backgroundColor = "red";	
	}

	if(randomX && randomY< 500 + 'px'){
		newDiv.setAttribute("style", "background-color: blue;");
	}
	*/

  },100);
}

function pause(){
	clearInterval(interval);
}

function clear(){
	var graph = document.getElementById('graph');

	while (graph.firstChild) {
  		graph.removeChild(graph.firstChild);
	}
}
	

function removeAllChildNodes(parent) {
    while (parent.firstChild) {
        parent.removeChild(parent.firstChild);
    }
}

function clearBox(elementID) { 
    var div = document.getElementById(elementID); 
              
    while(div.firstChild) { 
        div.removeChild(div.firstChild); 
    } 
} 