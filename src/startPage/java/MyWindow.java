import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class MyWindow extends JFrame implements ActionListener{

		private JButton[] button;
		private JMenu menu, gept;
		private JMenuItem[] item;
		private JMenuBar bar;
		
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
					this.setTitle("Begin");
				}
				else if(event.getSource() == button[1]){
					System.exit(0);
				}else if(event.getSource() == item[0]){
					menu.setText("   GEPT Beginner   ");
				}else if(event.getSource() == item[1]){
					menu.setText("GEPT Intermediate");
				}else if(event.getSource() == item[2]){
					menu.setText("  GEPT Advanced  ");
				}else if(event.getSource() == item[3]){
					menu.setText("          TOEIC          ");
				}else if(event.getSource() == item[4]){
					menu.setText("          TOFEL          ");
				}else if(event.getSource() == item[5]){
					menu.setText("            GRE            ");
				}
		}
	
}
