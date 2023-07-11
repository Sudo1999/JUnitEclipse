package com.openclassroom.testing;

import static java.lang.Character.getNumericValue;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

//import org.apache.commons.lang3.ArrayUtils;

public class Calculator {
	public int add(int a, int b) {
		return a + b;
	}

	public int multiply(int a, int b) {
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

		/*
		 * Syntaxe pour convertir un type primitif en son objet et vice-versa : byte[]
		 * primitiveTypeArray = ArrayUtils.toPrimitive(objectTypeArray); Byte[]
		 * objectTypeArray = ArrayUtils.toObject(primitiveTypeArray);
		 */

		char[] charsArray = String.valueOf(number).toCharArray();
		Integer[] integersArray = new Integer[charsArray.length];
		for (int i = 0; i < charsArray.length; i++) {
			if (Character.isDigit(charsArray[i])) {
				integersArray[i] = getNumericValue(charsArray[i]);
			}
		}
		Set<Integer> digitsOfTheNumber = Arrays.stream(integersArray).filter(Objects::nonNull)
				.collect(Collectors.toSet());

		// Implémentation simplifiée :
		String numberString = String.valueOf(number);
		Set<Integer> integers = new HashSet<>();
		for (int i = 0; i < numberString.length(); i++) {
			if (number < 0 && i == 0) {
				i++;
			}
			integers.add(Integer.parseInt(numberString, i, i + 1, 10));
		}

		System.out.println(charsArray);
		System.out.println(charsArray[0]);
		System.out.println(charsArray[charsArray.length - 1]);
		System.out.println(digitsOfTheNumber);

		return digitsOfTheNumber;
		// return integers;
	}
}