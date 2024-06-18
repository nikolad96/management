package com.example.management.Exception;

public class EntityNotFoundException extends RuntimeException{
    public EntityNotFoundException(String classType, Long id){
        super("Entity of type: '" + classType + "' and id: '" + id + "' not found");
    }
}
