package br.edu.poli.masters.tags;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

public class NodeTag {
	private String textNode;
	private List<NodeTag> nodesChildren;
	private List<IconTag> iconList;
	private CommentTag comment;
	
	public NodeTag() {
		nodesChildren = new ArrayList<NodeTag>();
		iconList = new ArrayList<IconTag>();
		
	}
	
	
	public String getTextNode() {
		return textNode;
	}

	@XmlAttribute(name="TEXT")
	public void setTextNode(String textNode) {
		this.textNode = textNode;
	}
	 
    @XmlElement(name="node")
    public List<NodeTag> getNodesChildren() {
        return nodesChildren;
    }
    
    @XmlElement(name="icon")
    public List<IconTag> getIconList() {
        return iconList;
    }

    @XmlElement(name="hook")
	public CommentTag getComment() {
		return comment;
	}

	public void setComment(CommentTag hooktag) {
		this.comment = hooktag;
	}

	public void setNodesChildren(List<NodeTag> nodesChildren) {
		this.nodesChildren = nodesChildren;
	}

	public void setIconList(List<IconTag> iconList) {
		this.iconList = iconList;
	}
    
    

	
}
