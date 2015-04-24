package ro.jademy.progressbarapp;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.text.Editable;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Random;

@SuppressLint("LongLogTag")
public class MainActivity extends ActionBarActivity {

    public static final String TAG = "ro.jademy.progressbarapp.MainActivity";

    ProgressBar bar;
    EditText edit;
    TextView text;
    Button button;

    ProgressTask task;

    boolean DEBUG = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (DEBUG) {
		Log.d(TAG, "onCreate()");
	}
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bar = (ProgressBar) findViewById(R.id.progressBar);
        edit = (EditText) findViewById(R.id.editText);
        text = (TextView) findViewById(R.id.george);
        button = (Button) findViewById(R.id.button);
    }

    public void onClickButton(View view) {
        bar.setMax((int) iteratii);

        if (task != null) {
            task.cancel(true);
        }
        task = new ProgressTask();
        task.execute(iteratii);
    }

    private class ProgressTask extends AsyncTask<Integer, Integer, Void> {
        @Override
        protected Void doInBackground(Integer... params) {

            int iteratii = params[0];
            int s = 0;
            Random random = new Random();
            for (int i = 0; i < iteratii; i++) {
                int number = random.nextInt(11);
                s = s + number;
                publishProgress(s, i);
                if (isCancelled()) {
                    break;
                }
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            text.setText("" + values[0]);
            bar.setProgress(values[1]);
        }
    }
}







