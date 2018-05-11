var canvas

function windowResized(){
	resizeCanvas();
}

function setup() {
	canvas = createCanvas(windowWidth, windowHeight);
	canvas.position (0,0);
	canvas.style ('z-index', '-1');
	
	noStroke();
	fill (143,66,244,150);
}

function draw() {
	ellipse (mouseX,mouseY,20,20);
}