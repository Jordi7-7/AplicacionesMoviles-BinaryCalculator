package com.example.binarycalculator;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

//    BinaryCalc calculadora = new BinaryCalc("1000101", "010110");
    TextView lblAnswer;
    TextView lblText;
    String concat;
    String num1;
    String num2;
    String op;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


//        Log.d("Calculator", calculadora.toString());

        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);


        lblAnswer = findViewById(R.id.lblAnswer);
        lblText = findViewById(R.id.lblText);


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


    }
    public void calcular (View v) {
        Button boton = (Button) v;
        String textoBoton = boton.getText().toString();

        if(textoBoton.equals("C")) {
            concat = "";
            lblAnswer.setText(concat);
            lblText.setText(concat);

            return ;
        }

        if(textoBoton.equals("+") || textoBoton.equals("-") || textoBoton.equals("*") || textoBoton.equals("/")) {
            num1 = lblAnswer.getText().toString();
            op = textoBoton;
            concat = num1+op;
            lblText.setText(concat);
            lblAnswer.setText("");

            return;
        }

        if(textoBoton.equals("=")) {

            num2 = lblAnswer.getText().toString();
            concat = num1+ op + num2+'=';

            if ((concat.indexOf('+') == concat.lastIndexOf('+') ||
                    concat.indexOf('-') == concat.lastIndexOf('-') ||
                    concat.indexOf('*') == concat.lastIndexOf('*') ||
                    concat.indexOf('/') == concat.lastIndexOf('/')) &&
                    !concat.endsWith("+") &&
                    !concat.endsWith("-") &&
                    !concat.endsWith("*") &&
                    !concat.endsWith("/")) {

                // Tu lógica aquí

                lblText.setText(concat);
                BinaryCalc calculadora = new BinaryCalc(num1, num2);
                if(op.equals("+")) {
                    lblAnswer.setText(calculadora.Sum());
                }else if(op.equals("-")) {
                    lblAnswer.setText(calculadora.Subtract());
                }else if(op.equals("*")) {
                    lblAnswer.setText(calculadora.Multiply());
                }else if(op.equals("/")) {
                    lblAnswer.setText(calculadora.Divide());
                }
                return;

            }else{
                //error operacion no valida

            }

        }


        concat = lblAnswer.getText().toString()+textoBoton;
        lblAnswer.setText(concat);
    }
}