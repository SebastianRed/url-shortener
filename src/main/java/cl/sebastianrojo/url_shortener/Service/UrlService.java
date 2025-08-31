package cl.sebastianrojo.url_shortener.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.sebastianrojo.url_shortener.Entity.Url;
import cl.sebastianrojo.url_shortener.Repository.UrlRepository;

@Service
public class UrlService {
    
    @Autowired
    private UrlRepository urlRepository;

    public String shortenUrl(String originalUrl) {
        String shortUrl = Integer.toHexString(originalUrl.hashCode());
        Url url = new Url(originalUrl, shortUrl);
        urlRepository.save(url);
        return shortUrl;
    }

    public String getOriginalUrl(String shortUrl) {
        Url url = urlRepository.findByShortUrl(shortUrl);
        if (url != null) {
            url.setAccessCount(url.getAccessCount() + 1);
            return url.getOriginalUrl();
        }
        return null;
    }
}