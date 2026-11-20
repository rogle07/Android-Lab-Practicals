package com.yogesh.androidlabpracticals.practical3;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.yogesh.androidlabpracticals.R;

public class CalculatorActivity extends AppCompatActivity {

    private TextView display;
    private StringBuilder currentInput = new StringBuilder();
    private double firstOperand = 0;
    private String operator = "";
    private boolean isNewInput = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Practical 3 – Calculator");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        display = findViewById(R.id.tv_display);

        int[] numberIds = {R.id.btn_0, R.id.btn_1, R.id.btn_2, R.id.btn_3,
                R.id.btn_4, R.id.btn_5, R.id.btn_6, R.id.btn_7,
                R.id.btn_8, R.id.btn_9};
        String[] numbers = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};

        for (int i = 0; i < numberIds.length; i++) {
            final String num = numbers[i];
            Button btn = findViewById(numberIds[i]);
            btn.setOnClickListener(v -> appendNumber(num));
        }

        findViewById(R.id.btn_dot).setOnClickListener(v -> appendDot());
        findViewById(R.id.btn_clear).setOnClickListener(v -> clear());
        findViewById(R.id.btn_plus).setOnClickListener(v -> setOperator("+"));
        findViewById(R.id.btn_minus).setOnClickListener(v -> setOperator("-"));
        findViewById(R.id.btn_multiply).setOnClickListener(v -> setOperator("×"));
        findViewById(R.id.btn_divide).setOnClickListener(v -> setOperator("÷"));
        findViewById(R.id.btn_equals).setOnClickListener(v -> calculate());
        findViewById(R.id.btn_plus_minus).setOnClickListener(v -> toggleSign());
        findViewById(R.id.btn_percent).setOnClickListener(v -> percent());
    }

    private void appendNumber(String num) {
        if (isNewInput) {
            currentInput.setLength(0);
            isNewInput = false;
        }
        currentInput.append(num);
        display.setText(currentInput.toString());
    }

    private void appendDot() {
        if (isNewInput) {
            currentInput.setLength(0);
            currentInput.append("0");
            isNewInput = false;
        }
        if (!currentInput.toString().contains(".")) {
            if (currentInput.length() == 0) currentInput.append("0");
            currentInput.append(".");
            display.setText(currentInput.toString());
        }
    }

    private void clear() {
        currentInput.setLength(0);
        firstOperand = 0;
        operator = "";
        isNewInput = false;
        display.setText("0");
    }

    private void setOperator(String op) {
        if (currentInput.length() > 0) {
            firstOperand = Double.parseDouble(currentInput.toString());
        }
        operator = op;
        isNewInput = true;
    }

    private void calculate() {
        if (operator.isEmpty() || currentInput.length() == 0) return;
        double secondOperand = Double.parseDouble(currentInput.toString());
        double result;
        switch (operator) {
            case "+": result = firstOperand + secondOperand; break;
            case "-": result = firstOperand - secondOperand; break;
            case "×": result = firstOperand * secondOperand; break;
            case "÷":
                if (secondOperand == 0) {
                    display.setText("Error: ÷0");
                    operator = "";
                    currentInput.setLength(0);
                    return;
                }
                result = firstOperand / secondOperand; break;
            default: return;
        }
        String resultStr = (result == (long) result) ? String.valueOf((long) result) : String.valueOf(result);
        display.setText(resultStr);
        currentInput.setLength(0);
        currentInput.append(resultStr);
        operator = "";
        isNewInput = true;
    }

    private void toggleSign() {
        if (currentInput.length() > 0) {
            double val = Double.parseDouble(currentInput.toString()) * -1;
            String str = (val == (long) val) ? String.valueOf((long) val) : String.valueOf(val);
            currentInput.setLength(0);
            currentInput.append(str);
            display.setText(str);
        }
    }

    private void percent() {
        if (currentInput.length() > 0) {
            double val = Double.parseDouble(currentInput.toString()) / 100.0;
            String str = (val == (long) val) ? String.valueOf((long) val) : String.valueOf(val);
            currentInput.setLength(0);
            currentInput.append(str);
            display.setText(str);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
