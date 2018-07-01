package com.china.zjz;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;
import java.util.TimeZone;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;
import java.awt.event.InputEvent;

public class MainFrame extends JFrame {

	private JPanel contentPane;
	private JTextField text_import;   //导入文件
	private JTextField text_export;   //导出文件
	private JTextField text_min;      //最小范围
	private JTextField text_max;      //最大范围
	private JTextField text_rows;     //计算条目
	private String lastImportPath=""; //保存导入文件浏览目录
	private String lastExportPath=""; //保存导出文件浏览目录
	
	// 导入excel后保存的数据
	public ArrayList<ArrayList<Object>> data = new ArrayList<ArrayList<Object>>();
	// 准备写入的结果数据
	public ArrayList<ArrayList<Object>> ans = new ArrayList<ArrayList<Object>>();
	// 还未出结果过的行数
	public ArrayList<Integer> include = new ArrayList<Integer>();
	// 日志区
	private JTextArea text_log;

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
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 961, 647);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu menu = new JMenu("\u64CD\u4F5C");
		menu.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));
		menuBar.add(menu);
		
		JMenuItem mntm_save = new JMenuItem("\u4FDD\u5B58\u914D\u7F6E");
		mntm_save.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 15));
		mntm_save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
		mntm_save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveConfig();
			}
		});
		
		JMenuItem mntm_load = new JMenuItem("\u8BFB\u53D6\u914D\u7F6E");
		mntm_load.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 15));
		mntm_load.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, InputEvent.CTRL_MASK));
		mntm_load.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loadConfig();
			}
		});
		menu.add(mntm_load);
		menu.add(mntm_save);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "\u529F\u80FD\u533A", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setLayout(null);
		panel.setBounds(10, 21, 460, 536);
		contentPane.add(panel);
		
		text_import = new JTextField();
		text_import.setName("import");
		text_import.setText("C:\\Users\\\u7ECF\u6D32\\Desktop\\test(1).xls");
		text_import.setColumns(10);
		text_import.setBounds(141, 54, 172, 32);
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
		button.setBounds(341, 54, 93, 32);
		panel.add(button);
		
		JLabel label = new JLabel("\u5BFC\u5165\u6587\u4EF6");
		label.setFont(new Font("宋体", Font.PLAIN, 16));
		label.setBounds(22, 52, 93, 32);
		panel.add(label);
		
		JLabel label_1 = new JLabel("\u5BFC\u51FA\u6587\u4EF6");
		label_1.setFont(new Font("宋体", Font.PLAIN, 16));
		label_1.setBounds(22, 126, 93, 32);
		panel.add(label_1);
		
		text_export = new JTextField();
		text_export.setName("export");
		text_export.setText("c:\\test\\ans2.xls");
		text_export.setColumns(10);
		text_export.setBounds(141, 128, 172, 32);
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
		button_1.setBounds(341, 128, 93, 32);
		panel.add(button_1);
		
		JLabel label_2 = new JLabel("\u7ED3\u679C\u533A\u95F4");
		label_2.setFont(new Font("宋体", Font.PLAIN, 16));
		label_2.setBounds(22, 205, 93, 32);
		panel.add(label_2);
		
		text_min = new JTextField();
		text_min.setName("min");
		text_min.setText("0.035");
		text_min.setColumns(10);
		text_min.setBounds(141, 207, 50, 32);
		panel.add(text_min);
		
		text_max = new JTextField();
		text_max.setName("max");
		text_max.setText("0.06");
		text_max.setColumns(10);
		text_max.setBounds(263, 205, 50, 32);
		panel.add(text_max);
		
		JLabel label_3 = new JLabel("-", SwingConstants.CENTER);
		label_3.setFont(new Font("宋体", Font.PLAIN, 16));
		label_3.setBounds(212, 205, 37, 32);
		panel.add(label_3);
		
		JLabel label_4 = new JLabel("\u8BA1\u7B97\u884C\u6570");
		label_4.setFont(new Font("宋体", Font.PLAIN, 16));
		label_4.setBounds(22, 274, 93, 32);
		panel.add(label_4);
		
		text_rows = new JTextField();
		text_rows.setName("rows");
		text_rows.setText("4");
		text_rows.setColumns(10);
		text_rows.setBounds(141, 274, 50, 32);
		panel.add(text_rows);
		
		JButton button_2 = new JButton("\u5F00\u59CB");
		button_2.setFont(new Font("宋体", Font.PLAIN, 16));
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				run();
				ExcelUtil.writeExcel(ans, text_export.getText());
			}
		});
		button_2.setBounds(22, 339, 93, 23);
		panel.add(button_2);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(new TitledBorder(null, "\u65E5\u5FD7", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		scrollPane.setBounds(541, 23, 361, 534);
		contentPane.add(scrollPane);
		
		text_log = new JTextArea();
		text_log.setEditable(false);
		scrollPane.setViewportView(text_log);
		
		loadConfig();
	}
	
	// 读取excel文件的数据到arraylist中，使用的是网上别人提供的excel读写函数
	public void readData()
	{
		reset();
		String input = text_import.getText();
		File file = new File(input);
		data = ExcelUtil.readExcel(file);
		// 以下为结果xls中的第一行抬头
		ArrayList<Object> row = new ArrayList<Object>();
		row.add("线路编号");
		row.add("线路名称");
		row.add("损失电量");
		row.add("输入电量");
		row.add("总损失电量");
		row.add("总输入电量");
		row.add("计算结果");
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
	
	// 递归计算结果是否满足要求
	public boolean calc(Path path, int count)
	{
		double min = Double.parseDouble(text_min.getText());
		double max = Double.parseDouble(text_max.getText());
		for (int i=0; i<include.size();i++)
		{
			int row = include.get(i);
			// 如果行已经在父函数中使用过了，就不要再尝试计算了，避免重复
			if (path.used.contains(row))
			{
				continue;
			}
			double out = 0;
			double in = 0;
			// 如果解析出错，将出错行打印到日志中
			try
			{
				out = Double.parseDouble((String)data.get(row).get(7));
				in = Double.parseDouble((String)data.get(row).get(8));
			}catch (Exception e)
			{
				String logstr = "";;
				logstr = String.format("第%d行数据可能存在问题", row);
				addlog(logstr);
				e.printStackTrace();
			}
			path.outsum += out;
			path.insum += in;
			//表示已经到达指定个数，需要比较结果
			if (count==1)
			{
				//分母为零就跳过吧
				if (path.insum == 0)
				{
				
				}
				else 
				{
					path.retsum = path.outsum / path.insum;
					//System.out.println(path.retsum);
					//以下为满足条件的判断 
					if (path.retsum >= min && path.retsum <= max)
					{
						path.used.add(row);
						//已经出结果了，可以把include里的相关条目去除
						include.removeAll(path.used);
						for (int j=0; j<path.used.size() ; j++)
						{
							ArrayList<Object> newrow1 = new ArrayList<Object>();
							int currow= path.used.get(j);
							newrow1.add(data.get(currow).get(0));
							newrow1.add(data.get(currow).get(1));
							newrow1.add(data.get(currow).get(7));
							newrow1.add(data.get(currow).get(8));
							if (j == path.used.size() - 1)
							{
								newrow1.add(path.outsum);
								newrow1.add(path.insum);
								newrow1.add(path.retsum);
							}
							ans.add(newrow1);
						}
						return true;
					}
					//不满足条件，把统计值还原，继续循环下一行
					else
					{
						path.retsum = 0;
						path.outsum -= out;
						path.insum -= in;
						continue;
					}
				}
				//循环结束也不满足条件，就返回失败，让递归上层函数处理
				return false;
			}
			//如果还没到递归底层
			else
			{
				//先将路径添加上
				path.used.add(row);
				//根据下层递归函数的值来处理，如果下层返回成功，表示已经找到一条记录，整个递归可以结束 
				boolean ret = calc(path, count - 1);
				if (ret)
				{
					return true;
				}
				//否则还原统计和路径，继续循环计算，直到找到或者循环结束为止
				path.retsum = 0;
				path.outsum -= out;
				path.insum -= in;
				path.used.remove(path.used.indexOf(row));
				continue;
			}
		}
		//如果一个结果也找不到，返回失败
		return false;
	}
	
	public void run()
	{
		addlog("开始执行");
		readData();
		addlog("读取数据完成，正在计算");
		int count = Integer.parseInt(text_rows.getText());
		Path path = new Path();
		//每次calc，预期得到一个结果，并且include里清除对应的行数。如果得不到结果了，表示全部计算完成了
		while (calc(path, count))
		{
			//path需要重新生成，以避免原来的统计项有干扰
			path = new Path();
		}
		addlog("执行完成");
	}
	
	// 添加日志，日志头部为时间戳
	public void addlog(String str) {
		TimeZone.setDefault(TimeZone.getTimeZone("Asia/Shanghai"));// 定义时区，可以避免虚拟机时间与系统时间不一致的问题
		Date nowTime = new Date();
		SimpleDateFormat matter = new SimpleDateFormat("[yyyy:MM:dd HH-mm-ss]");
		String timestr = matter.format(nowTime);// 方法三：SimpleDateFormat方式，完整输出现在时间
		String oristr = getText_log().getText();
		String newstr = oristr  + timestr + str + "\n";
		getText_log().setText(newstr);
	}
	
	public JTextArea getText_log() {
		return text_log;
	}
	
	//保存配置
	public void saveConfig()
	{
		OutputStream fos;
		try {
			fos = new FileOutputStream("config.properties");
			OrderedProperties props = new OrderedProperties();   
			props.setProperty(text_import.getName(), text_import.getText().trim());
			props.setProperty(text_export.getName(), text_export.getText().trim());
			props.setProperty(text_min.getName(), text_min.getText().trim());
			props.setProperty(text_max.getName(), text_max.getText().trim());
			props.setProperty(text_rows.getName(), text_rows.getText().trim());
			lastImportPath = (lastImportPath == null ? "" : lastImportPath);
			lastExportPath = (lastExportPath == null ? "" : lastExportPath);
			props.setProperty("lastImportPath", lastImportPath);
			props.setProperty("lastExportPath", lastExportPath);
			
			//props.setProperty("test", "123");   
			props.store(fos, "Do not Modify");   
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		addlog("保存配置完毕");
	}
	
	//读取配置
	public void loadConfig()
	{
		Properties props = new Properties(); 
		try {
			props.load(new FileInputStream("config.properties"));
			text_import.setText(props.getProperty(text_import.getName()));
			text_export.setText(props.getProperty(text_export.getName()));
			text_min.setText(props.getProperty(text_min.getName()));
			text_max.setText(props.getProperty(text_max.getName()));
			text_rows.setText(props.getProperty(text_rows.getName()));
			lastImportPath = props.getProperty("lastImportPath");
			lastExportPath = props.getProperty("lastExportPath");
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}   
		addlog("读取配置完毕");
	}
}
