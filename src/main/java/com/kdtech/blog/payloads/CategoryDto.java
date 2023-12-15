package com.kdtech.blog.payloads;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CategoryDto {
	
	private Integer categoryId;	
	
	@NotBlank
	@Size(min = 4, message = "Category must be min of 4 Characters !!")
	private String CategoryTitle;
	
	@NotBlank
	@Size(min = 10, message = "Category desc must be min of 4 Characters !!")
	private String categoryDescription;
}
