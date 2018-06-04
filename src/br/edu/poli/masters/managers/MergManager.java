package br.edu.poli.masters.managers;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import br.edu.poli.masters.helpers.SimilitaryHelper;
import br.edu.poli.masters.tags.NodeTag;
import br.edu.poli.masters.tags.RootTag;

public class MergManager{

	private static final String TAG_REGRA_DE_NEGOCIO = "Regras de negócio";
	private static final String TAG_FUNCIONALIDADE = "Funcionalidades";
	private static final String TAG_ENTIDADES = "Entidades";
	private static final String TAG_AGENTES = "Agentes";
	private static final int SIMILARITU_LEVEL_ACCEPTABLE = 3;
	private static final int SIMILARITU_LEVEL_ACCEPTABLE_IF_ATRIBUT = 1;
	private HashMap<NodeTag, List<NodeTag>> similitaryEntities = new HashMap<NodeTag, List<NodeTag>>();



	
	public void mergEntitys(RootTag mergMindMap, RootTag newdMindMap) throws Exception{

		if(!SimilitaryHelper.equalIgnoreCaseAndAccentuation(mergMindMap.getDomainNode().getTextNode(), newdMindMap.getDomainNode().getTextNode())){
			throw new Exception("Can not merg mind map of different domain");
		}

		for (NodeTag mergtag : mergMindMap.getDomainNode().getNodesChildren()) {
				for (NodeTag newtag : newdMindMap.getDomainNode().getNodesChildren()) {
					if(SimilitaryHelper.equalIgnoreCaseAndAccentuation(mergtag.getTextNode(),newtag.getTextNode())){
						if(SimilitaryHelper.equalIgnoreCaseAndAccentuation(mergtag.getTextNode(),TAG_REGRA_DE_NEGOCIO) || SimilitaryHelper.equalIgnoreCaseAndAccentuation(mergtag.getTextNode(),TAG_FUNCIONALIDADE) ){
							addAllNode(mergtag, newtag);
						}else{
							identifySimilitary(mergtag, newtag);
						}
					}
				}
		}
		for (NodeTag mergtag : mergMindMap.getDomainNode().getNodesChildren()) {
			int i= 1;
			for (NodeTag tag : mergtag.getNodesChildren()) {
				tag.setTextNode(tag.getTextNode());
				i++;
			}
		}


		printEntity(mergMindMap);



	}



	public void printEntity(RootTag domain) {
		printNode(domain.getDomainNode(), 0);
	}

	private void printNode(NodeTag node, int level){
		String tabs = "";
		for (int i = 0; i < level; i++) {
			tabs+="\t";
		}
		//		if(node.getComment() != null){
		//			System.out.println(tabs+node.getTextNode()+"--->"+node.getComment().getComment());
		//		}else{
		System.out.println(tabs+node.getTextNode());
		//		}


		level++;
		for (NodeTag chieldNode : node.getNodesChildren()) {
			printNode(chieldNode, level);
		}

	}
	
	
	private void identifySimilitary(NodeTag rootNode, NodeTag newNode){
		HashMap<NodeTag, Boolean> mergedMap = new HashMap<NodeTag, Boolean>();
		
		
		for (NodeTag mergtag : rootNode.getNodesChildren()) {
			for (NodeTag newtag : newNode.getNodesChildren()) {
				
				if(!mergedMap.containsKey(newtag)){
					mergedMap.put(newtag, false);
				}
				if(SimilitaryHelper.equalIgnoreCaseAndAccentuation(mergtag.getTextNode(), newtag.getTextNode()) && checkSimilitaryAtribut(mergtag, newtag)){
					
					
//					System.out.println("---------------------------------------Same String---------------------------------");
//					System.out.println(mergtag.getTextNode()+"------->"+newtag.getTextNode());
//					printNode(mergtag, 0);
//					printNode(newtag, 0);
//					
					mergAtribute(mergtag, newtag);
					mergedMap.put(newtag, true);
				}else if( SimilitaryHelper.isSubString(mergtag.getTextNode(), newtag.getTextNode())  && checkSimilitaryAtribut(mergtag, newtag)){
//					System.out.println("---------------------------------------SubString String---------------------------------");
//					System.out.println(mergtag.getTextNode()+"------->"+newtag.getTextNode());
//					printNode(mergtag, 0);
//					printNode(newtag, 0);

					// hasmap com chave entida do main mind map e valor lista de possiveis valores 	
						if(similitaryEntities.get(mergtag) == null){
							similitaryEntities.put(mergtag, new ArrayList<NodeTag>());
						}
						similitaryEntities.get(mergtag).add(newtag);
						mergedMap.put(newtag, false);
					mergedMap.put(newtag, true);
				}
				else if(SimilitaryHelper.levenshteinDistance(mergtag.getTextNode(), newtag.getTextNode()) <= SIMILARITU_LEVEL_ACCEPTABLE){
//					System.out.println("--------------------------SimilaryString---------------------------------------");
//					System.out.println(mergtag.getTextNode()+"------->"+newtag.getTextNode());
//					printNode(mergtag, 0);
//					printNode(newtag, 0);
					
					// hasmap com chave entida do main mind map e valor lista de possiveis valores 	
					if(!similitaryEntities.containsKey(mergtag)){
						if(similitaryEntities.get(mergtag) == null){
							similitaryEntities.put(mergtag, new ArrayList<NodeTag>());
						}
						similitaryEntities.get(mergtag).add(newtag);
						mergedMap.put(newtag, false);
					}
					
					mergedMap.put(newtag, true);
				}
			}
		}
		for (NodeTag nodeNotMerged : mergedMap.keySet()) {
			if(!mergedMap.get(nodeNotMerged)){
				rootNode.getNodesChildren().add(nodeNotMerged);
			}
		}
//		@SuppressWarnings("resource")
//		Scanner leitor = new Scanner(System.in); 
//		for (NodeTag mergNodeSimilar : similitaryEntities.keySet()) {
//			for (NodeTag newNodeSimilar : similitaryEntities.get(mergNodeSimilar)) {
//				System.out.print("Want to perform the merge entity with "+mergNodeSimilar.getTextNode()+" "+newNodeSimilar.getTextNode()+"?(S)im or (N)ot "); 
//				String str = leitor.nextLine();
//				
//				System.out.println("\n--------------"+mergNodeSimilar.getTextNode()+" Atributes------------------------");
//				printNode(mergNodeSimilar, 0);
//				System.out.println("--------------"+newNodeSimilar.getTextNode()+" Atributes------------------------");
//				printNode(newNodeSimilar, 0);
//				
//				if(str.equalsIgnoreCase("S")){
//					mergAtribute(mergNodeSimilar, newNodeSimilar);
//					System.out.println("-----------------------Result Merge------------------");
//					printNode(mergNodeSimilar, 0);
//					
//				}
//			}
//			
//		}
//		System.out.println("-----------------------------Mind map Final------------------");
//		printNode(rootNode, 0);
//		System.out.println("*******************************************************************");
		
	}
	
