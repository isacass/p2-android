package br.app;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;

public class Tarefa extends AsyncTask<String, String, Bitmap> {
	private Context context;
	private TarefaInterface ti;
	private ProgressDialog progress;
	
	
	public Tarefa(Context context, TarefaInterface ti){
		this.context = context;
		this.ti = ti;
	}
	
	
	@Override
	protected void onPreExecute() {
		progress = new ProgressDialog(context);
		progress.setMessage("Carregando imagem...");
		progress.show();
	}
	
	@Override
	protected Bitmap doInBackground(String... params) {
		Bitmap img = null;
		
		try{
			publishProgress("Ainda carregando...");
			Log.i("Script", "1 -> "+params[0]);
			Log.i("Script", "2 -> "+params[1]);
			URL url = new URL(params[0]);
			HttpURLConnection conexao = (HttpURLConnection) url.openConnection();
			InputStream input = conexao.getInputStream();
			img = BitmapFactory.decodeStream(input);
			publishProgress("Imagem carregada!");
		}
		catch(IOException e){}
		
		return(img);
	}
	
	@Override
	protected void onProgressUpdate(String... params) {
		progress.setMessage(params[0]);
	}
	
	@Override
	protected void onPostExecute(Bitmap params) {
		ti.depoisDownload(params);
		progress.dismiss();
	}
	
	
	
}
