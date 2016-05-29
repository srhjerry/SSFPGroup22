package finalalpha;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import controlP5.ControlP5;

import processing.core.PApplet;
import processing.core.PFont;

import java.util.ArrayList;
import processing.core.PApplet;
import processing.data.JSONArray;
import processing.data.JSONObject;

@SuppressWarnings("serial")
public class Upperapplet extends PApplet{
//	private String path = "gept-basic.json";

    private ArrayList<question> questionlist;
    private ArrayList<question> questionac;
//	private String file = "main/resources/data.json";
	private Random ran = new Random();
	
	JSONObject data;
	JSONObject voc;
	JSONArray word, localword;
	PFont myFont;
	
	//private ArrayList<Character> characters;
	private ControlP5 cp5;
	private int myColorBackground;
	private int qindex=0;
	  private int score=0;
	  private int skipchance=3;
		
		public int getscore(){
			return this.score;
		}
		public void addscore(int i){
			this.score=getscore()+i;
		}
		
	public void setup() {
		System.out.println("HelloWorld");
		size(1200, 570);
		questionlist = new ArrayList<question>();
		questionac = new ArrayList<question>();
		cp5=new ControlP5(this);
		cp5.addButton("skip").setLabel("skip").setPosition(1000,10).setSize(100, 40);
		cp5.addButton("more").setLabel("more").setPosition(1000,10+50).setSize(100, 40);
		myFont = createFont("�з���",100);
		  /* ������J�r���W�١A�u�n�q�����w�˸Ӧۦ�Y�i��� */
		  textFont(myFont);

		loadData();
		if(questionac.size()<1){
			question temp=questionlist.get(qindex);
			questionac.add(temp);
			
			
			
		}
		
		smooth();
	}

	public void more(){
		this.addqindex();
		question temp=questionlist.get(qindex);
		questionac.add(temp);	
		this.clear();
		
	}
	public void skip(){
		if(this.skipchance>0){
		Iterator<question> aciter=questionac.iterator();
		if(aciter.hasNext()){
		question temp=aciter.next();
			this.removequestionac(temp);
		this.addqindex();
		this.addquestionac(this.getquestionlist().get(this.getqindex()));
		this.addqindex();	
		}
		this.skipchance--;
		}
	}
	
	/*
	 * Draw the network here.
	 */
	public void draw(){
		this.clear();
		background(0);
		  pushMatrix();
		  translate(width/2 + 200, height/2);
		  stroke(255);
		  strokeWeight(2);
		  fill(myColorBackground);

		  popMatrix();
		  this.fill(0,255,0);
			textSize(26);
			this.text("Score: "+this.getscore(),0,50);
			this.text("SkipChance: "+this.skipchance,0,100);
		  
		  Iterator<question> iterator = questionac.iterator();
		  while(iterator.hasNext()){
			  question temp=iterator.next();
	//		  this.fill(150);
	//			this.rect(temp.getx()-25, temp.gety()-25, 200, 40);
				
				this.fill(255);
				textSize(26);
				this.text(temp.getword(), temp.getx(), temp.gety());
				
				this.stroke(50);
				if(temp.gety()>=-2 && temp.gety()<350 && temp.getdirection()==0){
					temp.sety(temp.gety()+1);
				}else if(temp.gety()>=350 && temp.getdirection()==0){
					temp.setdirection(1);
				}else if(temp.gety()>0 && temp.getdirection()==1){
					temp.sety(temp.gety()-1);
				}else if(temp.gety()<=0 && temp.getdirection()==1){
					temp.setdirection(0);
				}
		  }
	}
	
	/*
	 * Change the structure randomly when key pressed.
	 */
	
	
	/*
	 * Load the data here.
	 */
	private void loadData(){

		data = loadJSONObject("resources/gept-basic.json");
	    localword=data.getJSONArray("engword");
		word=data.getJSONArray("locword");
		for(int i=0;i<word.size();i++)
		{
			
			question current=new question(word.getString(i),localword.getString(i));
			current.setx(ran.nextInt(900));
			current.sety(10);
			current.setdirection(0);
			questionlist.add(current);
		}
		

		
		 
		
	}
	
	public int getqindex(){
		return this.qindex;
	}
	
	public void addqindex(){
		this.qindex++;
	}
	public void newquestion(){
		this.addquestionac(this.getquestionlist().get(qindex));
		this.addqindex();
	}
	
	public boolean checkqindex(){
		if(this.getqindex()<this.getquestionlist().size()){
			return true;
		}else{
			return false;
		}
	}
	
   public boolean checkac(String locword){
	   Iterator<question> questionaciter = questionac.iterator();
		while(questionaciter.hasNext())
		{
			question temp=questionaciter.next();
			if(temp.getlocword().equals(locword)){
				return true;
				
			}
		}
		return false;
   }


    public void addquestionlist(String word,String locword){
		this.questionlist.add(new question(word,locword));
	}
    public void removequestionac(String locword){
    	Iterator<question> questionaciter = questionac.iterator();
		while(questionaciter.hasNext())
		{
			question temp=questionaciter.next();
			if(temp.getlocword().equals(locword)){
				questionaciter.remove();
				break;
			}
		}
    }
    public void removequestionac(question qa){
    	Iterator<question> questionaciter = questionac.iterator();
		while(questionaciter.hasNext())
		{
			question temp=questionaciter.next();
			if(temp==qa){
				questionaciter.remove();
				break;
			}
		}
    }
    public void removequestionlist(String locword){
    	Iterator<question> questionlistiter = questionlist.iterator();
		while(questionlistiter.hasNext())
		{
			question temp=questionlistiter.next();
			if(temp.getlocword().equals(locword)){
				questionlistiter.remove();
			}
			break;
		}
	}
    public void addquestionac(String word,String locword){
    	question temp=new question(word,locword);
		this.questionac.add(temp);
		Thread thread=new Thread(temp);
		thread.start();
	}
    public void addquestionac(question temp){
    	
		this.questionac.add(temp);
		Thread thread=new Thread(temp);
		thread.start();
	}
	public ArrayList<question> getquestionlist(){
		return  this.questionlist;
	}
	public ArrayList<question> getquestionac(){
		return  this.questionac;
	}

	
	

	
	
}
