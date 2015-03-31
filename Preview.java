package pictureManage;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.Timer;

public class Preview extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public final static int xSize = 982, ySize = 602;
	public static File selectedFile = null;
	
	private JButton jbtAddMultiple = new JButton("+");
	private JButton jbtRedMultiple = new JButton("-");
	private JButton jbtNext = new JButton("Next");
	private JButton jbtPre = new JButton("Previous");
	private JButton jbtAuto = new JButton("Start");
	private JButton jbtExit = new JButton("Exit");
 	private Timer timer = null;
	private ShowImage showImage = null;
	private JScrollPane jscrollPanel = null;
	private File[] fileList = null;
	private int i = 0;

	public Preview(){ 
		if(selectedFile.isDirectory()){
		fileList = selectedFile.listFiles();
		setLayout(null);
		if(ShowPic.pictures.size() == 0){
			showImage = new ShowImage(fileList[i]);
			jscrollPanel = new JScrollPane(showImage,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
			jscrollPanel.setSize(xSize, ySize );
			jscrollPanel.setBounds(0, 60, xSize, ySize);
			add(jscrollPanel);
		}
		else if(ShowPic.pictures.size() == 1){
			for(i = 0; i < fileList.length; i++){
				if(isPicture(fileList[i])){
					//System.out.println(fileList[i].getName());
					if(fileList[i].equals(ShowPic.pictures.get(0)))break;
				}
			}
			showImage = new ShowImage(fileList[i]);
			jscrollPanel = new JScrollPane(showImage,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
			jscrollPanel.setSize(xSize, ySize );
			jscrollPanel.setBounds(0, 60, xSize, ySize);
			add(jscrollPanel);
			//add(showImage);
		}
		else{
			fileList = ShowPic.pictures.toArray(new File[0]);
			showImage = new ShowImage(fileList[i]);
			jscrollPanel = new JScrollPane(showImage,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
			jscrollPanel.setSize(xSize, ySize );
			jscrollPanel.setBounds(0, 60, xSize, ySize);
			add(jscrollPanel);
		}
		
		jbtAddMultiple.setBounds(0, 0, 50, 50);
		add(jbtAddMultiple);
		jbtAddMultiple.setVisible(true);
		jbtAddMultiple.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				timer.stop();jbtAuto.setText("Start");
				ShowImage.multiple += 1;
				//System.out.println(showImage.multiple);
				showImage.setPreferredSize(new Dimension(showImage.image.getWidth(null) * ShowImage.multiple, showImage.image.getHeight(null) * ShowImage.multiple));
				showImage.repaint();

				jscrollPanel.setVisible(false);
				remove(jscrollPanel);
				showImage = new ShowImage(fileList[i]);
				jscrollPanel = new JScrollPane(showImage,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
				jscrollPanel.setSize(xSize, ySize);
				jscrollPanel.setBounds(0, 60, xSize, ySize);
				add(jscrollPanel);
				jscrollPanel.setVisible(false);
				jscrollPanel.setVisible(true);
			}
			
		});
		
		jbtRedMultiple.setBounds(50, 0, 50, 50);
		add(jbtRedMultiple);
		jbtRedMultiple.setVisible(true);
		jbtRedMultiple.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(ShowImage.multiple > 1){
				timer.stop();jbtAuto.setText("Start");
				ShowImage.multiple -= 1;
				//System.out.println(showImage.multiple);
				showImage.setPreferredSize(new Dimension(showImage.image.getWidth(null) * ShowImage.multiple, showImage.image.getHeight(null) * ShowImage.multiple));
				showImage.repaint();

				jscrollPanel.setVisible(false);
				remove(jscrollPanel);
				showImage = new ShowImage(fileList[i]);
				jscrollPanel = new JScrollPane(showImage,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
				jscrollPanel.setSize(xSize, ySize);
				jscrollPanel.setBounds(0, 60, xSize, ySize);
				add(jscrollPanel);
				jscrollPanel.setVisible(false);
				jscrollPanel.setVisible(true);
				}
			}
			
		});
		jbtNext.setBounds(100, 0, 100, 50);
		add(jbtNext);
		jbtNext.setVisible(true);
		jbtNext.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				timer.stop();jbtAuto.setText("Start");
				i++;
				if(i >= fileList.length)i = 0;
				ShowImage.multiple = 1;
				jscrollPanel.setVisible(false);
				remove(jscrollPanel);
				showImage = new ShowImage(fileList[i]);
				jscrollPanel = new JScrollPane(showImage,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
				jscrollPanel.setSize(xSize, ySize);
				jscrollPanel.setBounds(0, 60, xSize, ySize);
				add(jscrollPanel);
				jscrollPanel.setVisible(false);
				jscrollPanel.setVisible(true);
			}
			
		});
		
		jbtPre.setBounds(200, 0, 100, 50);
		add(jbtPre);
		jbtPre.setVisible(true);
		jbtPre.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				timer.stop();jbtAuto.setText("Start");
				i--;
				if(i < 0)i = fileList.length - 1;
				ShowImage.multiple = 1;
				jscrollPanel.setVisible(false);
				remove(jscrollPanel);
				showImage = new ShowImage(fileList[i]);
				jscrollPanel = new JScrollPane(showImage,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
				jscrollPanel.setSize(xSize, ySize);
				jscrollPanel.setBounds(0, 60, xSize, ySize);
				add(jscrollPanel);
				jscrollPanel.setVisible(false);
				jscrollPanel.setVisible(true);
			}
			
		});
		
		timer = new Timer(1000, new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				i++;
				if(i >= fileList.length)i = 0;
				ShowImage.multiple = 1;
				jscrollPanel.setVisible(false);
				remove(jscrollPanel);
				showImage = new ShowImage(fileList[i]);
				jscrollPanel = new JScrollPane(showImage,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
				jscrollPanel.setSize(xSize, ySize);
				jscrollPanel.setBounds(0, 60, xSize, ySize);
				add(jscrollPanel);
				jscrollPanel.setVisible(false);
				jscrollPanel.setVisible(true);
			}
			
		});
		
		jbtAuto.setBounds(300, 0, 100, 50);
		add(jbtAuto);
		jbtAuto.setVisible(true);
		jbtAuto.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(!timer.isRunning()){timer.start();jbtAuto.setText("Stop");}
				else{
					timer.stop();jbtAuto.setText("Start");
				}
			}
			
		});
		
		jbtExit.setBounds(400, 0, 100, 50);
		add(jbtExit);
		jbtExit.setVisible(true);
		jbtExit.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				setVisible(false);
			}
			
		});
		
	}
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private boolean isPicture(File file){
		if(file.getName().endsWith(".jpg") || file.getName().endsWith(".JPG") || file.getName().endsWith(".png") || file.getName().endsWith(".PNG") || file.getName().endsWith(".jpeg") || file.getName().endsWith(".JPEG"))
			return true;
		return false;
	}
}
