package br.app;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.widget.Toast;


public class Sms extends BroadcastReceiver{
	// Intercepta um SMS recebido
	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		Bundle bundle = intent.getExtras();
		if (bundle != null){
			Object[] pdus = (Object[]) bundle.get("pdus");
			String senderNumber = null;
			for (int i = 0; i < pdus.length; i++) {
				SmsMessage sms = SmsMessage.createFromPdu((byte[]) pdus[i]);
				senderNumber = sms.getOriginatingAddress();
				String message = sms.getDisplayMessageBody();
				// Mostra SMS recebido
				Toast.makeText(context,"Mensagem De: "+senderNumber+" Mensagem:"+message,Toast.LENGTH_LONG).show();				
			}
			// Envia um SMS de resposta			
			SmsManager smsManager = SmsManager.getDefault();
			smsManager.sendTextMessage(senderNumber,null, "Desculpe, não poderei responde no momento. Retornarei mais tarde",null,null);
		}
	}
	

}
