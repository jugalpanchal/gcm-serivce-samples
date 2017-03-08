package com.jugalpanchal.app.workflows;

import java.util.ArrayList;
import java.util.List;

import com.jugalpanchal.app.helper.PushOperation;
import com.jugalpanchal.app.helper.PushType;
import com.jugalpanchal.service.gcm.PushNotification;

public class PushNotificationWorkflow {

	public boolean push(long id, String title, PushType pushType, PushOperation pushOperation) {

		boolean isPushed = false;
		try {
			String apiKey = "";/* GCM_API_KEY */
			List<?> devicesRegIds = new ArrayList<String>();
			String payloadData = "{\"id\":\"" + id + "\",\"pushType\":\"" + pushType + "\"}";//Added push operation
			
			PushNotification notificatoin = new PushNotification(apiKey);
			isPushed = notificatoin.push(devicesRegIds, payloadData, title);
		} catch (Exception ex) {
			throw ex;
		}
		return isPushed;
	}
}