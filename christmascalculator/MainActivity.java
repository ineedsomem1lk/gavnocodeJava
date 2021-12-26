package com.example.christmascalculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button btn0,btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9,btnAdd,btnMultiply,btnDivision,btnMinus,btnPercent,btnC,btnDel,btnEqual,btnDot;
    TextView inputTxtVw, opTxtVw, resTxtVw;

    String op, operand1, operand2,res;
    Double number1, number2;
    boolean isDot;

    MediaPlayer mp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn0 = (Button)findViewById(R.id.zeroButton);
        btn1 = (Button)findViewById(R.id.firstButton);
        btn2 = (Button)findViewById(R.id.secondButton);
        btn3 = (Button)findViewById(R.id.thirdButton);
        btn4 = (Button)findViewById(R.id.fourButton);
        btn5 = (Button)findViewById(R.id.fiveButton);
        btn6 = (Button)findViewById(R.id.sixButton);
        btn7 = (Button)findViewById(R.id.sevenButton);
        btn8 = (Button)findViewById(R.id.eightButton);
        btn9 = (Button)findViewById(R.id.nineButton);

        btnAdd = (Button)findViewById(R.id.addButton);
        btnMultiply = (Button)findViewById(R.id.mulButton);
        btnDivision = (Button)findViewById(R.id.divButton);
        btnMinus = (Button)findViewById(R.id.minusButton);
        btnPercent = (Button)findViewById(R.id.procentButton);
        btnC = (Button)findViewById(R.id.cButton);
        btnDel = (Button)findViewById(R.id.delButton);
        btnEqual = (Button)findViewById(R.id.equalButton);
        btnDot = (Button)findViewById(R.id.dotButton);

        resTxtVw = (TextView)findViewById(R.id.resTextView);
        inputTxtVw =(TextView)findViewById(R.id.inputTextView);
        opTxtVw = (TextView)findViewById(R.id.opTextView);
        isDot = false;
    }


    @SuppressLint("SetTextI18n")
    public void dotOnClick(View v) {
        if (!isDot) {
            if (inputTxtVw.getText().equals("")) {
                inputTxtVw.setText("0.");
            } else {
                inputTxtVw.setText(inputTxtVw.getText() + ".");
            }
            isDot = true;
        }
    }

    @SuppressLint({"SetTextI18n", "DefaultLocale"})
    public void equalOnClick(View v){
        String result;
        if (op == null) {
            onCreateDialog();
        } else if (inputTxtVw.getText().equals("")) {
            onCreateDialog();
        } else if ((op.equals("+") || op.equals("-") || op.equals("*") || op.equals("/")) && operand1.equals("")) {
            onCreateDialog();
        } else {
            switch (op) {
                case "^":
                    setOperands();
                    double x=number1,y=number2;
                    result = String.format("%8.2f", pow(x,y)).replace(',', '.');
                    resTxtVw.setText(number1+" ^ "+number2+" = " + result);
                    inputTxtVw.setText( result+ "");
                    break;
                case "sqrt":
                    operand1 = inputTxtVw.getText().toString();
                    number1 = Double.parseDouble((operand1));
                    result = String.format("%8.2f", Math.sqrt(number1)).replace(',', '.');
                    resTxtVw.setText("√ " + number1+ " ="+ result);
                    inputTxtVw.setText(result + "");
                    op = null;
                    opTxtVw.setText(null);
                    break;
                case "+":
                    setOperands();
                    result = String.format("%8.2f", number1+number2).replace(',', '.');
                    resTxtVw.setText(number1+" + "+number2+" = "+result);
                    inputTxtVw.setText(result + "");
                    break;
                case "-":
                    setOperands();
                    result = String.format("%8.2f", number1-number2).replace(',', '.');
                    resTxtVw.setText(number1+" - "+number2+" = "+result);
                    inputTxtVw.setText(result + "");
                    break;
                case "*":
                    setOperands();
                    result = String.format("%8.2f", number1*number2).replace(',', '.');
                    resTxtVw.setText(number1+" × "+number2+" = "+result);
                    inputTxtVw.setText(result + "");
                    break;
                case "/":
                    setOperands();
                    if(number2==0){number2=1.0;}
                    result = String.format("%8.2f", number1/number2).replace(',', '.');
                    resTxtVw.setText(number1+" ÷ "+number2+" = "+result);
                    inputTxtVw.setText(result + "");
                    break;
            }
        }
        if(mp == null) {
            mp = MediaPlayer.create(this,R.raw.song);
        }
        mp.start();
    }

    public void setOperands(){
        operand2 = inputTxtVw.getText().toString();
        number1 = Double.parseDouble(operand1);
        number2 = Double.parseDouble(operand2);
        op = null;
        opTxtVw.setText(null);
    }

    public double pow(double x,double y)
    {
        if(y==0){
            return 1;
        }
        return x*pow(x,y-1);
    }

    @SuppressLint("SetTextI18n")
    public void zeroOnClick(View v) {
        inputTxtVw.setText(inputTxtVw.getText() + "0");
    }

    @SuppressLint("SetTextI18n")
    public void firstOnClick(View v) {
        inputTxtVw.setText(inputTxtVw.getText() + "1");
    }

    @SuppressLint("SetTextI18n")
    public void secondOnClick(View v) {
        inputTxtVw.setText(inputTxtVw.getText() + "2");
    }

    @SuppressLint("SetTextI18n")
    public void thridOnClick(View v) {
        inputTxtVw.setText(inputTxtVw.getText() + "3");
    }

    @SuppressLint("SetTextI18n")
    public void fourdOnClick(View v) {
        inputTxtVw.setText(inputTxtVw.getText() + "4");
    }

    @SuppressLint("SetTextI18n")
    public void fiveOnClick(View v) {
        inputTxtVw.setText(inputTxtVw.getText() + "5");
    }

    @SuppressLint("SetTextI18n")
    public void sixOnClick(View v) {
        inputTxtVw.setText(inputTxtVw.getText() + "6");
    }

    @SuppressLint("SetTextI18n")
    public void sevenOnClick(View v) {
        inputTxtVw.setText(inputTxtVw.getText() + "7");
    }

    @SuppressLint("SetTextI18n")
    public void eightOnClick(View v) {
        inputTxtVw.setText(inputTxtVw.getText() + "8");
    }

    @SuppressLint("SetTextI18n")
    public void nineOnClick(View v) {
        inputTxtVw.setText(inputTxtVw.getText() + "9");
    }

    public void multiplyOnClick(View v) {
        op = "*";
        operand1 = inputTxtVw.getText().toString();
        inputTxtVw.setText(null);
        opTxtVw.setText("×");
        isDot = false;
    }

    public void cOnClick(View v){
        inputTxtVw.setText(null);
        opTxtVw.setText(null);
        resTxtVw.setText(null);
        operand1 = null;
        operand2 = null;
        op = null;
        isDot = false;
        res = null;
        mp.pause();
    }

    public void delOnClick(View v) {
        if (inputTxtVw.getText().equals("")) {
            inputTxtVw.setText(null);
        } else {
            String s = inputTxtVw.getText().toString();
            if(s.charAt(inputTxtVw.getText().length() - 1) == '.') {
                isDot = false;
                inputTxtVw.setText(inputTxtVw.getText().subSequence(0, inputTxtVw.getText().length() - 1));

            } else {
                inputTxtVw.setText(inputTxtVw.getText().subSequence(0, inputTxtVw.getText().length() - 1));
            }
        }
    }

    public void sqrtOnClick(View v) {
        op = "sqrt";
        inputTxtVw.setText(null);
        isDot = false;
        opTxtVw.setText("√");
    }

    public void divOnClick(View v) {
        op = "/";
        operand1 = inputTxtVw.getText().toString();
        inputTxtVw.setText(null);
        opTxtVw.setText("÷");
        isDot = false;
    }
    public void powOnClick(View v) {
        op = "^";
        operand1 = inputTxtVw.getText().toString();
        inputTxtVw.setText(null);
        isDot = false;
        opTxtVw.setText("xⁿ");
    }
    public void minusOnClick(View v) {
        op = "-";
        operand1 = inputTxtVw.getText().toString();
        inputTxtVw.setText(null);
        opTxtVw.setText("-");
        isDot = false;
    }
    public void addOnClick(View v) {
        op = "+";
        operand1 = inputTxtVw.getText().toString();
        inputTxtVw.setText(null);
        opTxtVw.setText("+");
        isDot = false;
    }

    @NonNull
    public void onCreateDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("oh,here we go again!")
                .setMessage("WRITE OPERAND/OPERATION ,PLEASE")
                .setPositiveButton("Oke", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog aDialog = builder.create();aDialog.show();
    }
}

