package org.eclipse.fx.ecp.ui.controls.numeric;

import java.math.BigDecimal;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.fx.ecp.ui.ECPControl;
import org.eclipse.fx.ecp.ui.ECPModelElementOpener;
import org.eclipse.fx.ecp.ui.controls.ECPControlBase;


public class BigDecimalSpinner extends NumberSpinner {

	public static final BigDecimal DEFAULT_VALUE = BigDecimal.ZERO;

	public BigDecimalSpinner(IItemPropertyDescriptor propertyDescriptor, final EObject modelElement, final EditingDomain editingDomain) {
		super(propertyDescriptor, modelElement, editingDomain);

		setSkin(new NumberSpinnerSkin<NumberSpinner, BigDecimal>(this) {

			@Override
			boolean validate(String literal) {
				if (literal.matches("^\\-?[0-9]+\\.?[0-9]*$")) {
					try {
						Double.parseDouble(literal);
						return true;
					} catch (NumberFormatException e) {
						// do nothing
					}
				}
				return false;
			}

			@Override
			BigDecimal decrease(BigDecimal value) {
				if(value == null)
					value = DEFAULT_VALUE;
				
				return value.subtract(BigDecimal.ONE);
			}

			@Override
			BigDecimal increase(BigDecimal value) {
				if(value == null)
					value = DEFAULT_VALUE;
				
				return value.add(BigDecimal.ONE);
			}

			@Override
			BigDecimal parseValue(String literal) {
				try {
					return new BigDecimal(literal);
				} catch (NumberFormatException e) {
					return DEFAULT_VALUE;
				}
			}

		});
	}

	public static class Factory implements ECPControl.Factory {

		@Override
		public ECPControlBase createControl(IItemPropertyDescriptor itemPropertyDescriptor, EObject modelElement, EditingDomain editingDomain, ECPModelElementOpener modelElementOpener) {
			return new BigDecimalSpinner(itemPropertyDescriptor, modelElement, editingDomain);
		}

	}

}
