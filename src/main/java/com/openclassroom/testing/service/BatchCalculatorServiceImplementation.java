package com.openclassroom.testing.service;

import java.util.List;
import java.util.stream.Stream;

import com.openclassroom.testing.model.CalculationModel;

public class BatchCalculatorServiceImplementation implements BatchCalculatorService {

	private final CalculatorService calculatorService;

	public BatchCalculatorServiceImplementation(CalculatorService calculatorService) {
		this.calculatorService = calculatorService;
	}

	@Override
	public List<CalculationModel> batchCalculate(Stream<String> operations) {
		/* La syntaxe avant d'être compactée :
		return operations
			.map(operation -> CalculationModel.fromText(operation))
			.map(modele -> calculatorService.calculate(modele))
			.collect(Collectors.toList());*/

		return operations.map(operation -> calculatorService.calculate(CalculationModel.fromText(operation)))
			.toList();
	}
}
