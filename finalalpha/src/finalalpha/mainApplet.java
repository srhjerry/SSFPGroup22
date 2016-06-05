package finalalpha;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Random;

import ddf.minim.AudioPlayer;
import ddf.minim.AudioSample;
import ddf.minim.Minim;
import de.looksgood.ani.Ani;
import processing.core.PApplet;
import processing.core.PFont;

@SuppressWarnings("serial")
public class mainApplet extends PApplet{
	private final static int rectWidth = 1000, rectHeight = 70;
	private Upperapplet upperapplet;
	private int x1, y1, x2, y2;
	private int n,sec;
	private int green=0;
	private int black=0;
	private boolean end = true;
	private boolean barct=false;
	//private Object barctlock=new Object();
	Random r = new Random();
	PFont myFont;
	
	Minim minim;
    AudioSample upgrade;
    AudioPlayer ending;
	
	public void setUpperapplet(Upperapplet applet)
	{
		this.upperapplet=applet;
	}
	public Upperapplet getUpperapplet()
	{
		return this.upperapplet;
	}
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
	boolean[] checkfill = new boolean[1200];		//false 空 ;true 有block
	
	public void setup(){
		size(1200, 400);
		Ani.init(this);
		minim=new Minim(this);
		upgrade=minim.loadSample("resources/093.wav");
		ending=minim.loadFile("resources/boss2.wav");
		x1 = 100; y1 = 100;
		x2 = 100; y2 = 70;
		blocks.add(new block(this,x2,100));
		for(int i = x2 ; i<=x2+100 ; i++){
			checkfill[i] = true;
		}
		n = 1;sec = 9;
		myFont = createFont("標楷體",100);
		  /* 直接輸入字型名稱，只要電腦有安裝該自行即可顯示 */
		  textFont(myFont);
	}
	public void draw(){
		this.clear();
		if(upperapplet.getdoom())
		{
			
			upperapplet.setdoom(false);
		}else{
		background(255);
		
		noFill();				// 外框
		strokeWeight(10);
		stroke(167);
		rect(x1, y1, rectWidth, rectHeight);		//x:100~1100

		this.fill(0,0,0);
		textSize(26);
		this.text("輸入對應的英文單字，在滾輪滑過磚塊時按下ENTER鍵以消除並獲取分數",100,230);
		this.text("開頭需大寫，add可消耗2combo來額外增加螢幕上顯示的單字量，skip可隨機跳過一字",100,260);
		this.text("Doom可以在combo>=10時摧毀所有磚塊，然而並不會因此獲得分數且消耗所有combo",100,290);
		this.text("藍色方塊可獲得跳過機會，綠色可加快滾軸，灰色會使滾軸速度下降,特殊磚塊皆可獲得額外分數",70,320);
		
		GregorianCalendar g = new GregorianCalendar(); 		//block
		
		if((int)g.get(GregorianCalendar.SECOND)%10 == sec){
			for(int i=0 ; i<n ; i++){
				int ran = 100 + r.nextInt(1000);			//block 中心位置
				int error = 0;

				while(checkfill[ran] == true){
					ran = 100 + r.nextInt(1000);
					if(error++>10000) break;
				}
				int ranwidth = 50+r.nextInt(100);			//block 長
				blocks.add(new block(this,ran,ranwidth));

				for(int j = ran-ranwidth/2 ; j<=ran+ranwidth/2 ; j++){
					checkfill[j] = true;
				}
			}
			sec--;
			if(n<4){
				n++;
			}
			//System.out.println(g.get(GregorianCalendar.SECOND) + " " + sec);
			
		}
		strokeWeight(3);stroke(0);
		for(block b:blocks)
			b.display();
		
		
		
		
		
		strokeWeight(0);	
		if(barct){
		fill(87,255,87);		//動感棒棒
		}else{
			fill(87,255,87);
		}
		rect(x2, y2, 10, 130);
	/*	
		if(x2 == 100 )
			Ani.to(this, (float) (2.5), "x2", 1100, Ani.LINEAR);
		if(x2 == 1100)
			Ani.to(this, (float) (2.5), "x2", 100, Ani.LINEAR);
	*/
		if(x2 == 100 )
			Ani.to(this, (float) ((float)2.5+(float)((float)0.5*(black-green))), "x2", 1100, Ani.LINEAR);
		if(x2 == 1100)
			Ani.to(this, (float) ((float)2.5+(float)((float)0.5*(black-green))), "x2", 100, Ani.LINEAR);
		
		
		if(keyPressed){
			
			keyPressed();
			
		}
		
		
		for(int i=100 ; i<=1100 ; i++){		//end case
			if(checkfill[i] == false)	end = false;
		}
		if(end == true) end();
		end = true;
		
		}
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
				if(blocks.get(j).getcolor()==0)
				{
					upperapplet.addskipchance(1);
					this.upperapplet.addscore(3-1);
					this.upgrade.trigger();
				}else if(blocks.get(j).getcolor()==1)
				{
					if((this.green-this.black)<4){
					this.green++;
					}
					this.upperapplet.addscore(3-1);
					this.upgrade.trigger();
				}else if(blocks.get(j).getcolor()==2)
				{
					this.black++;
					this.upperapplet.addscore(3-1);
					
				}
				blocks.remove(j);
				this.upperapplet.addcombo(1);
				this.upperapplet.addscore(1);
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
		
		noFill();				// 外框
		strokeWeight(10);
		stroke(167);
		rect(x1, y1, rectWidth, rectHeight);		//x:100~1100
		upperapplet.bgm1.close();;
		if(!ending.isPlaying()){
			ending.play();
		}
		strokeWeight(3);stroke(0);
		
		blocks_end = blocks;
		for(block b:blocks_end)
			b.display();
		
		strokeWeight(0);		
		fill(87,255,87);		//動感棒棒
		rect(x2, y2, 10, 130);
		Ani.killAll();
		
	}
}
