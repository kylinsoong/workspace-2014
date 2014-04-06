package com.kylin.labs;

public class MVCRunner {

	public static void main(String[] args) {
		TemperatureModel temperature = new TemperatureModel();
		new FarenheitGUI(temperature, 100, 100);
	}

}
