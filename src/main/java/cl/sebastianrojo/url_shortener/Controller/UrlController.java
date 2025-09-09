package cl.sebastianrojo.url_shortener.Controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import cl.sebastianrojo.url_shortener.Entity.Url;
import cl.sebastianrojo.url_shortener.Service.UrlService;

@RestController
public class UrlController {

    @Autowired
    private UrlService urlService;

    @PostMapping("/shorten")
    public ResponseEntity<String> shortenUrl(@RequestBody String originalUrl) {
        String shortUrl = urlService.shortenUrl(originalUrl);
        return ResponseEntity.ok("http://localhost:8080/" + shortUrl);
    }

    @GetMapping("/{shortUrl}")
    public RedirectView redirect(@PathVariable String shortUrl) {
        Optional<Url> urlOpt = urlService.getOriginalUrl(shortUrl);
        if (urlOpt.isPresent()) {
            return new RedirectView(urlOpt.get().getOriginalUrl());
        }
        return new RedirectView("/error");
    }
}