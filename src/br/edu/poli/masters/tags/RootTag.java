package br.edu.poli.masters.tags;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="map")
public class RootTag{
	private List<NodeTag> nodes = new ArrayList<NodeTag>();
	 
    @XmlElement(name="node")
    private List<NodeTag> getBooks() {
        return nodes;
    }
    
    public NodeTag getDomainNode(){
    	if(nodes == null || nodes.isEmpty()){
    		return null;
    	}
    	return nodes.get(0);
    }

	
    
    

}
