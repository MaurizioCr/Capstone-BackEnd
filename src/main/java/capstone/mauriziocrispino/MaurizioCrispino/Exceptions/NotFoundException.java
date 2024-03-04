package capstone.mauriziocrispino.MaurizioCrispino.Exceptions;

public class NotFoundException extends RuntimeException {
    public NotFoundException(long id) {
        super("Elemento con id " + id + " non trovato!");
    }

    public NotFoundException(String username) {
        super("username con id " + username + " non trovato!");
    }
}
