package org.eclipse.fx.ecp;

import java.net.URL;
import java.util.Collections;
import java.util.Comparator;

import javafx.scene.Node;
import javafx.scene.control.TreeItem;
import javafx.scene.image.ImageView;

import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EPackage.Registry;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.ReflectiveItemProviderAdapterFactory;
import org.eclipse.fx.emf.edit.ui.AdapterFactoryCellFactory;
import org.osgi.framework.Bundle;

public class ECPUtil {

	public static final ComposedAdapterFactory DEFAULT_ADAPTER_FACTORY;
	private static final String PACKAGE_IMAGE_URL;

	static {
		DEFAULT_ADAPTER_FACTORY = new ComposedAdapterFactory();
		DEFAULT_ADAPTER_FACTORY.addAdapterFactory(new ReflectiveItemProviderAdapterFactory());
		DEFAULT_ADAPTER_FACTORY.addAdapterFactory(new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE));
		Bundle bundle = Platform.getBundle("org.eclipse.fx.ecp.app");
		URL entry = bundle.getEntry("icons/EPackage.gif");
		PACKAGE_IMAGE_URL = entry.toExternalForm();
	}

	/**
	 * Helper class to sort the entries for the EClass selection alphabetically
	 */
	public static class ENamedElementTreeItemComparator implements Comparator<TreeItem<ENamedElement>> {

		@Override
		public int compare(TreeItem<ENamedElement> o1, TreeItem<ENamedElement> o2) {
			ENamedElement value1 = o1.getValue();
			ENamedElement value2 = o2.getValue();

			if (value1 instanceof EPackage && !(value2 instanceof EPackage))
				return -1;
			else if (!(value1 instanceof EPackage) && value2 instanceof EPackage)
				return +1;
			else {
				String name1 = value1.getName();
				String name2 = value2.getName();
				return name1.compareTo(name2);
			}
		}
	}

	public static TreeItem<ENamedElement> getConcreteClasses() {

		TreeItem<ENamedElement> root = new TreeItem<>();

		for (String nsURI : Registry.INSTANCE.keySet()) {
			EPackage ePackage = Registry.INSTANCE.getEPackage(nsURI);
			addPackage(root, ePackage);
		}

		Collections.sort(root.getChildren(), new ENamedElementTreeItemComparator());

		return root;
	}

	private static void addPackage(TreeItem<ENamedElement> root, EPackage ePackage) {
		TreeItem<ENamedElement> ePackageItem = new TreeItem<ENamedElement>(ePackage, new ImageView(PACKAGE_IMAGE_URL));
		root.getChildren().add(ePackageItem);

		for (EPackage eSubpackage : ePackage.getESubpackages())
			addPackage(ePackageItem, eSubpackage);

		addConcreteClasses(ePackage, ePackageItem);
		
		Collections.sort(ePackageItem.getChildren(), new ENamedElementTreeItemComparator());
	}

	private static void addConcreteClasses(EPackage ePackage, TreeItem<ENamedElement> ePackageItem) {

		for (EClassifier eClassifier : ePackage.getEClassifiers()) {
			if (eClassifier instanceof EClass) {
				EClass eClass = (EClass) eClassifier;
				if (!eClass.isAbstract() && !eClass.isInterface()) {
					Node graphic = getIconForEClass(eClass);
					ePackageItem.getChildren().add(new TreeItem<ENamedElement>(eClass, graphic));
				}
			}
		}

	}

	private static Node getIconForEClass(EClass eClass) {
		EPackage ePackage = eClass.getEPackage();
		EObject instance = ePackage.getEFactoryInstance().create(eClass);
		try {
			IItemLabelProvider labelProvider = (IItemLabelProvider) DEFAULT_ADAPTER_FACTORY.adapt(instance, IItemLabelProvider.class);
			if (labelProvider != null) {
				Object image = labelProvider.getImage(instance);
				return AdapterFactoryCellFactory.graphicFromObject(image);
			}
		} catch (Exception e) {
			// TODO maybe log this?
		}
		return null;
	}

}
