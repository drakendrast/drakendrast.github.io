import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class water_ripples_it_finally extends PApplet {

//source: https://www.youtube.com/watch?v=BZUdGqeOD0w
//the dan sherman guy is crazy good
//when he explains stuff, it makes sense, but when you try to explain it, you can't.

//DIRECTIONS: click or drag mouse. water ripple effect.
//NOTE: program loses it if you click or drag mouse off screen.

int cols = 200;
int rows = 200;
float [][] current;   //array#1 holds current state, can be any word
float [][] previous;  //array#2 hold state from previous frame, can be any word

float dampening = 0.98f; //ripple intensity (pick a number between 0 & 1)
                        //example: .9 = small, .999 = very large

public void setup () {
        //canvas size
  cols = width;
  rows = height;
current = new float[cols][rows];    //this is in setup so...
previous = new float[cols][rows];   //when you change the window size,
                                    //the 2-dimensional arrays also change.
}

public void mousePressed() {
  current[mouseX][mouseY] = 255;     //ripple color is white
}                                    
public void mouseDragged() {
  current[mouseX][mouseY] = 255;     
}

public void draw () {
  background(0);          //background is black
  loadPixels();
  for (int i = 1; i < cols-1; i++) {    // a method for looping through every non-edge element.
    for (int j = 1; j < rows-1; j++) {  // same thing, excpet for j now.
      
      current[i][j] = (              //basically current[i][j] is...
        previous [i-1][j] +          //...a function of the previous[i][j].
        previous [i+1][j] +          
        previous [i][j-1] +         // "add all the things around previous, then...
        previous [i][j+1]) / 2 -    // ...subtract current value." 
        current [i][j];
        
     current[i][j] = current[i][j] * dampening;
     
     int index = i + j * cols;                   //"pixels are stored in a one dimensional array, but
     pixels[index] = color(current[i][j]*255);   //...we're looping through this two dimensional array,
  }                                              //...so find the right location in the one dimensional array,
}                                                //...and give me a color = current[i][j] value.

updatePixels();           //use value of current[i][j] to be pixel color

float [][] temp = previous;  //this is an algorithm to swap stuff around
previous = current;
current = temp;
}
  public void settings() {  size(600,400); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "water_ripples_it_finally" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
