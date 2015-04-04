package com.example.ex16gestos;

import java.util.ArrayList;

import android.app.Activity;
import android.gesture.Gesture;
import android.gesture.GestureLibraries;
import android.gesture.GestureLibrary;
import android.gesture.GestureOverlayView;
import android.gesture.GestureOverlayView.OnGesturePerformedListener;
import android.gesture.Prediction;
import android.os.Bundle;
import android.view.Menu;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity implements
		OnGesturePerformedListener {

	GestureOverlayView gestureView;
	EditText nomeGesto;
	GestureLibrary gestLib;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		gestureView = (GestureOverlayView) findViewById(R.id.gestures);
		nomeGesto = (EditText) findViewById(R.id.editText1);
		gestLib = GestureLibraries.fromRawResource(this,
				R.raw.gestures);
		if (!gestLib.load()) {
			Toast.makeText(this, "Erro ao carregar gestos predefinidos",
					Toast.LENGTH_LONG).show();
			finish();
		}
	}

	@Override
	protected void onResume() {
		super.onResume();
		gestureView.addOnGesturePerformedListener(this);
	}

	@Override
	protected void onStop() {
		gestureView.removeOnGesturePerformedListener(this);
		super.onStop();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onGesturePerformed(GestureOverlayView overlay, Gesture gesture) {
		ArrayList<Prediction> previsoes =gestLib.recognize(gesture);
		if (previsoes.size() > 0) {
			Prediction previsao = previsoes.get(0);
			if (previsao.score > 2.0) {
				nomeGesto.setText(nomeGesto.getText() + previsao.name );
			return;
			} else {
				nomeGesto.setText(nomeGesto.getText() +"¿" + previsao.name + "?");
			}
			}else{
				Toast.makeText(this, "gesto desconhecido",Toast.LENGTH_LONG).show();
			}
			
		}
	

}
