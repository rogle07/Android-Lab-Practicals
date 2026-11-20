package com.yogesh.androidlabpracticals.practical4;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.yogesh.androidlabpracticals.R;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Second Activity");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        Button btnGoThird = findViewById(R.id.btn_to_third);
        Button btnBack = findViewById(R.id.btn_back_first);

        btnGoThird.setOnClickListener(v -> startActivity(new Intent(this, ThirdActivity.class)));
        btnBack.setOnClickListener(v -> finish());
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
