package br.edu.poli.masters.gui;

import java.awt.Component;

import javax.swing.ImageIcon;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeCellRenderer;

public class RenderizarTree  extends DefaultTreeCellRenderer implements TreeCellRenderer {  
      
    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected, boolean expanded, boolean leaf, int row, boolean hasFocus) {  
        super.getTreeCellRendererComponent(tree, value, selected, expanded, leaf, row, hasFocus);  
          
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;  
        setIcon(new ImageIcon("./res/images/area.png"));  
          
        return this;  
    }  

}
