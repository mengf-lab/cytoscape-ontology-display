package edu.umich.med.mbni.lkq.cyontology.internal.task;

import org.cytoscape.model.CyNetwork;
import org.cytoscape.task.AbstractNetworkTaskFactory;
import org.cytoscape.work.TaskIterator;

public class PopulateNewOntologyNetworkTaskFactory extends AbstractNetworkTaskFactory {

	private String interactionType;
	public PopulateNewOntologyNetworkTaskFactory(String interactionType) {
		this.interactionType = interactionType;
	}
	
	@Override
	public TaskIterator createTaskIterator(CyNetwork network) {
		return new TaskIterator(new PopulateNewOntologyNetworkTask(network, interactionType));
	}

}
