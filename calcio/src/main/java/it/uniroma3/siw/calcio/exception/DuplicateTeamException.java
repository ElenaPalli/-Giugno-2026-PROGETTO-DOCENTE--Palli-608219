package it.uniroma3.siw.calcio.exception;

public class DuplicateTeamException extends RuntimeException {

    public DuplicateTeamException(String name, Integer yearFoundation) {
        super("La squadra '" + name + "' (" + yearFoundation + ") è già presente nel sistema");
    }
}