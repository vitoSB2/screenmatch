package br.com.vitor.screenmatch.principal;

import br.com.vitor.screenmatch.model.DadosSerie;
import br.com.vitor.screenmatch.model.DadosTemporada;
import br.com.vitor.screenmatch.service.ConsumoApi;
import br.com.vitor.screenmatch.service.ConverteDados;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Principal {
    private Scanner leitura = new Scanner(System.in);
    private ConsumoApi consumoApi = new ConsumoApi();
    private ConverteDados conversor =  new ConverteDados();
    private final String ENDERECO = "http://www.omdbapi.com/?t=";
    private final String API_KEY = "&apikey=1abb9d6e";

    public void exibeMenu(){
        System.out.println("Bem-vindo ao ScreenMatch!");
        System.out.println("Digite o nome da Série:");
        var nomeSerie = leitura.nextLine();
        var json = consumoApi.obterDados(ENDERECO + nomeSerie.replace(" ", "+") + API_KEY);
        DadosSerie dados = conversor.obterDados(json, DadosSerie.class);
        System.out.println(dados);

		List<DadosTemporada> temporadas = new ArrayList<>();

		for(int i=1; i<=dados.nTemporadas(); i++){
			json = consumoApi.obterDados(ENDERECO + nomeSerie.replace(" ", "+")+ "&season=" + i + API_KEY);
			DadosTemporada dadosTemporada = conversor.obterDados(json, DadosTemporada.class);
			temporadas.add(dadosTemporada);
		}
		temporadas.forEach(t-> t.episodios().forEach(e -> System.out.println(e.titulo())));
    }
}
