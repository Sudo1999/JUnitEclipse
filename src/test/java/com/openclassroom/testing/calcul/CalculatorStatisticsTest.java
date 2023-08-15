package com.openclassroom.testing.calcul;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CalculatorStatisticsTest {

	@Spy
	IntSummaryStatistics spiedSummaryStatistics = new IntSummaryStatistics();

	private CalculatorStatistics calculatorStatistics;

	@BeforeEach
	public void setUp() {
		calculatorStatistics = new CalculatorStatistics(spiedSummaryStatistics);
	}

	@Test
	void testAverage_shouldSample_allProvidedIntegers() {
		// Une des méthodes appelées par average(), getAverage(), est une méthode finale et ne pourra pas être mockée.
		// L'annotation @Spy permet d'utiliser une classe réelle, tout en la mockant partiellement. Par défaut,
		// le comportement réel est utilisé, mais les méthodes non finales (par exemple ici, accept()) peuvent être modifiées.

		final List<Integer> samples = Arrays.asList(2, 8, 5, 3, 7);
		final ArgumentCaptor<Integer> captor = ArgumentCaptor.forClass(Integer.class);

		calculatorStatistics.average(samples);

		verify(spiedSummaryStatistics, times(samples.size())).accept(captor.capture());
		assertThat(captor.getAllValues()).containsExactly(samples.toArray(new Integer[0]));

		/* L'interface List possède une méthode toArray() qui par défaut retourne un tableau d'objets non définis :
		 * Object[] array = myList.toArray();
		 * Si on lui passe un tableau d'un type précis, et qu'il est de longueur 0, c'est Java qui déterminera la taille :
		 * String[] array = myList.toArray(new String[myList.size()]);
		 * String[] array = myList.toArray(new String[0]);
		 * */
	}

	@Test
	void testAverage_shouldReturnTheAverage_ofAListOfIntegers() {

		final List<Integer> samples = Arrays.asList(2, 8, 5, 3, 7);
		final Integer result = calculatorStatistics.average(samples);

		assertThat(result).isEqualTo(5);
	}
}
