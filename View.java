package pictureManage;

import java.awt.Color;
import java.awt.Dimension;
import java.io.File;

import javax.swing.JPanel;


public class View extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private ShowPic[] showPics = new ShowPic[100000];
	private int dx = 0,dy = 0;
	private int length = 215, width = 215, height = 0;
	private int count = 0;
	
	private File file;
	private File[] fileList;
	
	public View(){
		setLayout(null);
		setBackground(Color.GREEN);
		//setBounds(205, 0, 670, width);
		setPreferredSize(new Dimension(650, width));
		if(FileTree.staticFile != null){
		file = FileTree.staticFile;
		fileList = null;
		if(file.isDirectory())fileList = file.listFiles();
		
		if(fileList != null){
		
		for(int i = 0; i < fileList.length; i++){
			if(fileList[i].getName().endsWith(".JPG") || fileList[i].getName().endsWith(".jpg") || fileList[i].getName().endsWith(".jpeg") || fileList[i].getName().endsWith(".PNG") || fileList[i].getName().endsWith(".png") || fileList[i].getName().endsWith(".GIF") || fileList[i].getName().endsWith(".gif")){
				showPics[i] = new ShowPic(fileList[i]);
				//System.out.println(this.getWidth());
				showPics[i].setBounds(dx + 5, dy + 5, width, length);
				add(showPics[i]);
				showPics[i].setVisible(true);
				showPics[i] = null;
				
				dx += (width + 2);
				if(dx > 550){
					dx = 0;
					dy += (length + 2);
				}
//				if(dy > width){
//					width += 140;
//					setPreferredSize(new Dimension(650, width));
//				}
				count++;
			}
		}
		
		height = (int)((double)count / 3.0 + 0.9) * (length + 2) + 10;
		if(height > 360){
			setPreferredSize(new Dimension(650, height));
		}
		
		}
			//System.out.println(FileTree.staticFile.getName());
		}
		//setBounds(205, 0, 670, 400);
	
	}
	
	public void clear(){
		if(fileList != null){
		for(int i = 0; i < fileList.length; i++){
			ShowPic.pictures.clear();
			showPics[i] = null;
			fileList[i] = null;
		}
		}
	}


}
