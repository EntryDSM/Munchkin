package kr.hs.entrydsm.score.integrate;

public class InvalidGetterAccessException extends RuntimeException {
    public InvalidGetterAccessException() {
        super("can't access getter");
    }
}
