package com.china.zjz;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class MainFrame extends JFrame {

	private JPanel contentPane;
	private JTextField text_import;
	private JTextField text_export;
	private JTextField text_min;
	private JTextField text_max;
	private JTextField text_rows;
	private String lastImportPath="";
	private String lastExportPath="";
	
	public ArrayList<ArrayList<Object>> data = new ArrayList<ArrayList<Object>>();
	public ArrayList<ArrayList<Object>> ans = new ArrayList<ArrayList<Object>>();
	public ArrayList<Integer> exclude = new ArrayList<Integer>();
	public ArrayList<Integer> include = new ArrayList<Integer>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
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
	public MainFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 790, 606);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(0, 0, 779, 577);
		contentPane.add(panel);
		
		text_import = new JTextField();
		text_import.setText("C:\\test\\test.xls");
		text_import.setColumns(10);
		text_import.setBounds(186, 54, 172, 32);
		panel.add(text_import);
		
		JButton button = new JButton("\u6D4F\u89C8");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//初始化文件选择框
				JFileChooser fDialog = new JFileChooser(lastImportPath);
				//设置文件选择框的标题 
				fDialog.setDialogTitle("请选择导入文件");
				//弹出选择框
				int returnVal = fDialog.showOpenDialog(null);
				// 如果是选择了文件
				if(JFileChooser.APPROVE_OPTION == returnVal){
				       //打印出文件的路径，你可以修改位 把路径值 写到 textField 中
					System.out.println(fDialog.getSelectedFile());
					text_import.setText(fDialog.getSelectedFile().toString());
					lastImportPath = fDialog.getSelectedFile().toString();
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
		text_export.setText("c:\\test\\ans2.xls");
		text_export.setColumns(10);
		text_export.setBounds(186, 128, 172, 32);
		panel.add(text_export);
		
		JButton button_1 = new JButton("\u6D4F\u89C8");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//初始化文件选择框
				JFileChooser fDialog = new JFileChooser(lastExportPath);
				//设置文件选择框的标题 
				fDialog.setDialogTitle("请选择导出文件");
				//弹出选择框
				int returnVal = fDialog.showOpenDialog(null);
				// 如果是选择了文件
				if(JFileChooser.APPROVE_OPTION == returnVal){
				       //打印出文件的路径，你可以修改位 把路径值 写到 textField 中
					System.out.println(fDialog.getSelectedFile());
					text_export.setText(fDialog.getSelectedFile().toString());
					lastExportPath = fDialog.getSelectedFile().toString();
				}
			}
		});
		button_1.setBounds(436, 130, 93, 32);
		panel.add(button_1);
		
		JLabel label_2 = new JLabel("\u7ED3\u679C\u533A\u95F4");
		label_2.setFont(new Font("宋体", Font.PLAIN, 16));
		label_2.setBounds(67, 205, 93, 32);
		panel.add(label_2);
		
		text_min = new JTextField();
		text_min.setText("0.035");
		text_min.setColumns(10);
		text_min.setBounds(186, 207, 50, 32);
		panel.add(text_min);
		
		text_max = new JTextField();
		text_max.setText("0.06");
		text_max.setColumns(10);
		text_max.setBounds(308, 205, 50, 32);
		panel.add(text_max);
		
		JLabel label_3 = new JLabel("-", SwingConstants.CENTER);
		label_3.setFont(new Font("宋体", Font.PLAIN, 16));
		label_3.setBounds(256, 205, 37, 32);
		panel.add(label_3);
		
		JLabel label_4 = new JLabel("\u8BA1\u7B97\u884C\u6570");
		label_4.setFont(new Font("宋体", Font.PLAIN, 16));
		label_4.setBounds(67, 274, 93, 32);
		panel.add(label_4);
		
		text_rows = new JTextField();
		text_rows.setText("3");
		text_rows.setColumns(10);
		text_rows.setBounds(186, 274, 50, 32);
		panel.add(text_rows);
		
		JButton button_2 = new JButton("\u5F00\u59CB");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				run();
				ExcelUtil.writeExcel(ans, text_export.getText());
