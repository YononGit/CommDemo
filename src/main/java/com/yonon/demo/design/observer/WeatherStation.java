package com.yonon.demo.design.observer;

/**
 * @author JiangYinghan 2017年2月14日
 *
 */
public class WeatherStation {
	public static void main(String[] args) {
		// 建立WeatherData对象
		WeatherData weatherData = new WeatherData();

		// 建立目前状况布告板
		CurrentConditionsDisplay currentDisplay = new CurrentConditionsDisplay(weatherData);

		// 模拟新的气象测量
		weatherData.setMeasurements(80, 65, 30.4f);
		weatherData.setMeasurements(82, 70, 29.2f);
		weatherData.setMeasurements(78, 90, 29.2f);
	}
}
