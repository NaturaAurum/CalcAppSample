package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText edit1, edit2;
    Button btnAdd, btnSub, btnMul, btnDiv, btnRemain;
    TextView textResult;
    String num1, num2;
    Integer result;

    Context context;
    int toastDuration = 0;

    View.OnTouchListener onCalcTouchEvent = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            if(event.getAction() != MotionEvent.ACTION_DOWN)
                return false;
            Button btn = (Button)v;
            String btnText = btn.getText().toString();

            num1 = edit1.getText().toString();
            num2 = edit2.getText().toString();

            num1 = num1.replace(' ', '\0');
            num2 = num2.replace(' ', '\0');


            boolean isEmpty = (num1.isEmpty() || num2.isEmpty());

            if(isEmpty)
            {
                Toast.makeText(context, "값을 입력하지 않았습니다." ,toastDuration).show();
                textResult.setText("");
                return false;
            }

            int num1Int = Integer.parseInt(num1);
            int num2Int = Integer.parseInt(num2);

            switch(btnText)
            {
                case "+":
                    result = num1Int + num2Int;
                    break;
                case "-":
                    result = num1Int - num2Int;
                    break;
                case "*":
                    result = num1Int * num2Int;
                    break;
                case "/":
                    if(num2Int == 0)
                    {
                        Toast.makeText(context, "0으로 나눌수 없습니다." ,toastDuration).show();
                        textResult.setText("");
                        return false;
                    }
                    result = num1Int / num2Int;
                    break;
                case "%":

                    if(num2Int == 0)
                    {
                        Toast.makeText(context, "0으로 나눌수 없습니다." ,toastDuration).show();
                        textResult.setText("");
                        return false;
                    }

                    result = num1Int % num2Int;
                    break;
            }

            textResult.setText("계산 결과 : " + result.toString());

            return true;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = getApplicationContext();
        toastDuration = Toast.LENGTH_SHORT;

        setTitle("초간단 계산기");

        edit1 = (EditText) findViewById(R.id.Edit1);
        edit2 = (EditText) findViewById(R.id.Edit2);
        btnAdd = (Button) findViewById(R.id.BtnAdd);
        btnSub = (Button) findViewById(R.id.BtnSub);
        btnMul = (Button) findViewById(R.id.BtnMul);
        btnDiv = (Button) findViewById(R.id.BtnDiv);
        btnRemain = (Button) findViewById(R.id.BtnRemain);

        textResult = (TextView) findViewById(R.id.TextResult);

        btnAdd.setOnTouchListener(onCalcTouchEvent);
        btnSub.setOnTouchListener(onCalcTouchEvent);
        btnMul.setOnTouchListener(onCalcTouchEvent);
        btnDiv.setOnTouchListener(onCalcTouchEvent);
        btnRemain.setOnTouchListener(onCalcTouchEvent);
    }
}
