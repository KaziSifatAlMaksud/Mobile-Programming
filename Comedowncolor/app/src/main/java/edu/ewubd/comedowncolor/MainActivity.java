package edu.ewubd.comedowncolor;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    private TextView countdownTextView;
    private Handler handler;
    private Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        countdownTextView = findViewById(R.id.countdownTextView);

        handler = new Handler(Looper.getMainLooper());
        runnable = new Runnable() {
            long countdownTime = TimeUnit.DAYS.toMillis(3) +
                    TimeUnit.HOURS.toMillis(3) +
                    TimeUnit.MINUTES.toMillis(5) +
                    TimeUnit.SECONDS.toMillis(10); // 3 days, 3 hours, 5 minutes, and 10 seconds

            @Override
            public void run() {
                long days = TimeUnit.MILLISECONDS.toDays(countdownTime);
                long hours = TimeUnit.MILLISECONDS.toHours(countdownTime) % 24;
                long minutes = TimeUnit.MILLISECONDS.toMinutes(countdownTime) % 60;
                long seconds = TimeUnit.MILLISECONDS.toSeconds(countdownTime) % 60;
                countdownTextView.setText(String.format("%02d:%02d:%02d:%02d", days, hours, minutes, seconds));
                if (countdownTime > 0) {
                    countdownTime -= 1000; // decrement by one second
                    handler.postDelayed(this, 1000); // repeat after one second
                } else {
                    countdownTextView.setText("Countdown finished!");
                }
            }
        };

        new Thread(new Runnable() {
            @Override
            public void run() {
                handler.post(runnable);
            }
        }).start();
    }
}