//				run(text_import.getText(), text_export.getText(), 
//						Double.parseDouble(text_min.getText()),
//						Double.parseDouble(text_max.getText()),
//						Integer.parseInt(text_rows.getText()));
			}
		});
		button_2.setBounds(67, 339, 93, 23);
		panel.add(button_2);
	}
	
	public void readData()
	{
		reset();
		String input = text_import.getText();
		File file = new File(input);
		data = ExcelUtil.readExcel(file);
		ArrayList<Object> row = new ArrayList<Object>();
		row.add("线路编号");
		row.add("线路名称");
		row.add("损失电量");
		row.add("输入电量");
		ans.add(row); 
		for (int i=1; i<data.size(); i++)
		{
			include.add(i);
		}
	}
	
	public void reset()
	{
		data.clear();
		ans.clear();
		include.clear();
	}
	
	public boolean calc(Path path, int count)
	{
		double min = Double.parseDouble(text_min.getText());
		double max = Double.parseDouble(text_max.getText());
		for (int i=0; i<include.size();i++)
		{
			int row = include.get(i);
			if (path.used.contains(row))
			{
				continue;
			}
			double out = Double.parseDouble((String)data.get(row).get(7));
			double in = Double.parseDouble((String)data.get(row).get(8));
			path.outsum += out;
			path.insum += in;
			if (count==1)
			{
				if (path.insum == 0)
				{
				
				}
				else 
				{
					path.retsum = path.outsum / path.insum;
					if (path.retsum >= min && path.retsum <= max)
					{
						path.used.add(row);
						include.removeAll(path.used);
						for (int j=0; j<path.used.size() ; j++)
						{
							ArrayList<Object> newrow1 = new ArrayList<Object>();
							int currow= path.used.get(j);
							newrow1.add(data.get(currow).get(0));
							newrow1.add(data.get(currow).get(1));
							newrow1.add(data.get(currow).get(7));
							newrow1.add(data.get(currow).get(8));
							ans.add(newrow1);
						}
						ArrayList<Object> newrow4 = new ArrayList<Object>();
						newrow4.add("总计");
						newrow4.add("");
						newrow4.add(2, path.outsum);
						newrow4.add(3, path.insum);
						newrow4.add(4,path.retsum);
						ans.add(newrow4);
						ArrayList<Object> newrow5 = new ArrayList<Object>();
						ans.add(newrow5);
						return true;
					}
					else
					{
						path.retsum = 0;
						path.outsum -= out;
						path.insum -= in;
						continue;
					}
				}
				return false;
			}
			else
			{
				path.used.add(row);
				boolean ret = calc(path, count - 1);
				if (ret)
				{
					return true;
				}
				path.retsum = 0;
				path.outsum -= out;
				path.insum -= in;
				path.used.remove(path.used.indexOf(row));
				continue;
			}
		}
		return false;
	}
	
	public void run()
	{
		readData();
		int count = Integer.parseInt(text_rows.getText());
		Path path = new Path();
		while (calc(path, count))
		{
			path = new Path();
		}
	}
	
	public void run(String input, String output, double min, double max, int calcrows)
	{
		
		int rows=1;
		
		boolean back = false;
	
		for(int i=1; i<data.size(); i++)
		{
			if (exclude.contains(i))
			{
				continue;
			}
			//System.out.println(result.get(i).get(6));
			for (int j=i+1; j<data.size(); j++)
			{
				if (exclude.contains(j))
				{
					continue;
				}
				for (int k=j+1; k<data.size(); k++)
				{
					if (exclude.contains(k))
					{
						continue;
					}
					double out1 = Double.valueOf((String)((data.get(i).get(7))));
					double out2 = Double.valueOf((String)((data.get(j).get(7))));
					double out3 = Double.valueOf((String)((data.get(k).get(7))));
					double out = out1 + out2 + out3;
					double in1 = Double.valueOf((String)((data.get(i).get(8))));
					double in2 = Double.valueOf((String)((data.get(j).get(8))));
					double in3 = Double.valueOf((String)((data.get(k).get(8))));
					double in = in1 + in2 + in3;
					double r = out / in;
					
					if (min<=r && r<=max)
					{
						ArrayList<Object> newrow1 = new ArrayList<Object>();
						newrow1.add(data.get(i).get(0));
						newrow1.add(data.get(i).get(1));
						newrow1.add(data.get(i).get(7));
						newrow1.add(data.get(i).get(8));
						ans.add(newrow1);
						ArrayList<Object> newrow2 = new ArrayList<Object>();
						newrow2.add(data.get(j).get(0));
						newrow2.add(data.get(j).get(1));
						newrow2.add(data.get(j).get(7));
						newrow2.add(data.get(j).get(8));
						ans.add(newrow2);
						ArrayList<Object> newrow3 = new ArrayList<Object>();
						newrow3.add(data.get(k).get(0));
						newrow3.add(data.get(k).get(1));
						newrow3.add(data.get(k).get(7));
						newrow3.add(data.get(k).get(8));
						ans.add(newrow3);
						ArrayList<Object> newrow4 = new ArrayList<Object>();
						newrow4.add("总计");
						newrow4.add("");
						newrow4.add(2, out);
						newrow4.add(3, in);
						newrow4.add(4,r);
						ans.add(newrow4);
						ArrayList<Object> newrow5 = new ArrayList<Object>();
						ans.add(newrow5);
						
						rows += 5;
						exclude.add(i);
						exclude.add(j);
						exclude.add(k);
						if (rows>=60000)
						{
							ExcelUtil.writeExcel(ans, output);
							return;
						}
						System.out.println(rows);
						break;
					}
				}
				if (exclude.contains(j))
				{
					break;
				}
			}
			if (exclude.contains(i))
			{
				continue;
			}
		}
		ExcelUtil.writeExcel(ans, output);
	}
}
