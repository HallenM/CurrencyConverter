package com.currency.converter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        String currFrom = intent.getStringExtra("curr_from");
        String currTo = intent.getStringExtra("curr_to");
        String value = intent.getStringExtra("value_for_convert");

        EditText from = findViewById(R.id.textFirstCurr);
        from.setText(value);
        TextView info = findViewById(R.id.textViewInfo);

        if(currFrom != null && currTo != null) {
            info.setText("Convert from " + currFrom + " to " + currTo);

            String currChanging = intent.getStringExtra("changing");
            if (currChanging.equals("true")) {
                doConvert();
            }
        }
        else if(currFrom != null){
            info.setText("Convert from " + currFrom + " to B");
        } else if(currTo != null){
            info.setText("Convert from A to " + currTo);
        }
    }

    @Override
    protected void onStart(){
        super.onStart();

        Button btnConvert = findViewById(R.id.btnConvert);
        Button btnChangeFirst = findViewById(R.id.btnCangeFirst);
        Button btnChangeSecond = findViewById(R.id.btnCangeSecond);

        btnChangeFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();
                EditText from = findViewById(R.id.textFirstCurr);

                String currTo = intent.getStringExtra("curr_to");
                String nominalTo = intent.getStringExtra("curr_nominal_to");
                String valueTo = intent.getStringExtra("value_to");
                String value = from.getText().toString();

                intent = new Intent(getBaseContext(), CurrencyActivity.class);
                intent.putExtra("btn_click", 1);
                intent.putExtra("value_for_convert", value);

                intent.putExtra("curr_to", currTo);
                intent.putExtra("curr_nominal_to", nominalTo);
                intent.putExtra("value_to", valueTo);

                startActivity(intent);
            }
        });

        btnChangeSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();
                EditText from = findViewById(R.id.textFirstCurr);

                String currFrom = intent.getStringExtra("curr_from");
                String nominalFrom = intent.getStringExtra("curr_nominal_from");
                String valueFrom = intent.getStringExtra("value_from");
                String value = from.getText().toString();

                intent = new Intent(getBaseContext(), CurrencyActivity.class);
                intent.putExtra("btn_click", 2);
                intent.putExtra("value_for_convert", value);

                intent.putExtra("curr_from", currFrom);
                intent.putExtra("curr_nominal_from", nominalFrom);
                intent.putExtra("value_from", valueFrom);

                startActivity(intent);
            }
        });

        btnConvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doConvert();
            }
        });
    }

    void doConvert(){
        EditText from = findViewById(R.id.textFirstCurr);
        EditText to = findViewById(R.id.textSecondCurr);

        String value = from.getText().toString();
        if(!value.equals("")){
            value = value.replace(',', '.');
            float convertValue = Float.parseFloat(value);
            Intent intent = getIntent();

            value = intent.getStringExtra("curr_nominal_from");
            int nominalFrom = Integer.parseInt(value);

            value = intent.getStringExtra("value_from");
            value = value.replace(',', '.');
            float valueFrom = Float.parseFloat(value);

            value = intent.getStringExtra("curr_nominal_to");
            int nominalTo = Integer.parseInt(value);

            value = intent.getStringExtra("value_to");
            value = value.replace(',', '.');
            float valueTo = Float.parseFloat(value);

            float curToVal = (convertValue * nominalTo * valueFrom) / (valueTo * nominalFrom);

            to.setText(String.valueOf(curToVal));
        }

    }
}
