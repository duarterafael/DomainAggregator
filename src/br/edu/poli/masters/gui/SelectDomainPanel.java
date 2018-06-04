package br.edu.poli.masters.gui;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FilenameFilter;
import java.util.HashMap;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import br.edu.poli.masters.helpers.LoadXMLHelper;
import br.edu.poli.masters.tags.RootTag;

public class SelectDomainPanel extends JPanel implements ActionListener {

	  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	  private JButton mainMindMapSelectPathButton;
	  private JTextField mainMindMapPath;
	  private JRadioButton newDomainRdBnt;
	  private JRadioButton mergDomainRdBnt;
	  private JComboBox selectDominCombobox;
	  private JButton btnNext;
	  private MainForm parentFrame;
	  private JFileChooser fc;
	  



	  public SelectDomainPanel(final MainForm parentFrame) {
		  
	    super(new BorderLayout());
	    
	   
//	    RootTag rootNode;
//		try {
//			
//			rootNode = LoadXMLHelper.readXML(new File("C:\\Users\\rafin_000\\Google Drive\\MESTRADO-POLI\\BD\\Projeto 2.0\\Templat\\Estudo de caso 1\\Reserve de Viagem\\Rafael\\Reserva aérea_Final.mm")); 
//		} catch (Exception e2) {
//			JOptionPane.showMessageDialog(SelectDomainPanel.this,  "Load data base",  "Load erro",    JOptionPane.ERROR_MESSAGE);
//		}
	    this.parentFrame = parentFrame;

	    //Create a file chooser
	    mainMindMapPath = new JTextField("Select a mind map path");
	    mainMindMapPath.setColumns(45);
	    fc = new JFileChooser("C:\\Users\\rafin_000\\Google Drive\\MESTRADO-POLI\\BD\\Projeto 2.0\\Templat\\Estudo de caso 1\\Reserve de Viagem");
	    

	    //Uncomment one of the following lines to try a different
	    //file selection mode. The first allows just directories
	    //to be selected (and, at least in the Java look and feel,
	    //shown). The second allows both files and directories
	    //to be selected. If you leave these lines commented out,
	    //then the default mode (FILES_ONLY) will be used.
	    //
	    //fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
	    fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);

	    //Create the open button. We use the image from the JLF
	    //Graphics Repository (but we extracted it from the jar).
	    mainMindMapSelectPathButton = new JButton("");
	    mainMindMapSelectPathButton.addActionListener(this);
	    mainMindMapSelectPathButton.setIcon(new ImageIcon("./res/images/folder.png"));


	    //For layout purposes, put the buttons in a separate panel
	    JPanel buttonPanel = new JPanel(); //use FlowLayout
	    buttonPanel.add(new Label("Select Concept model:"));
	    buttonPanel.add(mainMindMapPath);
	    buttonPanel.add(mainMindMapSelectPathButton);
	    
