package com.watad.resetPassword;

public interface NotificationStrategy {

	void sendResetLink(String destination , String resetUrl );
}
