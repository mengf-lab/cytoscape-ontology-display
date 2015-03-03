package edu.umich.med.mbni.lkq.cyontology.internal.action;

import java.awt.event.ActionEvent;

import org.cytoscape.application.swing.AbstractCyAction;
import org.cytoscape.model.CyNetwork;
import org.cytoscape.work.swing.DialogTaskManager;

import edu.umich.med.mbni.lkq.cyontology.internal.app.MyApplicationCenter;
import edu.umich.med.mbni.lkq.cyontology.internal.app.MyApplicationManager;
import edu.umich.med.mbni.lkq.cyontology.internal.task.PopulateNewOntologyNetworkTaskFactory;
import edu.umich.med.mbni.lkq.cyontology.internal.util.OntologyNetworkUtils;

public class GenerateOntologyNetworkWithOneInteractionAction extends AbstractCyAction {

	private final MyApplicationManager appManager;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5430469897020869631L;

	public GenerateOntologyNetworkWithOneInteractionAction(String name) {
		super(name);
		appManager = MyApplicationCenter.getInstance().getApplicationManager();
		setPreferredMenu("Apps.Ontology Viewer");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		DialogTaskManager taskManager = appManager.getTaskManager();
		
		CyNetwork underlyingNetwork = appManager.getCyApplicationManager()
				.getCurrentNetwork();
		PopulateNewOntologyNetworkTaskFactory populateNewOntologyNetworkTaskFactory = new PopulateNewOntologyNetworkTaskFactory(OntologyNetworkUtils.INTERACTION_IS_A);
		
		taskManager.execute(populateNewOntologyNetworkTaskFactory.createTaskIterator(underlyingNetwork));
	}

}