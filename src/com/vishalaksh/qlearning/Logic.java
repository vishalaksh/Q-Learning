package com.vishalaksh.qlearning;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Logic implements Constants {

	double[][] R, Q;

	int[] episodes;
	ArrayList<Integer> al;

	void doQLearning(int goalState) {

		// Set the gamma parameter, and environment rewards in matrix R.
		R = Formula.getR(statesNumber, actionsNumber, goalState);

		System.out.println("R:");
		for (int i = 0; i < statesNumber; i++) {
			System.out.println(Arrays.toString(R[i]));
		}

		// Initialize matrix Q to zero.
		Q = new double[statesNumber][actionsNumber];

		// TODO init each to 0

		// TODO episodes is no. of training iterations. It is independent of
		// statesNumber. States can be repeated during training.
		// generate random episode initial states

		int currState;

		for (int i = 0; i < numberEpisodes; i++) {
			currState = (int) (Math.random() * statesNumber);

			do {
				// Select one among all possible actions for the current state.

				int action = getRandomAction(R, currState);
				// System.out.println("currState:"+currState+" action:"+action);
				// System.out.println("action:" + action);
				/**
				 * Using this possible action, consider going to the next state.
				 * Get maximum Q value for this next state based on all possible
				 * actions. Compute: Q(state, action)
				 */
				Q[currState][action] = (R[currState][action] + gamma
						* getMaxPossibleAction(Q, R, action));
				// System.out.println("Q["+currState+"]["+action+"]:"+Q[currState][action]);
				// TODO see that values in Q have been casted to int

				// Set the next state as the current state.
				currState = action;

			} while (currState != goalState);

			// System.out.println("episode completed:"+i);
		}
		System.out.println("Q:");
		int[] arr = new int[statesNumber];
		populateArray(arr);
		System.out.println(Arrays.toString(arr));
		for (int i = 0; i < statesNumber; i++) {
			System.out.println(i + " " + Arrays.toString(Q[i]));
		}

	}

	private void populateArray(int[] episodes) {
		for (int i = 0; i < episodes.length; i++) {
			episodes[i] = i;
		}

	}

	void getPath(int initialState, int goalState) {
		int currState = initialState;
		al = new ArrayList<>();

		while (currState != goalState) {

			// System.out.println("currState:"+currState);
			al.add(currState);
			int tempState = getStateWithMaxQValue(Q, currState);

			if (tempState == currState) {
				break;
			} else {
				currState = tempState;
			}

		}

	}

	private double getMaxPossibleAction(double[][] Q, double[][] R, int state) {
		// TODO what should be the default value of max when every element in Q
		// is step_invalid?
		double max = 0;

		for (int i = 0; i < actionsNumber; i++) {
			if ((R[state][i] >= step_unchanged) && (Q[state][i] > max)) {
				max = Q[state][i];
			}
		}

		return max;
	}

	int getStateWithMaxQValue(double[][] Q, int state) {
		double max = Double.MIN_VALUE;
		int maxState = 0;

		for (int i = 0; i < actionsNumber; i++) {

			if ((Q[state][i] >= max)/* &&i!=state */) {
				max = Q[state][i];
				maxState = i;
				// System.out.println("new maxState:"+maxState+" state:"+state);
			}
		}
		return maxState;
	}

	private int getRandomAction(double[][] R, int currState) {
		// TODO make it more efficient
		double reward_value = step_invalid;
		int action;
		do {
			int j = (int) (Math.random() * actionsNumber);
			// System.out.println("currState:" + currState + " j:" + j);
			reward_value = R[currState][j];
			action = j;
		} while (reward_value <= step_invalid);

		return action;
	}

	// Implementing Fisher–Yates shuffle
	// http://stackoverflow.com/questions/1519736/random-shuffling-of-an-array
	static void shuffleArray(int[] ar) {
		Random rnd = new Random();
		for (int i = ar.length - 1; i > 0; i--) {
			int index = rnd.nextInt(i + 1);
			// Simple swap
			int a = ar[index];
			ar[index] = ar[i];
			ar[i] = a;
		}
	}
}
