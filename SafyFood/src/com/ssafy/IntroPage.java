package com.ssafy;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class IntroPage implements ActionListener{
	private JFrame f1;
	private JLabel title;
	private JPanel panel;
	private JButton b1;
	
	
	public IntroPage() {
		createFrame1GUI();
		addEvent();
	}

	public void showFrame() {
		f1.setVisible(true);
	}
	private void addEvent() {
		b1.addActionListener(this);
		
	}
	public void createFrame1GUI() {
		f1 = new JFrame();
		panel = new JPanel();
		b1 = new JButton("검색");
		
		f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f1.setLayout(new BorderLayout());
		
		title = new JLabel("SAFE FOOD");
		title.setFont(new Font("Serif",Font.BOLD,50));
		title.setForeground(Color.RED);
		title.setHorizontalAlignment(SwingConstants.CENTER);
	
		f1.add(title,"North");
		
		panel.setLayout(new FlowLayout());
		panel.add(b1);
		
		f1.add(panel);
		
		f1.setSize(400,200);
		f1.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == b1) {
			SearchPage f2 = new SearchPage();
			f1.setVisible(false);
		}
		
		
	}
	
}
