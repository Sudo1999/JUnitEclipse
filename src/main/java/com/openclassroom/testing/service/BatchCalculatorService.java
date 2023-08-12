package com.openclassroom.testing.service;

import java.util.List;
import java.util.stream.Stream;

import com.openclassroom.testing.model.CalculationModel;

public interface BatchCalculatorService {

	public List<CalculationModel> batchCalculate(Stream<String> operations);
}
