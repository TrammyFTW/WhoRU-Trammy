package com.uic.whoru;

import android.animation.ValueAnimator;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import javax.xml.transform.Result;

public class ResultActivity extends AppCompatActivity {

    Button button_testAgain;
    ListView listView_report;

    ArrayList<String> listData;
    ListAdapter adapter;
    DatabaseHelper databaseHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        final MediaPlayer hit = MediaPlayer.create(ResultActivity.this,R.raw.menuhit);
        final MediaPlayer ring= MediaPlayer.create(ResultActivity.this,R.raw.omocha);

        ring.setLooping(true);
        ring.start();

        final ImageView backgroundOne = (ImageView) findViewById(R.id.imageView13);
        final ImageView backgroundTwo = (ImageView) findViewById(R.id.imageView14);
        final ValueAnimator animator = ValueAnimator.ofFloat(0.0f, 1.0f);

        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setInterpolator(new LinearInterpolator());
        animator.setDuration(10000L);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                final float progress = (float) animation.getAnimatedValue();
                final float width = backgroundOne.getWidth();
                final float translationX = width * progress;
                backgroundOne.setTranslationX(translationX);
                backgroundTwo.setTranslationX(translationX - width);
            }
        });
        animator.start();

        listView_report = (ListView) findViewById(R.id.listView_report);
        databaseHelper = new DatabaseHelper(this);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        button_testAgain = (Button) findViewById(R.id.button_testAgain);
        button_testAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hit.start();
                AlertDialog.Builder builder = new AlertDialog.Builder(ResultActivity.this);
                builder.setTitle("Retake the Test?");
                builder.setMessage("Do you want to try the quiz again?");
                builder.setIcon(R.mipmap.ic_launcher);
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        WhoRU.currentQuestion = 0;
                        ring.stop();
                        ring.release();
                        hit.stop();
                        hit.release();
                        startActivity(new Intent(ResultActivity.this, MainActivity.class));
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        uicToastMessage("Operation Cancelled...");
                    }
                });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });

        populateListView();
    }

    public void uicToastMessage(String message){
        Toast.makeText(this, message ,Toast.LENGTH_SHORT).show();
    }

    public void populateListView(){
        listData = new ArrayList<>();

        Cursor data = databaseHelper.getData();
        ArrayList<String> listData = new ArrayList<>();
        while(data.moveToNext()){
            listData.add(data.getString(2) + " - " + data.getString(1));
        }
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listData);
        listView_report.setAdapter(adapter);





        /*listView_report.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String name = adapterView.getItemAtPosition(i).toString();
                Intent intent = new Intent(ResultActivity.this, AnalysisActivity.class);
                intent.putExtra("name", name);
                startActivity(intent);
            }
        });*/

    }
}
