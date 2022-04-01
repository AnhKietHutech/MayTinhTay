package com.ak.maytinhtay;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.Stack;

public class MainActivity extends AppCompatActivity {

    private TextView mangHinh;
    private Button button1, button2, button3, button4, button5, button6, button7, button8, button9, button0, cong, tru, nhan, chia, ac, del, bang, cham;
    private String x="";
    private String kq = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        anhxa();

        event();

    }

    private void event() {

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                x+=button1.getText();
                hienThi();
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                x+=button2.getText();
                hienThi();
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                x+=button3.getText();
                hienThi();
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                x+=button4.getText();
                hienThi();
            }
        });

        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                x+=button5.getText();
                hienThi();
            }
        });

        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                x+=button6.getText();
                hienThi();
            }
        });

        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                x+=button7.getText();
                hienThi();
            }
        });

        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                x+=button8.getText();
                hienThi();
            }
        });

        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                x+=button9.getText();
                hienThi();
            }
        });

        button0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                x+=button0.getText();
                hienThi();
            }
        });

        cham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                x+=cham.getText();
                hienThi();
            }
        });

        cong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                x= x + " " + cong.getText() + " ";
                hienThi();
            }
        });

        tru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                x = x + " " + tru.getText() + " ";
                hienThi();
            }
        });

        nhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                x = x + " " + nhan.getText() + " ";
                hienThi();
            }
        });

        chia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                x = x + " " + chia.getText() + " ";
                hienThi();
            }
        });

        ac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                x="";
                hienThi();
            }
        });

        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!x.isEmpty()){
                    x=x.substring(0,x.length()-1);
                    hienThi();
                }
            }
        });

        bang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EvaluatePostfix(infixtoPostfix(mangHinh.getText().toString()));
                if (isANum(kq)){
                    mangHinh.setText(String.valueOf(kq));
                }
            }
        });

    }

    private void hienThi(){
        mangHinh.setText(x.trim());
    }

    private void anhxa() {

        mangHinh = findViewById(R.id.mangHinh);
        button0 = findViewById(R.id.khong);
        button1 = findViewById(R.id.mot);
        button2 = findViewById(R.id.hai);
        button3 = findViewById(R.id.ba);
        button4 = findViewById(R.id.bon);
        button5 = findViewById(R.id.nam);
        button6 = findViewById(R.id.sau);
        button7 = findViewById(R.id.bay);
        button8 = findViewById(R.id.tam);
        button9 = findViewById(R.id.chin);

        cham = findViewById(R.id.cham);

        cong = findViewById(R.id.cong);
        tru = findViewById(R.id.tru);
        nhan = findViewById(R.id.nhan);
        chia = findViewById(R.id.chia);

        ac = findViewById(R.id.AC);
        del = findViewById(R.id.del);
        bang = findViewById(R.id.ketqua);

    }

    private boolean isANum(String x){
        try{
            Double.parseDouble(x);
        }catch (NumberFormatException e){
            return false;
        }
        return true;
    }


    private String infixtoPostfix(String infix)
    {
        String postfix = "0 ";
        Stack<String> S;
        String x, token;
        // i-index of infix,j-index of postfix
        S = new Stack<>();
        infix = infix.trim();
        String[] cvInfix = infix.split(" ");
        for (int i = 0; i<cvInfix.length; i++)
        {
            token = cvInfix[i];
            if (isANum(token)){
                postfix= postfix + token + " ";
            }
            else
            if (token.equals("(")) {
                S.push(token);
            }
            else
            if (token.equals(")")) {
                while (!S.isEmpty() ) {
                    x = S.pop();
                    if (!x.equals("(")){
                        postfix = postfix + x + " ";
                    }
                    //postfix = postfix + x + " ";
                }
            }
            else
            {
                while (!S.isEmpty() && (precedence(token) <= precedence(S.peek())))
                {
                    System.out.println(S.peek());
                    x = S.pop();
                    postfix = postfix + x + " ";
                }
                S.push(token);
            }
        }

        while (!S.isEmpty())
        {
            x = S.pop();
            postfix = postfix + x + " ";
        }
        return postfix.trim() + " 0 +";

        //postfix[j] = '\0';
    }

    private void EvaluatePostfix(String postfix) {
        Stack<Double> stack = new Stack<>();
        postfix = postfix.trim();

        String[] cvPostfix = postfix.split(" ");

        for (int i = 0; i < cvPostfix.length; i++) {
            String x = cvPostfix[i];
            if (isANum(x))
                stack.push(Double.parseDouble(x));
            else {
                Double z ;
                Double y ;

                try {
                    z = stack.pop();
                    y = stack.pop();
                }catch (java.util.EmptyStackException e){
                    mangHinh.setText("Loi ");
                    kq = "Loi";
                    return;

                }

                switch (x) {
                    case "+":
                        y += z;
                        break;
                    case "-":
                        y -= z;
                        break;
                    case "*":
                        y *= z;
                        break;
                    case "/":
                        y /= z;
                        break;
                    case "%":
                        y %= z;
                        break;
                }
                stack.push(y);
            }
        }

        kq = String.valueOf(stack.pop());

        return;

    }

    static int precedence(String x)
    {
        if (x.equals("("))
            return 0;
        if (x.equals("+") || x.equals("-"))
            return 1 ;
        if (x.equals("*") || x.equals("/") || x.equals("%"))
            return 2;
        return 3;
    }

}