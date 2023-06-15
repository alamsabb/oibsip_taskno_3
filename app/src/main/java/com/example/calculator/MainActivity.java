package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;
import com.google.android.material.button.MaterialButton;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView res,sol;
    MaterialButton btnC,btnclose,btnopen,btndiv;
    MaterialButton btn7,btn8,btn9,btnmuk;
    MaterialButton btn4,btn5,btn6,btnplus;
    MaterialButton btn1,btn2,btn3,btnminus;
    MaterialButton btnac,btn0,btndot,btnequal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        res=findViewById(R.id.res);
        sol=findViewById(R.id.sol);
        btnC=findViewById(R.id.btnC);
        btnC.setOnClickListener(this);
        btnclose=findViewById(R.id.btnclose);
        btnclose.setOnClickListener(this);
        btnopen=findViewById(R.id.btnopen);
        btnopen.setOnClickListener(this);
        btndiv=findViewById(R.id.btndiv);
        btn7=findViewById(R.id.btn7);
        btn8=findViewById(R.id.btn8);
        btn9=findViewById(R.id.btn9);
        btnmuk=findViewById(R.id.btnmul);
        btn4=findViewById(R.id.btn4);
        btn5=findViewById(R.id.btn5);
        btn6=findViewById(R.id.btn6);
        btnplus=findViewById(R.id.btnplus);
        btn1=findViewById(R.id.btn1);
        btn2=findViewById(R.id.btn2);
        btn3=findViewById(R.id.btn3);
        btnminus=findViewById(R.id.btnminus);
        btnac=findViewById(R.id.btnac);
        btn0=findViewById(R.id.btn0);
        btndot=findViewById(R.id.btndot);
        btnequal=findViewById(R.id.btnequal);
        btndiv.setOnClickListener(this);
        btn7.setOnClickListener(this);
        btn8.setOnClickListener(this);
        btn9.setOnClickListener(this);
        btnmuk.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btnplus.setOnClickListener(this);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btnminus.setOnClickListener(this);
        btnac.setOnClickListener(this);
        btn0.setOnClickListener(this);
        btndot.setOnClickListener(this);
        btnequal.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        MaterialButton button=(MaterialButton) v;
        String buttontext=button.getText().toString();
        String calcu=sol.getText().toString();
        if(buttontext.equals("AC")){
            sol.setText("");
            res.setText("0");
            return;
        }
        if(buttontext.equals("=")){
            sol.setText(res.getText());
            return;
        }
        if(buttontext.equals("C")){
            if(calcu.isEmpty()){
                sol.setText("");
            }else {
                calcu = calcu.substring(0, calcu.length() - 1);
            }
        }else {
            calcu=calcu+buttontext;
        }

        sol.setText(calcu);
        String finalres;
        if(!calcu.isEmpty()) {
             finalres= result(calcu);
            if(!finalres.equals("err")){
                res.setText(finalres);
            }
        }

    }
    String result(String data){
        try {
            Context context=Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable=context.initStandardObjects();
            String finalres=context.evaluateString(scriptable,data,"Javascript",1,null).toString();
            if(finalres.endsWith(".0")){
                finalres=finalres.replace(".0","");
            }else {
                double value = Double.parseDouble(finalres);

// Format the value to have 2 decimal places
                DecimalFormat decimalFormat = new DecimalFormat("#0.00");
                finalres = decimalFormat.format(value);
            }


            return finalres;
        }catch (Exception e){
            return "err";
        }
    }
}