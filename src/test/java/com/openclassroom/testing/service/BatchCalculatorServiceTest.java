package com.openclassroom.testing.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.openclassroom.testing.calcul.CalculatorCouvert;
import com.openclassroom.testing.model.CalculationModel;

public class BatchCalculatorServiceTest {

	private BatchCalculatorService service;

	@BeforeEach
	public void init() {
		service = new BatchCalculatorServiceImplementation(new CalculatorServiceImplementation(
			new CalculatorCouvert(), new SolutionFormatterImplementation()));
	}

	@Test
	public void testBatchCalculate_returnsCorrectAnswerList() throws IOException, URISyntaxException {

		// GIVEN
		final Stream<String> operations = Arrays.asList("2 + 2", "5 - 4", "6 x 8", "9 / 3").stream();

		// WHEN
		final List<CalculationModel> results = service.batchCalculate(operations);

		// THEN
		assertThat(results)
			.extracting(CalculationModel::getSolution) // La méthode extrait l'attribut solution de l'objet CalculationModel
			.containsExactly(4, 1, 48, 3);
	}
}
