/*
 * generated by Xtext
 */
package org.eclipse.fx.code.compensator.hsl.ui;

import org.eclipse.xtext.ui.guice.AbstractGuiceAwareExecutableExtensionFactory;
import org.osgi.framework.Bundle;

import com.google.inject.Injector;

import org.eclipse.fx.code.compensator.hsl.ui.internal.HSLActivator;

/**
 * This class was generated. Customizations should only happen in a newly
 * introduced subclass. 
 */
public class HSLExecutableExtensionFactory extends AbstractGuiceAwareExecutableExtensionFactory {

	@Override
	protected Bundle getBundle() {
		return HSLActivator.getInstance().getBundle();
	}
	
	@Override
	protected Injector getInjector() {
		return HSLActivator.getInstance().getInjector(HSLActivator.ORG_ECLIPSE_FX_CODE_COMPENSATOR_HSL_HSL);
	}
	
}
