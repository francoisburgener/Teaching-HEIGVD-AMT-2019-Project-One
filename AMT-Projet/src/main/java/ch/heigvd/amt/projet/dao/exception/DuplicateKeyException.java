package ch.heigvd.amt.projet.dao.exception;

import java.util.DuplicateFormatFlagsException;

public class DuplicateKeyException extends Exception {

    public DuplicateKeyException(String message){
        super(message);
    }
}
