package com.yonon.demo.design.observer;

/**
 * @author JiangYinghan 2017年2月14日
 *
 */
public interface Observer {
	//// 当气象观测值改变时，主题会把温度、湿度、气压的状态值当作方法的参数，传送给观察者
	void update(float temp, float humidity, float presure);
}
