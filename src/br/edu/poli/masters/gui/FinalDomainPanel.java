package br.edu.poli.masters.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import br.edu.poli.masters.tags.RootTag;

public class FinalDomainPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private RootTag mergedNode;
	private JButton btnExport, btnSave;
	private MainForm parentFrame;

	public FinalDomainPanel(final MainForm parentFrame){
		super(new BorderLayout());
		
		this.parentFrame = parentFrame;
		
		btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				parentFrame.getDbInstance().updateDomain(mergedNode);
				parentFrame.backTab(3);
				
			}
		});
		
		
		btnExport = new JButton("Export");
	    btnExport.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			}
		});
	    Panel p = new Panel(new FlowLayout());
	    p.add(btnSave);
	    p.add(btnExport);
	    add(p, BorderLayout.SOUTH);
	}
	
	private void createTree(){
		if(mergedNode != null){
			Panel treePanel = new Panel(new GridLayout(1,1));
			treePanel.add(TreePanelHelper.createTree(mergedNode));
			Panel labelPanel = new Panel(new GridLayout(1,1));
			labelPanel.add(new Label("Merged Mind Map"));
			add(labelPanel, BorderLayout.NORTH);
			add(treePanel, BorderLayout.CENTER);
		}
	}
	


	public void setMergedNode(RootTag mergedNode) {
		this.mergedNode = mergedNode;
		createTree();
	}
	
	

}
