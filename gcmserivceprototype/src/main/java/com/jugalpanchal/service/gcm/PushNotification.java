package com.jugalpanchal.service.gcm;

import java.util.List;

import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.Message.Builder;
import com.google.android.gcm.server.MulticastResult;
import com.google.android.gcm.server.Result;
import com.google.android.gcm.server.Sender;

public class PushNotification {
	private final String MESSAGE_KEY = "message";
	private final String PAYLOAD_KEY = "data.payload";
	private final int retries = 3;
	String apiKey;/* GCM_API_KEY */

	public PushNotification(String apiKey) {
		this.apiKey = apiKey;
	}

	public boolean push(String devicesRegId, String id, String title)
			throws Exception {

		boolean isPushed = false;
		final String notificationToken = devicesRegId;

		Sender sender = new Sender(this.apiKey);
		// Message msg = new Message.Builder().build();

		// use this line to send message with payload data
		/*
		 * Message msg = new Message.Builder() .collapseKey("1") .timeToLive(3)
		 * .delayWhileIdle(true) .addData(MESSAGE_KEY, title)s .build();
		 */

		/*
		 * Message msg = new Message.Builder() .timeToLive(30)
		 * .delayWhileIdle(true) .addData(PAYLOAD_KEY, title) .build();
		 */

		Builder builder = new Message.Builder();
		builder.addData(MESSAGE_KEY, title);
		builder.addData(PAYLOAD_KEY, id);
		// builder.collapseKey(this.retries);
		Message msg = builder.build();

		try {
			Result result = sender.send(msg, notificationToken, this.retries);
			isPushed = true;
		} catch (Exception ex) {
			throw ex;
		}
		return isPushed;
	}

	public boolean push(List devicesRegIds, String payloadData, String title) {

		boolean isPushed = false;
		List<String> notificationTokens = devicesRegIds;
		if (!notificationTokens.isEmpty()) {
			try {
				Sender sender = new Sender(this.apiKey);

				Builder builder = new Message.Builder();
				builder.addData(MESSAGE_KEY, title);
				builder.addData(PAYLOAD_KEY, payloadData);
				Message msg = builder.build();
				try {
					MulticastResult result = sender.send(msg, notificationTokens, this.retries);
				} catch (java.io.IOException ex) {
				}
				
				//TODO : @jugalpanchal - Remove unwanted registration ids.
				//http://stackoverflow.com/questions/11593767/gcm-multicastresult-which-result-is-from-which-device
				/*for (Result r : result.getResults()) {
					if (r.getMessageId() != null) {
						String canonicalRegId = r.getCanonicalRegistrationId();
						if (canonicalRegId != null) { // same device has more than on
							// registration ID: update database // BUT WHICH DEVICE IS IT?
						}
					} else {
						String error = r.getErrorCodeName();
						if (error.equals(Constants.ERROR_NOT_REGISTERED)) {
							// application has been removed from device - 
							// unregister database // BUT WHICH DEVICE IS IT?
						}
					}
				}*/
				
				isPushed = true;
			} catch (Exception ex) {
				throw ex;
			}
		}
		else
		{
			isPushed = true;/* There is no device registered. */
		}
		return isPushed;
	}
}