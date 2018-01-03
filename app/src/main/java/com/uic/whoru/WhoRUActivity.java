package com.uic.whoru;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Build;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Transition;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Timer;
import java.util.TimerTask;


public class WhoRUActivity extends AppCompatActivity {

//    public SoundPool soundPool;
//    private AudioManager audioManager;
//    private static final int MAX_STREAMS = 5;
//    private static final int streamType = AudioManager.STREAM_MUSIC;
//    private boolean loaded;
//    private int menuback;
//    private int menuhit;
//    private int stop;
//    private float volume;


    Button button_A, button_B, button_stop;
    EditText editText_question;
    TextView textView_progress;

    DatabaseHelper databaseHelper;
    WhoRU whoRU;
    MediaPlayer ring;
    int count=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        final AnimationSet as = new AnimationSet(true);
        final Timer time = new Timer();
        final MediaPlayer ring = MediaPlayer.create(WhoRUActivity.this, R.raw.lavender_town);
        final MediaPlayer stop = MediaPlayer.create(WhoRUActivity.this, R.raw.seeya);
        ring.setLooping(true);

        if(count ==0) {
            ring.start();
        }

        final Animation in = AnimationUtils.loadAnimation(WhoRUActivity.this, R.anim.fadeinin);
        final Animation out = AnimationUtils.loadAnimation(WhoRUActivity.this, R.anim.fadeoutout);
        in.setStartOffset(1000);
        out.setInterpolator(new FastOutSlowInInterpolator());
        out.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                textView_progress.startAnimation(in);
                editText_question.startAnimation(in);
                button_A.startAnimation(in);
                button_B.startAnimation(in);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        final MediaPlayer hit1 = MediaPlayer.create(WhoRUActivity.this, R.raw.menuhit);
        final MediaPlayer hit2 = MediaPlayer.create(WhoRUActivity.this, R.raw.menuback);

//        audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
//        float currentVolumeIndex = (float) audioManager.getStreamVolume(streamType);
//        float maxVolumeIndex  = (float) audioManager.getStreamMaxVolume(streamType);
//        this.volume = currentVolumeIndex / maxVolumeIndex;
//        this.setVolumeControlStream(streamType);
//
//        if (Build.VERSION.SDK_INT >= 21 ) {
//
//            AudioAttributes audioAttrib = new AudioAttributes.Builder()
//                    .setUsage(AudioAttributes.USAGE_GAME)
//                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
//                    .build();
//
//            SoundPool.Builder builder= new SoundPool.Builder();
//            builder.setAudioAttributes(audioAttrib).setMaxStreams(MAX_STREAMS);
//
//            this.soundPool = builder.build();
//        }
//        else {
//            this.soundPool = new SoundPool(MAX_STREAMS, AudioManager.STREAM_MUSIC, 0);
//        }
//
//        this.soundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
//            @Override
//            public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
//                loaded = true;
//            }
//        });
//
//        this.menuback = this.soundPool.load(this, R.raw.menuback,1);
//        this.menuhit = this.soundPool.load(this, R.raw.menuhit,1);
//        this.stop = this.soundPool.load(this,R.raw.seeya,1);



//        hit1.reset();
//        hit1.release();
//        hit2.reset();
//        hit2.release();
//        stop.reset();
//        stop.release();


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_who_ru);
        final String currentUsername2 = uicGetSharedPreferenceValue("userInfo", "username");
        databaseHelper = new DatabaseHelper(this);
        whoRU = new WhoRU();
        overridePendingTransition(R.anim.slidein, R.anim.slideout);
        editText_question = (EditText) findViewById(R.id.editText_question);
        textView_progress = (TextView) findViewById(R.id.textView_progress);
        button_A = (Button) findViewById(R.id.button_A);
        button_A.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                time.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        runOnUiThread(new Runnable() {

                            @Override
                            public void run() {
                                saveUserChoice("A");
                            }
                        });
                    }
                }, 300);

//                if(loaded)  {
//                    float leftVolumn = volume;
//                    float rightVolumn = volume;
//                    // Play sound of gunfire. Returns the ID of the new stream.
//                    int streamId = soundPool.play(menuhit,leftVolumn, rightVolumn, 1, 0, 1f);
//                }
                hit1.start();
                count++;
                textView_progress.startAnimation(out);
                editText_question.startAnimation(out);
                button_A.startAnimation(out);
                button_B.startAnimation(out);
