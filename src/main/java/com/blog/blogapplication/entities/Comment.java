package com.blog.blogapplication.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="comments")
@Getter
@Setter
@NoArgsConstructor
public class Comment {

    @Id
    @GeneratedValue
    private Integer  id;

    private String content;

    @ManyToOne
    private Post post;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}
    
    
}
