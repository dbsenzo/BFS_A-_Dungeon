package sae.solver;

import sae.graph.GraphSoluce;

public interface Solver {
	
	public void resolve();
	public GraphSoluce getSoluce();
	public int getSteps();

}
