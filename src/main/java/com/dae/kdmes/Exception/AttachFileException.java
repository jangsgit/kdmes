package com.dae.kdmes.Exception;

public class AttachFileException extends RuntimeException{
    public AttachFileException(String message){
        super(message);
    }

    public AttachFileException(String message, Throwable cause){

        super(message, cause);
    }
}
