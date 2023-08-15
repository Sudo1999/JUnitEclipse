package com.openclassroom.testing.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.openclassroom.testing.calcul.CalculatorCouvert;
import com.openclassroom.testing.model.CalculationModel;
import com.openclassroom.testing.model.CalculationType;

@ExtendWith(MockitoExtension.class)
public class BatchCalculatorServiceTest {

	@Mock
	private CalculatorService mockCalculatorService;

	private BatchCalculatorService batchService;
	private BatchCalculatorService batchServiceSansMock;

	@BeforeEach
	public void init() {

		batchService = new BatchCalculatorServiceImplementation(mockCalculatorService);

		batchServiceSansMock = new BatchCalculatorServiceImplementation(new CalculatorServiceImplementation(
			new CalculatorCouvert(), new SolutionFormatterImplementation()));
	}

	@Test
	void testBatchCalculate_returnsCorrectAnswerList() throws IOException, URISyntaxException {

		// GIVEN
		final Stream<String> operations = Arrays.asList("2 + 2", "5 - 4", "6 x 8", "9 / 3").stream();

		// WHEN
		final List<CalculationModel> resultList = batchServiceSansMock.batchCalculate(operations);

		// THEN
		assertThat(resultList)
			.extracting(CalculationModel::getSolution) // La méthode extrait l'attribut solution de l'objet CalculationModel
			.containsExactly(4, 1, 48, 3);
	}

	@Test
	void testBatchCalculate_callsServiceWithCorrectArguments() throws IOException, URISyntaxException {
		// ArgumentCaptor enregistre les arguments réels utilisés lors d'un appel de mock (ici les modèles de calcul).

		// GIVEN
		final Stream<String> operations = Arrays.asList("2 + 2", "5 - 4", "6 x 8", "9 / 3").stream();
		final ArgumentCaptor<CalculationModel> captor = ArgumentCaptor.forClass(CalculationModel.class);

		// WHEN
		batchService.batchCalculate(operations);

		// THEN
		verify(mockCalculatorService, times(4)).calculate(captor.capture());
		assertThat(captor.getAllValues())
			.extracting(CalculationModel::getType, CalculationModel::getLeftArgument, CalculationModel::getRightArgument)
			.containsExactly(
				tuple(CalculationType.ADDITION, 2, 2),
				tuple(CalculationType.SOUSTRACTION, 5, 4),
				tuple(CalculationType.MULTIPLICATION, 6, 8),
				tuple(CalculationType.DIVISION, 9, 3));
	}

	@Test
	void testBatchCalculate_callsServiceAndReturnsAnswer_WithWhenThen() throws IOException, URISyntaxException {
		// L'association de when() avec then() permet de mocker la réponse en fonction des arguments appelés.

		// GIVEN
		final Stream<String> operations = Arrays.asList("2 + 2", "5 - 4", "6 x 8", "9 / 3").stream();
		when(mockCalculatorService.calculate(any(CalculationModel.class)))
			.then(invocation -> {
				final CalculationModel modele = invocation.getArgument(0, CalculationModel.class);
				switch (modele.getType()) {
				case ADDITION:
					modele.setSolution(4);
					break;
				case SOUSTRACTION:
					modele.setSolution(1);
					break;
				case MULTIPLICATION:
					modele.setSolution(48);
					break;
				case DIVISION:
					modele.setSolution(3);
					break;
				default:
				}
				return modele;
			});

		// WHEN
		final List<CalculationModel> resultList = batchService.batchCalculate(operations);

		// THEN
		verify(mockCalculatorService, times(4)).calculate(any(CalculationModel.class));
		assertThat(resultList).extracting("solution").containsExactly(4, 1, 48, 3);
	}

	@Test
	void testBatchCalculate_callsServiceAndReturnsAnswer_WithChainedThenReturn() throws IOException, URISyntaxException {
		// L'association de when() avec des thenReturn() en chaînes permet de mocker une succession de réponses.

		// GIVEN
		final Stream<String> operations = Arrays.asList("2 + 2", "5 - 4", "6 x 8", "9 / 3").stream();
		when(mockCalculatorService.calculate(any(CalculationModel.class)))
			.thenReturn(new CalculationModel(CalculationType.ADDITION, 2, 2, 4))
			.thenReturn(new CalculationModel(CalculationType.SOUSTRACTION, 5, 4, 1))
			.thenReturn(new CalculationModel(CalculationType.MULTIPLICATION, 6, 8, 48))
			.thenReturn(new CalculationModel(CalculationType.DIVISION, 9, 3, 3));

		// WHEN
		final List<CalculationModel> resultList = batchService.batchCalculate(operations);

		// THEN
		verify(mockCalculatorService, times(4)).calculate(any(CalculationModel.class));
		assertThat(resultList).extracting("solution").containsExactly(4, 1, 48, 3);
	}
}
