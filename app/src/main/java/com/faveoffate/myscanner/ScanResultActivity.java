package com.faveoffate.myscanner;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by egerbin on 8/28/2015.
 */
public class ScanResultActivity extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_result);

        Intent i = getIntent();
        String resultString = i.getStringExtra("extra_resultString");

        TextView resultText;

        resultText = (TextView)findViewById(R.id.resultText);
        resultText.setTextSize(20);
        resultText.setText(resultString);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

}
