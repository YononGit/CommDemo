package com.yonon.demo.design.observer;

/**
 * @author JiangYinghan 2017年2月14日
 *
 */
public interface Subject {
	// // registerObserver和removeObserver都需要一个观察者作为变量，该观察者是用来注册或被删除的
	public void registerObserver(Observer o);

	public void removedObserver(Observer o);

	// 当主题状态改变时。这个方法会被调用，以通知所有的观察者
	public void notifyObserver();
}
