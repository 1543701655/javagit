package pictureManage;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class ShowPic extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private ImageIcon imageIcon = null;
	private Image image = null;
	private int width = 0, height = 0;
	private String name = null;
	

	public ShowPic(){
		//repaint();
		//setBackground(Color.BLUE);
		//System.out.println("hello1");
		//setLayout(null);
		//imageIcon = new ImageIcon("123.jpg");
		//image = imageIcon.getImage();
	}
	
	public ShowPic(File file){
		//setBackground(Color.BLUE);
		setBackground(null);
		imageIcon = new ImageIcon(file.getAbsolutePath());
		width = imageIcon.getIconWidth();
		height = imageIcon.getIconHeight();
		image = imageIcon.getImage();
		if(image != null){
		if(width > height)image = image.getScaledInstance(190, -1, 1);
		else image = image.getScaledInstance(-1, 155, 1);
		}
		
		setBorder(BorderFactory.createLineBorder(Color.red));
		
		name = file.getName();
		if(name.length() > 25){
			name = name.substring(0, 25);
			name = name + " бнбн";
		}
		
		addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				if(!getBackground().equals( new Color(48, 194 , 243))){
					setBackground(new Color(48, 194 , 243));
				}
				else setBackground(null);
				
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				//setBackground(new Color(48, 194 , 243));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				//setBackground(null);
			}
			
		});
	}
	
	protected void paintComponent(Graphics g){
		super.paintComponent(g);	
		g.drawImage(image, getWidth() / 2 - image.getWidth(null) / 2, getHeight() / 2 - image.getHeight(null) / 2 - 10, image.getWidth(null), image.getHeight(null), this);
		
		FontMetrics fm = g.getFontMetrics();
		int stringWidth = fm.stringWidth(name);
		int xCoordinate = getWidth()/2 - stringWidth/2;
		
		g.drawString(name, xCoordinate, getHeight() - 10);
	}
	
}

