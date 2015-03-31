package pictureManage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.tree.DefaultMutableTreeNode;

public class Main extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static int lengthOfPic = 0;
	static FileTree fileTree = null;

	public static void main(String[] args){
		JFrame frame = new JFrame("Picture Management");
		frame.setLayout(null);
		fileTree = new FileTree();
		File[] pic = new File[100000];
		JButton jbtOfDele = new JButton("delete");             //-删除按钮以及删除文件相关操作
		jbtOfDele.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(FileTree.selectedNode != null){
				if(ShowPic.pictures.size() != 0){
				int option = JOptionPane.showConfirmDialog(null, "确定删除选中图片？","删除选中图片",JOptionPane.YES_NO_OPTION);
				if(option == JOptionPane.YES_OPTION){
					int count = ShowPic.pictures.size();
					Set<?> keys = fileTree.map.keySet();
					DefaultMutableTreeNode tempKey = null;
					Iterator<?> iterator = keys.iterator( );
				for(int i = 0; i < count; i++){
					//System.out.println(ShowPic.pictures.get(i));
					while(iterator.hasNext( )){
						DefaultMutableTreeNode key = (DefaultMutableTreeNode)iterator.next( );  
						File value = (File)fileTree.map.get(key);  
						//System.out.println(value);
						if(value.equals(ShowPic.pictures.get(i))){
							//System.out.println(key.getParent());
							//tempKey = key;
							key.removeFromParent();
							FileTree.tree.updateUI();
						
						 }
						 //break;
						}
					ShowPic.pictures.get(i).delete();
					Main.fileTree.map.remove(tempKey);
					iterator = keys.iterator( );
				}
				fileTree.refreshView();
				}
			}
				else{
					int option = JOptionPane.showConfirmDialog(null, "确定删除选中文件夹？","删除选中文件夹",JOptionPane.YES_NO_OPTION);
					if(option == JOptionPane.YES_OPTION){
					File file = fileTree.map.get(FileTree.selectedNode);
					deleteFile(file);
					file.delete();
					FileTree.selectedNode.removeFromParent();
					FileTree.tree.updateUI();
					fileTree.refreshView();
					}
				}
			}
			}
			
		});
		jbtOfDele.setBounds(5, 5, 100, 100);
	    frame.add(jbtOfDele);
	    jbtOfDele.setVisible(true);                             //-
	    
	    
	    JButton jbtOfPreview = new JButton("preview");          //-预览按钮以及预览图片的相关操作
	    jbtOfPreview.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(FileTree.selectedNode != null){
				ShowImage.multiple = 1;
				Preview.selectedFile = fileTree.map.get(FileTree.selectedNode);
				Preview preview = new Preview();
				preview.setSize(1000, 700);
				preview.setVisible(true);
				}
				
			}
		});
	    
	    jbtOfPreview.setBounds(110, 5, 100, 100);
	    frame.add(jbtOfPreview);                              //-
	   
		JButton jbtOfCopy = new JButton("Copy");              //-复制按钮以及复制图片的相关操作
		jbtOfCopy.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(FileTree.selectedNode != null){
					int count = ShowPic.pictures.size();
					lengthOfPic = 0;
					for(int i = 0; i < count; i++){
						//pic[i] = new File(ShowPic.pictures.get(i).getAbsolutePath());
						pic[i] = ShowPic.pictures.get(i);
						//System.out.println(pic[i]);
						lengthOfPic++;
					}
					//System.out.println(pic.length);

				//fileTree.refreshView();
				}
			}
			
		});
		jbtOfCopy.setBounds(210, 5, 100, 100);
	    frame.add(jbtOfCopy);                                //-
	    
		JButton jbtOfPaste = new JButton("Paste");              //-黏贴按钮以及黏贴图片的相关操作
		jbtOfPaste.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(FileTree.selectedNode != null){
					File filePath = fileTree.map.get(FileTree.selectedNode);
					for(int i = 0; i < lengthOfPic; i++){
						try {
							//System.out.println(pic[i]);
							
							Method.CopyFileToOtherPlace(pic[i], filePath);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						//System.out.println("hello");
						//DefaultMutableTreeNode temp = new DefaultMutableTreeNode(fsv.getSystemDisplayName());
						
					}

				}
			}
			
		});
		jbtOfPaste.setBounds(310, 5, 100, 100);
	    frame.add(jbtOfPaste);                                //-
	    
	    
	    frame.setVisible(true);
	
		//frame.setLayout(new BorderLayout(5, 10));
		frame.setSize(900, 600);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fileTree.setBounds(0, 160, 900, 400);
		frame.add(fileTree);
		//frame.add(fileTree, BorderLayout.CENTER);		
		fileTree.setVisible(true);
	}
	
	
	private static void deleteFile(File file){
		File[] fileList = file.listFiles();
		for(int i = 0; i < fileList.length; i++){
			if(fileList[i].isDirectory())deleteFile(fileList[i]);
			else fileList[i].delete();
		}
	}
}
