package br.edu.poli.masters.tags;

import javax.xml.bind.annotation.XmlElement;
/*
 * This is a hook tag.
 */
public class CommentTag {
private String comment;
	
	public String getComment() {
		return comment;
	}

	@XmlElement
	public void setComment(String Comment) {
		this.comment = Comment;
	}

}
