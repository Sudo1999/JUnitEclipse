package com.openclassroom.testing;

import static java.lang.Integer.valueOf;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class AssertJTest {
	private Calculator calculator;

	// Le nom contient entre 5 et 10 caractères
	@Test
	public void testAutonome_NameLength() {
		String name = "Concerto";
		// Assertion JUnit
		assertTrue(name.length() > 4 && name.length() < 11);
		// Assertion AssertJ
		assertThat(name).hasSizeGreaterThan(4).hasSizeLessThan(11);
	}

	// Le nom commence par une lettre de la première moitié de l'alphabet
	@Test
	public void testAutonome_NameInAlphabet() {
		String name = "Concerto";
		// Assertion JUnit
		assertTrue(name.compareTo("A") >= 0 && name.compareTo("M") <= 0);
		// Assertion AssertJ
		assertThat(name).isBetween("A", "M");
	}

	// Le jour considéré est soit présent, soit futur
	@Test
	public void testAutonome_ValueOfTime() {
		LocalDateTime now = LocalDateTime.now();
		LocalDateTime testedLocalDateTime;
		testedLocalDateTime = now.minusDays(1); // => false
		testedLocalDateTime = now.minusMinutes(1440); // => false (1440 = 24 * 60)
		testedLocalDateTime = now; // => true
		testedLocalDateTime = now.plusHours(24); // => true
		// Assertion JUnit
		assertTrue(testedLocalDateTime.toLocalDate().isEqual(LocalDate.now())
				|| testedLocalDateTime.toLocalDate().isAfter(LocalDate.now()));
		// Assertion AssertJ
		assertThat(testedLocalDateTime.toLocalDate()).isAfterOrEqualTo(LocalDate.now());
	}

	// En TDD on va d'abord coder les tests : un pour un nombre positif, un pour un
	// nombre négatif, et un pour zéro.
	// Set est une Collection non ordonnée qui refuse les éléments dupliqués :
	// chacun ne peut exister qu'une fois.
	@ParameterizedTest(name = "{0} est composé de {1}")
	@CsvSource({ "-1234555, '1,2,3,4,5'" })
	public void testReturnDigitsOfTheNumber(int number, String digitsString) {
//    @Test
//    public void testReturnDigitsOfTheNumber() {

		// GIVEN
		calculator = new Calculator();
		int firstNumber = 529045581;
		int negativeNumber = -3490;
		int zeroNumber = 0;

		Set<Integer> arraysAsListDigits = new HashSet<>(Arrays.asList(5, 2, 9, 0, 4, 5, 5, 8, 1));
		// (Tout élément en double présent dans la liste sera rejeté. Cette approche
		// n'est pas efficace dans le temps :
		// nous créons un array, puis le convertissons en liste, et enfin transmettons
		// la liste pour créer un ensemble)

		Set<Integer> collectorsToSetDigits = Stream.of(5, 2, 9, 0, 4, 5, 5, 8, 1).collect(Collectors.toSet());
		// (Collectors.toSet() accumule les éléments d'entrée dans un nouvel ensemble,
		// mais ne garantit pas son type :
		// même si Java 8 et supérieurs renvoient un HashSet, cela pourrait changer dans
		// les versions futures)

		// Set<Integer> setOfDigits = Set.of(5, 2, 9, 0, 4, 5, 5, 8, 1);
		// (Les instances d'ensemble créées par Set.of() sont immuables et rejettent les
		// éléments en double et les nuls
		// au moment de la création. Les éléments ne peuvent être ni ajoutés, ni
		// supprimés, ni remplacés)

		// Le collect(Collectors.toSet()) retourne un ensemble ordonné qui facilite les
		// comparaisons :
		Set<Integer> firstDigits = Stream.of(5, 2, 9, 0, 4, 8, 1).collect(Collectors.toSet());
		Set<Integer> negativeDigits = Stream.of(3, 4, 9, 0).collect(Collectors.toSet());
		Set<Integer> zeroDigits = Stream.of(0).collect(Collectors.toSet());

		// WHEN
		Set<Integer> calculatorFirstResult = calculator.returnDigitsOfTheNumber(firstNumber);
		Set<Integer> calculatorNegativeResult = calculator.returnDigitsOfTheNumber(negativeNumber);
		Set<Integer> calculatorZeroResult = calculator.returnDigitsOfTheNumber(zeroNumber);

		// THEN
		assertThat(calculatorFirstResult).isEqualTo(firstDigits);
		assertThat(calculatorNegativeResult).isEqualTo(negativeDigits);
		assertThat(calculatorZeroResult).isEqualTo(zeroDigits);

		//// TEST PARAMETRE

		String[] splitArray = digitsString.split(",");
		Integer[] convertArray = new Integer[splitArray.length];
		for (int i = 0; i < splitArray.length; i++) {
			convertArray[i] = valueOf(splitArray[i]);
		}
		Set<Integer> digits = Set.of(convertArray);
		Set<Integer> calculatorParameterResult = calculator.returnDigitsOfTheNumber(number);
		assertThat(calculatorParameterResult).isEqualTo(digits);

		//// AUTRE SYNTAXE ASSERTJ

		assertThat(calculatorFirstResult).containsExactlyInAnyOrder(5, 2, 9, 0, 4, 8, 1);
	}
}