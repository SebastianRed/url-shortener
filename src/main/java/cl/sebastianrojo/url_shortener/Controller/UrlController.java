package cl.sebastianrojo.url_shortener.Controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import cl.sebastianrojo.url_shortener.Service.UrlService;
import jakarta.servlet.http.HttpServletResponse;


@RestController
public class UrlController {

    @Autowired
    private UrlService urlService;

    @PostMapping("/shorten")
    public String shortenUrl(@RequestBody String originalUrl) {
        return urlService.shortenUrl(originalUrl);
    }

    @GetMapping("/{shortUrl:[a-zA-Z0-9]{6}}")
    public void redirectToOriginalUrl(@PathVariable String shortUrl, HttpServletResponse response) throws IOException {
        String originalUrl = urlService.getOriginalUrl(shortUrl);
        response.sendRedirect(originalUrl);
    }

}