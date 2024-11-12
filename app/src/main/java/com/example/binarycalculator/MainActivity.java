package com.example.binarycalculator;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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


    // Método para mostrar el mensaje de error y restablecer los valores
    private void showErrorAndReset(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        lblAnswer.setText("0");
        lblText.setText("");
        num1 = "";
        num2 = "";
        op = "";
    }

    public void calcular(View v) {
        Button boton = (Button) v;
        String textoBoton = boton.getText().toString();

        // Verifica si la operacion ya termino
        if (lblText.getText().toString().endsWith("=")) {
            concat = "";
            lblAnswer.setText("0");
            lblText.setText("");
            num1 = "";
            num2 = "";
            op = "";
        }

        // Limpia la pantalla
        if (textoBoton.equals("C")) {
            concat = "";
            lblAnswer.setText("0");
            lblText.setText("");
            num1 = "";
            num2 = "";
            op = "";
            return;
        }

        // Verifica si el botón es un operador (+, -, *, /)
        if (textoBoton.equals("+") || textoBoton.equals("-") || textoBoton.equals("*") || textoBoton.equals("/")) {
            if (lblAnswer.getText().toString().isEmpty() || lblAnswer.getText().toString().equals("0")) {
                showErrorAndReset("Debe ingresar un número antes de la operación.");
                return;
            }
            if (lblText.getText().toString().endsWith("+") || lblText.getText().toString().endsWith("-") || lblText.getText().toString().endsWith("*") || lblText.getText().toString().endsWith("/")) {
                Toast.makeText(this, "Solo se puede realizar una operación a la vez.", Toast.LENGTH_SHORT).show();

                return;
            }

            num1 = lblAnswer.getText().toString();
            op = textoBoton;
            concat = num1 + op;
            lblText.setText(concat);
            lblAnswer.setText("0");
            return;
        }

        // Verifica si el botón es el de igualdad (=)
        if (textoBoton.equals("=")) {
            num2 = lblAnswer.getText().toString();

            if (num2.isEmpty() || op.isEmpty()) {
                showErrorAndReset("Operación incompleta.");
                return;
            }

            if (op.equals("/") && num2.equals("0")) {
                showErrorAndReset("Error: División por cero.");
                return;
            }

            concat = num1 + op + num2 + '=';
            lblText.setText(concat);

            BinaryCalc calculadora = new BinaryCalc(num1, num2);
            switch (op) {
                case "+":
                    lblAnswer.setText(calculadora.Sum());
                    break;
                case "-":
                    lblAnswer.setText(calculadora.Subtract());
                    break;
                case "*":
                    lblAnswer.setText(calculadora.Multiply());
                    break;
                case "/":
                    lblAnswer.setText(calculadora.Divide());
                    break;
                default:
                    showErrorAndReset("Operación no válida.");
                    break;
            }
            return;
        }

        // Evita 0 inicial sin un 1 delante
        if (textoBoton.equals("0") && lblAnswer.getText().toString().equals("0")) {
//            showErrorAndReset("Un número binario no puede comenzar con 0.");
            Toast.makeText(this, "Un número binario no puede comenzar con 0.", Toast.LENGTH_SHORT).show();
            return;
        }

        if (textoBoton.equals("1") && lblAnswer.getText().toString().equals("0")) {

            lblAnswer.setText("");
        }

        // Concatenar el número ingresado
        concat = lblAnswer.getText().toString() + textoBoton;
        lblAnswer.setText(concat);
    }


}