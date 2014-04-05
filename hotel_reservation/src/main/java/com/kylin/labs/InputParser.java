package com.kylin.labs;

import java.util.ArrayList;
import java.util.List;

public class InputParser {

	public static Input getInput(String str) {
		
		Input input = new Input();
		
		String [] array = str.split(":");
		if(array[0].trim().equals("Regular")){
			input.setCustomerType(CustomerType.Regular);
		} else {
			input.setCustomerType(CustomerType.Rewards);
		}
		
		input.setDayType(getDayType(array[1].trim().split(",")));

				
		return input ;
	}
	
	private static List<DayType>  getDayType(String[] days) {
		
		List<DayType> dayType = new ArrayList<DayType>();
		
		for (String str : days) {
			String line = str.trim();
			if (line.contains("sat") || line.contains("sun")) {
				dayType.add(DayType.Weekend);
			} else {
				dayType.add(DayType.Weekday);
			}
		}

		return dayType;
	}

	/**
	 * Regular: 16Jan2009(mon), 17Jan2009(tues), 18Jan2009(wed)
	 * Regular: 16Feb2009(mon), 17Feb2009(tues), 18Feb2009(wed)
	 * 
	 * mon tues weds thur fri sat sun
	 * 
	 */
	public static void main(String[] args) {
//		
//		
//		Input input = getInput(str);
//		
//		System.out.println(input);
		
	}
}
