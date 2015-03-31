package pictureManage;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.plaf.FontUIResource;
import javax.swing.tree.DefaultMutableTreeNode;

public class Method /*implements Runnable*/{
	
	/*public void run(){
		
	}*/
	
	static File returnfile = null;
	//static boolean flag = false;
	public static void CopyFileToOtherPlace(File sourcefile/* 文件 */, File filepath/* 路径 */) throws IOException {

		List<File> list = null;  //存储文件的集合
		File [] file = null;     //存储文件的数组
		//File returnfile = null;
		
		if(filepath.isDirectory()){
			file = filepath.listFiles();
		}
		
		if (file == null) {
			Copy(sourcefile, filepath, false);
			
		}
		else {
			list = Arrays.asList(file); //将文件数组转为List
			
			if (list.contains(new File(filepath.toString() + "\\" + sourcefile.getName().toString()))) {
				CreatFrame(sourcefile, filepath);
//				while(!flag){
//					
//				}
				
//				String[] str_1 = sourcefile.toString().split("\\.");
//				
//				String  str_2 = JOptionPane.showInputDialog(null, "该文件已经存在，请更改文件名", "移动文件", JOptionPane.ERROR_MESSAGE);
//				
//				if(str_2 == null)
//					return;
//				
//				String str_3 = str_2 + "." +str_1[str_1.length - 1];
			//	Copy(sourcefile, filepath, str_3);
			}
			else
			{
				Copy(sourcefile, filepath, false);
			}
		}
		//return null;

	}

