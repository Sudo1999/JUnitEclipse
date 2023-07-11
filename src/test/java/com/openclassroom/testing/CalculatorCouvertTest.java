package com.openclassroom.testing;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.MessageFormat;
import java.time.Duration;
import java.time.Instant;
import java.util.Set;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

public class CalculatorCouvertTest {

	private static Instant startInstant;
	private static int testNumber = 0;
	private CalculatorCouvert calculatorCouvert;

	// @BeforeAll et @AfterAll ne fonctionnent pas avec les tests paramétrés
	@BeforeAll
	public static void initStartingInstant() {
		System.out.println("Appel avant tous les tests");
		startInstant = Instant.now();
	}

	@AfterAll
	public static void showTotalDuration() {
		System.out.println("Appel après tous les tests");
		Instant endInstant = Instant.now();
		Long duration = Duration.between(startInstant, endInstant).toMillis();
		System.out.println(MessageFormat.format("Durée de la totalité des tests : {0} millisecondes", duration));
	}

	@BeforeEach
	public void initCalculator() {
		calculatorCouvert = new CalculatorCouvert();
	}

	//////// DEBUT DES TESTS ////////

	@Test
	public void testAdd_twoPositiveNumbers() {

		// ARRANGE = GIVEN
		int a = 2;
		int b = 3;
		// ACT = WHEN
		int somme = calculatorCouvert.add(a, b);
		// ASSERT = THEN
		assertEquals(5, somme);
		// ASSERT WITH ASSERTJ
		assertThat(somme).isEqualTo(5);
	}

	@ParameterizedTest(name = "{0} + {1} doit être égal à {2}")
	@CsvSource({ "1,1,2", "5,10,15", "256,410,666" })
	public void testAdd_manyDifferentIntegers(int argument1, int argument2, int resultat) {

		// ACT
		int somme = calculatorCouvert.add(argument1, argument2);
		// ASSERT
		assertEquals(resultat, somme);
		// ASSERT WITH ASSERTJ
		assertThat(somme).isEqualTo(resultat);
	}

	@Test
	public void testMultiply_twoPositiveNumbers() {

		// ARRANGE
		int a = 6;
		int b = 5;
		// ACT
		int produit = calculatorCouvert.multiply(a, b);
		// ASSERT
		assertEquals(30, produit);
		// ASSERT WITH ASSERTJ
		assertThat(produit).isEqualTo(30);
	}

	@ParameterizedTest(name = "{0} x 0 doit être égal à 0")
	@ValueSource(ints = { 0, 1, 10, 555, 18990 })
	public void testMultiply_anyNumberByZeroMustReturnZero(int argument) {

		// ACT
		int produit = calculatorCouvert.multiply(argument, 0);
		// ASSERT
		assertEquals(0, produit);
		// ASSERT WITH ASSERTJ
		assertThat(produit).isZero();
	}

	@Test
	@Timeout(value = 2)
	public void TestLongCalcul_shouldComputeInLessThan2Seconds() {

		// ACT
		String message = calculatorCouvert.longCalcul();
		// ASSERT
		assertEquals("Le test est passé avec succès !!!", message);
		// ASSERT WITH ASSERTJ
		assertThat(message).isEqualTo("Le test est passé avec succès !!!");
	}

	@Test
	public void testReturnDigitsOfTheNumber() {

		// GIVEN
		calculatorCouvert = new CalculatorCouvert();
		int firstNumber = 529045581;
		int negativeNumber = -3490;
		int zeroNumber = 0;

		// WHEN
		Set<Integer> calculatorFirstResult = calculatorCouvert.returnDigitsOfTheNumber(firstNumber);
		Set<Integer> calculatorNegativeResult = calculatorCouvert.returnDigitsOfTheNumber(negativeNumber);
		Set<Integer> calculatorZeroResult = calculatorCouvert.returnDigitsOfTheNumber(zeroNumber);

		// THEN
		assertThat(calculatorFirstResult).containsExactlyInAnyOrder(5, 2, 9, 0, 4, 8, 1);
		assertThat(calculatorNegativeResult).containsExactlyInAnyOrder(3, 4, 9, 0);
		assertThat(calculatorZeroResult).containsExactlyInAnyOrder(0);
	}
}
