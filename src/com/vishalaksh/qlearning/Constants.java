package com.vishalaksh.qlearning;

public interface Constants {

	/**
	 * states start from 0
	 */
	public int statesNumber = 20;
	public int actionsNumber = statesNumber;

	public float gamma = 0.7f;

	public double step_reward = 1;
	public double step_unchanged = 0;
	public double step_invalid = -1;

	public int numberEpisodes = 200;

	public int goalState = 2;
	public int initialState = 9;
}
