package finalalpha;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import ddf.minim.AudioPlayer;
import ddf.minim.Minim;

public class MyWindow extends JFrame implements ActionListener{

		private JButton[] button;
		private JMenu menu, gept;
		private JMenuItem[] item;
		private JMenuBar bar;
		private int start;
		private JScrollPane pane;
		private JPanel buttonpanel=new JPanel();
		 JTextArea console;
		 MyPanel panel;
		Windowsound sound;
		JFrame window2;
		JPanel lowpanel;
		mainApplet applet;
		JLabel label=new JLabel("Score: 0");
		public void setlabel(int score){
			label.setText("Score: "+score);
		}
		
		public int getstart(){
			return this.start;
		}
		
		public MyWindow(){
			sound=new Windowsound();
			
			this.setTitle("HomePage");
		
			button = new JButton[2];
			item = new JMenuItem[6];
			button[0] = new JButton("   Single Player   ");
			button[1] = new JButton("          Exit          ");
			menu = new JMenu("          LEVELS          ");
			gept = new JMenu("GEPT");
			item[0] = new JMenuItem("Beginner");
			item[1] = new JMenuItem("Intermediate");
			item[2] = new JMenuItem("Advanced");
			gept.add(item[0]);
			gept.add(item[1]);
			gept.add(item[2]);
			item[3] = new JMenuItem("TOEIC");
			item[4] = new JMenuItem("TOFEL");
			item[5] = new JMenuItem("GRE");
			
			menu.add(gept);
			menu.add(item[3]);
			menu.add(item[4]);
			menu.add(item[5]);
			menu.setPopupMenuVisible(true);
			
			
			console=new JTextArea(10,10);
			console.setEditable(false);
			pane=new JScrollPane(console);
			pane.setHorizontalScrollBarPolicy(  JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
			pane.setVerticalScrollBarPolicy(   JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); 
			bar = new JMenuBar();
			bar.add(menu);
			this.setJMenuBar(bar);
			
			this.setLayout(new FlowLayout(FlowLayout.LEFT, 140, 30));
			this.add(label);
			this.add(button[0]);
			this.add(bar);
			this.add(button[1]);
			this.add(pane);
			
			sound.init();
			sound.start();
			button[0].addActionListener(this);
			button[1].addActionListener(this);
			for(int i = 0; i<6; i++)
				item[i].addActionListener(this);
			
			
		}
			
		public void actionPerformed(ActionEvent event){
				if(event.getSource() == button[0]){
					if(panel!=null)
					{
						panel.getapplet().setisending(false);
						panel.getapplet().bgm1.close();
						panel.getapplet().destroy();
						panel.removeAll();
						panel.setEnabled(false);
						
					}
					if(lowpanel!=null)
					{
						applet.destroy();		
						lowpanel.removeAll();
								lowpanel.setEnabled(false);
					}
					if(window2!=null){
						window2.dispose();
						
						window2=null;
					}
					
					final int windowWidth = 1200;
					final int windowHeight = 1000; 
				sound.bgm.pause();
					this.dispose();
					  panel=new MyPanel(this.getstart());
						lowpanel=new JPanel();
						applet = new mainApplet();
						panel.getapplet().setlow(applet);
						panel.getapplet().window=this;
						applet.setUpperapplet(panel.getapplet());
						applet.init();
						applet.start();
						applet.setFocusable(true);
						lowpanel.add(applet);

					  window2 = new JFrame("GAMING");
				//	window2.setLayout(new GridLayout(2,1));
					  window2.setLayout(new BorderLayout());
					window2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					window2.setSize(windowWidth, windowHeight);
				//	window.setContentPane(applet);
					panel.setlow(applet);
					window2.add(panel,BorderLayout.CENTER);
					window2.add(lowpanel,BorderLayout.SOUTH);
					
					window2.setVisible(true);
				}
				else if(event.getSource() == button[1]){
					if(panel!=null && !panel.getapplet().getstate())
					{
						this.dispose();
					}else{
					System.exit(0);
					}
				}else if(event.getSource() == item[0]){
					menu.setText("   GEPT Beginner   ");
					this.start=0;
					sound.click.trigger();
					if(panel!=null)
					{
						this.panel.getapplet().loadData(this.start);
					}
				}else if(event.getSource() == item[1]){
					menu.setText("GEPT Intermediate");
					this.start=1;
					sound.click.trigger();
					if(panel!=null)
					{
						this.panel.getapplet().loadData(this.start);
					}
				}else if(event.getSource() == item[2]){
					menu.setText("  GEPT Advanced  ");
					this.start=2;
					sound.click.trigger();
					if(panel!=null)
					{
						this.panel.getapplet().loadData(this.start);
					}
				}else if(event.getSource() == item[3]){
					menu.setText("          TOEIC          ");
					this.start=3;
					sound.click.trigger();
					if(panel!=null)
					{
						this.panel.getapplet().loadData(this.start);
					}
				}else if(event.getSource() == item[4]){
					menu.setText("          TOFEL          ");
					this.start=4;
					sound.click.trigger();
					if(panel!=null)
					{
						this.panel.getapplet().loadData(this.start);
					}
				}else if(event.getSource() == item[5]){
					menu.setText("            GRE            ");
					this.start=5;
					sound.click.trigger();
					if(panel!=null)
					{
						this.panel.getapplet().loadData(this.start);
					}
				}
		}
	
}
