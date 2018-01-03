package com.uic.whoru;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class AnalysisActivity extends AppCompatActivity {

    Button button_testAgain;
    EditText editText_analysis;

    String selectedName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analysis);

        Intent recievedIntent = getIntent();
        selectedName = recievedIntent.getStringExtra("name");

        editText_analysis = (EditText) findViewById(R.id.editText_analysis);
        editText_analysis.setText(selectedName);

        button_testAgain = (Button) findViewById(R.id.button_testAgain);
        button_testAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AnalysisActivity.this, MainActivity.class));
            }
        });
    }
}
