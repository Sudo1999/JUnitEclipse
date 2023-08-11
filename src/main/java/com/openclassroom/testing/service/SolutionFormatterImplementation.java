package com.openclassroom.testing.service;

import java.util.Locale;

public class SolutionFormatterImplementation implements SolutionFormatter {

	@Override
	public String format(int solution) {
		return String.format(Locale.FRENCH, "%,d", solution);
		// Pour le détail de la syntaxe, voir https://docs.oracle.com/javase/8/docs/api/java/util/Formatter.html
		// ',' => The result will include locale-specific grouping separators.
		// 'd' 	=> The result is formatted as a decimal integer.		
	}
}
