package br.com.brunazorzanello.CrudEtestesUnitarios.services.exception;

public class DataIntegrateViolationException extends RuntimeException{
    public DataIntegrateViolationException(String message) {

        super(message);
    }
}
