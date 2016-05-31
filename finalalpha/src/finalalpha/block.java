package finalalpha;
import java.util.Random;

import processing.core.PApplet;


public class block {
	private final static int y = 70 , height = 120;
	private Random ran;
	private PApplet parent;
	private int x;
	private int width;
	private int color;
	
	
	public block(mainApplet parent, int x, int width){
		ran=new Random();
		this.parent = parent;
		this.x = x;
		this.width = width;
		this.color=ran.nextInt(5);
	}
	public int getcolor(){
		return this.color;
	}
	


	public void display(){
		if(color==0)
		{
			parent.fill(87,87,255);
			parent.rect(x-width/2,y,width,height);
		}else if(color==1)
		{
			parent.fill(87,255,87);
			parent.rect(x-width/2,y,width,height);
		}else if(color==2)
		{
			parent.fill(87,87,87);
			parent.rect(x-width/2,y,width,height);
		}
		else{
		parent.fill(255,87,87);
		parent.rect(x-width/2,y,width,height);
		}
	}
	
	public int getX(){
		return x;
	}
	
	public int getWidth(){
		return width;
	}
}
