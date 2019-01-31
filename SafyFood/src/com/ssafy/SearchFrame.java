package com.ssafy;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
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

public class SearchFrame extends JFrame {

	private JPanel contentPane;
	private JTextField tf;
	private JTable st;
	private JTable table;
	private final Action action = new SwingAction();
	private JButton sb;
	private JComboBox cb;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SearchFrame frame = new SearchFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public SearchFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel pnn = new JPanel();
		contentPane.add(pnn, BorderLayout.NORTH);
		
		cb = new JComboBox();
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
		
		JPanel pnc = new JPanel();
		contentPane.add(pnc, BorderLayout.CENTER);
		pnc.setLayout(new BorderLayout(0, 0));
		
		st = new JTable();
		st.setColumnSelectionAllowed(true);
		pnc.add(st, BorderLayout.CENTER);
		
		JPanel pne = new JPanel();
		contentPane.add(pne, BorderLayout.EAST);
		pne.setLayout(new BorderLayout(0, 0));
		
		table = new JTable();
		pne.add(table);
	}

	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "SwingAction");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == sb) {
				String tag = cb.getSelectedItem().toString();
				String input = tf.getText();
				
				//TODO tag와 input으로 검색하기
			}
		}
	}
}
