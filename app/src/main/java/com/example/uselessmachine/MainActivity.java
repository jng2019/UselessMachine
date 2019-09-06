package com.example.uselessmachine;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Switch uselessSwitch;
    private Button buttonSelfDestruct;
    private TextView showProgress;
    private ProgressBar barShowProgress;
    private Button buttonBeBusy;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        wirewidgets();
        setListeners();

    }


    private void wirewidgets() {
        buttonSelfDestruct = findViewById(R.id.button_main_selfDestruct);
        uselessSwitch = findViewById(R.id.switch_main_useless);
        buttonBeBusy = findViewById(R.id.buttonLookBusy);
        barShowProgress = findViewById(R.id.progressBar_main_LookBusy);
        showProgress = findViewById(R.id.textView2);
    }

    private void setListeners() {
        uselessSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    new CountDownTimer(2000, 1000) {
                        @Override
                        public void onTick(long l) {
                            if (!uselessSwitch.isChecked()) {
                                cancel();
                            }
                        }

                        @Override
                        public void onFinish() {
                            uselessSwitch.setChecked(false);
                        }
                    }.start();
                }
                if (isChecked) {
                    Toast.makeText(MainActivity.this, "Switch Is On!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Switch Is Off!", Toast.LENGTH_SHORT).show();
                }

            }
        });
        buttonSelfDestruct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new CountDownTimer(10000, 1000){
                    @Override
                    public void onTick(long ticksLeft) {
                        buttonSelfDestruct.setText(String.valueOf(ticksLeft/1000));
                    }
                    @Override
                    public void onFinish() {
                        finish();
                    }
                }.start();
            }
        });

        buttonBeBusy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonBeBusy.setVisibility(View.INVISIBLE);
                buttonSelfDestruct.setVisibility(View.INVISIBLE);
                uselessSwitch.setVisibility(View.INVISIBLE);
                barShowProgress.setVisibility(View.VISIBLE);
                showProgress.setVisibility(View.VISIBLE);
                new CountDownTimer(10000, 100) {
                    int countDown = 1;
                    @Override
                    public void onTick(long l) {
                        barShowProgress.setProgress(countDown);
                        countDown++;
                    }

                    @Override
                    public void onFinish() {
                        buttonBeBusy.setVisibility(View.VISIBLE);
                        buttonSelfDestruct.setVisibility(View.VISIBLE);
                        uselessSwitch.setVisibility(View.VISIBLE);
                        barShowProgress.setVisibility(View.INVISIBLE);
                        showProgress.setVisibility(View.INVISIBLE);
                    }
                }.start();
            }

        });
    }


}

