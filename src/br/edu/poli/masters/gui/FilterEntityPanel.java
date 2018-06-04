package br.edu.poli.masters.gui;

import java.awt.BorderLayout;
import java.awt.Checkbox;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import br.edu.poli.masters.managers.MergManager;
import br.edu.poli.masters.tags.NodeTag;
import br.edu.poli.masters.tags.RootTag;

public class FilterEntityPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private MainForm parentFrame;
	private RootTag mergedNode;
	private RootTag newNode;
	private MergManager aggregatorDomainManager;
	private HashMap<NodeTag, List<NodeTag>> similitaryEntity;
	private JButton btnNext;
	private List<JCheckBox> checkboxSimilitatyEntityList;
	private static final String isSimilarStr  = " is similar ";
	
	
	public FilterEntityPanel(final MainForm parentFrame){
		 super(new BorderLayout());
		 this.parentFrame = parentFrame;
		 
		 btnNext = new JButton("NEXT >");
		    btnNext.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
						mergSelectedEntities();
						parentFrame.nextTab();
				}
			});
		 add(new Label("Was detected similarity between entities, select those that you want to perform merg."), BorderLayout.NORTH);
		 add(btnNext, BorderLayout.SOUTH);
		 aggregatorDomainManager = new MergManager();
	}
	
	private void mergSelectedEntities(){
		for (JCheckBox checkbox : checkboxSimilitatyEntityList) {
			if(checkbox.isSelected()){
				String storedEntity = checkbox.getText().substring(0, checkbox.getText().indexOf(isSimilarStr));
				String newEntity = checkbox.getText().substring(checkbox.getText().indexOf(isSimilarStr)+isSimilarStr.length(), checkbox.getText().length()-1);
				
				NodeTag storedNode = null;
				for (NodeTag node : similitaryEntity.keySet()) {
					if(node.getTextNode().equalsIgnoreCase(storedEntity)){
						storedNode = node;
						break;
					}
				}
				
				for (NodeTag node : similitaryEntity.get(storedNode)) {
					if(node.getTextNode().equalsIgnoreCase(newEntity)){
						aggregatorDomainManager.mergAtribute(storedNode, node);
					}
				}
			}
		}
	}
	
	public void setTreeNodes(RootTag mergedNode, RootTag newNode){
		
		try {
			this.mergedNode = mergedNode;
			this.newNode =  newNode;
			aggregatorDomainManager.mergEntitys(this.mergedNode, this.newNode);
			similitaryEntity = aggregatorDomainManager.getSimilitaryEntities();
			
			checkboxSimilitatyEntityList = new ArrayList<>();
			for (NodeTag mergNodeSimilar : similitaryEntity.keySet()) {
				for (NodeTag newNodeSimilar : similitaryEntity.get(mergNodeSimilar)) {
					JCheckBox chbx = new JCheckBox(mergNodeSimilar.getTextNode()+isSimilarStr+newNodeSimilar.getTextNode()+"?");
					chbx.setFont(new java.awt.Font("Arial", 0, 20));
					checkboxSimilitatyEntityList.add(chbx);
				}
			}
			Panel checkboxPanel = new Panel(new GridLayout(checkboxSimilitatyEntityList.size(), 1));
			for (JCheckBox checkbox : checkboxSimilitatyEntityList) {
				checkboxPanel.add(checkbox);
			}
			
			JScrollPane scrollPane = new JScrollPane(checkboxPanel);
			add(scrollPane, BorderLayout.CENTER);
			
			
		} catch (Exception e) {
			
			e.printStackTrace();
			JOptionPane.showMessageDialog(FilterEntityPanel.this,  "Load in merg process",  "",    JOptionPane.ERROR_MESSAGE);
		}
		
	}

}
