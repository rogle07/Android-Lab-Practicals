package com.yogesh.androidlabpracticals.practical5;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.yogesh.androidlabpracticals.R;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class InternalStorageActivity extends AppCompatActivity {

    private static final String FILE_NAME = "yogesh_notes.txt";
    private EditText etInput;
    private TextView tvSavedData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_internal_storage);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Practical 5 – Internal Storage");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        etInput = findViewById(R.id.et_input);
        tvSavedData = findViewById(R.id.tv_saved_data);

        Button btnSave = findViewById(R.id.btn_save);
        Button btnRead = findViewById(R.id.btn_read);
        Button btnUpdate = findViewById(R.id.btn_update);
        Button btnDelete = findViewById(R.id.btn_delete);

        btnSave.setOnClickListener(v -> saveData());
        btnRead.setOnClickListener(v -> readData());
        btnUpdate.setOnClickListener(v -> updateData());
        btnDelete.setOnClickListener(v -> deleteData());

        readData();
    }

    private void saveData() {
        String text = etInput.getText().toString().trim();
        if (text.isEmpty()) {
            Toast.makeText(this, "Please enter some text first.", Toast.LENGTH_SHORT).show();
            return;
        }
        try (FileOutputStream fos = openFileOutput(FILE_NAME, MODE_PRIVATE)) {
            fos.write(text.getBytes());
            Toast.makeText(this, "✅ Saved successfully!", Toast.LENGTH_SHORT).show();
            readData();
            etInput.setText("");
        } catch (IOException e) {
            Toast.makeText(this, "Save failed: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private void readData() {
        try (FileInputStream fis = openFileInput(FILE_NAME);
             InputStreamReader isr = new InputStreamReader(fis);
             BufferedReader br = new BufferedReader(isr)) {
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) sb.append(line).append("\n");
            String content = sb.toString().trim();
            tvSavedData.setText(content.isEmpty() ? "No data saved yet." : content);
        } catch (IOException e) {
            tvSavedData.setText("No data saved yet.");
        }
    }

    private void updateData() {
        String text = etInput.getText().toString().trim();
        if (text.isEmpty()) {
            Toast.makeText(this, "Enter new text to update.", Toast.LENGTH_SHORT).show();
            return;
        }
        // Update = overwrite
        try (FileOutputStream fos = openFileOutput(FILE_NAME, MODE_PRIVATE)) {
            fos.write(text.getBytes());
            Toast.makeText(this, "✏️ Updated successfully!", Toast.LENGTH_SHORT).show();
            readData();
            etInput.setText("");
        } catch (IOException e) {
            Toast.makeText(this, "Update failed: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private void deleteData() {
        boolean deleted = deleteFile(FILE_NAME);
        tvSavedData.setText("No data saved yet.");
        etInput.setText("");
        Toast.makeText(this, deleted ? "🗑️ Deleted successfully!" : "No file to delete.", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
