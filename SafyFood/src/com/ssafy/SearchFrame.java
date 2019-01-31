package com.ssafy;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
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
import java.util.ArrayList;
import java.util.List;

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
	private FoodSAX fsax;
	private List<Foods> list;

	/**
	 * Create the frame.
	 */
	public SearchFrame() {
		fsax = new FoodSAX();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1500, 500);
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
		smodel = new DefaultTableModel(sheader, 0) {
            // Jtable 내용 편집 안되게
            public boolean isCellEditable(int i, int c) {
                return false;
            }
        };
		
		
		st = new JTable(smodel);
		st.getColumnModel(). setColumnSelectionAllowed (true);
		st.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		st.getColumnModel().getColumn(0).setPreferredWidth(27);
		st.getColumnModel().getColumn(1).setPreferredWidth(150);
		st.getColumnModel().getColumn(2).setPreferredWidth(100);
		st.getColumnModel().getColumn(3).setPreferredWidth(300);
		
		String dheader[] = {"영양성분", "함량"};
		dmodel = new DefaultTableModel(dheader, 0) {
            // Jtable 내용 편집 안되게
            public boolean isCellEditable(int i, int c) {
                return false;
            }
        };
		
		dt = new JTable(dmodel);
		dt.getColumnModel(). setColumnSelectionAllowed (true);
		dt.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		dt.getColumnModel().getColumn(0).setPreferredWidth(50);
		dt.getColumnModel().getColumn(1).setPreferredWidth(50);
		
		spnw = new JScrollPane(st);
		contentPane.add(spnw, BorderLayout.CENTER);
		
		spnc = new JScrollPane(dt);
		contentPane.add(spnc, BorderLayout.EAST);
		
		st.setAutoCreateRowSorter(true);
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
				//TODO
				String sp[];
				list = fsax.getFoodsList();
				
				while(smodel.getRowCount() != 0) {
					smodel.removeRow(0);
				}
				if(tag.equals("상품명")) {
					if(input.equals("")) {
						for(Foods f : list) {
							smodel.addRow(new Object[] {f.getFoodcode(), f.getFoodname(), f.getMaker(), f.getMaterial()});
						}
					}else {
						for(Foods f : list) {
							if(f.getFoodname().contains(input)) {
								smodel.addRow(new Object[] {f.getFoodcode(), f.getFoodname(), f.getMaker(), f.getMaterial()});
							}
						}						
					}
				}else if(tag.equals("제조사")) {
					if(input.equals("")) {
						for(Foods f : list) {
							smodel.addRow(new Object[] {f.getFoodcode(), f.getFoodname(), f.getMaker(), f.getMaterial()});
						}
					}else {
						for(Foods f : list) {
							if(f.getMaker().contains(input)) {
								smodel.addRow(new Object[] {f.getFoodcode(), f.getFoodname(), f.getMaker(), f.getMaterial()});
							}
						}						
					}
				}else if(tag.equals("원재료명")) {
					if(input.equals("")) {
						for(Foods f : list) {
							smodel.addRow(new Object[] {f.getFoodcode(), f.getFoodname(), f.getMaker(), f.getMaterial()});
						}
					}else {
						for(Foods f : list) {
							if(f.getMaterial().contains(input)) {
								smodel.addRow(new Object[] {f.getFoodcode(), f.getFoodname(), f.getMaker(), f.getMaterial()});
							}
						}						
					}
				}
			}
		}
	}
	
	public class Smodel extends DefaultTableModel{
		
		List<Foods> res;
		
		
		public void setRes(List<Foods> res) {
			this.res = res;
		}

		@Override
		public int getRowCount() {
			// TODO Auto-generated method stub
			return res.size();
		}

		@Override
		public Object getValueAt(int row, int column) {
			
			return super.getValueAt(row, column);
		}
		
	}
	
	
}
