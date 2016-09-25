package com.example.inspired.simplified;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private boolean mSaveIsChecked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final SharedPreferences prefs = this.getSharedPreferences(
                "com.example.app", Context.MODE_PRIVATE);

        Button actionButton = (Button) findViewById(R.id.action_button);
        actionButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                EditText editText = (EditText) findViewById(R.id.text_form_field);
                TextView textView = (TextView) findViewById(R.id.message);
                String message = editText.getText().toString();
                textView.setText(message);

                System.out.println(mSaveIsChecked);

                if(mSaveIsChecked){
                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putString(message, message);
                    editor.apply();
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        System.out.println("Here");
        switch (item.getItemId()){
            case R.id.checkbox_save:
                mSaveIsChecked = item.isChecked();
                return true;
            default:
                return true;
        }
    }
}
