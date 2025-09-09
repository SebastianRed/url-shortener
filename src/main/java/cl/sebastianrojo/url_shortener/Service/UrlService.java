package cl.sebastianrojo.url_shortener.Service;

import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.sebastianrojo.url_shortener.Entity.Url;
import cl.sebastianrojo.url_shortener.Repository.UrlRepository;


@Service
public class UrlService {
    @Autowired
    private UrlRepository urlRepository;

    private static final String BASE62_CHARS = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public String shortenUrl(String originalUrl) {
        // Verifica si la URL ya existe para evitar duplicados
        Optional<Url> existingUrl = urlRepository.findByOriginalUrl(originalUrl);
        if (existingUrl.isPresent()) {
            return existingUrl.get().getShortUrl();
        }

        // Genera un shortUrl único
        String shortUrl;
        do {
            shortUrl = generateShortUrl();
        } while (urlRepository.findByShortUrl(shortUrl).isPresent());

        Url newUrl = new Url();
        newUrl.setOriginalUrl(originalUrl);
        newUrl.setShortUrl(shortUrl);
        urlRepository.save(newUrl);

        return shortUrl;
    }

    private String generateShortUrl() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 6; i++) { // Longitud del shortUrl
            int randomIndex = ThreadLocalRandom.current().nextInt(BASE62_CHARS.length());
            sb.append(BASE62_CHARS.charAt(randomIndex));
        }
        return sb.toString();
    }

    public Optional<Url> getOriginalUrl(String shortUrl) {
        Optional<Url> url = urlRepository.findByShortUrl(shortUrl);
        url.ifPresent(u -> {
            u.setAccessCount(u.getAccessCount() + 1);
            urlRepository.save(u);
        });
        return url;
    }
}