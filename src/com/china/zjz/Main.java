package com.china.zjz;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;

public class Main extends JDialog {
	private JTextField text_import;
	private JTextField text_export;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main dialog = new Main();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the dialog.
	 */
	public Main() {
		setBounds(100, 100, 795, 616);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 779, 577);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		text_import = new JTextField();
		text_import.setBounds(186, 54, 172, 32);
		panel.add(text_import);
		text_import.setColumns(10);
		
		JButton button = new JButton("\u6D4F\u89C8");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//初始化文件选择框
				JFileChooser fDialog = new JFileChooser();
				//设置文件选择框的标题 
				fDialog.setDialogTitle("请选择音频文件");
				//弹出选择框
				int returnVal = fDialog.showOpenDialog(null);
				// 如果是选择了文件
				if(JFileChooser.APPROVE_OPTION == returnVal){
				       //打印出文件的路径，你可以修改位 把路径值 写到 textField 中
					System.out.println(fDialog.getSelectedFile());
					text_import.setText(fDialog.getSelectedFile().toString());
				}
			}
		});
		button.setBounds(436, 56, 93, 32);
		panel.add(button);
		
		JLabel label = new JLabel("\u5BFC\u5165\u6587\u4EF6");
		label.setFont(new Font("宋体", Font.PLAIN, 16));
		label.setBounds(67, 52, 93, 32);
		panel.add(label);
		
		JLabel label_1 = new JLabel("\u5BFC\u51FA\u6587\u4EF6");
		label_1.setFont(new Font("宋体", Font.PLAIN, 16));
		label_1.setBounds(67, 126, 93, 32);
		panel.add(label_1);
		
		text_export = new JTextField();
		text_export.setColumns(10);
		text_export.setBounds(186, 128, 172, 32);
		panel.add(text_export);
		
		JButton button_1 = new JButton("\u6D4F\u89C8");
		button_1.setBounds(436, 130, 93, 32);
		panel.add(button_1);
		
		JLabel label_2 = new JLabel("\u7ED3\u679C\u533A\u95F4");
		label_2.setFont(new Font("宋体", Font.PLAIN, 16));
		label_2.setBounds(67, 205, 93, 32);
		panel.add(label_2);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(186, 207, 50, 32);
		panel.add(textField);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(308, 205, 50, 32);
		panel.add(textField_1);
		
		JLabel label_3 = new JLabel("-", JLabel.CENTER);
		label_3.setFont(new Font("宋体", Font.PLAIN, 16));
		label_3.setBounds(256, 205, 37, 32);
		panel.add(label_3);
		
		JLabel label_4 = new JLabel("\u7ED3\u679C\u533A\u95F4");
		label_4.setFont(new Font("宋体", Font.PLAIN, 16));
		label_4.setBounds(67, 274, 93, 32);
		panel.add(label_4);

	}
}
