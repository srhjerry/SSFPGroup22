package finalalpha;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class question extends Thread{
	private BufferedImage image;
	private String word;
	private String locword;
	private String filepath;
	private int x;
	private int y;
	private int direction;
	public question(String word,String locword){
		this.word=word;
		this.locword=locword;
		
	//	this.run();
	}
	public String getword(){
		return this.word;
	}
	public String getlocword(){
		return this.locword;
	}
	public int getx(){
		return this.x;
	}
	public int gety(){
		return this.y;
	}
	public int getdirection(){
		return this.direction;
	}

	public void setword(String word){
		this.word=word;
	}
	public void setlocword(String locword){
		this.locword=locword;
	}
	public void setfilepath(String filepath){
		this.filepath=filepath;
	}
	public String getfilepath(){
		return this.filepath;
	}
	public void setx(int x){
		this.x=x;
	}
	public void sety(int y){
		this.y=y;
	}
	public void setdirection(int direction){
		this.direction=direction;
	}
	public Image getImage(String filepath){
		File originalImgFile;
		
			originalImgFile = new File(filepath);
		try {
			
			this.image=ImageIO.read(originalImgFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return image;
	}
	public void run(){
	if(this.gety()>=-2 && this.getdirection()==0){
		this.sety(this.gety()+1);
	}else if(this.gety()>=550 && this.getdirection()==0){
		this.setdirection(1);
	}else if(this.gety()>0 && this.getdirection()==1){
		this.sety(this.gety()-1);
	}else if(this.gety()<=0 && this.getdirection()==1){
		this.setdirection(0);
	}
		
		
	}	
	
}
