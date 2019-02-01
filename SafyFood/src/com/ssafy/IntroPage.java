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
import java.awt.GridLayout;
import java.awt.CardLayout;
import javax.swing.BoxLayout;

public class IntroPage implements ActionListener{
	private JFrame f1;
	private JLabel title;
	private JPanel panel;
	private JButton b1;
	private JLabel copyright;
	
	
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
		f1.setBackground(Color.ORANGE);
		f1.getContentPane().setBackground(Color.WHITE);
		panel = new JPanel();
		panel.setBackground(Color.WHITE);
		b1 = new JButton("SEARCH");
		b1.setBackground(Color.ORANGE);
		b1.setFont(new Font("궁서체", Font.BOLD, 23));
		
		f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f1.getContentPane().setLayout(new BorderLayout());
		
		title = new JLabel("SAFE FOOD");
		title.setFont(new Font("Serif",Font.BOLD,50));
		title.setForeground(Color.RED);
		title.setHorizontalAlignment(SwingConstants.CENTER);
	
		f1.getContentPane().add(title,"North");
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		panel.add(b1);
		
		f1.getContentPane().add(panel, BorderLayout.CENTER);
		
		copyright = new JLabel("20190201");
		copyright.setHorizontalAlignment(SwingConstants.CENTER);
		f1.getContentPane().add(copyright, BorderLayout.SOUTH);
		
		f1.setSize(400,400);
		f1.setLocation(800, 250);
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
