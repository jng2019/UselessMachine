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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        wirewidgets();
        setListeners();
        updateGameDisplay();

    }

    private void updateGameDisplay() {

    }

    private void wirewidgets() {
        buttonSelfDestruct = findViewById(R.id.button_main_selfDestruct);
        uselessSwitch = findViewById(R.id.switch_main_useless);
    }

    private void setListeners() {
        uselessSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    new CountDownTimer(2000, 1000) {
                        @Override
                        public void onTick(long l) {
                            if (!uselessSwitch.isChecked()){
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
                }
                else {
                    Toast.makeText(MainActivity.this, "Switch Is Off!", Toast.LENGTH_SHORT).show();
                }

        buttonSelfDestruct.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View view) {
                        new CountDownTimer(10000, 1000){
                            @Override
                            public void onTick(long ticksLeft) {
                                buttonSelfDestruct.setText(String.valueOf(ticksLeft));
                            }
                            @Override
                            public void onFinish() {
                                cancel();
                            }
                        }.start();
                        return false;
                    }
                });
            }
        });
    }


}

