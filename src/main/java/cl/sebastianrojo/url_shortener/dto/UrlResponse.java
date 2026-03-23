package cl.sebastianrojo.url_shortener.dto;

import java.time.LocalDateTime;

public class UrlResponse {

    private String originalUrl;
    private String shortUrl;
    private String fullShortUrl;
    private Long accessCount;
    private LocalDateTime createdAt;

    public UrlResponse() {}

    public UrlResponse(String originalUrl, String shortUrl, String fullShortUrl,
                       Long accessCount, LocalDateTime createdAt) {
        this.originalUrl  = originalUrl;
        this.shortUrl     = shortUrl;
        this.fullShortUrl = fullShortUrl;
        this.accessCount  = accessCount;
        this.createdAt    = createdAt;
    }

    public String getOriginalUrl() { 
        return originalUrl;
    }

    public void setOriginalUrl(String originalUrl) { 
        this.originalUrl = originalUrl; 
    }

    public String getShortUrl() { 
        return shortUrl; 
    }

    public void setShortUrl(String shortUrl) { 
        this.shortUrl = shortUrl; 
    }

    public String getFullShortUrl() { 
        return fullShortUrl; 
    }

    public void setFullShortUrl(String fullShortUrl) { 
        this.fullShortUrl = fullShortUrl; 
    }

    public Long getAccessCount() { 
        return accessCount; 
    }

    public void setAccessCount(Long accessCount) { 
        this.accessCount = accessCount; 
    }

    public LocalDateTime getCreatedAt() { 
        return createdAt; 
    }

    public void setCreatedAt(LocalDateTime createdAt) { 
        this.createdAt = createdAt; 
    }
}