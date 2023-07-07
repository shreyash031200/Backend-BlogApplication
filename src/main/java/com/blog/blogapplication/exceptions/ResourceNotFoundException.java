package com.blog.blogapplication.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResourceNotFoundException extends RuntimeException {

    String resourceName;
    String fieldName;
    int fieldValue;


    public ResourceNotFoundException(String resourceName, String fieldName, int fieldValue) {
        super(String.format("%s Not found with  %s : %d", resourceName, fieldName,fieldValue));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }
}
