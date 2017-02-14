package com.yonon.demo.design.observer;

import java.util.ArrayList;

/**
 * @author JiangYinghan 2017年2月14日
 *
 */
public class WeatherData implements Subject {
	// 我们加上一个ArrayList来记录观察者，此ArrayList是在构造器中建立的。
	public ArrayList observers;
	private float temperature;
	private float humidity;
	private float pressure;

	public WeatherData() {
		observers = new ArrayList();
	}

	public void registerObserver(Observer o) {
		observers.add(o);
	}

	public void removedObserver(Observer o) {
		int i = observers.indexOf(o);
		if (i >= 0) {
			observers.remove(i);
		}
	}

	// 在这里，我们把状态告诉每一个观察者
	// 因为观察者都实现了update()，所以我们知道如何通知它们
	public void notifyObserver() {
		for (int i = 0; i < observers.size(); i++) {
			Observer observer = (Observer) observers.get(i);
			observer.update(temperature, humidity, pressure);
		}
	}

	// 当从气象站得到更新观测值时，我们通知观察者
	public void measurementsChanged() {
		notifyObserver();
	}

	public void setMeasurements(float temperature, float humidity, float pressure) {
		this.temperature = temperature;
		this.humidity = humidity;
		this.pressure = pressure;
		measurementsChanged();
	}

}
