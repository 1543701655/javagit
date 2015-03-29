package pictureManage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
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

	public static void main(String[] args){
		JFrame frame = new JFrame("Picture Management");
		frame.setLayout(null);
		FileTree fileTree = new FileTree();
		
		JButton jbtOfDele = new JButton("delete");             //-ɾ����ť�Լ�ɾ���ļ���ز���
		jbtOfDele.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(ShowPic.pictures.size() != 0){
				int option = JOptionPane.showConfirmDialog(null, "ȷ��ɾ��ѡ��ͼƬ��","ɾ��ѡ��ͼƬ",JOptionPane.YES_NO_OPTION);
				if(option == JOptionPane.YES_OPTION){
					int count = ShowPic.pictures.size();
					Set<?> keys = fileTree.map.keySet();
					Iterator<?> iterator = keys.iterator( );
				for(int i = 0; i < count; i++){
					//System.out.println(ShowPic.pictures.get(i));
					while(iterator.hasNext( )){
						DefaultMutableTreeNode key = (DefaultMutableTreeNode)iterator.next( );  
						File value = (File)fileTree.map.get(key);  
						//System.out.println(value);
						if(value.equals(ShowPic.pictures.get(i))){
							//System.out.println(key.getParent());
							key.removeFromParent();
							fileTree.tree.updateUI();
						 }
						 //break;
						}
					ShowPic.pictures.get(i).delete();
					iterator = keys.iterator( );
				}
				fileTree.refreshView();
				}
			}
				else{
					File file = fileTree.map.get(FileTree.selectedNode);
					deleteFile(file);
					file.delete();
					FileTree.selectedNode.removeFromParent();
					fileTree.tree.updateUI();
					fileTree.refreshView();
				}
			}
			
		});
		jbtOfDele.setBounds(5, 5, 100, 100);
	    frame.add(jbtOfDele);
	    jbtOfDele.setVisible(true);                             //-
	    
	    
	    JButton jbtOfPreview = new JButton("preview");          //-Ԥ����ť�Լ�Ԥ��ͼƬ����ز���
	    jbtOfPreview.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				ShowImage.multiple = 1;
				Preview.selectedFile = fileTree.map.get(FileTree.selectedNode);
				Preview preview = new Preview();
				preview.setSize(1000, 700);
				preview.setVisible(true);
				
			}
		});
	    
	    jbtOfPreview.setBounds(110, 5, 100, 100);
	    frame.add(jbtOfPreview);
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
