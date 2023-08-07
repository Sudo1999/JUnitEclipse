package com.openclassroom.testing.calcul;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestInstancePostProcessor;

public class LoggingExtension implements TestInstancePostProcessor {
	/*
	 * Source = https://openclassrooms.com/fr/courses/6100311-testez-votre-code-java-pour-realiser-des-applications-de-qualite/
	 * 6440676-etiquetez-vos-tests-avec-des-annotations-junit-avancees
	 * 
	 * Cette extension implémente une interface permettant de manipuler la classe de test juste après sa création.
	 */

	@Override
	public void postProcessTestInstance(Object testInstance, ExtensionContext context) throws Exception {
		// Auto-generated method stub

		Logger logger = LogManager.getLogger(testInstance.getClass());
		testInstance.getClass().getMethod("setLogger", Logger.class).invoke(testInstance, logger);
	}
}
