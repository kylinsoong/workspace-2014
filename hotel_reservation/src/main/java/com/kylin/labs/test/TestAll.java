package com.kylin.labs.test;

import java.util.Date;

import com.kylin.labs.CustomerType;
import com.kylin.labs.Hotel;

public class TestAll {

	public static void main(String[] args) {
		
		System.out.println(CustomerType.Regular.equals(CustomerType.Rewards));

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
