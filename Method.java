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
	public static void CopyFileToOtherPlace(File sourcefile/* �ļ� */, File filepath/* ·�� */) throws IOException {

		List<File> list = null;  //�洢�ļ��ļ���
		File [] file = null;     //�洢�ļ�������
		//File returnfile = null;
		
		if(filepath.isDirectory()){
			file = filepath.listFiles();
		}
		
		if (file == null) {
			Copy(sourcefile, filepath, false);
			
		}
		else {
			list = Arrays.asList(file); //���ļ�����תΪList
			
			if (list.contains(new File(filepath.toString() + "\\" + sourcefile.getName().toString()))) {
				CreatFrame(sourcefile, filepath);
//				while(!flag){
//					
//				}
				
//				String[] str_1 = sourcefile.toString().split("\\.");
//				
//				String  str_2 = JOptionPane.showInputDialog(null, "���ļ��Ѿ����ڣ�������ļ���", "�ƶ��ļ�", JOptionPane.ERROR_MESSAGE);
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

		final Color myColor = new Color(221, 241, 238);  //�Լ�����һ����ɫ
		
		//File returnfile = null;
//		File sourcefile = new File("F:\\me\\change.jpg");
		/*
		 * �����ļ��Ĵ�С
		 */
		String filelength = "";
		if(sourcefile.length() > 1048576)
			filelength = "��С:" + ((long)((sourcefile.length() / 1048576.0)*100) / 100.0) + "MB";
		else if(sourcefile.length() > 1024)
			filelength = "��С:" + ((long)((sourcefile.length() / 1024.0)*100) / 100.0) + "KB";
		else 
			filelength = "��С:" + sourcefile.length() + "B";
		
		/*
		 * ��ʾ�ļ������޸�ʱ��
		 */
		Date d = new Date(sourcefile.lastModified());
//		System.out.println(d);
		Time t = new Time(sourcefile.lastModified());
//		System.out.println(t);
		String filemodifydate = "�޸�����:" + d.toString() + " " + t.toString();
		
		/*
		 * ��ʾ�ļ�������
		 */
		String filename = sourcefile.getName();
		
		/*
		 * "�ƶ����滻"��ǩ
		 */
		JLabel jl_2 = new JLabel("�ƶ����滻");
		jl_2.setFont(new Font("dialog", 1, 16));
		jl_2.setForeground(Color.BLUE);
		
		/*
		 * "�����ƶ�"��ǩ
		 */
		JLabel jl_3 = new JLabel("�����ƶ�");
		jl_3.setFont(new Font("dialog", 1, 16));
		jl_3.setForeground(Color.BLUE);
		
		/*
		 * "�ƶ�����������"��ǩ
		 */
		JLabel jl_4 = new JLabel("�ƶ�����������");
		jl_4.setFont(new Font("dialog", 1, 16));
		jl_4.setForeground(Color.BLUE);
		
		/*
		 * ���Ǳ�ǩ
		 */
		final JLabel jl_5 = new JLabel(filename);
		jl_5.setFont(new FontUIResource("dialog", 1, 14));
		final JLabel jl_6 = new JLabel(filelength);
		JLabel jl_7 = new JLabel(filemodifydate);
		
		/*
		 * ���Ǳ�ǩ
		 */
		final JLabel jl_8 = new JLabel(filename);
		jl_5.setFont(new FontUIResource("dialog", 1, 14));
		final JLabel jl_9 = new JLabel(filelength);
		JLabel jl_10 = new JLabel(filemodifydate);
		
		/*
		 * ����һ��JFrame����
		 */
		JFrame frame = new JFrame("�ƶ��ļ�");
		frame.setSize(350, 450);
		frame.setLocationRelativeTo(null);
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		
		/*
		 * ����һ������
		 */
		JPanel jp = new JPanel();
		jp.setLayout(null);
		frame.add(jp);
		
		//��ͷ�ı�ǩ
		JLabel jl_1 = new JLabel("��λ���Ѿ�����ͬ���ļ���");
		jl_1.setFont(new Font("dialog", 1, 16));
		jl_1.setForeground(Color.BLUE);
		jl_1.setBounds(0, 0, 350, 30);
		jp.add(jl_1);
		
		//��ť
		JButton jb_1 = new JButton();
		JButton jb_2 = new JButton();
		JButton jb_3 = new JButton();
		JButton jb_4 = new JButton("ȡ��");
		
		//ȥ����ť�ı߿�
		jb_1.setBorderPainted(false);
		jb_2.setBorderPainted(false);
		jb_3.setBorderPainted(false);
		
		//���ð�ť�ı���ɫ
		jb_1.setBackground(Color.WHITE);
		jb_2.setBackground(Color.WHITE);
		jb_3.setBackground(Color.WHITE);
		
		//��ť1�е�����
		jb_1.setLayout(null);
		jl_2.setBounds(20, 0, 350, 30);
		jl_5.setBounds(20, 35, 350, 20);
		jl_6.setBounds(20, 60, 350, 20);
		jl_7.setBounds(20, 85, 350, 20);
		jb_1.add(jl_2);
		jb_1.add(jl_5);
		jb_1.add(jl_6);
		jb_1.add(jl_7);
		
		//��ť2�е�����
		jb_2.setLayout(null);
		jl_3.setBounds(20, 0, 350, 30);
		jl_8.setBounds(20, 35, 350, 20);
		jl_9.setBounds(20, 60, 350, 20);
		jl_10.setBounds(20, 85, 350, 20);
		jb_2.add(jl_3);
		jb_2.add(jl_8);
		jb_2.add(jl_9);
		jb_2.add(jl_10);
		
		//��ť3������
		jl_4.setBounds(0, 10, 350, 30);
		jb_3.add(jl_4);
		
		//�ڴ�������еİ�ť��λ��
		jb_1.setBounds(0, 35, 350, 120);
		jb_2.setBounds(0, 165, 350, 120);
		jb_3.setBounds(0, 295, 350, 70); 
		jb_4.setBounds(270, 375, 60, 35);
		
		//����ť��ӵ�������
		jp.add(jb_1);
		jp.add(jb_2);
		jp.add(jb_3);
		jp.add(jb_4);
		
		/*
		 * ��ť1�ļ�����
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
		 * ��ť2�ļ�����
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
		 * ��ť3�ļ�����
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
		 * "ȡ��"��ť�ļ�����
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
//		String str = JOptionPane.showInputDialog(null, "���ļ��Ѿ����ڣ�������ļ���", "�ƶ��ļ�", JOptionPane.ERROR_MESSAGE);
		//return returnfile;
		return 0;
		}

	private static void Copy(File sourcefile, File filepath, Boolean b) throws IOException {
		/*
		 * �����ļ�
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
			 * �޸��ļ���ʱ���ļ����Ĵ���
			 */
			String[] str_1 = sourcefile.toString().split("\\.");
			String  str_2 = JOptionPane.showInputDialog(null, "�������µ��ļ���", "�ƶ��ļ�", JOptionPane.ERROR_MESSAGE);
			
			//if(str_2 == null) return;//gai
			String str_3 = str_2 + "." +str_1[str_1.length - 1];
			returnfile  = new File(filepath.toString() + "\\" + str_3);
			
			while(list.contains(returnfile)){
				str_2 = JOptionPane.showInputDialog(null, "�������Ѿ�����", "����", JOptionPane.ERROR_MESSAGE);
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
		 * ��д�ļ�
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
//		CopyFileToOtherPlace(new File("a.jpg"), new File("F:\\test"));//���ԵĻ�������������
//	}
}
