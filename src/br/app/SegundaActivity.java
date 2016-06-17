package br.app;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import br.com.appp2.R;
import br.app.Tarefa;
import br.app.TarefaInterface;

public class SegundaActivity extends Activity implements TarefaInterface{
	
	Button btnSair;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_segunda);
		
		btnSair = (Button)findViewById(R.id.btnSair);
	}
		
	
	public void baixarImagem(View view){
		Tarefa tarefa = new Tarefa(this, this);
		tarefa.execute("https://santapazz.files.wordpress.com/2013/09/sorte.jpg", "imagem");
	}

	@Override
	public void depoisDownload(Bitmap bitmap) {
		ImageView iv = (ImageView)findViewById(R.id.imageView);
		iv.setImageBitmap(bitmap);	
	
	
	
	btnSair.setOnClickListener(new OnClickListener() {
		
		public void onClick(View v) {				
			finish();
				}
			});
		}
       		
			@Override
			protected void onPause() {
				super.onPause();		
				finish();		
			}	
		
		}