//                if(out.hasEnded()){
//                    textView_progress.startAnimation(in);
//                    editText_question.startAnimation(in);
//                    button_A.startAnimation(in);
//                    button_B.startAnimation(in);
//                }
                if(count >= 70){
                    ring.stop();
                    ring.release();
                    count=0;
                }
            }
        });

        button_B = (Button) findViewById(R.id.button_B);
        button_B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                time.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        runOnUiThread(new Runnable() {

                            @Override
                            public void run() {
                                saveUserChoice("B");
                            }
                        });
                    }
                }, 300);
//                if(loaded)  {
//                    float leftVolumn = volume;
//                    float rightVolumn = volume;
//                    int streamId = soundPool.play(menuback,leftVolumn, rightVolumn, 1, 0, 1f);
//                }
                hit2.start();
                count++;
                textView_progress.startAnimation(out);
                editText_question.startAnimation(out);
                button_A.startAnimation(out);
                button_B.startAnimation(out);
//                if(out.hasEnded()){
//                    textView_progress.startAnimation(in);
//                    editText_question.startAnimation(in);
//                    button_A.startAnimation(in);
//                    button_B.startAnimation(in);
//                }
                if(count >= 70){
                    ring.stop();
                    ring.release();
                    count=0;
                }
            }
        });

        button_stop = (Button) findViewById(R.id.button_stop);
        button_stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                if(loaded)  {
//                    float leftVolumn = volume;
//                    float rightVolumn = volume;
//                    int streamId = soundPool.play(stop,leftVolumn, rightVolumn, 1, 0, 1f);
//                }

                AlertDialog.Builder builder = new AlertDialog.Builder(WhoRUActivity.this);
                builder.setTitle("Stopping Already?");
                builder.setMessage("Do you wish to stop "+currentUsername2+"?");
                builder.setIcon(R.mipmap.ic_launcher);
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        whoRU.currentQuestion =0;
                        stop.start();
                        ring.stop();
                        ring.release();
                        startActivity(new Intent(WhoRUActivity.this, MainActivity.class));
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

        generateWhoRU();
    }

    public void saveUserChoice(String choice){
        whoRU.answers[whoRU.currentQuestion]=choice;
        whoRU.currentQuestion++;
        if(whoRU.currentQuestion>=whoRU.SIZE){
            saveUserData();
        }else{
            generateWhoRU();
        }
    }

    public void generateWhoRU(){
        int currentIndex = whoRU.currentQuestion;
        String currentUsername = uicGetSharedPreferenceValue("userInfo", "username");
        String question = currentUsername + "!\n" + whoRU.getQuestion(currentIndex);
        editText_question.setText(question);
        button_A.setText(whoRU.getChoice(currentIndex,0));
        button_B.setText(whoRU.getChoice(currentIndex,1));
        String progress = (whoRU.currentQuestion+1) + "/" + whoRU.SIZE;
        textView_progress.setText(progress);
    }

    public void saveUserData(){
        String currentUsername = uicGetSharedPreferenceValue("userInfo", "username");
        String traits = generateTraits();

        boolean insertData = databaseHelper.addData(currentUsername, traits);
        if(insertData) {
            startActivity(new Intent(WhoRUActivity.this, ResultActivity.class));
        }else{
            uicToastMessage("Something wrong in your database!");
        }
    }

    public String generateTraits(){
        String traits = "";

        if(whoRU.isExtrovert()) traits+="E"; //Extrovert
        else traits+="I"; // Introvert

        if(whoRU.isSensing()) traits+="S"; //Sensing
        else traits+="N"; //Intuiting

        if(whoRU.isThinking()) traits+="T"; //Thinking
        else traits+="F"; //Feeling

        if(whoRU.isJudging()) traits+="J"; //Judging
        else traits+="P"; //Perceiving

        return traits;
    }

    public String uicGetSharedPreferenceValue(String sharedPrefName, String key){
        SharedPreferences sharedPreferences = getSharedPreferences(sharedPrefName, Context.MODE_PRIVATE);
        String value = sharedPreferences.getString(key, "");
        return value;
    }

    public void uicToastMessage(String message){
        Toast.makeText(this, message ,Toast.LENGTH_SHORT).show();
    }
}