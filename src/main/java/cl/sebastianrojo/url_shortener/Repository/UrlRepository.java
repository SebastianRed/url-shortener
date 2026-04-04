package cl.sebastianrojo.url_shortener.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import cl.sebastianrojo.url_shortener.entity.Url;

public interface UrlRepository extends JpaRepository<Url, Long> {

    Optional<Url> findByShortUrl(String shortUrl);

    Optional<Url> findByOriginalUrl(String originalUrl);
}