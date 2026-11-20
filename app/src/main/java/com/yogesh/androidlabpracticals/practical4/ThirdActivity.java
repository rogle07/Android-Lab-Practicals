package com.yogesh.androidlabpracticals.practical4;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import com.yogesh.androidlabpracticals.R;

public class ThirdActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Third Activity");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        Button btnBack = findViewById(R.id.btn_back_home);
        btnBack.setOnClickListener(v -> {
            Intent intent = new Intent(this, MenusActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
