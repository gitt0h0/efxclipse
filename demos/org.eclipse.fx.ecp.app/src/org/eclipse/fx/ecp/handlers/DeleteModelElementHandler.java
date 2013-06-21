package org.eclipse.fx.ecp.handlers;

import java.util.Collection;

import javax.inject.Named;

import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecp.core.ECPProject;
import org.eclipse.emf.edit.command.DeleteCommand;
import org.eclipse.emf.edit.domain.EditingDomain;

public class DeleteModelElementHandler {

	private Command command;
	private EditingDomain editingDomain;

	@CanExecute
	public boolean canAddNewElement(@Optional ECPProject project,
			@Optional @Named("modelExplorer.selectedItems") Collection<?> selectedItems) {
		
		if (project != null) {
			editingDomain = project.getEditingDomain();
			command = DeleteCommand.create(editingDomain, selectedItems);
			return command.canExecute();
		}

		return false;
	}

	@Execute
	public void executeCommand() {
		editingDomain.getCommandStack().execute(command);
	}

}
