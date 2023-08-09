package com.openclassroom.testing.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import com.openclassroom.testing.calcul.CalculatorCouvert;
import com.openclassroom.testing.model.CalculationModel;
import com.openclassroom.testing.model.CalculationType;

public class CalculatorServiceTest {

	CalculatorCouvert calculator = new CalculatorCouvert();
	CalculatorService service = new CalculatorServiceImplementation(calculator);

	@Test
	public void add_returnsTheSum_ofTwoPositiveNumbers() {
		final int result = service.calculate(
			new CalculationModel(CalculationType.ADDITION, 1, 2)).getSolution();
		assertThat(result).isEqualTo(3);
	}

	@Test
	public void add_returnsTheSum_ofTwoNegativeNumbers() {
		final int result = service.calculate(
			new CalculationModel(CalculationType.ADDITION, -1, 2)).getSolution();
		assertThat(result).isEqualTo(1);
	}

	@Test
	public void add_returnsTheSum_ofZeroAndZero() {
		final int result = service.calculate(
			new CalculationModel(CalculationType.ADDITION, 0, 0)).getSolution();
		assertThat(result).isEqualTo(0);
	}
}
