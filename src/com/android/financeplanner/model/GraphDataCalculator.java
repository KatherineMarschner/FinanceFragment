package com.android.financeplanner.model;

import java.util.List;

import android.util.Log;

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

	/**
	 * Takes a list of questions and fills out the variables necessary for
	 * creating graphable data
	 * 
	 * @param questions
	 *            : list of Question data types which contain the variables used
	 *            to calculate all values to be graphed
	 */
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
		for (int year = 0; year < netWorthPerYear.length; year++) {

			int previousNetWorth;

			if (year == 0) {
				netWorthPerYear[year] = (int) baseNetWorth;
			} else {
				/*
				 * TODO handle Roth vs. normal 401k!!!! Right now assuming
				 * normal 401k
				 */

				/*
				 * How to handle the networth of a 401k? Might need to do some
				 * NPV on that/look it up
				 */
				int income = (int) (baseSalary
						* (Math.pow(1 + salaryRaiseRate, year))
						* (1 - rate401k) * (1 - fedTaxRate) * (1 - stateTaxRate));
				Log.w("Income: ", income + "");

				int expenses = (int) ((monthlyHousingCost + monthlyCarPayment) * 12);
				Log.w("Expenses: ", expenses + "");
				// TODO we need a mortgage rate!!!!!!!
				previousNetWorth = netWorthPerYear[year - 1];
				netWorthPerYear[year] = previousNetWorth + income - expenses;
			}
		}
		return netWorthPerYear;
	}

	/**
	 * This method takes in an array of forecasted data, a value representing
	 * the percentage uncertainty there is to the data, and returns either an
	 * upper or lower bound array of values, compounded year over year.
	 * 
	 * @param forecastedData
	 *            : Data used as the midpoint of the bounding array
	 * @param uncertainty
	 *            : Percentage used to calculate each bounding value. Compounds
	 *            on top of itself every year
	 * @param isUpperBound
	 *            : boolean determining whether to give the upper or lower bound
	 * @return
	 */
	public int[] bounds(int[] forecastedData, double uncertainty,
			boolean isUpperBound) {

		int[] boundingArray = new int[forecastedData.length];

		for (int year = 0; year < boundingArray.length; year++) {
			boundingArray[year] = calculateBound(forecastedData[year],
					uncertainty, year, isUpperBound);
		}
		return boundingArray;
	}

	/**
	 * @param baseValue
	 * @param uncertainty
	 * @param year
	 * @param isUpperBound
	 * @return
	 */
	private int calculateBound(int baseValue, double uncertainty, int year,
			boolean isUpperBound) {

		int bound;
		/*
		 * If the base value is positive, then the upper bound will increase the
		 * absolute value, and if the base value is negative, the upper bound
		 * will decrease the absolute value. the reasoning being that if you had
		 * negative net worth, the upper bound should be less negative, since
		 * having less debt is a better outcome than having more debt
		 */
		if (baseValue > 0) {
			/*
			 * These values represent the percentage of the base which either
			 * bound will take
			 */
			double low = 1 - uncertainty;
			double high = 1 + uncertainty;
			if (isUpperBound) {
				bound = (int) (baseValue * Math.pow(high, year));
			} else {
				bound = (int) (baseValue * Math.pow(low, year));
			}
		} else {
			double low = 1 + uncertainty;
			double high = 1 - uncertainty;
			if (isUpperBound) {
				bound = (int) (baseValue * Math.pow(high, year));
			} else {
				bound = (int) (baseValue * Math.pow(low, year));
			}
		}

		return bound;
	}

}
