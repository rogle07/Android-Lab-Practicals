package com.yogesh.androidlabpracticals.practical4;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.yogesh.androidlabpracticals.R;

public class MenusActivity extends AppCompatActivity {

    private TextView tvContextTarget;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menus);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Practical 4 – Menus & Navigation");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        tvContextTarget = findViewById(R.id.tv_context_target);
        registerForContextMenu(tvContextTarget);

        findViewById(R.id.btn_go_second).setOnClickListener(v ->
                startActivity(new Intent(this, SecondActivity.class)));

        findViewById(R.id.btn_go_third).setOnClickListener(v ->
                startActivity(new Intent(this, ThirdActivity.class)));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menu_about) {
            Toast.makeText(this, "Android Lab Practicals – Made by Yogesh", Toast.LENGTH_SHORT).show();
            return true;
        } else if (id == R.id.menu_home) {
            finish();
            return true;
        } else if (id == R.id.menu_share) {
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_TEXT, "Check out Android Lab Practicals by Yogesh!");
            startActivity(Intent.createChooser(shareIntent, "Share via"));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.context_menu, menu);
        menu.setHeaderTitle("Context Menu");
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.context_copy) {
            Toast.makeText(this, "Text Copied!", Toast.LENGTH_SHORT).show();
            return true;
        } else if (id == R.id.context_edit) {
            tvContextTarget.setText("✏️ Edited by context menu!");
            return true;
        } else if (id == R.id.context_delete) {
            tvContextTarget.setText("🗑️ Deleted! Long press again to restore.");
            return true;
        }
        return super.onContextItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
