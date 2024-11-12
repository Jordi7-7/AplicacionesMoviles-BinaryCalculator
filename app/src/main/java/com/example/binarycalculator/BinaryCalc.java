package com.example.binarycalculator;

public class BinaryCalc {

    private String num1;  // Número binario 1
    private String num2;  // Número binario 2

    public BinaryCalc(String num1, String num2) {
        this.num1 = num1;
        this.num2 = num2;
    }

    // Método que convierte un número decimal a binario
    public String DecToBin(int decimal) {
        return Integer.toBinaryString(decimal); // Convierte de decimal a binario
    }

    // Método que convierte un número binario a decimal
    public int BinToDec(String binary) {
        return Integer.parseInt(binary, 2); // Convierte de binario a decimal
    }

    // Método para sumar dos números binarios
    public String Sum() {
        int decimal1 = BinToDec(num1); // Convierte el binario num1 a decimal
        int decimal2 = BinToDec(num2); // Convierte el binario num2 a decimal
        int sum = decimal1 + decimal2; // Suma los decimales
        return DecToBin(sum);          // Convierte la suma a binario
    }

    // Método para restar dos números binarios
    public String Subtract() {
        int decimal1 = BinToDec(num1); // Convierte el binario num1 a decimal
        int decimal2 = BinToDec(num2); // Convierte el binario num2 a decimal
        int difference = decimal1 - decimal2; // Resta los decimales
        return DecToBin(difference);          // Convierte la diferencia a binario
    }

    // Método para multiplicar dos números binarios
    public String Multiply() {
        int decimal1 = BinToDec(num1); // Convierte el binario num1 a decimal
        int decimal2 = BinToDec(num2); // Convierte el binario num2 a decimal
        int product = decimal1 * decimal2; // Multiplica los decimales
        return DecToBin(product);          // Convierte el producto a binario
    }

    // Método para dividir dos números binarios
    public String Divide() {
        int decimal1 = BinToDec(num1); // Convierte el binario num1 a decimal
        int decimal2 = BinToDec(num2); // Convierte el binario num2 a decimal
        if (decimal2 == 0) {
            throw new ArithmeticException("Error: División por cero."); // Maneja la división por cero
        }
        int quotient = decimal1 / decimal2; // Divide los decimales
        return DecToBin(quotient);          // Convierte el cociente a binario
    }

    // Métodos setter para num1 y num2
    public void setNum1(String num1) {
        this.num1 = num1;
    }

    public void setNum2(String num2) {
        this.num2 = num2;
    }

    @Override
    public String toString() {
        return "BinaryCalc{" +
                "num1=" + num1 +
                ", num2=" + num2 +
                '}';
    }

}
