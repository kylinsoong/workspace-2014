package com.kylin.labs.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Observable;

import com.kylin.labs.CustomerType;
import com.kylin.labs.DayType;
import com.kylin.labs.Hotel;
import com.kylin.labs.Input;
import com.kylin.labs.Reservation;

public class ReservationModel extends Observable {

	protected Hotel cheapest;

	public String getCheapestName() {
		return cheapest.toString();
	}

	public void doPost(String type, Date startDate, Date endDate) {
		
		cheapest = null;

		Input input = new Input();

		if ("Rewards".equals(type)) {
			input.setCustomerType(CustomerType.Rewards);
		} else {
			input.setCustomerType(CustomerType.Regular);
		}
		
		input.setDayType(getDateTypes(startDate, endDate));

		cheapest = Reservation.getInstance().getCheapest(input);
		
		setChanged();
		
		notifyObservers();

	}

	private List<DayType> getDateTypes(Date startDate, Date endDate) {

		List<DayType> dayTypes = new ArrayList<DayType>();

		long interval = 24 * 1000 * 60 * 60;
		long endTime = endDate.getTime();
		long curTime = startDate.getTime();

		while (curTime <= endTime) {
			dayTypes.add(getDateType(new Date(curTime)));
			curTime += interval;
		}
		return dayTypes;
	}

	private DayType getDateType(Date date) {

		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int d = c.get(Calendar.DAY_OF_WEEK);
		if (d == 1 || d == 7) {
			return DayType.Weekend;
		} else {
			return DayType.Weekday;
		}
	}

}
