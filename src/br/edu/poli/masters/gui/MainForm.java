package br.edu.poli.masters.gui;

import java.net.UnknownHostException;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import com.mongodb.MongoException;

import br.edu.poli.masters.stored.IStored;
import br.edu.poli.masters.stored.MongoDb;
import br.edu.poli.masters.tags.RootTag;

public class MainForm extends JFrame{
	
	private static final int WITH_SCRREN = 800;
	private static final int HEIGHT_SCRREN = 600;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JTabbedPane tabbedPane = new JTabbedPane();
	private SelectDomainPanel selectDomainPanel;
	private PreviewPanel previewPanel;
	private FilterEntityPanel filterEntityPanel;
	private FinalDomainPanel finalDomainPanel;
	private RootTag newNode ;
	private RootTag mergedNode;
	private IStored bdInstance = new MongoDb();
	
	public MainForm() throws UnknownHostException, MongoException{
        setTitle("Concept model Aggregator");
        creatTabbedPane();
        pack();
        this.setSize(WITH_SCRREN, HEIGHT_SCRREN); 
        setLocationRelativeTo(null);  
        setDefaultCloseOperation(DISPOSE_ON_CLOSE); 
        
        
        setVisible(true);  
	}
	
	private void creatTabbedPane(){
		
        this.setLocationRelativeTo(null);  
        this.setResizable(false);  
  
        tabbedPane.setBounds(0, 0, 502, 371);  
        getContentPane().add(tabbedPane);  
  
        selectDomainPanel = new SelectDomainPanel(this);
        tabbedPane.addTab("Select Domain >", null,selectDomainPanel, null);  
        
        previewPanel= new PreviewPanel(this);
        tabbedPane.addTab("New Mind Map >", null,previewPanel, null);  
  
        filterEntityPanel =  new FilterEntityPanel(this);
        tabbedPane.addTab("Filter Entity >", null,filterEntityPanel, null);  
  
        finalDomainPanel = new FinalDomainPanel(this);
        tabbedPane.addTab("Final Domain", null,finalDomainPanel , null);  
        
        enableCurrentTab();
  
	}
	
	public void nextTab(){
		tabbedPane.setSelectedIndex(tabbedPane.getSelectedIndex()+1);
		if(tabbedPane.getSelectedIndex() == 1){
			previewPanel.setTreeNodes(mergedNode, newNode);
		}else if(tabbedPane.getSelectedIndex() == 2){
			filterEntityPanel.setTreeNodes(mergedNode, newNode);
		}else if(tabbedPane.getSelectedIndex() == 3){
			finalDomainPanel.setMergedNode(mergedNode);
		}
		tabbedPane.repaint();
		enableCurrentTab();
	}
	
	public void backTab(int index){
		switch (index) {
		case 1:
			selectDomainPanel.refresScreen();
			break;

		default:
			break;
		}
		tabbedPane.setSelectedIndex(tabbedPane.getSelectedIndex()-index);

		this.repaint();
		enableCurrentTab();
	}
	
	private void enableCurrentTab(){
//		tabbedPane.setEnabled(false);
//		tabbedPane.setEnabledAt(tabbedPane.getSelectedIndex(), true);
	}

	public RootTag getNewNode() {
		return newNode;
	}

	public void setNewNode(RootTag rootNode) {
		this.newNode = rootNode;
	}
	public RootTag getMergedNode() {
		return mergedNode;
	}

	public void setMergedNode(RootTag mergedNode) {
		this.mergedNode = mergedNode;
	}
	
	public IStored getDbInstance(){
		return bdInstance;
	}
	
	
	
	
	
	
}
