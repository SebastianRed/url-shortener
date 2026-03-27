package cl.sebastianrojo.url_shortener.controller;

import cl.sebastianrojo.url_shortener.dto.ShortenRequest;
import cl.sebastianrojo.url_shortener.dto.UrlResponse;
import cl.sebastianrojo.url_shortener.service.UrlService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequestMapping
public class UrlController {

    @Autowired
    private UrlService urlService;

    /**
     * POST /shorten
     * Recibe un JSON { "url": "https://ejemplo.com" }
     * Devuelve un UrlResponse con la URL corta y sus metadatos.
     */
    @PostMapping("/shorten")
    public ResponseEntity<UrlResponse> shortenUrl(@Valid @RequestBody ShortenRequest request) {
        UrlResponse response = urlService.shortenUrl(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /**
     * GET /{shortUrl}
     * Redirige a la URL original. Incrementa el contador de accesos.
     */
    @GetMapping("/{shortUrl:[a-zA-Z0-9]{6}}")
    public RedirectView redirect(@PathVariable String shortUrl) {
        String originalUrl = urlService.resolveUrl(shortUrl); // lanza UrlNotFoundException si no existe
        return new RedirectView(originalUrl);
    }

    /**
     * GET /stats/{shortUrl}
     * Devuelve los metadatos y contador de accesos de una URL corta.
     */
    @GetMapping("/stats/{shortUrl:[a-zA-Z0-9]{6}}")
    public ResponseEntity<UrlResponse> stats(@PathVariable String shortUrl) {
        UrlResponse response = urlService.getStats(shortUrl);
        return ResponseEntity.ok(response);
    }
}