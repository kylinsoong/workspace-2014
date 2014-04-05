package com.kylin.labs;

public class Reservation {
	
	private static Reservation instance = new Reservation();
	
	private Reservation() {
		
	}
	
	public static Reservation getInstance() {
		return instance ;
	}
	
	Hotel lakewood = new Hotel(3, 110, 90, 80, 80, "Lakewood");
	
	Hotel bridgewood = new Hotel(4, 160, 60, 110, 50, "Bridgewood");
	
	Hotel ridgewood = new Hotel(5, 220, 150, 100, 40, "Ridgewood");
	
	private void updateCost(Input input) {
		
		int lakewoodCost = 0;
		int bridgewoodCost = 0;
		int ridgewoodCost = 0;
		
		if(input.getCustomerType().equals(CustomerType.Regular)) {
			
			for(int i = 0 ; i < input.getDayType().size() ; i ++) {
				if(input.getDayType().get(i).equals(DayType.Weekday)) {
					lakewoodCost += lakewood.getRegularWeekday();
					bridgewoodCost += bridgewood.getRegularWeekday();
					ridgewoodCost += ridgewood.getRegularWeekday();
				} else {
					lakewoodCost += lakewood.getRegularWeekend();
					bridgewoodCost += bridgewood.getRegularWeekend();
					ridgewoodCost += ridgewood.getRegularWeekend();
				}
			}
			
		} else {
			
			for(int i = 0 ; i < input.getDayType().size() ; i ++) {
				DayType type = input.getDayType().get(i);
				if(input.getDayType().get(i).equals(DayType.Weekday)) {
					lakewoodCost += lakewood.getRewardsWeekday();
					bridgewoodCost += bridgewood.getRewardsWeekday();
					ridgewoodCost += ridgewood.getRewardsWeekday();
				} else {
					lakewoodCost += lakewood.getRewardsWeekend();
					bridgewoodCost += bridgewood.getRewardsWeekend();
					ridgewoodCost += ridgewood.getRewardsWeekend();
				}
			}
			
		}
		
		lakewood.setCost(lakewoodCost);
		bridgewood.setCost(bridgewoodCost);
		ridgewood.setCost(ridgewoodCost);
	}
	
	public Hotel getCheapest(String input) {
		return getCheapest(InputParser.getInput(input));
	}
	
	public Hotel getCheapest(Input input) {
		
		lakewood.setCost(0);
		bridgewood.setCost(0);
		ridgewood.setCost(0);
		
		updateCost(input);
		
		Hotel cheapest;
		
		if(lakewood.compareTo(bridgewood) >= 0) {
			cheapest = bridgewood ;
		} else {
			cheapest = lakewood ;
		} 
		
		if(cheapest.compareTo(ridgewood) >= 0) {
			cheapest = ridgewood;
		}
		
		return cheapest;
		
	}

}
