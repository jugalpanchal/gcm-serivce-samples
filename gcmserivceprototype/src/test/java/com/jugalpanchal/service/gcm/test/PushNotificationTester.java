package com.jugalpanchal.service.gcm.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.jugalpanchal.app.helper.PushOperation;
import com.jugalpanchal.app.helper.PushType;
import com.jugalpanchal.app.workflows.PushNotificationWorkflow;

public class PushNotificationTester {

	@Test
	public void test() {

		long id = 1L;
		String title = "Tire rotation is due.";
		
		PushNotificationWorkflow pushNotificationWorkflow = new PushNotificationWorkflow();
		boolean isPushed = pushNotificationWorkflow.push(id, title, PushType.TireRotation , PushOperation.Add);
		
		assertTrue("Push notification is failed.", isPushed);
	}

}
