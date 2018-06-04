package br.edu.poli.masters.gui;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.net.UnknownHostException;

import javax.swing.SwingUtilities;

import com.mongodb.MongoException;

public class Main {
	private static MainForm mainForm;
	

	public static void main(String[] args) throws Exception {
//		File f = new File(".\\res\\Input\\MainMindMap\\MainMindMapReservaAerea.mm");
//		File f = new File(".\\res\\Input\\SubMindMap\\Entevista.mm");
//		if(!f.exists()){
//			System.out.println("O arquivo n existe");
//		}
//		
//		IParser parser = FactoryParser.createParserXML(ParserType.RELATIONAL_MODEL_PARSER);
//		
//		
//		RootTag domanin = (RootTag) parser.readXML(f);
//		parser.printEntity(domanin);
//		RootTag mergMindMap = MindMapConsolidator.loadMapInstance(".\\res\\Input\\MindMap1-Rafael");
//		
//		System.out.println("***********************************************************************************************");
//		
//		
//		MindMapConsolidator.merg(mergMindMap, newMindMap);
		
		try {  
//            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());  
//			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel");  
			javax.swing.UIManager.setLookAndFeel("com.jtattoo.plaf.acryl.AcrylLookAndFeel");
//			javax.swing.UIManager.setLookAndFeel("com.jtattoo.plaf.hifi.HiFiLookAndFeel");
		} catch (Exception e) {  
            e.printStackTrace();  
        }  
        // --> cria um novo objeto do tipo Swing1  
        // por causa da execucao multithreading da  
        // API swing,isso deve ser feito dessa forma:  
        SwingUtilities.invokeLater(new Runnable() {  
            public void run() {  
                try {
                	mainForm = new MainForm();
                	mainForm.addWindowListener(new WindowListener() {
						
						@Override
						public void windowOpened(WindowEvent e) {
							// TODO Auto-generated method stub
							
						}
						
						@Override
						public void windowIconified(WindowEvent e) {
							// TODO Auto-generated method stub
							
						}
						
						@Override
						public void windowDeiconified(WindowEvent e) {
							// TODO Auto-generated method stub
							
						}
						
						@Override
						public void windowDeactivated(WindowEvent e) {
							// TODO Auto-generated method stub
							
						}
						
						@Override
						public void windowClosing(WindowEvent e) {
							mainForm.getDbInstance().closeConnection();
						}
						
						@Override
						public void windowClosed(WindowEvent e) {
							// TODO Auto-generated method stub
							
						}
						
						@Override
						public void windowActivated(WindowEvent e) {
							// TODO Auto-generated method stub
							
						}
					});;
				} catch (UnknownHostException | MongoException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}  
            }  
        });  
		
	}
}
