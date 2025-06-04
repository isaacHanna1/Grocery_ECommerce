package com.watad.model;

import com.watad.notification.strategy.NotificationStrategy;

public interface Observer {

	public void notify(NotificationStrategy notifaction ,String messageSubject,String message);
	
}
