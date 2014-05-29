package com.vishalaksh.qlearning;

public class Formula implements Constants {

	static double getResistance(int v, int n, int fi, int k, int i) {
		double r;

		r = (v - (n * fi) / k) / i;

		return r;
	}

	static int getState(double r, double totalResistance, int totalStates) {
		int s;

		s = (int) (r / totalResistance * totalStates);

		return s;
	}

	static double[][] getR(int states, int actions, int goalState) {

		double[][] R = new double[states][actions];

		for (int i = 0; i < states; i++) {
			for (int j = 0; j < actions; j++) {

				if ((Math.abs(j - i) == 1)) {
					if ((j == goalState)) {
						R[i][j] = step_reward;
					} else {
						R[i][j] = step_unchanged;
					}
				} else {
					R[i][j] = step_invalid;
				}

			}
		}

		return R;

	}
}
