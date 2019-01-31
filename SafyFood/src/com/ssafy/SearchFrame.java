package com.ssafy;

import java.awt.BorderLayout;
import java.awt.Dimension;
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
import java.awt.Label;

import javax.swing.JTable;
import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import javax.swing.Action;
import javax.swing.ImageIcon;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JScrollPane;
import java.awt.GridLayout;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.FlowLayout;
import javax.swing.JTextArea;

public class SearchFrame extends JFrame {

	private JPanel contentPane;
	private JTextField tf;
	private JTable st;
	private final Action action = new SwingAction();
	private JButton sb;
	private JComboBox cb;
	private JScrollPane spnc;
	private DefaultTableModel smodel, dmodel;
	private FoodSAX fsax;
	private List<Foods> list;
	private JPanel pne;
	private JPanel pndetail; //디테일을 실을 패널
	private JLabel foodimg, foodNameL, foodDetailL; //순서대로 이미지, 이름, 디테일
	private JTextArea textArea;

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
		st.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) { // 마우스로 클릭하면
				int row = st.getSelectedRow();
				int col = st.getSelectedColumnCount();
				System.out.println(row + " " + col + " " +st.getValueAt(row, col) );
				Foods f;
				String str = "";
				foodNameL.setText(st.getValueAt(row, col).toString());
				for(Foods ff : list) {
					if(ff.getFoodname().equals(st.getValueAt(row, col))) {
						str = ff.getNut();

						foodDetailL.setText(ff.getMaterial().toString());
						try {
							URL u = new URL(ff.getImage());
							Image tmp = ImageIO.read(u);
							//foodimg = new JLabel(new ImageIcon(tmp));
							foodimg.setIcon(new ImageIcon(tmp));
						} catch (MalformedURLException e1) {
							System.out.println(e1);
						}catch(Exception ee) {
							System.out.println(ee);
						}
					}
				}
				String spp[] = str.split(",");
				textArea.setText("");
				for(int i =0; i<spp.length; i++) {
					textArea.append(spp[i] +"\n");
				}				
			}
		});
		
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
		
		spnc = new JScrollPane(st);
		contentPane.add(spnc, BorderLayout.CENTER);
		
		st.setAutoCreateRowSorter(true);
		
		pne = new JPanel();
		contentPane.add(pne, BorderLayout.EAST);
		pne.setLayout(new GridLayout(2,2));
		
		foodimg = new JLabel("Image Not Found");
		pne.add(foodimg);
		
		pndetail = new JPanel();
		pne.add(pndetail);
		pndetail.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		textArea = new JTextArea();
		pndetail.add(textArea);
		
		foodNameL = new JLabel("New label");
		pne.add(foodNameL);
		
		foodDetailL = new JLabel("New label");
		pne.add(foodDetailL);
	}
	
	private void setDetail(String data) {
		String detail[] = {"총용량","칼로리","탄수화물","단백질","지방","당류","나트륨","콜레스테롤","포화 지방산","트랜스지방"};
		String[] sp = data.split(",");
		
		GridBagConstraints[] gbc = new GridBagConstraints[20];
		int idx = 0;
		for(int i =0; i<20; i++) {
			gbc[i]=new GridBagConstraints();
		}
		
		for(int i=0; i<18; i+=2) {
			System.out.println(i);
			gbc[i].gridx = i;
			gbc[i].gridx = 0;
			pndetail.add(new Label(detail[i/2]), gbc[i]);
			
			gbc[i+1].gridx = i+1;
			gbc[i+1].gridx = 1;
			pndetail.add(new Label(detail[i/2+1]), gbc[i+1]);
		}
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
	
	
}
