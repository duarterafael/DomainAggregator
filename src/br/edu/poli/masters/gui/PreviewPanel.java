package br.edu.poli.masters.gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import br.edu.poli.masters.helpers.SimilitaryHelper;
import br.edu.poli.masters.stored.MongoDb;
import br.edu.poli.masters.tags.RootTag;

public class PreviewPanel extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton btnNext;
	private JButton btnSave;
	private MainForm parentFrame;
	private RootTag mergedNode;
	private RootTag newNode;
	
	
	
	public PreviewPanel(final MainForm parentFrame){
		 super(new BorderLayout());
		
		this.parentFrame = parentFrame;
		
	}
	
	private void addButtonMenu(){
		btnNext = new JButton("NEXT >");
	    btnNext.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(SimilitaryHelper.equalIgnoreCaseAndAccentuation(mergedNode.getDomainNode().getTextNode(), newNode.getDomainNode().getTextNode())){
					PreviewPanel.this.parentFrame.nextTab();
				}else{
					JOptionPane.showMessageDialog(PreviewPanel.this,  "The mind maps are different domains.",  "",    JOptionPane.ERROR_MESSAGE);
					PreviewPanel.this.parentFrame.backTab(1);
				}
			}
		});
	    
	    btnSave = new JButton("Save");
	    btnSave.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int selectedOption = JOptionPane.showConfirmDialog(PreviewPanel.this, "Do you wanna save the domain?", "Save", JOptionPane.YES_NO_OPTION); 
				if (selectedOption == JOptionPane.YES_OPTION) {
					parentFrame.getDbInstance().insertNode(newNode);
					PreviewPanel.this.parentFrame.backTab(1);
				}
			}
		});
	    JPanel menuPanel = new JPanel();
	    menuPanel.add(btnNext);
	    menuPanel.add(btnSave);
	    
	    add(menuPanel, BorderLayout.SOUTH);

	}
	
	public void setTreeNodes(RootTag mergedNode, RootTag newNode){
		this.mergedNode = mergedNode;
		this.newNode =  newNode;
		removeAll();
		createTrees();
		addButtonMenu();
		if(mergedNode==null){
			btnNext.setVisible(false);
		}else{
			btnSave.setVisible(false);
		}
	}
	
	private void createTrees(){
		Panel treePanel = new Panel(new GridLayout(1,1));
		Panel labelPanel = new Panel(new GridLayout(1,1));
		if(mergedNode != null){
			treePanel.add(TreePanelHelper.createTree(mergedNode));
			labelPanel.add(new Label("Stored Mind Map"));
			
		}
		if(newNode !=  null){
			
			treePanel.add(TreePanelHelper.createTree(newNode));
			labelPanel.add(new Label("New Mind Map"));
			add(labelPanel, BorderLayout.NORTH);
			add(treePanel, BorderLayout.CENTER);
		}
	}
	
	@Override
	public void repaint() {
		super.repaint();
		createTrees();
	}
	

}
