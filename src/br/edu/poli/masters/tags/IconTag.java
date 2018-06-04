package br.edu.poli.masters.tags;

import javax.xml.bind.annotation.XmlAttribute;

public class IconTag {
	private String type;
	
	public String getText() {
		return type;
	}

	@XmlAttribute(name="BUILTIN")
	public void setText(String text) {
		this.type = text;
	}
	

	
	

}
