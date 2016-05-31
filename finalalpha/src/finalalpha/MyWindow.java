package finalalpha;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class MyWindow extends JFrame implements ActionListener{

		private JButton[] button;
		private JMenu menu, gept;
		private JMenuItem[] item;
		private JMenuBar bar;
		private int start;
		 MyPanel panel;
		public int getstart(){
			return this.start;
		}
		
		public MyWindow(){
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
			
			bar = new JMenuBar();
			bar.add(menu);
			this.setJMenuBar(bar);
			
			this.setLayout(new FlowLayout(FlowLayout.LEFT, 140, 30));
			this.add(button[0]);
			this.add(bar);
			this.add(button[1]);
			
			button[0].addActionListener(this);
			button[1].addActionListener(this);
			for(int i = 0; i<6; i++)
				item[i].addActionListener(this);
		}
			
		public void actionPerformed(ActionEvent event){
				if(event.getSource() == button[0]){
					final int windowWidth = 1200;
					final int windowHeight = 900; 
					this.dispose();
					  panel=new MyPanel(this.getstart());
						JPanel lowpanel=new JPanel();
						mainApplet applet = new mainApplet();
						panel.getapplet().setlow(applet);
						panel.getapplet().window=this;
						applet.setUpperapplet(panel.getapplet());
						applet.init();
						applet.start();
						applet.setFocusable(true);
						lowpanel.add(applet);

					  JFrame window2 = new JFrame("TESTING");
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
					System.exit(0);
				}else if(event.getSource() == item[0]){
					menu.setText("   GEPT Beginner   ");
					this.start=0;
					if(panel!=null)
					{
						this.panel.getapplet().loadData(this.start);
					}
				}else if(event.getSource() == item[1]){
					menu.setText("GEPT Intermediate");
					this.start=1;
					if(panel!=null)
					{
						this.panel.getapplet().loadData(this.start);
					}
				}else if(event.getSource() == item[2]){
					menu.setText("  GEPT Advanced  ");
					this.start=2;
					if(panel!=null)
					{
						this.panel.getapplet().loadData(this.start);
					}
				}else if(event.getSource() == item[3]){
					menu.setText("          TOEIC          ");
					this.start=3;
					if(panel!=null)
					{
						this.panel.getapplet().loadData(this.start);
					}
				}else if(event.getSource() == item[4]){
					menu.setText("          TOFEL          ");
					this.start=4;
					if(panel!=null)
					{
						this.panel.getapplet().loadData(this.start);
					}
				}else if(event.getSource() == item[5]){
					menu.setText("            GRE            ");
					this.start=5;
					if(panel!=null)
					{
						this.panel.getapplet().loadData(this.start);
					}
				}
		}
	
}
