package cl.sebastianrojo.url_shortener.exception;

public class UrlNotFoundException extends RuntimeException {

    public UrlNotFoundException(String shortUrl) {
        super("No se encontró una URL para el código: " + shortUrl);
    }
}