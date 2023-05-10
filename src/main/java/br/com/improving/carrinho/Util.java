package br.com.improving.carrinho;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Classe responsável pela formatação e conversão de valores (câmbio internacional) e métodos utilitários.
 */
public class Util {
	public static String formatarValorReal(BigDecimal valor) {
		NumberFormat nf = NumberFormat.getCurrencyInstance(Moeda.REAL.getPais());
		return nf.format(valor);
	}
	
	public static String formatarValorReal(double valor) {
		NumberFormat nf = NumberFormat.getCurrencyInstance(Moeda.REAL.getPais());
		return nf.format(valor);
	}
	
	public static String formatarValorEuro(BigDecimal valor) {
		NumberFormat nf = NumberFormat.getCurrencyInstance(Moeda.EURO.getPais());
		return nf.format(valor.divide(BigDecimal.valueOf(Moeda.EURO.getCotacao()), 2, RoundingMode.HALF_UP));
	}
	
	public static String formatarValorDolar(BigDecimal valor) {
		NumberFormat nf = NumberFormat.getCurrencyInstance(Moeda.DOLAR.getPais());
		return nf.format(valor.divide(BigDecimal.valueOf(Moeda.DOLAR.getCotacao()), 2, RoundingMode.HALF_UP));
	}
	
	public static List<String> extractNumbers(String input) {
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(input);
        List<String> numbers = new ArrayList<>(matcher.groupCount());
        int i = 0;
        while (matcher.find()) {
            numbers.add(matcher.group());
        }
        return numbers;
    }
}
