package com.example.inspired.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button myButton = (Button) findViewById(R.id.my_button);
        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView myText = (TextView) findViewById(R.id.my_text);
                String previousText = (String) myText.getText();
                String defaultString = getResources().getString(R.string.my_custom_text);
                //System.out.println(previousText.equals(defaultString));

                if(previousText.equals(defaultString)){
                    myText.setText("I'm changed");
                } else {
                    myText.setText(defaultString);
                }
            }
        });
    }
}
