package com.openclassroom.testing.service;

import com.openclassroom.testing.model.CalculationModel;

public interface CalculatorService {

	/**
	 * Mettre en oeuvre le calcul demandé par le modèle
	 * 
	 * @param calculationModel : Modèle de calcul
	 * @return le modèle de calcul avec son résultat
	 */
	public CalculationModel calculate(CalculationModel calculationModel);
}
