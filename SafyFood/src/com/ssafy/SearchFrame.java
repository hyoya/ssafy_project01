package com.ssafy;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTable;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import javax.swing.Action;
import java.awt.event.ActionListener;
import javax.swing.JScrollPane;

public class SearchFrame extends JFrame {

	private JPanel contentPane;
	private JTextField tf;
	private JTable st;
	private final Action action = new SwingAction();
	private JButton sb;
	private JComboBox cb;
	private JTable dt;
	private JScrollPane spnw;
	private DefaultTableModel smodel, dmodel;
	private JScrollPane spnc;
	

	/**
	 * Create the frame.
	 */
	public SearchFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel pnn = new JPanel();
		contentPane.add(pnn, BorderLayout.NORTH);
		
		String[] type = {"상품명", "제조사", "원재료명"};
		cb = new JComboBox(type);
		cb.setEditable(true);
		pnn.add(cb);
		
		tf = new JTextField();
		pnn.add(tf);
		tf.setColumns(20);
		
		sb = new JButton("Search");
		sb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		sb.setAction(action);
		pnn.add(sb);
		
		String sheader[] = {"상품 코드", "상품이름", "제조사", "원재료"};
		smodel = new DefaultTableModel(sheader, 0);
		smodel.addRow(new Object[] {"상품코드","상품이름","!23123","adsfasdfasdf"});
		
		
		st = new JTable(smodel);
		
		String dheader[] = {"영양성분", "함량"};
		dmodel = new DefaultTableModel(dheader, 0);
		dmodel.addRow(new Object[] {"dafd", "adfas"});
		
		dt = new JTable(dmodel);
		
		spnw = new JScrollPane(st);
		contentPane.add(spnw, BorderLayout.WEST);
		
		spnc = new JScrollPane(dt);
		contentPane.add(spnc, BorderLayout.CENTER);
		
		
	}

	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "SwingAction");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == sb) { //서치 버튼을 눌렀다면
				String tag = cb.getSelectedItem().toString();
				String input = tf.getText();
				
				
				//TODO tag와 input을 받아서 서치
			}
		}
	}
	
	
}
