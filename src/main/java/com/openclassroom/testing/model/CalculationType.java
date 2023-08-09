package com.openclassroom.testing.model;

public enum CalculationType {
	ADDITION,
	SOUSTRACTION,
	MULTIPLICATION,
	DIVISION,
	CONVERSION;

	public static CalculationType fromSymbol(String operation) {
		switch (operation) {
		case "+":
			return ADDITION;
		case "-":
			return SOUSTRACTION;
		case "*":
			return MULTIPLICATION;
		case "x":
			return MULTIPLICATION;
		case "/":
			return DIVISION;
		default:
			throw new UnsupportedOperationException("Not implemented yet");
		}
	}
}
