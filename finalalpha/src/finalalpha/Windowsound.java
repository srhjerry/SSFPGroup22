package finalalpha;

import ddf.minim.AudioPlayer;
import ddf.minim.AudioSample;
import ddf.minim.Minim;
import processing.core.PApplet;

public class Windowsound extends PApplet{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6036546928129829584L;
	Minim minim;
    AudioSample click;
    AudioSample dooom;
    AudioPlayer bgm;
    
    public void setup(){
    	this.setVisible(false);
    	minim=new Minim(this);
    	bgm=minim.loadFile("resources/main.wav");
		bgm.loop();
		bgm.play();
		
		 click = minim.loadSample("resources/m_join.wav");

    }
    public void draw(){
    	
    }
    
}
