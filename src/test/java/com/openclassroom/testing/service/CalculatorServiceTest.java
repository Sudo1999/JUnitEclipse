package com.openclassroom.testing.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.openclassroom.testing.calcul.CalculatorCouvert;
import com.openclassroom.testing.model.CalculationModel;
import com.openclassroom.testing.model.CalculationType;

@ExtendWith(MockitoExtension.class) // On va mocker la classe CalculatorCouvert pour focaliser le test sur CalculatorService
public class CalculatorServiceTest {

	@Mock
	CalculatorCouvert calculator;
	@Mock
	SolutionFormatter formatter;

	private CalculatorService service;

	@BeforeEach
	public void init() {
		service = new CalculatorServiceImplementation(calculator, formatter);
	}

	@Test
	public void testCalculate_shouldUseCalculator_forAddition() {
		
		// GIVEN (when est une méthode statique de Mockito)
		when(calculator.add(1, 2)).thenReturn(3);	// On paramètre la méthode add(1, 2) du calculator mocké pour retourner 3.
	
		// WHEN
		final int result = service.calculate(new CalculationModel(CalculationType.ADDITION, 1, 2))
			.getSolution();
	
		// THEN (verify est une méthode statique de Mockito)
		verify(calculator).add(1, 2);		// On vérifie que la méthode add(1, 2) du calculator mocké a bien été utilisée.
	    verify(calculator, times(1)).add(any(Integer.class), any(Integer.class));		// L'addition a été appelée une fois.
		verify(calculator, never()).subtract(any(Integer.class), any(Integer.class));	// La soustraction n'a jamais été appelée.
		assertThat(result).isEqualTo(3);	// On vérifie que la méthode service.calculate testée produit bien le résultat attendu.
	}

	@Test
	public void testCalculate_shouldFormatSolution_forAddition() {
		
		// GIVEN
		when(calculator.add(10000, 3000)).thenReturn(13000);
		when(formatter.format(13000)).thenReturn("13 000");

		// WHEN
		final String formattedResult = service.calculate(new CalculationModel(CalculationType.ADDITION, 10000, 3000))
			.getFormattedSolution();

		// THEN
		assertThat(formattedResult).isEqualTo("13 000");
	}

	@Test
	public void testCalculate_shouldUseCalculator_forSoustraction() {
		
		// GIVEN
		when(calculator.subtract(10, 6)).thenReturn(4);
	
		// WHEN
		final int result = service.calculate(new CalculationModel(CalculationType.SOUSTRACTION, 10, 6))
			.getSolution();
	
		// THEN
		verify(calculator).subtract(10, 6);
		assertThat(result).isEqualTo(4);
	}

	@Test
	public void testCalculate_shouldUseCalculator_forMultiplication() {
		
		// GIVEN
		when(calculator.multiply(3, 4)).thenReturn(12);
	
		// WHEN
		final int result = service.calculate(new CalculationModel(CalculationType.MULTIPLICATION, 3, 4))
			.getSolution();
	
		// THEN
		verify(calculator).multiply(3, 4);
		assertThat(result).isEqualTo(12);
	}

	@Test
	public void testCalculate_shouldUseCalculator_forDivision() {
		
		// GIVEN
		when(calculator.divide(40, 5)).thenReturn(8);
	
		// WHEN
		final int result = service.calculate(new CalculationModel(CalculationType.DIVISION, 40, 5))
			.getSolution();
	
		// THEN
		verify(calculator).divide(40, 5);
		assertThat(result).isEqualTo(8);
	}

	@Test
	public void testCalculate_shouldThrowIllegalArgumentException_forDivisionBy0() {
		
		// GIVEN
		when(calculator.divide(1, 0)).thenThrow(new IllegalArgumentException());
		
		// THEN
		assertThrows(IllegalArgumentException.class, () -> service.calculate(new CalculationModel(CalculationType.DIVISION, 1, 0)));
		verify(calculator, times(1)).divide(1, 0);
	}

	/* La classe de tests avant le mock :
	
	CalculatorCouvert calculator = new CalculatorCouvert();
	CalculatorService service = new CalculatorServiceImplementation(calculator);
		
	@Test
	public void testAdd_returnsTheSum_ofTwoPositiveNumbers() {
		final int result = service.calculate(
			new CalculationModel(CalculationType.ADDITION, 1, 2)).getSolution();
		assertThat(result).isEqualTo(3);
	}
	
	@Test
	public void testAdd_returnsTheSum_ofTwoNegativeNumbers() {
		final int result = service.calculate(
			new CalculationModel(CalculationType.ADDITION, -1, 2)).getSolution();
		assertThat(result).isEqualTo(1);
	}
	
	@Test
	public void testAdd_returnsTheSum_ofZeroAndZero() {
		final int result = service.calculate(
			new CalculationModel(CalculationType.ADDITION, 0, 0)).getSolution();
		assertThat(result).isEqualTo(0);
	}*/
}
