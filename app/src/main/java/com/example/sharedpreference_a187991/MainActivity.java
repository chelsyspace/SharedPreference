package com.example.sharedpreference_a187991;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView tv_language;
    LinearLayout ll_setting_language, ll_setting_notification;
    Switch switch_notification;

    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;
    String SP_LANGUAGE = "language";
    String SP_NOTIFICATION = "notification";

    String[] values = {"English", "Melayu", "Chinese", "Tamil"};
    AlertDialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_language = findViewById(R.id.tv_language);
        switch_notification = findViewById(R.id.switch_notification);
        ll_setting_language = findViewById(R.id.ll_setting_language);
        ll_setting_notification = findViewById(R.id.ll_setting_notification);

        sharedPref = getSharedPreferences("app_setting",MODE_PRIVATE);
        editor = sharedPref.edit();

        switch_notification.setChecked(sharedPref.getBoolean(SP_NOTIFICATION, false));

       switch_notification.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
           @Override
           public void onCheckedChanged(CompoundButton buttonView, boolean b) {
               editor.putBoolean(SP_NOTIFICATION,b);
               editor.commit();
           }
       });
        /*switch_notification.setOnCheckedChangeListener(CompoundButton compoundButton, boolean b){
            editor.putBoolean(SP_NOTIFICATION,b);
            editor.commit();
        };*/

        ll_setting_notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean switchState = switch_notification.isChecked();
                switch_notification.setChecked(!switchState);
                editor.putBoolean(SP_NOTIFICATION,!switchState);
                editor.commit();
            }
        });

        ll_setting_language.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowLanguageOptions();
            }
        });

    }

    public void ShowLanguageOptions(){
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Select your language:");
        builder.setSingleChoiceItems(values, sharedPref.getInt(SP_LANGUAGE,0), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                switch (i){
                    case 0:
                        editor.putInt(SP_LANGUAGE,0);
                        editor.commit();
                        break;

                    case 1:
                        editor.putInt(SP_LANGUAGE,1);
                        editor.commit();
                        break;

                    case 2:
                        editor.putInt(SP_LANGUAGE,2);
                        editor.commit();
                        break;

                    case 3:
                        editor.putInt(SP_LANGUAGE,3);
                        editor.commit();
                        break;
                }

                alertDialog.dismiss();
                tv_language.setText(values[sharedPref.getInt(SP_LANGUAGE,0)]);
            }
        });

        alertDialog = builder.create();
        alertDialog.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }
    
}