package com.openclassroom.testing.service;

import com.openclassroom.testing.calcul.CalculatorCouvert;
import com.openclassroom.testing.model.CalculationModel;
import com.openclassroom.testing.model.CalculationType;

public class CalculatorServiceImplementation implements CalculatorService {

	private final CalculatorCouvert calculator;
	private final SolutionFormatter formatter;

	public CalculatorServiceImplementation(CalculatorCouvert calculator, SolutionFormatter formatter) {
		this.calculator = calculator;
		this.formatter = formatter;
	}

	@Override
	public CalculationModel calculate(CalculationModel calculationModel) {

		final CalculationType type = calculationModel.getType();
		Integer response = null;

		switch (type) {
		case ADDITION:
			response = calculator.add(calculationModel.getLeftArgument(), calculationModel.getRightArgument());
			break;
		case SOUSTRACTION:
			response = calculator.subtract(calculationModel.getLeftArgument(), calculationModel.getRightArgument());
			break;
		case MULTIPLICATION:
			response = calculator.multiply(calculationModel.getLeftArgument(), calculationModel.getRightArgument());
			break;
		case DIVISION:
			response = calculator.divide(calculationModel.getLeftArgument(), calculationModel.getRightArgument());
			break;
		default:
			throw new UnsupportedOperationException("Unsupported calculations");
		}

		calculationModel.setSolution(response);
		calculationModel.setFormattedSolution(formatter.format(response));
		return calculationModel;
	}
}
