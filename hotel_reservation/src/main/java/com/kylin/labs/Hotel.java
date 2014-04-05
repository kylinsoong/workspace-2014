package com.kylin.labs;

public class Hotel implements Comparable<Hotel> {
	
	private final int rating;
	private final int regularWeekday;
	private final int regularWeekend;
	private final int rewardsWeekday;
	private final int rewardsWeekend;
	
	private final String name;
	
	public Hotel(int rating, int regularWeekday, int regularWeekend, int rewardsWeekday,int rewardsWeekend, String name) {
		this.rating = rating;
		this.regularWeekday = regularWeekday;
		this.regularWeekend = regularWeekend;
		this.rewardsWeekday = rewardsWeekday;
		this.rewardsWeekend = rewardsWeekend;
		this.name = name;
	}

	public int getRating() {
		return rating;
	}

	public int getRegularWeekday() {
		return regularWeekday;
	}

	public int getRegularWeekend() {
		return regularWeekend;
	}

	public int getRewardsWeekday() {
		return rewardsWeekday;
	}

	public int getRewardsWeekend() {
		return rewardsWeekend;
	}

	public String getName() {
		return name;
	}
	
	private int cost;

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public String toString() {
		return name;
	}

	public int compareTo(Hotel hotel) {
		return getCost() - hotel.getCost();
	}

}
