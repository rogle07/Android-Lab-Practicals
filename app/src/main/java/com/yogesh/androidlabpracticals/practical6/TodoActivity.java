package com.yogesh.androidlabpracticals.practical6;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.yogesh.androidlabpracticals.R;
import java.util.List;

public class TodoActivity extends AppCompatActivity {

    private TodoDatabaseHelper dbHelper;
    private EditText etTask;
    private LinearLayout taskContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Practical 6 – SQLite To-Do");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        dbHelper = new TodoDatabaseHelper(this);
        etTask = findViewById(R.id.et_task);
        taskContainer = findViewById(R.id.task_container);

        Button btnAdd = findViewById(R.id.btn_add_task);
        btnAdd.setOnClickListener(v -> addTask());

        loadTasks();
    }

    private void addTask() {
        String task = etTask.getText().toString().trim();
        if (task.isEmpty()) {
            Toast.makeText(this, "Please enter a task.", Toast.LENGTH_SHORT).show();
            return;
        }
        dbHelper.insertTask(task);
        etTask.setText("");
        Toast.makeText(this, "✅ Task added!", Toast.LENGTH_SHORT).show();
        loadTasks();
    }

    private void loadTasks() {
        taskContainer.removeAllViews();
        List<TodoItem> tasks = dbHelper.getAllTasks();

        if (tasks.isEmpty()) {
            TextView empty = new TextView(this);
            empty.setText("No tasks yet. Add one above!");
            empty.setPadding(16, 32, 16, 16);
            empty.setTextSize(16f);
            taskContainer.addView(empty);
            return;
        }

        for (TodoItem item : tasks) {
            View row = LayoutInflater.from(this).inflate(R.layout.item_todo, taskContainer, false);
            CheckBox cb = row.findViewById(R.id.cb_task);
            Button btnEdit = row.findViewById(R.id.btn_edit);
            Button btnDelete = row.findViewById(R.id.btn_delete);

            cb.setText(item.task);
            cb.setChecked(item.isDone);

            if (item.isDone) {
                cb.setPaintFlags(cb.getPaintFlags() | android.graphics.Paint.STRIKE_THRU_TEXT_FLAG);
            }

            cb.setOnCheckedChangeListener((buttonView, isChecked) -> {
                dbHelper.toggleDone(item.id, isChecked);
                loadTasks();
            });

            btnEdit.setOnClickListener(v -> showEditDialog(item));
            btnDelete.setOnClickListener(v -> {
                dbHelper.deleteTask(item.id);
                Toast.makeText(this, "Task deleted.", Toast.LENGTH_SHORT).show();
                loadTasks();
            });

            taskContainer.addView(row);
        }
    }

    private void showEditDialog(TodoItem item) {
        EditText input = new EditText(this);
        input.setText(item.task);
        new AlertDialog.Builder(this)
                .setTitle("Edit Task")
                .setView(input)
                .setPositiveButton("Update", (d, w) -> {
                    String newTask = input.getText().toString().trim();
                    if (!newTask.isEmpty()) {
                        dbHelper.updateTask(item.id, newTask);
                        Toast.makeText(this, "Task updated!", Toast.LENGTH_SHORT).show();
                        loadTasks();
                    }
                })
                .setNegativeButton("Cancel", null)
                .show();
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
