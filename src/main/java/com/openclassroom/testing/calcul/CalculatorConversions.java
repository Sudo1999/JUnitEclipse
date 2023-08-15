package com.openclassroom.testing.calcul;

public class CalculatorConversions {
	/*
	 * Source = https://github.com/geoffreyarthaud/oc-testing-java-cours/blob/p2ch2/
	 * src/main/java/com/openclassrooms/testing/ConversionCalculator.java
	 */

	// Constantes pour la conversion des températures
	private static final double BASE_FAHRENHEIT = 32.0;
	private static final double CELSIUS_FAHRENHEIT_CONVERSION_FACTOR = 9.0 / 5.0;

	// Constante pour la conversion des volumes
	private static final double LITRE_TO_GALLON_MULTIPLIER = 0.264172;

	// Exposant pour le calcul de la surface d'un disque
	private static final double POWER_OF_RADIUS = 2.0;

	/**
	 * Convertir centigrade (celsius) en fahrenheit.
	 * 
	 * @param celsiusTemperature : Température en degrés celsius à convertir
	 * @return la température équivalente en degrés fahrenheit
	 */
	public Double celsiusToFahrenheit(Double celsiusTemperature) {
		return (celsiusTemperature * CELSIUS_FAHRENHEIT_CONVERSION_FACTOR) + BASE_FAHRENHEIT;
	}

	/**
	 * Convertir fahrenheit en centigrade (celsius).
	 * 
	 * @param fahrenheitTemperature : Température en degrés fahrenheit à convertir
	 * @return la température équivalente en degrés celsius
	 */
	public Double fahrenheitToCelsius(Double fahrenheitTemperature) {
		return (fahrenheitTemperature - BASE_FAHRENHEIT) * CELSIUS_FAHRENHEIT_CONVERSION_FACTOR;
	}

	/**
	 * Convertir un volume en litres en gallons.
	 * 
	 * @param litreVolume : Volume en litres à convertir
	 * @return le volume équivalent en gallons
	 */
	public Double litresToGallons(Double litreVolume) {
		return Math.ceil(litreVolume * LITRE_TO_GALLON_MULTIPLIER);
	}

	/**
	 * Convertir un rayon en surface de disque
	 * 
	 * @param radius : Rayon du disque
	 * @return la surface du disque
	 */
	public Double radiusToAreaOfCircle(Double radius) {
		return Math.PI * Math.pow(radius, POWER_OF_RADIUS);
	}
}
