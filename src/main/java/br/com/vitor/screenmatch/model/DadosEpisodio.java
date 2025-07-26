package br.com.vitor.screenmatch.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosEpisodio(@JsonAlias("Title") String titulo,
                            @JsonAlias("imdbRating") String avaliacao,
                            @JsonAlias("Episode") Integer numero,
                            @JsonAlias("Released") String dataLancamento) {
}
