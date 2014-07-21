package com.android.financeplanner.model;

import java.util.List;

public class GraphDataCalculator {

	// Arrays to be returned by various methods
	private int[] netWorthPerYear;
	private int[] netWorthUpperBounds;
	private int[] netWorthLowerBounds;

	// Variables pulled from database
	private double baseNetWorth;
	private double baseSalary;
	private double salaryRaiseRate;
	private double rate401k;
	private double monthlyHousingCost;
	private double mortgageRate;
	private double fedTaxRate;
	private double stateTaxRate;
	private double monthlyCarPayment;

	public GraphDataCalculator(List<Question> questions) {

		// TODO use enums instead of hard-coding the values 0, 1, ...
		baseNetWorth = questions.get(0).getmAnswer();
		baseSalary = questions.get(1).getmAnswer();
		salaryRaiseRate = questions.get(2).getmAnswer() * 0.01;
		rate401k = questions.get(3).getmAnswer() * 0.01;
		monthlyHousingCost = questions.get(4).getmAnswer();
		fedTaxRate = questions.get(5).getmAnswer() * 0.01;
		stateTaxRate = questions.get(6).getmAnswer() * 0.01;
		monthlyCarPayment = questions.get(7).getmAnswer();
	}

	/**
	 * Returns an array representing the predicted net worth over a set of 10
	 * years
	 * 
	 * @return array of values, with the index i representing the year, and the
	 *         value representing the predicted networth of that year
	 */
	public int[] getNetWorthArray() {
		/* This is the value that will be returned */
		int[] netWorthPerYear = new int[10];
		for (int i = 0; i < netWorthPerYear.length; i++) {

			int previousNetWorth;

			if (i == 0) {
				netWorthPerYear[i] = (int) baseNetWorth;
			} else {
				/*
				 * TODO handle Roth vs. normal 401k!!!! Right now assuming
				 * normal 401k
				 */

				/*
				 * How to handle the networth of a 401k? Might need to do some
				 * NPV on that/look it up
				 */
				int income = (int) (baseSalary * Math.pow(salaryRaiseRate, i)
						* (1 - rate401k) * (1 - fedTaxRate) * (1 - stateTaxRate));
				int expenses = (int) ((monthlyHousingCost + monthlyCarPayment) * 12);
				// TODO we need a mortgage rate!!!!!!!
				previousNetWorth = netWorthPerYear[i - 1];
				netWorthPerYear[i] = previousNetWorth + income - expenses;
			}
		}
		return netWorthPerYear;
	}
}
