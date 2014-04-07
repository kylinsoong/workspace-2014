package com.kylin.labs;

import java.util.ArrayList;
import java.util.List;

public class Input {
	
	private CustomerType customerType;
	
	private List<DayType> dayType = new ArrayList<DayType>();

	public CustomerType getCustomerType() {
		return customerType;
	}

	public void setCustomerType(CustomerType customerType) {
		this.customerType = customerType;
	}

	public List<DayType> getDayType() {
		return dayType;
	}

	public void setDayType(List<DayType> dayType) {
		this.dayType = dayType;
	}
	
	public void addDayType(DayType date) {
		dayType.add(date);
	}

	public String toString() {
		return "Input [customerType=" + customerType + ", dayType=" + dayType + "]";
	}


}
