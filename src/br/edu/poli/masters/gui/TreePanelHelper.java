package br.edu.poli.masters.gui;

import java.util.Enumeration;

import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

import br.edu.poli.masters.tags.NodeTag;
import br.edu.poli.masters.tags.RootTag;

public class TreePanelHelper {

	
	
	public static  JScrollPane createTree(RootTag rootRag){
		JTree tree = new JTree(getDefaultMutableTreeNode(rootRag.getDomainNode()));
		expandAll(tree);
		tree.setCellRenderer(new RenderizarTree()); 
	    JScrollPane scrollPane = new JScrollPane(tree);
		return scrollPane;
	}
	
	private static DefaultMutableTreeNode getDefaultMutableTreeNode(NodeTag node){
		 DefaultMutableTreeNode treenode = new DefaultMutableTreeNode(node.getTextNode());


		for (NodeTag chieldNode : node.getNodesChildren()) {
			treenode.add(getDefaultMutableTreeNode(chieldNode));
		}
		return treenode;
	}
	
	public static void expandAll(JTree tree) {
	    TreeNode root = (TreeNode) tree.getModel().getRoot();
	    expandAll(tree, new TreePath(root));
	  }

	  private static void expandAll(JTree tree, TreePath parent) {
	    TreeNode node = (TreeNode) parent.getLastPathComponent();
	    if (node.getChildCount() >= 0) {
	      for (Enumeration e = node.children(); e.hasMoreElements();) {
	        TreeNode n = (TreeNode) e.nextElement();
	        TreePath path = parent.pathByAddingChild(n);
	        expandAll(tree, path);
	      }
	    }
	    tree.expandPath(parent);
	    // tree.collapsePath(parent);
	  }
	

}
