package cl.sebastianrojo.url_shortener.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class ShortenRequest {

    @NotBlank(message = "La URL no puede estar vacía.")
    @Size(max = 2000, message = "La URL no puede superar los 2000 caracteres.")
    @Pattern(
        regexp = "^(https?://).+",
        message = "La URL debe comenzar con http:// o https://"
    )
    private String url;

    public ShortenRequest() {}

    public String getUrl() { return url; }
    public void setUrl(String url) { this.url = url; }
}