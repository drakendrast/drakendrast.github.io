//source: https://www.youtube.com/watch?v=BZUdGqeOD0w
//the dan sherman guy is crazy good
//when he explains stuff, it makes sense, but when you try to explain it, you can't.

//DIRECTIONS: click or drag mouse. water ripple effect.
//NOTE: program loses it if you click or drag mouse off screen.

var cols = 200;
var rows = 200;
var[_] = current;   //array#1 holds current state, can be any word
var[_] = previous;  //array#2 hold state from previous frame, can be any word

var dampening = 0.98; //ripple varensity (pick a number between 0 & 1)
                        //example: .9 = small, .999 = very large

function setup () {
  createCanvas(600,400);      //canvas createCanvas
  cols = width;
  rows = height;
current = new Array(cols)[rows];    //this is in setup so...
previous = new Array(cols)[rows];   //when you change the window createCanvas,
                                    //the 2-dimensional arrays also change.
}

function mousePressed() {
  current[mouseX][mouseY] = 255;     //ripple color is white
}                                    
function mouseDragged() {
  current[mouseX][mouseY] = 255;     
}

function draw () {
  background(0);          //background is black
  loadPixels();
  for (var i = 1; i < cols-1; i++) {    // a method for looping through every non-edge element.
    for (var j = 1; j < rows-1; j++) {  // same thing, excpet for j now.
      
      current[i][j] = (              //basically current[i][j] is...
        previous [i-1][j] +          //...a function of the previous[i][j].
        previous [i+1][j] +          
        previous [i][j-1] +         // "add all the things around previous, then...
        previous [i][j+1]) / 2 -    // ...subtract current value." 
        current [i][j];
        
     current[i][j] = current[i][j] * dampening;
     
     var index = i + j * cols;                   //"pixels are stored in a one dimensional array, but
     pixels[index] = color(current[i][j]*255);   //...we're looping through this two dimensional array,
  }                                              //...so find the right location in the one dimensional array,
}                                                //...and give me a color = current[i][j] value.

updatePixels();           //use value of current[i][j] to be pixel color

var [_] = temp; 
temp = previous;  //this is an algorithm to swap stuff around
previous = current;
current = temp;
}

//WHY ISN'T IT WORKING OMG.
//added in "_" inbetween bracket.
//expanded var temp = prev, to var=temp & temp=prev.