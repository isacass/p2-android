package br.app;

import java.util.ArrayList;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;
import br.com.appp2.R;

public class PrimeiraActivity extends Activity {

// Carrega uma lista mostrando o Zoodíaco enquanto barra de progresso é mostrada
	
	private String[] nomes={"Signo de Áries (21/3 - 20/4)","Signo de Touro (21/4 - 21/5)","Signo de Gêmeos ( 22/5 - 21/6)",
			"Signo de Câncer (21/6 - 23/7)","Signo de Leão (24/7 - 23/8)","Signo de Virgem (24/8 - 23/9)",
			"Signo de Libra (24/9 - 23/10)","Signo de Escorpião (24/10 - 22/11)","Signo de Sagitário (23/11 - 21/12)",
			"Signo de Capricórnio (22/12 - 20/1)","Signo de Aquário (21/1 - 19/2)","Signo de Peixes (20/2 - 20/3)",};

	ListView listView;	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_primeira);
		
		
		listView =(ListView)findViewById(R.id.list_view);
		listView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,new ArrayList<String>()));
		new ListaTask().execute();
	}
		
	 class ListaTask extends AsyncTask<Void,String,String>{
		
		 ArrayAdapter<String> adapter;
		 ProgressBar progressBar;
		 int count;
		 
		 @SuppressWarnings("unchecked")
		protected void onPreExecute() 
		 {
			adapter=(ArrayAdapter<String>)listView.getAdapter();
			progressBar = (ProgressBar) findViewById(R.id.progress_bar);
			progressBar.setMax(15);
			progressBar.setProgress(0);
			progressBar.setVisibility(View.VISIBLE);
			count= 0;
			
		 } 		 
		 
		protected String doInBackground(Void...params){
			for (String Name :nomes){
				publishProgress(Name);
				try{
				Thread.sleep(1000);
				}
				catch (InterruptedException e){
					e.printStackTrace();					
				}
			}
		return "Zodíaco carregado com sucesso!";
		}
		
		protected void onProgressUpdate(String...values){
			adapter.add(values[0]);
			count++;
			progressBar.setProgress(count);
		}
		
		 protected void onPostExecute(String result) {
			 Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();	
			 progressBar.setVisibility(View.GONE);
		} 
	}
	
	// Gera notificação padrão Android e direciona para outra Activity
	 
	public void gerarNotificacao(View view){
		
			NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
			PendingIntent p = PendingIntent.getActivity(this, 0, new Intent(this, SegundaActivity.class), 0);
			
			NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
			builder.setTicker("Ticker Texto");
			builder.setContentTitle("Sorte do Dia");
			
			builder.setSmallIcon(R.drawable.ic_launcher);
			builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher));
			builder.setContentIntent(p);
			
			NotificationCompat.InboxStyle style = new NotificationCompat.InboxStyle();
			String [] descs = new String[]{"Veja Sua Sorte do Dia!", "Descubra o que os Astros Lhe Reservam"};
				for(int i = 0; i < descs.length; i++){
					style.addLine(descs[i]);
				}
				
			builder.setStyle(style);
			
			Notification n = builder.build();
			n.vibrate = new long[]{150, 300, 150, 600};
			n.flags = Notification.FLAG_AUTO_CANCEL;
			nm.notify(R.drawable.ic_launcher, n);		
	 }	
	       		
				@Override
				protected void onPause() {
					super.onPause();		
					finish();}					
			
}
	