	public void mergAtribute(NodeTag rootNode, NodeTag newNode){
		HashMap<NodeTag, Boolean> mergedMap = new HashMap<>();
		
		if(rootNode.getNodesChildren().isEmpty() && !newNode.getNodesChildren().isEmpty()){
			rootNode.getNodesChildren().addAll(newNode.getNodesChildren());
		}else{
			
			for (NodeTag rootNodeAtribute : rootNode.getNodesChildren()) {
				
				for (NodeTag newNodeAtribute : newNode.getNodesChildren()) {
					if(!mergedMap.containsKey(newNodeAtribute)){
						mergedMap.put(newNodeAtribute, false);
					}
					if(SimilitaryHelper.equalIgnoreCaseAndAccentuation(rootNodeAtribute.getTextNode(), newNodeAtribute.getTextNode()) || 
							SimilitaryHelper.isSubString(rootNodeAtribute.getTextNode(), newNodeAtribute.getTextNode()) ||
							SimilitaryHelper.levenshteinDistance(rootNodeAtribute.getTextNode(), newNodeAtribute.getTextNode()) < SIMILARITU_LEVEL_ACCEPTABLE){
						mergedMap.put(newNodeAtribute, true);
					}
				}
			}
			for (NodeTag nodeNotMerged : mergedMap.keySet()) {
				if(!mergedMap.get(nodeNotMerged)){
					rootNode.getNodesChildren().add(nodeNotMerged);
				}
			}
		}
		
	}
	
	private boolean checkSimilitaryAtribut(NodeTag rootNode, NodeTag newNode){
		int qtySameAtribute = 0;
		if(newNode.getNodesChildren() == null || newNode.getNodesChildren().isEmpty() || newNode.getNodesChildren().size() <= 1){
			return true;
		}
		
		for (NodeTag rootNodeAtribute : rootNode.getNodesChildren()) {
			for (NodeTag newNodeAtribute : newNode.getNodesChildren()) {
				if(SimilitaryHelper.equalIgnoreCaseAndAccentuation(rootNodeAtribute.getTextNode(), newNodeAtribute.getTextNode()) || 
						SimilitaryHelper.isSubString(rootNodeAtribute.getTextNode(), newNodeAtribute.getTextNode()) ||
						SimilitaryHelper.levenshteinDistance(rootNodeAtribute.getTextNode(), newNodeAtribute.getTextNode()) <= SIMILARITU_LEVEL_ACCEPTABLE){
				     qtySameAtribute++;
				}
			}
		}
		if(qtySameAtribute >= SIMILARITU_LEVEL_ACCEPTABLE_IF_ATRIBUT){
			return true;
		}
		return false;
	}
	
	
	public void addAllNode(NodeTag rootNode, NodeTag newNode){
		if(SimilitaryHelper.equalIgnoreCaseAndAccentuation(rootNode.getTextNode(), newNode.getTextNode())){
			rootNode.getNodesChildren().addAll(newNode.getNodesChildren());
		}else{
			for (NodeTag chieldNode : rootNode.getNodesChildren()) {
				addAllNode(chieldNode, newNode);
			}
		}

	}

//	public void writeXML(File file, RootTag domain) {
//
//		try {
//
//			JAXBContext jaxbContext;
//			jaxbContext = JAXBContext.newInstance(RootTag.class);
//			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
//
//			// output pretty printed
//			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
//
//			jaxbMarshaller.marshal(domain, file);
//			jaxbMarshaller.marshal(domain, System.out);
//		} catch (JAXBException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//	}
	public HashMap<NodeTag, List<NodeTag>> getSimilitaryEntities() {
		return similitaryEntities;
	}





}
