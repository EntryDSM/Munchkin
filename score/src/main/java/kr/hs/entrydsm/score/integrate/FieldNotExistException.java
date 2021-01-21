package kr.hs.entrydsm.score.integrate;

public class FieldNotExistException extends RuntimeException {
    public FieldNotExistException() {
        super("all field essentially required");
    }
}
