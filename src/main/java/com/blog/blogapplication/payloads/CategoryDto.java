package com.blog.blogapplication.payloads;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class CategoryDto {

    private Integer categoryId;
    @NotBlank
    @Size(min = 4)
    private String categoryTitle;
    @NotBlank
    @Size(min = 10)
    private String categoryDescription;
    
    
	public CategoryDto(Integer categoryId, @NotBlank @Size(min = 4) String categoryTitle,
			@NotBlank @Size(min = 10) String categoryDescription) {
		super();
		this.categoryId = categoryId;
		this.categoryTitle = categoryTitle;
		this.categoryDescription = categoryDescription;
	}
	public Integer getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
	public String getCategoryTitle() {
		return categoryTitle;
	}
	public void setCategoryTitle(String categoryTitle) {
		this.categoryTitle = categoryTitle;
	}
	public String getCategoryDescription() {
		return categoryDescription;
	}
	public void setCategoryDescription(String categoryDescription) {
		this.categoryDescription = categoryDescription;
	}
    
    
}
