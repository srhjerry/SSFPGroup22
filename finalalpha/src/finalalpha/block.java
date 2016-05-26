package finalalpha;
import processing.core.PApplet;


public class block {
	private final static int y = 70 , height = 120;
	
	private PApplet parent;
	private int x;
	private int width;
	
	
	public block(mainApplet parent, int x, int width){
		this.parent = parent;
		this.x = x;
		this.width = width;
	}
	


	public void display(){
		parent.fill(255,87,87);
		parent.rect(x-width/2,y,width,height);
	}
	
	public int getX(){
		return x;
	}
	
	public int getWidth(){
		return width;
	}
}
