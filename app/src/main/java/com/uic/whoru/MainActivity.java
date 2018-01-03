package com.uic.whoru;

import android.animation.ValueAnimator;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebHistoryItem;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button button_start, button_about;
    EditText editText_username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);

        final MediaPlayer ring= MediaPlayer.create(MainActivity.this,R.raw.omocha);
        final MediaPlayer hit = MediaPlayer.create(MainActivity.this,R.raw.menuhit);

        final ImageView backgroundOne = (ImageView) findViewById(R.id.background1);
        final ImageView backgroundTwo = (ImageView) findViewById(R.id.background2);

        ring.setLooping(true);
        ring.start();

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



        editText_username = (EditText) findViewById(R.id.editText_username);
        editText_username.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { /**/ }

            @Override
            public void afterTextChanged(Editable editable) { /**/ }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 1) {
                    button_start.setEnabled(true);
                }
            }
        });

        button_start = (Button) findViewById(R.id.button_start);
        button_start.setEnabled(false);
        button_start.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                hit.start();

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                Editable nem = editText_username.getText();
                builder.setTitle("Start the Quiz?");
                builder.setMessage("Are you ready to start " + nem + "?");
                builder.setIcon(R.mipmap.ic_launcher);
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        uicSetSharedPreferenceValue("userInfo", "username", editText_username.getText().toString());
                        startActivity(new Intent(MainActivity.this, WhoRUActivity.class));
                        ring.pause();
                        uicHideKeyboard();
                        if(ring!=null){
                            ring.release();
                        }
                        if(hit!=null & !hit.isPlaying()){
                            hit.release();
                        }
                        uicToastMessage("You may now begin! :D");
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
                if (uicGetSharedPreferenceValue("userInfo", "username").isEmpty()) {
                    button_start.setEnabled(false);
                }
            }
        });

        button_about = (Button) findViewById(R.id.button_about);
        button_about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AboutActivity.class);
                hit.start();
                if(hit!=null & !hit.isPlaying()){
                    hit.release();
                }
                ring.pause();
                startActivity(intent);
            }
        });
    }

    public void uicToastMessage(String message){
        Toast.makeText(this, message ,Toast.LENGTH_SHORT).show();
    }

    public void uicSetSharedPreferenceValue(String sharedPrefName, String key, String value){
        SharedPreferences sharedPreferences = getSharedPreferences(sharedPrefName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public String uicGetSharedPreferenceValue(String sharedPrefName, String key){
        SharedPreferences sharedPreferences = getSharedPreferences(sharedPrefName, Context.MODE_PRIVATE);
        String value = sharedPreferences.getString(key, "");
        return value;
    }

    public void uicHideKeyboard(){
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),InputMethodManager.RESULT_UNCHANGED_SHOWN);
    }
}

