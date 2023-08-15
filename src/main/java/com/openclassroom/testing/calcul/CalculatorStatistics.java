package com.openclassroom.testing.calcul;

import java.util.IntSummaryStatistics;
import java.util.List;

public class CalculatorStatistics {
	// Calcul d'une moyenne en utilisant une instance de IntSummaryStatistics

	private final IntSummaryStatistics summaryStatistics;

	public CalculatorStatistics(IntSummaryStatistics summaryStatistics) {
		this.summaryStatistics = summaryStatistics;
	}

	public Integer average(List<Integer> samples) {
		Double average;
		samples.forEach(summaryStatistics::accept);
		average = summaryStatistics.getAverage();

		/* La méthode getAverage() est une méthode finale de la classe java.util.IntSummaryStatistics. Elle ne pourra pas être mockée :
		   public final double getAverage() {
		    return getCount() > 0 ? (double) getSum() / getCount() : 0.0d;
		   }*/

		return average.intValue();
	}
}
