package finalalpha;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Random;

import de.looksgood.ani.Ani;
import processing.core.PApplet;

@SuppressWarnings("serial")
public class mainApplet extends PApplet{
	private final static int rectWidth = 1000, rectHeight = 70;

	private int x1, y1, x2, y2;
	private int n,sec;
	private boolean end = true;
	private boolean barct=false;
	//private Object barctlock=new Object();
	Random r = new Random();
	public void setbarct(boolean b)
	{
		
		this.barct=b;
		
			keyPressed();
		
	}
	public boolean getbarct()
	{
		
		return barct;
		
	}

	ArrayList<block> blocks = new ArrayList<block>();
	ArrayList<block> blocks_end = new ArrayList<block>();
	boolean[] checkfill = new boolean[1200];		//false  ;true Τblock
	
	public void setup(){
		size(1200, 400);
		Ani.init(this);
		x1 = 100; y1 = 100;
		x2 = 100; y2 = 70;
		blocks.add(new block(this,x2,100));
		for(int i = x2 ; i<=x2+100 ; i++){
			checkfill[i] = true;
		}
		n = 1;sec = 9;
	}
	public void draw(){
		background(255);
		
		noFill();				// 
		strokeWeight(10);
		stroke(167);
		rect(x1, y1, rectWidth, rectHeight);		//x:100~1100

		
		
		GregorianCalendar g = new GregorianCalendar(); 		//block
		
		if((int)g.get(GregorianCalendar.SECOND)%10 == sec){
			for(int i=0 ; i<n ; i++){
				int ran = 100 + r.nextInt(1000);			//block いみ竚
				int error = 0;

				while(checkfill[ran] == true){
					ran = 100 + r.nextInt(1000);
					if(error++>10000) break;
				}
				int ranwidth = 50+r.nextInt(100);			//block 
				blocks.add(new block(this,ran,ranwidth));

				for(int j = ran-ranwidth/2 ; j<=ran+ranwidth/2 ; j++){
					checkfill[j] = true;
				}
			}
			sec--;n++;
			//System.out.println(g.get(GregorianCalendar.SECOND) + " " + sec);
			
		}
		strokeWeight(3);stroke(0);
		for(block b:blocks)
			b.display();
		
		
		
		
		
		strokeWeight(0);	
		if(barct){
		fill(87,255,87);		//笆稰次次
		}else{
			fill(0,0,0);
		}
		rect(x2, y2, 10, 130);
		
		if(x2 == 100 )
			Ani.to(this, (float) 2, "x2", 1100, Ani.LINEAR);
		if(x2 == 1100)
			Ani.to(this, (float) 2, "x2", 100, Ani.LINEAR);
		
		
		if(keyPressed){
			
			keyPressed();
			
		}
		
		
		for(int i=100 ; i<=1100 ; i++){		//end case
			if(checkfill[i] == false)	end = false;
		}
		if(end == true) end();
		end = true;
		
	}
	public void mousePressed(){
		
	}
	
	
	public void keyPressed(){
		
		for(int j=0 ; j<blocks.size(); j++){
			int low = blocks.get(j).getX() - blocks.get(j).getWidth()/2, up = blocks.get(j).getX() + blocks.get(j).getWidth()/2;
			if(low <= x2 && up >= x2 ){
				for(int i=low ; i <= up ; i++){
					checkfill[i] = false;
				}if(this.getbarct()){
				blocks.remove(j);
				barct=false;
				}
			}
		}
		barct=false;
		
	}

	public void end(){
		background(255);
		textSize(100);
		text("GameOver QQ",300,300);
		
		noFill();				// 
		strokeWeight(10);
		stroke(167);
		rect(x1, y1, rectWidth, rectHeight);		//x:100~1100
		
		
		strokeWeight(3);stroke(0);
		
		blocks_end = blocks;
		for(block b:blocks_end)
			b.display();
		
		strokeWeight(0);		
		fill(87,255,87);		//笆稰次次
		rect(x2, y2, 10, 130);
		Ani.killAll();
		
	}
}
