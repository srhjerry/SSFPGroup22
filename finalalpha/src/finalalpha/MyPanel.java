package finalalpha;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;

import javax.imageio.ImageIO;
import javax.swing.*;

import ddf.minim.AudioInput;
import ddf.minim.AudioSample;
import ddf.minim.Minim;

/*
import processing.awt.PSurfaceAWT;
import processing.awt.PSurfaceAWT.SmoothCanvas;
import processing.core.PSurface;
*/




public class MyPanel extends JPanel implements Runnable,ActionListener,KeyListener {
	private Upperapplet applet;
    private ArrayList<question> storedlist;
    private JTextField textfield;
    private JButton buttona;
    private JPanel Southpanel;
    private boolean start=false;
    private mainApplet low;
    
    
  
	public Upperapplet getapplet(){
		return this.applet;
	}
	
	public MyPanel(int start){
	//   questionlist=new ArrayList<question>(); 
		
	   this.textfield=new JTextField();
	   this.textfield.setSize(1000, 100);
	   this.textfield.setEditable(true);
	   this.textfield.addActionListener(this);
	   buttona=new JButton("Enter");
	   buttona.setSize(40, 40);
	   buttona.addActionListener(this);
	   Southpanel=new JPanel();
	   Southpanel.setLayout(new GridLayout(2,1));
	   Southpanel.setSize(1200, 150);
	   Southpanel.add(textfield);
	   Southpanel.add(buttona);
	    this.applet = new Upperapplet();
	    this.applet.setdata(start);
		 this.textfield.addKeyListener(this);
	  
	   this.applet.setFocusable(true);
		 this.setLayout(new BorderLayout());
		this.add(this.applet,BorderLayout.CENTER);
	   this.applet.init();
	   this.applet.start();
		//applet.start();
		this.applet.setFocusable(true);
	  
	   this.add(Southpanel,BorderLayout.SOUTH);
	  
		this.setVisible(true);
	}
	public void setlow(mainApplet lower){
		this.low=lower;
	}
/*
	protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        	setBackground(Color.CYAN);
 
     //   		for(question i:applet.getquestionlist()){
   //     	g.drawImage(i.getImage(i.getfilepath()),i.getx(),i.gety(),null);}
  
    }
*/
	@Override
	public void run() {
		// TODO Auto-generated method stub
	//	 this.applet.init();
	//   this.applet.start();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==buttona )
		{
			pressed();
		}
	}
	public void pressed(){
		String temp=textfield.getText();
			if(this.applet.checkac(temp)){
				this.applet.click.trigger();
				this.applet.removequestionac(temp);
				this.applet.addqindex();
				this.applet.addquestionac(this.applet.getquestionlist().get(this.applet.getqindex()));
				this.applet.removequestionlist(this.applet.getqindex());
				this.applet.addqindex();
				low.setbarct(true);
				this.textfield.setText("");
			}else{
				applet.resetcombo();
				JOptionPane.showMessageDialog(null," Hint: First word is "+this.applet.getquestionac().get(0).getlocword().charAt(0)+"\n"+"Last word is "+this.applet.getquestionac().get(0).getlocword().charAt(this.applet.getquestionac().get(0).getlocword().length()-1),
                        "Wrong", JOptionPane.WARNING_MESSAGE);
			}
	}
   
@Override
public void keyPressed(KeyEvent e) {
	// TODO Auto-generated method stub
	if(e.getKeyChar()==KeyEvent.VK_ENTER)
	pressed();
}
@Override
public void keyReleased(KeyEvent arg0) {
	// TODO Auto-generated method stub
	
}
@Override
public void keyTyped(KeyEvent arg0) {
	// TODO Auto-generated method stub
	
}
}
