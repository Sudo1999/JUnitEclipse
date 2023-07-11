package com.openclassroom.testing;

import java.util.HashSet;
import java.util.Set;

public class CalculatorCouvert {

	public int add(int a, int b) {
		return a + b;
	}

	public int multiply(int a, int b) {
		return a * b;
	}

	public double add(double a, double b) {
		return a + b;
	}

	public double multiply(double a, double b) {
		return a * b;
	}

	public String longCalcul() /* throws InterruptedException */ {
		String message = "Le test est passé avec succès !!!";
		try {
			// Attendre 1 seconde
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return message;
	}

	public Set<Integer> returnDigitsOfTheNumber(int number) {

		String numberString = String.valueOf(number);
		Set<Integer> integers = new HashSet<>();
		for (int i = 0; i < numberString.length(); i++) {
			if (number < 0 && i == 0) {
				i++;
			}
			integers.add(Integer.parseInt(numberString, i, i + 1, 10));
		}
		return integers;
	}
}