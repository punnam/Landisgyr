package com.landisgyr.project.java.excercise.random.driver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class GenerteRandomNumbers {

	public static void main(String[] args) {
		try {
			while (true) {
				System.out.println("Enter number of random numbers to be generated: ");
				BufferedReader bufferRead = new BufferedReader(
						new InputStreamReader(System.in));
				String s = bufferRead.readLine();
				GenerteRandomNumbers driver = new GenerteRandomNumbers();
				try{
					Integer integer = new Integer(s);
					driver.processRandomNumbers(integer.intValue());
				}catch(NumberFormatException ne){
					System.out.println("Enter something here : ");
				}	
				
				System.out.println(s);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private double getRandom() {
		return Math.random();
	}

	private static void log(Object aMsg) {
		System.out.println(String.valueOf(aMsg));
	}

	private void processRandomNumbers(int j) {
		List<Integer> firstFractionDigits = new ArrayList<Integer>();
		List<String> allRandomNumbers = new ArrayList<String>();
		Set<Integer> firstFractionDigitsSet = new TreeSet<Integer>();

		GenerteRandomNumbers genRandom = new GenerteRandomNumbers();
		for (int idx = 1; idx <= j; ++idx) {
			double number = genRandom.getRandom();
			log("Generated : " + number);
			String numberString = Double.toString(number);
			allRandomNumbers.add(numberString);
			int index = numberString.indexOf(".");
			if (index != -1) {
				String fisrtDigitOfFraction = numberString.substring(index + 1,
						index + 2);
				Integer fisrtDigitOfFractionInt = Integer
						.valueOf(fisrtDigitOfFraction);
				firstFractionDigits.add(fisrtDigitOfFractionInt);
			}
		}

		firstFractionDigitsSet.addAll(firstFractionDigits);
		Iterator<Integer> iter = firstFractionDigitsSet.iterator();
		Map<Integer, Integer> digitsAndTimes = new HashMap<Integer, Integer>();
		while (iter.hasNext()) {
			Integer fractionDigit = iter.next();
			int repetetionNumber = 0;
			for (int i = 0; i < firstFractionDigits.size(); i++) {
				Integer firstDigits = firstFractionDigits.get(i);
				if (firstDigits.intValue() == fractionDigit.intValue()) {
					repetetionNumber++;
				}
			}
			digitsAndTimes
					.put(fractionDigit, Integer.valueOf(repetetionNumber));
		}

		Iterator<Integer> iteratorResult = digitsAndTimes.keySet().iterator();
		while (iteratorResult.hasNext()) {
			Integer key = iteratorResult.next();
			Integer value = digitsAndTimes.get(key);
			log("digit in the first decimal :" + key + "::Number of Occurances:" + value);
		}
	}

}
