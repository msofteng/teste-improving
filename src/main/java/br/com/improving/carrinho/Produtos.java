package br.com.improving.carrinho;

import static org.jsoup.Connection.Method.GET;
import static org.jsoup.Jsoup.connect;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Connection;
import org.jsoup.Connection.Response;

import com.fasterxml.jackson.core.json.JsonReadFeature;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

/**
 * Classe responsável pelo retorno de produtos da API REST.
 */
public class Produtos {
	public static List<Produto> getProdutos() {
		try {
			Connection con = connect("https://dummyjson.com/products?limit=100").ignoreContentType(true).method(GET);
			Response res = con.execute();
			
			ObjectMapper mapper = new ObjectMapper();
			
			mapper.registerModule(new JavaTimeModule());
			mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
			mapper.configure(JsonReadFeature.ALLOW_UNESCAPED_CONTROL_CHARS.mappedFeature(), true);
			
			List<Produto> produtos = mapper.readValue(mapper.readTree(res.body()).get("products").toString(), new TypeReference<List<Produto>>(){});
			return produtos;
		} catch (IOException ex) {
			System.err.println("Erro: " + ex.getMessage());
			return new ArrayList<>();
		}
	}
}
