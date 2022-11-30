package com.son.librarymanagementsystem.validation.validator;

import java.util.Optional;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.son.librarymanagementsystem.model.Category;
import com.son.librarymanagementsystem.repository.CategoryRepository;
import com.son.librarymanagementsystem.validation.annotation.UniqueCategory;

public class UniqueCategoryValidator implements ConstraintValidator<UniqueCategory, String> {

	private CategoryRepository repository;
	
	public UniqueCategoryValidator(CategoryRepository repository) {
		this.repository = repository;
	}
	
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		
		Optional<Category> category = repository.findByCategoryName(value);
		
		if (category.isEmpty())
			return true;
		
		return false;
	}

}
