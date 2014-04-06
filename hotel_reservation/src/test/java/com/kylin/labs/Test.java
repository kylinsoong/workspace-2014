package com.kylin.labs;

import com.kylin.labs.Reservation;

public class Test {

	public static void main(String[] args) {

		String input1 = "Regular: 16Mar2009(mon), 17Mar2009(tues), 18Mar2009(wed)";
		String input2 = "Regular: 20Mar2009(fri), 21Mar2009(sat), 22Mar2009(sun)";
		String input3 = "Rewards: 26Mar2009(thur), 27Mar2009(fri), 28Mar2009(sat)";
		System.out.println(Reservation.getInstance().getCheapest(input1));
		System.out.println(Reservation.getInstance().getCheapest(input2));
		System.out.println(Reservation.getInstance().getCheapest(input3));
	}

}