	private static int CreatFrame(File sourcefile, File filepath) {

		final Color myColor = new Color(221, 241, 238);  //自己创建一个颜色
		
		//File returnfile = null;
//		File sourcefile = new File("F:\\me\\change.jpg");
		/*
		 * 计算文件的大小
		 */
		String filelength = "";
		if(sourcefile.length() > 1048576)
			filelength = "大小:" + ((long)((sourcefile.length() / 1048576.0)*100) / 100.0) + "MB";
		else if(sourcefile.length() > 1024)
			filelength = "大小:" + ((long)((sourcefile.length() / 1024.0)*100) / 100.0) + "KB";
		else 
			filelength = "大小:" + sourcefile.length() + "B";
		
		/*
		 * 显示文件最后的修改时间
		 */
		Date d = new Date(sourcefile.lastModified());
//		System.out.println(d);
		Time t = new Time(sourcefile.lastModified());
//		System.out.println(t);
		String filemodifydate = "修改日期:" + d.toString() + " " + t.toString();
		
		/*
		 * 显示文件的名字
		 */
		String filename = sourcefile.getName();
		
		/*
		 * "移动和替换"标签
		 */
		JLabel jl_2 = new JLabel("移动和替换");
		jl_2.setFont(new Font("dialog", 1, 16));
		jl_2.setForeground(Color.BLUE);
		
		/*
		 * "请勿移动"标签
		 */
		JLabel jl_3 = new JLabel("请勿移动");
		jl_3.setFont(new Font("dialog", 1, 16));
		jl_3.setForeground(Color.BLUE);
		
		/*
		 * "移动但更改名字"标签
		 */
		JLabel jl_4 = new JLabel("移动但更改名字");
		jl_4.setFont(new Font("dialog", 1, 16));
		jl_4.setForeground(Color.BLUE);
		
		/*
		 * 都是标签
		 */
		final JLabel jl_5 = new JLabel(filename);
		jl_5.setFont(new FontUIResource("dialog", 1, 14));
		final JLabel jl_6 = new JLabel(filelength);
		JLabel jl_7 = new JLabel(filemodifydate);
		
		/*
		 * 都是标签
		 */
		final JLabel jl_8 = new JLabel(filename);
		jl_5.setFont(new FontUIResource("dialog", 1, 14));
		final JLabel jl_9 = new JLabel(filelength);
		JLabel jl_10 = new JLabel(filemodifydate);
		
		/*
		 * 创建一个JFrame窗口
		 */
		JFrame frame = new JFrame("移动文件");
		frame.setSize(350, 450);
		frame.setLocationRelativeTo(null);
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		
		/*
		 * 创建一个容器
		 */
		JPanel jp = new JPanel();
		jp.setLayout(null);
		frame.add(jp);
		
		//开头的标签
		JLabel jl_1 = new JLabel("此位置已经包含同名文件。");
		jl_1.setFont(new Font("dialog", 1, 16));
		jl_1.setForeground(Color.BLUE);
		jl_1.setBounds(0, 0, 350, 30);
		jp.add(jl_1);
		
		//按钮
		JButton jb_1 = new JButton();
		JButton jb_2 = new JButton();
		JButton jb_3 = new JButton();
		JButton jb_4 = new JButton("取消");
		
		//去掉按钮的边框
		jb_1.setBorderPainted(false);
		jb_2.setBorderPainted(false);
		jb_3.setBorderPainted(false);
		
		//设置按钮的背景色
		jb_1.setBackground(Color.WHITE);
		jb_2.setBackground(Color.WHITE);
		jb_3.setBackground(Color.WHITE);
		
		//按钮1中的容器
		jb_1.setLayout(null);
		jl_2.setBounds(20, 0, 350, 30);
		jl_5.setBounds(20, 35, 350, 20);
		jl_6.setBounds(20, 60, 350, 20);
		jl_7.setBounds(20, 85, 350, 20);
		jb_1.add(jl_2);
		jb_1.add(jl_5);
		jb_1.add(jl_6);
		jb_1.add(jl_7);
		
		//按钮2中的容器
		jb_2.setLayout(null);
		jl_3.setBounds(20, 0, 350, 30);
		jl_8.setBounds(20, 35, 350, 20);
		jl_9.setBounds(20, 60, 350, 20);
		jl_10.setBounds(20, 85, 350, 20);
		jb_2.add(jl_3);
		jb_2.add(jl_8);
		jb_2.add(jl_9);
		jb_2.add(jl_10);
		
		//按钮3的容器
		jl_4.setBounds(0, 10, 350, 30);
		jb_3.add(jl_4);
		
		//在大的容器中的按钮的位置
		jb_1.setBounds(0, 35, 350, 120);
		jb_2.setBounds(0, 165, 350, 120);
		jb_3.setBounds(0, 295, 350, 70); 
		jb_4.setBounds(270, 375, 60, 35);
		
		//将按钮添加到容器中
		jp.add(jb_1);
		jp.add(jb_2);
		jp.add(jb_3);
		jp.add(jb_4);
		
		/*
		 * 按钮1的监听器
		 */
		jb_1.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e){
				jb_1.setBackground(myColor);
			}
			
			public void mouseExited(MouseEvent e){
				jb_1.setBackground(Color.WHITE);
			}
			
			public void mouseClicked(MouseEvent e){
				File tempFile = new File(filepath.toString() + "\\" + sourcefile.toString());
				
				Set<?> keys = Main.fileTree.map.keySet();
				DefaultMutableTreeNode tempKey = null;
				Iterator<?> iterator = keys.iterator( );
				//System.out.println(ShowPic.pictures.get(i));
				while(iterator.hasNext( )){
					DefaultMutableTreeNode key = (DefaultMutableTreeNode)iterator.next( );  
					File value = (File)Main.fileTree.map.get(key);  
					//System.out.println(value);
					if(value.getName().equals(tempFile.getName())){
						//System.out.println(key.getParent());
						tempKey = key;
						key.removeFromParent();
						FileTree.tree.updateUI();
					
					 }
					 //break;
					}
				tempFile.delete();
				Main.fileTree.map.remove(tempKey);
				
				try {
					Copy(sourcefile, filepath, false);
					//flag = true;
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				finally{
					frame.dispose();
				}
			}
		});
		
		/*
		 * 按钮2的监听器
		 */
		jb_2.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e){
				jb_2.setBackground(myColor);
			}
			
			public void mouseExited(MouseEvent e){
				jb_2.setBackground(Color.WHITE);
			}
			public void mouseClicked(MouseEvent e){
				frame.dispose();
				//frame.setVisible(false);
			}
		});
		
		/*
		 * 按钮3的监听器
		 */
		jb_3.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e){
				jb_3.setBackground(myColor);
			}
			
			public void mouseExited(MouseEvent e){
				jb_3.setBackground(Color.WHITE);
			}
			public void mouseClicked(MouseEvent e){
				try {
					Copy(sourcefile, filepath, true);
					//flag = true;
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				finally{
					frame.dispose();
					//frame.setVisible(false);
					//System.out.println(returnfile);
				}
			}
		});
		
		/*
		 * "取消"按钮的监听器
		 */
		jb_4.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//System.exit(0);
				//frame.setVisible(false);
				frame.dispose();
				//return;
			}
		});
