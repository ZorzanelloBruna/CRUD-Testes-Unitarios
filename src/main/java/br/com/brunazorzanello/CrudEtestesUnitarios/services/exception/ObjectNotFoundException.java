package br.com.brunazorzanello.CrudEtestesUnitarios.services.exception;

public class ObjectNotFoundException extends RuntimeException{
    public ObjectNotFoundException(String message) {
        super(message);
    }
}
