package com.vishalaksh.qlearning;

import java.util.Iterator;

public class MyQLearning implements Constants {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		// System.out.println("statesNumber:"+i);
		long lastTime = System.currentTimeMillis();
		Logic mLogic = new Logic();
		mLogic.doQLearning(goalState);
		long learnTime = System.currentTimeMillis();
		mLogic.getPath(initialState, goalState);
		long pathTime = System.currentTimeMillis();

		System.out.println((pathTime - lastTime));

		Iterator<Integer> it = mLogic.al.iterator();

		while (it.hasNext()) {
			System.out.print(it.next());
			System.out.print(",");
		}
	}

}
