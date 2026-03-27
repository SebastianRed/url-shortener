package cl.sebastianrojo.url_shortener.service;

import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cl.sebastianrojo.url_shortener.dto.ShortenRequest;
import cl.sebastianrojo.url_shortener.dto.UrlResponse;
import cl.sebastianrojo.url_shortener.entity.Url;
import cl.sebastianrojo.url_shortener.exception.UrlNotFoundException;
import cl.sebastianrojo.url_shortener.repository.UrlRepository;

@Service
public class UrlService {
 
    private static final String BASE62 =
            "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final int SHORT_URL_LENGTH = 6;
 
    @Value("${app.base-url:http://localhost:8080}")
    private String baseUrl;
 
    @Autowired
    private UrlRepository urlRepository;
 
    // ── Crear o reutilizar una URL corta ─────────────────────────────────────
    @Transactional
    public UrlResponse shortenUrl(ShortenRequest request) {
        String originalUrl = request.getUrl().trim();
 
        // Si ya existe, devuelve la misma
        return urlRepository.findByOriginalUrl(originalUrl)
                .map(this::toResponse)
                .orElseGet(() -> {
                    String shortUrl = generateUniqueShortUrl();
                    Url saved = urlRepository.save(new Url(originalUrl, shortUrl));
                    return toResponse(saved);
                });
    }
 
    // ── Obtener URL original e incrementar contador ──────────────────────────
    @Transactional
    public String resolveUrl(String shortUrl) {
        Url url = urlRepository.findByShortUrl(shortUrl)
                .orElseThrow(() -> new UrlNotFoundException(shortUrl));
 
        url.setAccessCount(url.getAccessCount() + 1);
        urlRepository.save(url);
 
        return url.getOriginalUrl();
    }
 
    // ── Obtener estadísticas de una URL corta ────────────────────────────────
    @Transactional(readOnly = true)
    public UrlResponse getStats(String shortUrl) {
        Url url = urlRepository.findByShortUrl(shortUrl)
                .orElseThrow(() -> new UrlNotFoundException(shortUrl));
        return toResponse(url);
    }
 
    // ── Helpers ──────────────────────────────────────────────────────────────
    private String generateUniqueShortUrl() {
        String shortUrl;
        do {
            shortUrl = generateShortUrl();
        } while (urlRepository.findByShortUrl(shortUrl).isPresent());
        return shortUrl;
    }
 
    private String generateShortUrl() {
        StringBuilder sb = new StringBuilder(SHORT_URL_LENGTH);
        for (int i = 0; i < SHORT_URL_LENGTH; i++) {
            sb.append(BASE62.charAt(ThreadLocalRandom.current().nextInt(BASE62.length())));
        }
        return sb.toString();
    }
 
    private UrlResponse toResponse(Url url) {
        return new UrlResponse(
                url.getOriginalUrl(),
                url.getShortUrl(),
                baseUrl + "/" + url.getShortUrl(),
                url.getAccessCount(),
                url.getCreatedAt()
        );
    }
}