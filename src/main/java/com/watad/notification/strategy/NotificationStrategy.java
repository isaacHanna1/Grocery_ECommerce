package com.watad.notification.strategy;

import com.watad.model.User;


public interface NotificationStrategy {
	
	void sendNotification (String notificationSubject , User user , String message);
}
