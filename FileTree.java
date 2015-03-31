package pictureManage;

import java.awt.Component;
import java.io.File;
import java.util.HashMap;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeExpansionListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.filechooser.FileSystemView;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;

public class FileTree extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static File staticFile = null;
	public static DefaultMutableTreeNode selectedNode;
	public static FileSystemView fsv = FileSystemView.getFileSystemView();
	public HashMap<DefaultMutableTreeNode, File> map = new HashMap<DefaultMutableTreeNode, File>();
	
	View view = null;
	
	public static JTree tree;
	private File[] temp;
	private File[] fileList = File.listRoots();
	private JScrollPane jsPane = null;
	
	private class MyTreeCellRenderer extends DefaultTreeCellRenderer{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public MyTreeCellRenderer(){
		}
		
		public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected, boolean expanded, boolean leaf, int row, boolean hasFocus){
			
			DefaultMutableTreeNode dmtn = (DefaultMutableTreeNode)value;		
			File file = map.get(dmtn);		
			JLabel jLabel = (JLabel)super.getTreeCellRendererComponent(tree, value, selected, expanded, leaf, row, hasFocus);
			jLabel.setIcon(fsv.getSystemIcon(file));
			return jLabel;
		}
	}
	
	public FileTree(){
	this.setLayout(null);
	DefaultMutableTreeNode root = new DefaultMutableTreeNode("这台电脑");
	tree = new JTree(root);
	for(int i = 0; i <  fileList.length; i++){
		DefaultMutableTreeNode node = new DefaultMutableTreeNode(fsv.getSystemDisplayName(fileList[i]));		
		map.put(node, fileList[i]);
		if(fileList[i].isDirectory()){
			temp = fileList[i].listFiles(new HiddenFileFilter());
		for(int j = 0; j < temp.length; j++){
    		DefaultMutableTreeNode tempNode = new DefaultMutableTreeNode(fsv.getSystemDisplayName(temp[j]));
    		map.put(tempNode, temp[j]);
    		node.add(tempNode);
		}
		}
		root.add(node);
		temp = null;
	}

	tree.setShowsRootHandles(true);
	tree.setRootVisible(true);
	tree.addTreeSelectionListener(new TreeSelectionListener() {
	    public void valueChanged(TreeSelectionEvent evt) {
	    	if( tree.getLastSelectedPathComponent() != null && !tree.getLastSelectedPathComponent().toString().equals("这台电脑")){     //判断用于保证用户点到根节点“这台电脑”不会抛出异常
	    		selectedNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
	    		staticFile = map.get(selectedNode);
	    		if(staticFile.isDirectory()){
	    			refreshView();
//	    		view.setVisible(false);
//	    		jsPane.setVisible(false);
//	    		remove(view);
//	    		remove(jsPane);
//	    		view.clear();
//	    		view = null;
//	    		jsPane = null;
//	    		
//	    		view = new View();
//	    		view.setVisible(true);
//	    		view.setBounds(205, 0, 670, 400);
//	    		//add(view);
//	    		jsPane = new JScrollPane(view, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
//	    		jsPane.setBounds(205, 0, 675, 400);
//	    		add(jsPane);
//	    		jsPane.setVisible(true);
//	    		jsPane.setVisible(false);
//	    		jsPane.setVisible(true);
	    		}
	    	}
	    }
	});
	
	tree.addTreeExpansionListener(new TreeExpansionListener(){
		@Override
		public void treeExpanded(TreeExpansionEvent event) {
			// TODO Auto-generated method stub
	    	//DefaultMutableTreeNode selectedNode;
	    	selectedNode = (DefaultMutableTreeNode)event.getPath().getLastPathComponent();
    		File file = map.get(selectedNode);
	    	if(file != null && file.isDirectory()){
	        int count = selectedNode.getChildCount();
	        DefaultMutableTreeNode[] nodeSon = new DefaultMutableTreeNode[1000];
	        for(int i = 0; i < count; i++){
	        	nodeSon[i] = (DefaultMutableTreeNode) selectedNode.getChildAt(i);
	        }
	    	for(int i = 0; i < count; i++){
	    		File tempFile = map.get(nodeSon[i]);
	    		if(tempFile.isDirectory()){
	    			temp = tempFile.listFiles(new HiddenFileFilter());
	    			if(temp != null){
	    	  for(int j = 0; j < temp.length; j++){
	        		DefaultMutableTreeNode tempNode = new DefaultMutableTreeNode(temp[j].getName());
	        		map.put(tempNode, temp[j]);
	        		nodeSon[i].add(tempNode);
	    		}
	    		}
	    		}
	    		selectedNode.add(nodeSon[i]); 
	    		temp = null;
	    	}    	
    		}
    		else {
    			
    		}
	    	}

		@Override
		public void treeCollapsed(TreeExpansionEvent event) {
			// TODO Auto-generated method stub
			
		}

		
	});
	FileTree.tree.setCellRenderer(new MyTreeCellRenderer());
	JScrollPane jScrollPane = new JScrollPane(tree);
	jScrollPane.setBounds(0, 0, 200, 400);
	
	add(jScrollPane);
	jScrollPane.setVisible(true);
	
	view = new View();
	view.setVisible(true);
	
	
	jsPane = new JScrollPane(view ,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
	jsPane.setBounds(205, 0, 675, 400);
	add(jsPane);
	jsPane.setVisible(true);
	
	}
	
	public void refreshView(){                            //-刷新View面板
		view.setVisible(false);
		jsPane.setVisible(false);
		remove(view);
		remove(jsPane);
		view.clear();
		view = null;
		jsPane = null;
		
		view = new View();
		view.setVisible(true);
		view.setBounds(205, 0, 670, 400);
		//add(view);
		jsPane = new JScrollPane(view, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		jsPane.setBounds(205, 0, 675, 400);
		add(jsPane);
		jsPane.setVisible(true);
		jsPane.setVisible(false);
		jsPane.setVisible(true);
	}                                                    //-


}
