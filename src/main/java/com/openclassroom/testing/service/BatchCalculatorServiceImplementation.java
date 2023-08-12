package com.openclassroom.testing.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.openclassroom.testing.model.CalculationModel;

public class BatchCalculatorServiceImplementation implements BatchCalculatorService {

	private CalculatorServiceImplementation calculatorService;

	public BatchCalculatorServiceImplementation(CalculatorServiceImplementation calculatorService) {
		this.calculatorService = calculatorService;
	}

	@Override
	public List<CalculationModel> batchCalculate(Stream<String> operations) {

		/*return operations
			.map(operation -> CalculationModel.fromText(operation))
			.map(modele -> calculatorService.calculate(modele))
			.collect(Collectors.toList());*/
		return operations.map(operation -> calculatorService.calculate(CalculationModel.fromText(operation)))
			.collect(Collectors.toList());
	}
}
