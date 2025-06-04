package com.watad.model;


public interface Subject {
	
	void registerObserver(Observer observer);
	void unregisterObserver(Observer observer);
	void notifyUser(Item item);

}
