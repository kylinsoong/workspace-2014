package com.kylin.labs;

import java.util.Calendar;


public class TestAll {

	public static void main(String[] args) {
		
		Calendar c = Calendar.getInstance();
		
		c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		System.out.println(c.get(Calendar.DAY_OF_WEEK));
		
		c.set(Calendar.DAY_OF_WEEK, Calendar.TUESDAY);
		System.out.println(c.get(Calendar.DAY_OF_WEEK));
		
		c.set(Calendar.DAY_OF_WEEK, Calendar.WEDNESDAY);
		System.out.println(c.get(Calendar.DAY_OF_WEEK));
		
		c.set(Calendar.DAY_OF_WEEK, Calendar.THURSDAY);
		System.out.println(c.get(Calendar.DAY_OF_WEEK));
		
		c.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY);
		System.out.println(c.get(Calendar.DAY_OF_WEEK));
		
		c.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
		System.out.println(c.get(Calendar.DAY_OF_WEEK));
		
		c.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		System.out.println(c.get(Calendar.DAY_OF_WEEK));
		
		
//		System.out.println(CustomerType.Regular.equals(CustomerType.Rewards));

//		Hotel a = new Hotel(110, 90, 80, 80, "Lakewood");
//		
//		Hotel b = new Hotel(160, 60, 110, 50, "Bridgewood");
//		
//		Hotel c = new Hotel(220, 150, 100, 40, "Ridgewood");
//		
//		a.setCost(130);
//		b.setCost(130);
//		c.setCost(130);
//		
//		Hotel cheapest;
//		if(a.compareTo(b) >= 0) {
//			cheapest = b ;
//		} else {
//			cheapest = a ;
//		}
//		
//		if(cheapest.compareTo(c) >= 0) {
//			cheapest = c;
//		}
//		
//		System.out.println(cheapest);
	}

}