//		String str = JOptionPane.showInputDialog(null, "该文件已经存在，请更改文件名", "移动文件", JOptionPane.ERROR_MESSAGE);
		//return returnfile;
		return 0;
		}

	private static void Copy(File sourcefile, File filepath, Boolean b) throws IOException {
		/*
		 * 关联文件
		 */
		//FileInputStream fi = new FileInputStream(sourcefile);
		BufferedInputStream bis = new BufferedInputStream(new FileInputStream(sourcefile));
		//BufferedInputStream bis = new BufferedInputStream(fi);
		BufferedOutputStream bos = null;
		
		File[] filelist = null;
		if(filepath.isDirectory()){
			filelist = filepath.listFiles();
		}
		List<File> list = Arrays.asList(filelist);
		//File returnfile = null;
		
		if(b){
			/*
			 * 修改文件名时新文件名的创建
			 */
			String[] str_1 = sourcefile.toString().split("\\.");
			String  str_2 = JOptionPane.showInputDialog(null, "请输入新的文件名", "移动文件", JOptionPane.ERROR_MESSAGE);
			
			//if(str_2 == null) return;//gai
			String str_3 = str_2 + "." +str_1[str_1.length - 1];
			returnfile  = new File(filepath.toString() + "\\" + str_3);
			
			while(list.contains(returnfile)){
				str_2 = JOptionPane.showInputDialog(null, "该名字已经存在", "出错", JOptionPane.ERROR_MESSAGE);
				str_3 = str_2 + "." +str_1[str_1.length - 1];
				returnfile  = new File(filepath.toString() + "\\" + str_3);

			}
			//System.out.println(returnfile);
			bos = new BufferedOutputStream(new FileOutputStream(returnfile));
		}
		else {
			
			returnfile = new File(filepath.toString() + "\\" + sourcefile.getName().toString());
			bos = new BufferedOutputStream(new FileOutputStream(returnfile));
			
		}
		
		/*
		 * 读写文件
		 */
		byte[] buf = new byte[2048];
		int len = 0;
		while ((len = bis.read(buf)) != -1) {
			bos.write(buf, 0, len);
			bos.flush();
		}
		
		bis.close();
		bos.close();
		
		if(Main.fileTree != null){
			DefaultMutableTreeNode temp = new DefaultMutableTreeNode(FileTree.fsv.getSystemDisplayName(returnfile));
			Main.fileTree.map.put(temp, returnfile);
			FileTree.selectedNode.add(temp);
			FileTree.tree.updateUI();
			Main.fileTree.refreshView();
		}
		
	//	return returnfile;
		//System.out.println(returnfile);
	}
	
/*	private static void Copy(File sourcefile, File filepath) throws IOException {
		BufferedInputStream bis = new BufferedInputStream(new FileInputStream(sourcefile));
		
		
		byte[] buf = new byte[2048];
		int len = 0;
		while ((len = bis.read(buf)) != -1) {
			bos.write(buf, 0, len);
			bos.flush();
		}
		
		bis.close();
		bos.close();
	}*/
	
//	public static void main(String[] args) throws IOException{
//		CopyFileToOtherPlace(new File("a.jpg"), new File("F:\\test"));//测试的话，改这里两个
//	}
}
