"use strict";

//make functions work the moment the page loads
window.onload = function() {

	//activate our buttons
	var startButton = document.getElementById("start");
    startButton.onclick = start;

    var stopButton = document.getElementById("stop");
    stopButton.onclick = stop;

    //this is calling the setFont function to change our font
    document.getElementById("size36pt").onclick = setFont;
    document.getElementById("size48pt").onclick = setFont;
    document.getElementById("size60pt").onclick = setFont;

   //this is calling the setSpeed function to change speed buuut it's not working sadly..
  //document.getElementById("speed300").onclick = setSpeed;
  //document.getElementById("speed200").onclick = setSpeed;
  //document.getElementById("speed171").onclick = setSpeed;
  //document.getElementById("speed150").onclick = setSpeed;
  //document.getElementById("speed133").onclick = setSpeed;
  //document.getElementById("speed120").onclick = setSpeed;
}
  
var timer;

//what happens when we press the start button
function start() {
	var text = document.getElementById("words").value;
	var list = text.split(/\s+/);
    runDisplay(list, "display")
}
 
//handles the display
function runDisplay(data, id) {
	var reader = document.getElementById(id);
    var index = 0;
    
    if (timer) {
    	clearInterval(timer);
    }

    if (data.length) {
    	timer = setInterval(function() {
      	reader.innerHTML = data[index++];
        index = index % data.length;s
      }, 350);
    }
  }

//what happnes when we press the stop button
function stop() {
	clearInterval(timer);
    timer = null;
}

///to change our font size
function setFont() {
	document.getElementById("display").style.fontSize = this.value;
}

//to change our speed.. but I couldn't figure it out so I commented it out.. my speed is rn set to 350 as indicated in runDisplay
//function setSpeed() {
//	document.getElementById("speed") = this.value;
//}