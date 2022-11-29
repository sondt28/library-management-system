package com.son.librarymanagementsystem.validation.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.son.librarymanagementsystem.validation.validator.UniqueCategoryValidator;

@Constraint(validatedBy = UniqueCategoryValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueCategory {
	String message() default "Category name already existed";
    
	Class<?>[] groups() default {};
    
	Class<? extends Payload>[] payload() default {};
}
