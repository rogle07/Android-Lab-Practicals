package com.yogesh.androidlabpracticals;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import com.yogesh.androidlabpracticals.practical3.CalculatorActivity;
import com.yogesh.androidlabpracticals.practical4.MenusActivity;
import com.yogesh.androidlabpracticals.practical5.InternalStorageActivity;
import com.yogesh.androidlabpracticals.practical6.TodoActivity;
import com.yogesh.androidlabpracticals.practical7.InternetEmailActivity;
import com.yogesh.androidlabpracticals.practical8.GraphicsAnimationActivity;
import com.yogesh.androidlabpracticals.practical9.LocationActivity;
import com.yogesh.androidlabpracticals.practical10.CameraActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CardView card3 = findViewById(R.id.card_practical3);
        CardView card4 = findViewById(R.id.card_practical4);
        CardView card5 = findViewById(R.id.card_practical5);
        CardView card6 = findViewById(R.id.card_practical6);
        CardView card7 = findViewById(R.id.card_practical7);
        CardView card8 = findViewById(R.id.card_practical8);
        CardView card9 = findViewById(R.id.card_practical9);
        CardView card10 = findViewById(R.id.card_practical10);

        card3.setOnClickListener(v -> startActivity(new Intent(this, CalculatorActivity.class)));
        card4.setOnClickListener(v -> startActivity(new Intent(this, MenusActivity.class)));
        card5.setOnClickListener(v -> startActivity(new Intent(this, InternalStorageActivity.class)));
        card6.setOnClickListener(v -> startActivity(new Intent(this, TodoActivity.class)));
        card7.setOnClickListener(v -> startActivity(new Intent(this, InternetEmailActivity.class)));
        card8.setOnClickListener(v -> startActivity(new Intent(this, GraphicsAnimationActivity.class)));
        card9.setOnClickListener(v -> startActivity(new Intent(this, LocationActivity.class)));
        card10.setOnClickListener(v -> startActivity(new Intent(this, CameraActivity.class)));
    }
}
