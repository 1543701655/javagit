package pictureManage;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class Main extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static void main(String[] args){
		JFrame frame = new JFrame("Picture Management");
		frame.setLayout(null);
		FileTree fileTree = new FileTree();
		Operation operation = new Operation();
		
		//frame.setLayout(new BorderLayout(5, 10));
		frame.setSize(900, 600);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fileTree.setBounds(0, 160, 900, 400);
		frame.add(fileTree);
		//frame.add(fileTree, BorderLayout.CENTER);		
		frame.add(operation, BorderLayout.NORTH);
		fileTree.setVisible(true);
		operation.setVisible(true);
	}
}
