package com.example.calculadora;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView display;
    private String currentInput = "";
    private String operator = "";
    private double firstValue = 0;
    //    Santiago Franco De La Rosa - 2155080 Autor

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        display = findViewById(R.id.tvDisplay);

        // Setting number button listeners
        setNumberClickListener();
        setOperatorClickListener();
        setClearClickListener();
        setDeleteClickListener();
        setEqualsClickListener();
    }

    private void setNumberClickListener() {
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button button = (Button) v;
                currentInput += button.getText().toString();
                display.setText(currentInput);
            }
        };

        int[] numberButtons = {R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4, R.id.btn5, R.id.btn6, R.id.btn7, R.id.btn8, R.id.btn9};
        for (int id : numberButtons) {
            findViewById(id).setOnClickListener(listener);
        }
    }

    private void setOperatorClickListener() {
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button button = (Button) v;
                if (!currentInput.isEmpty()) {
                    firstValue = Double.parseDouble(currentInput);
                    operator = button.getText().toString();
                    currentInput = "";
                }
            }
        };

        int[] operatorButtons = {R.id.btnPlus, R.id.btnMinus, R.id.btnMultiply, R.id.btnDivide};
        for (int id : operatorButtons) {
            findViewById(id).setOnClickListener(listener);
        }
    }

    private void setClearClickListener() {
        findViewById(R.id.btnClear).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentInput = "";
                operator = "";
                firstValue = 0;
                display.setText("0");
            }
        });
    }

    private void setDeleteClickListener() {
        findViewById(R.id.btnDelete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!currentInput.isEmpty()) {
                    currentInput = currentInput.substring(0, currentInput.length() - 1);
                    if (currentInput.isEmpty()) {
                        display.setText("0");
                    } else {
                        display.setText(currentInput);
                    }
                }
            }
        });
    }

    private void setEqualsClickListener() {
        findViewById(R.id.btnEquals).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!currentInput.isEmpty()) {
                    double secondValue = Double.parseDouble(currentInput);
                    double result = 0;
                    switch (operator) {
                        case "+":
                            result = firstValue + secondValue;
                            break;
                        case "-":
                            result = firstValue - secondValue;
                            break;
                        case "*":
                            result = firstValue * secondValue;
                            break;
                        case "/":
                            if (secondValue != 0) {
                                result = firstValue / secondValue;
                            } else {
                                display.setText("Error");
                                return;
                            }
                            break;
                    }
                    display.setText(String.valueOf(result));
                    currentInput = String.valueOf(result);
                    operator = "";
                }
            }
        });
    }
}
