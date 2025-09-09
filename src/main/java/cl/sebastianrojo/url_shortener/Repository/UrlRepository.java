package cl.sebastianrojo.url_shortener.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import cl.sebastianrojo.url_shortener.Entity.Url;

public interface UrlRepository extends JpaRepository<Url, Long> {
    
    //Url findByShortUrl(String shortUrl);

    Optional<Url> findByShortUrl(String shortUrl);
    
    Optional<Url> findByOriginalUrl(String originalUrl);

}