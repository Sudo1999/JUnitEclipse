package com.openclassroom.testing.calcul;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.withinPercentage;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

//import com.openclassroom.testing.calcul.CalculatorConversion;

@Tag("ConversionTest") // @Tag désigne tous les tests de la classe en tant que tests de conversion.
@DisplayName("Convertir différentes unités de mesure.") // @DisplayName permet de nommer les tests de façon claire et compréhensible.
public class CalculatorConversionTest {
	/*
	 * Source = https://openclassrooms.com/fr/courses/6100311-testez-votre-code-java-pour-realiser-des-applications-de-qualite/
	 * 6440676-etiquetez-vos-tests-avec-des-annotations-junit-avancees
	 */

	private CalculatorConversion calculatorConversion = new CalculatorConversion();

	@Nested // @Nested permet de grouper les tests dans une classe interne. Si un seul test échoue, tout le groupe échoue.
	@Tag("TemperatureTest")
	@DisplayName("Convertir des températures.")
	public class TemperatureTest {

		@Test
		@DisplayName("Soit une T° à 0°C, lorsque l'on convertit en °F, on obtient 32°F.")
		void testCelsiusToFahrenheit_returnsThirtyTwoFahrenheit_whenCelsiusIsZero() {
			Double actualFahrenheit = calculatorConversion.celsiusToFahrenheit(0.0);
			assertThat(actualFahrenheit).isCloseTo(32.0, withinPercentage(0.01));
		}

		@Test
		@DisplayName("Soit une T° à 32°F, lorsque l'on convertit en °C, on obtient 0°C.")
		void testFahrenheitToCelsius_returnsZeroCelsius_whenFahrenheitIsThirtyTwo() {
			Double actualCelsius = calculatorConversion.fahrenheitToCelsius(32.0);
			assertThat(actualCelsius).isCloseTo(0.0, withinPercentage(0.01));
		}
	}

	@Test
	@DisplayName("Un volume de 3.78541 litres équivaut à 1 gallon.")
	void testLitresToGallons_returnsOneGallon_whenConvertingTheEquivalentNumberOfLitres() {
		Double actualLitres = calculatorConversion.litresToGallons(3.78541);
		assertThat(actualLitres).isCloseTo(1.0, withinPercentage(0.01));
	}

	@Test
	@DisplayName("La surface d'un disque de rayon 1 doit valoir Pi.")
	void testRadiusToAreaOfCircle_returnsPi_whenTheRadiusIsOne() {
		Double actualArea = calculatorConversion.radiusToAreaOfCircle(1.0);
		assertThat(actualArea).isCloseTo(Math.PI, withinPercentage(0.01));
	}
}
