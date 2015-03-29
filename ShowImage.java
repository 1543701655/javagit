package pictureManage;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class ShowImage extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ImageIcon imageIcon = null;
	public Image image = null;
	public static int multiple = 1;
	private int correctValue = 20;
	public ShowImage(){
		
	}
	
	public ShowImage(File file){
		imageIcon = new ImageIcon(file.getAbsolutePath());
		image = imageIcon.getImage();
		if(image.getWidth(null) / (Preview.xSize - correctValue) > image.getHeight(null) / (Preview.ySize - correctValue)){
			if(image.getWidth(null) > Preview.xSize){
				image = image.getScaledInstance((Preview.xSize - correctValue), -1, 1);
			}

		}
		else{
			if(image.getHeight(null) > Preview.ySize){
				image = image.getScaledInstance(-1, (Preview.ySize - correctValue), 1);
			}
		}
		setPreferredSize(new Dimension(image.getWidth(null) * multiple, image.getHeight(null) * multiple));
		//repaint();
		//System.out.println(image.getWidth(null) + " " + image.getHeight(null));
		//setPreferredSize(new Dimension(970, 640));
	}
	
	protected void paintComponent(Graphics g){
		super.paintComponent(g);
		setPreferredSize(new Dimension(image.getWidth(null) * multiple, image.getHeight(null) * multiple));
		g.drawImage(image, getWidth() / 2 - image.getWidth(null) * multiple / 2, getHeight() / 2 - image.getHeight(null) * multiple / 2, image.getWidth(null) * multiple, image.getHeight(null) * multiple, this);
		System.out.println(image.getWidth(null) * multiple + " " + image.getHeight(null) * multiple);
		//System.out.println(image.getWidth(null) + " " + image.getHeight(null));
		//g.drawImage(image, 0, 0, image.getWidth(null) * multiple, image.getHeight(null) * multiple, this);
	}
}
