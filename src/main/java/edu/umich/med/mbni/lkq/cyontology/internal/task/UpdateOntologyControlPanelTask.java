package edu.umich.med.mbni.lkq.cyontology.internal.task;

import java.util.Collection;
import java.util.HashSet;

import org.cytoscape.model.CyColumn;
import org.cytoscape.model.CyEdge;
import org.cytoscape.model.CyNetwork;
import org.cytoscape.model.CyRow;
import org.cytoscape.task.AbstractNetworkTask;
import org.cytoscape.work.TaskMonitor;

import edu.umich.med.mbni.lkq.cyontology.internal.app.MyApplicationCenter;
import edu.umich.med.mbni.lkq.cyontology.internal.view.OntologyControlPanel;

public class UpdateOntologyControlPanelTask extends AbstractNetworkTask {
	
	public static class UpdateOntologyControlOptions {
		public boolean updateInteraction;
		public boolean updateAggregationColumns;
		public boolean updateOntologyTree;
		public String interactionType;
		
		public UpdateOntologyControlOptions(boolean updateInteraction, boolean updateAggregationColumns, boolean updateOntologyTree, String interactionType) {
			this.updateAggregationColumns = updateAggregationColumns;
			this.updateInteraction = updateInteraction;
			this.updateOntologyTree = updateOntologyTree;
			this.interactionType = interactionType;
		}
	}
	
	private final OntologyControlPanel ontologyControlPanel;
	private final UpdateOntologyControlOptions options;

	public UpdateOntologyControlPanelTask(final CyNetwork network, final OntologyControlPanel ontologyControlPanel, final UpdateOntologyControlOptions options) {
		super(network);
		this.ontologyControlPanel = ontologyControlPanel;
		this.options = options;
	}

	@Override
	public void run(TaskMonitor taskMonitor) throws Exception {
		if (options.updateAggregationColumns) rePopAggregationValues();
		if (options.updateInteraction) rePopInteractionType();
		if (options.updateOntologyTree) rePopOntologyTree();
	}

	private void rePopOntologyTree() {
		// TODO Auto-generated method stub
		
	}

	private void rePopInteractionType() {
		
		Collection<CyRow> allRows = network.getDefaultEdgeTable()
				.getAllRows();

		HashSet<String> allTypes = new HashSet<String>();

		for (CyRow row : allRows) {
			String interactionType = row.get(CyEdge.INTERACTION, String.class);
			allTypes.add(interactionType);
		}
		
		ontologyControlPanel.setInteractionTypeChoice(allTypes, options.interactionType);
	}

	private void rePopAggregationValues() {
		
		if (!MyApplicationCenter.getInstance().hasEncapsulatingOntologyNetwork(
				network))
			return;

		Collection<CyColumn> allColumns = network.getDefaultNodeTable()
				.getColumns();
		
		HashSet<String> allAggregationColumns = new HashSet<String>();

		for (CyColumn column : allColumns) {
			if (column.getType() == Double.class) {
				allAggregationColumns.add(column.getName());
			}
		}
		
		ontologyControlPanel.setAggregationColumnChoice(allAggregationColumns);
	}
	
}
