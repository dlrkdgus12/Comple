package comple.common.util;

import java.io.IOException;

import com.google.android.gcm.server.Constants;
import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.Result;
import com.google.android.gcm.server.Sender;
import com.notnoop.apns.APNS;
import com.notnoop.apns.ApnsService;

public class PushMessage {

	public boolean setPushForAndroid(String token, String msg) throws IOException{
		Sender sender = new Sender("AIzaSyDW2qFtGPJacbY_MgRAiLDDZbDIv0KlkJE");
		
		Message message = new Message.Builder()
							.addData("msg", msg)
							//.addData("url", url)
							.build();
		
		
		Result result = sender.send(message, token, 5);

		if (result.getMessageId() != null) {
			String canonicalRegId = result.getCanonicalRegistrationId();
			if (canonicalRegId != null) {
				// same device has more than on registration ID: update database
//				System.out.println(canonicalRegId);
				return false;
			} else {
//				System.out.println("?"+result.getMessageId());
				return true;
			}
		} else {
			String error = result.getErrorCodeName();
			if (error.equals(Constants.ERROR_NOT_REGISTERED)) {
				// application has been removed from device - unregister
				// database
//				System.out.println(error);
			} else {
//				System.out.println("??"+result.getMessageId());
			}
			return false;
		}
	}
	
	public boolean setPushForiPhone(String token, String msg) throws Exception{
		 
		ApnsService service =
			    APNS.newService()
			    .withCert("./WEB-INF/classes/bin/ck.p12", "67wnsdud")
//			    .withCert("./src/main/resources/bin/ck.p12", "67wnsdud")
			    .withSandboxDestination()
			    .build();
		
		String payload = APNS.newPayload()
				.alertBody(msg)
				.build();
		service.push(token, payload);
		
		///////////////////////////////////////////////
//		String deviceToken = "e826ac84 486b88bf f4b05289 32b7c6f2 69156ae0 b5ae0e27 9a0693b1 31638aa6";
		//String deviceToken = "ff403272 51627ae9 db32770b 366b0360 66e788f3 cfb99106 f95ceb2b a48eb79c";   
		//?��?�� 번호?�� ?��?��?�� ?��?��?��?��?��  구현 ?�� ?��버그 모드�? 출력?���? ?�� ?�� ?��?��?��. ?��?�� 5�? ?��?��?��?��?��구현 �? 참고
//		int badge = 1;
//		PayLoad payLoad = new PayLoad();
//		payLoad.addAlert(msg);    // ?��?��?��?�� ?���? 보낼 메세�? ?��?��?��?��?��.
//		payLoad.addBadge(badge);
//		payLoad.addSound("default");    
//		  
//		PushNotificationManager pushManager = PushNotificationManager.getInstance();
//
//		pushManager.addDevice("iPhone", token);
//		  
//		//Connect to APNs
//		String host = "gateway.sandbox.push.apple.com";
//		int port = 2195;
////		String certificatePath = "./src/main/resources/bin/ck.p12";     // ?��?�� �??��2 4번에 ?��명한 �?분이?��?��, �?>?��?��?��?? 경로�?+?��?���?
//		String certificatePath = "./WEB-INF/classes/bin/ck.p12";     // ?��?�� �??��2 4번에 ?��명한 �?분이?��?��, �?>?��?��?��?? 경로�?+?��?���?
//		String certificatePassword = "67wnsdud";                                           // ?��?�� �??��2 1�? �?분에	 ?��명한 ?��?��?��?��?��?��
//		pushManager.initializeConnection(host, port, certificatePath, certificatePassword, SSLConnectionHelper.KEYSTORE_TYPE_PKCS12);
//		 
//		//Send Push
//		Device client = pushManager.getDevice("iPhone");
//		pushManager.sendNotification(client, payLoad);
//		pushManager.stopConnection();
//		pushManager.removeDevice("iPhone");

		return true;
	}

}