	    JPanel buttonPanel2 = new JPanel(); 
	    //Add the buttons and the log to this panel.
	    
	    
	    newDomainRdBnt = new JRadioButton("New Domain");
	    newDomainRdBnt.setMnemonic(KeyEvent.VK_B);
	    newDomainRdBnt.setActionCommand("New Domain");
	    newDomainRdBnt.setSelected(true);
	    newDomainRdBnt.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				selectDominCombobox.enable(false);
			}
		});

	    mergDomainRdBnt = new JRadioButton("Merge Domain");
	    mergDomainRdBnt.setMnemonic(KeyEvent.VK_C);
	    mergDomainRdBnt.setActionCommand("Merge Domain");
	    mergDomainRdBnt.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				selectDominCombobox.enable(true);
			}
		});
	    
	    
	    
	    //Create the combo box, select item at index 4.
	    //Indices start at 0, so 4 specifies the pig.
	    selectDominCombobox = new JComboBox();
	    loadComboBox();	    
	    selectDominCombobox.enable(false);
	    
	    ButtonGroup btnGroup = new ButtonGroup();
	    btnGroup.add(newDomainRdBnt);
	    btnGroup.add(mergDomainRdBnt);
	    
	    btnNext = new JButton("NEXT >");
	    btnNext.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					RootTag rootNode = LoadXMLHelper.readXML(new File(mainMindMapPath.getText()));
					parentFrame.setNewNode(rootNode);
					if(newDomainRdBnt.isSelected()){
						parentFrame.setMergedNode(null);
					}else{
						parentFrame.setMergedNode(parentFrame.getDbInstance().getNodeByDomain(selectDominCombobox.getSelectedItem().toString()));
					}
					parentFrame.nextTab();
				} catch (Exception e1) {
					e1.printStackTrace();
					JOptionPane.showMessageDialog(SelectDomainPanel.this,  "Load erro",  "Load erro",    JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	    
	    JPanel menuPanel = new JPanel();
	    menuPanel.add(newDomainRdBnt);
	    menuPanel.add(mergDomainRdBnt);
	    menuPanel.add(selectDominCombobox);
	    menuPanel.add(btnNext);
	    
	    add(buttonPanel, BorderLayout.NORTH);
	    add(buttonPanel2, BorderLayout.CENTER);
	    add(menuPanel, BorderLayout.SOUTH);
	  }
	  
	  private void loadComboBox(){
		  for (String i : parentFrame.getDbInstance().getAllDomainName()) {
		    	selectDominCombobox.addItem(i);
		    }
	  }

	  public void actionPerformed(ActionEvent e) {

	    //Handle open button action.
	    if (e.getSource() == mainMindMapSelectPathButton) {
	      int returnVal = fc.showOpenDialog(SelectDomainPanel.this);
	      if (returnVal == JFileChooser.APPROVE_OPTION) {
	        File file = fc.getSelectedFile();
	        if(!file.isFile() || !file.getAbsolutePath().substring(file.getAbsolutePath().lastIndexOf(".")).equalsIgnoreCase(".mm")){
	        	JOptionPane.showMessageDialog(this,  "Selectio a file whose extension is .mm",  "Erro to select file",    JOptionPane.ERROR_MESSAGE);
	        }else{
	        	mainMindMapPath.setText(file.getAbsolutePath());
	        }
	      } 

	      //Handle save button action.
	    }
	    
	  }
	  
	    
	  private File[] getAllMMFile(File diretory){
		  return diretory.listFiles(new FilenameFilter() {
			
			@Override
			public boolean accept(File file, String arg1) {
				return arg1.substring(arg1.lastIndexOf(".")).equalsIgnoreCase(".mm");
			}
		});
	  }

	  /** Returns an ImageIcon, or null if the path was invalid. */
	  protected static ImageIcon createImageIcon(String path) {
	    java.net.URL imgURL = SelectDomainPanel.class.getResource(path);
	    if (imgURL != null) {
	      return new ImageIcon(imgURL);
	    } else {
	      System.err.println("Couldn't find file: " + path);
	      return null;
	    }
	  }
	  
	public void refresScreen() {
		loadComboBox();
	}
	  
	

	  

//	  /**
//	   * Create the GUI and show it. For thread safety, this method should be
//	   * invoked from the event-dispatching thread.
//	   */
//	  private static void createAndShowGUI() {
//	    //Make sure we have nice window decorations.
//	    JFrame.setDefaultLookAndFeelDecorated(true);
//	    JDialog.setDefaultLookAndFeelDecorated(true);
//
//	    //Create and set up the window.
//	    JFrame frame = new JFrame("SelectDomainPanel");
//	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//	    //Create and set up the content pane.
//	    JComponent newContentPane = new SelectDomainPanel();
//	    newContentPane.setOpaque(true); //content panes must be opaque
//	    frame.setContentPane(newContentPane);
//
//	    //Display the window.
//	    frame.pack();
//	    frame.setVisible(true);
//	  }
//
//	  public static void main(String[] args) {
//	    //Schedule a job for the event-dispatching thread:
//	    //creating and showing this application's GUI.
//	    javax.swing.SwingUtilities.invokeLater(new Runnable() {
//	      public void run() {
//	        createAndShowGUI();
//	      }
//	    });
//	  }
	}