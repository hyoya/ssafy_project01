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
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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
import com.ssafy.IntroPage;
import javax.swing.SwingConstants;

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
	private JTextArea taMet, taDetail;
	private JPanel pnta;
	private JLabel pntal2;
	private JLabel pntal1;

	/**
	 * Create the frame.
	 */
	public SearchFrame() {
		fsax = new FoodSAX();
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				IntroPage i = new IntroPage();
				setVisible(false);
				i.showFrame();
			}
		});
		setBounds(100, 100, 1500, 900);
		contentPane = new JPanel();
		contentPane.setForeground(Color.WHITE);
		contentPane.setBackground(Color.ORANGE);
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
				//System.out.println(row + " " + col + " " +st.getValueAt(row, col) );
				
				String str = "";
				String destr = "";
				foodNameL.setText(st.getValueAt(row, col).toString());
				for(Foods ff : list) {
					if(ff.getFoodname().equals(st.getValueAt(row, col))) {
						str = ff.getNut();
						destr = ff.getMaterial();
						//foodDetailL.setText(ff.getMaterial().toString());
						try {
							URL u = new URL(ff.getImage());
							Image tmp = ImageIO.read(u);
							foodimg.setIcon(new ImageIcon(tmp));
						} catch (MalformedURLException e1) {
							System.out.println(e1);
						}catch(Exception ee) {
							System.out.println(ee);
						}
					}
				}
				String spp[] = str.split(",");
				taMet.setText("");
				for(int i =0; i<spp.length; i++) {
					taMet.append(spp[i] +"\n");
				}
				
				spp = destr.split(",");
				taDetail.setText("");
				for(int i =0; i<spp.length; i++) {
					taDetail.append(spp[i]+ "\n");
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
		pne.setLayout(new GridLayout(3,1,0,10));
		
		foodimg = new JLabel();
		pne.add(foodimg);
		
		pndetail = new JPanel();
		//pne.add(pndetail);
		pndetail.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		
		foodNameL = new JLabel();
		foodNameL.setHorizontalAlignment(SwingConstants.CENTER);
		pne.add(foodNameL);
		foodNameL.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		
		pnta = new JPanel();
		pnta.setBackground(Color.WHITE);
		pne.add(pnta);
		pnta.setLayout(new GridLayout(2, 2, 0, 0));
		
		pntal1 = new JLabel("영양성분");
		pntal1.setHorizontalAlignment(SwingConstants.CENTER);
		pntal1.setFont(new Font("돋움", Font.PLAIN, 15));
		pnta.add(pntal1);
		
		pntal2 = new JLabel("원재료");
		pntal2.setHorizontalAlignment(SwingConstants.CENTER);
		pntal2.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		pnta.add(pntal2);
		
		taMet = new JTextArea();
		pnta.add(taMet);
		
		taDetail = new JTextArea();
		pnta.add(taDetail);
		
		JPanel pnDetail = new JPanel();
		pnDetail.setLayout(new FlowLayout(FlowLayout.CENTER,5,5));
		
		pnn.setBackground(new Color(255,255,255));
		pnDetail.setBackground(new Color(255,255,255));
		pne.setBackground(new Color(255,255,255));
		pnn.setBackground(new Color(255,255,255));
	}
	

	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "Search");
			putValue(SHORT_DESCRIPTION, "검색하세요!");
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
