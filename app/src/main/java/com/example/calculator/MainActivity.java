package com.example.calculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;
import java.util.Queue;

public class MainActivity extends AppCompatActivity {



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menumain, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.menuItem_calculator) {

        } else if (itemId == R.id.menuItem_length) {
            Intent intent = new Intent(MainActivity.this, LengthConverter.class);
            startActivity(intent);
        } else if (itemId == R.id.menuItem_volume) {
            Intent intent = new Intent(MainActivity.this, VolumeConverter.class);
            startActivity(intent);
        } else if (itemId == R.id.menuItem_numberSystem) {
            Intent intent = new Intent(MainActivity.this, NumberConverter.class);
            startActivity(intent);
        } else {
            Intent intent = new Intent(MainActivity.this, Help.class);
            startActivity(intent);
        }
        return true;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calculator);


        Button btn_0 = (Button) findViewById(R.id.button_0);
        Button btn_1 = (Button) findViewById(R.id.button_1);
        Button btn_2 = (Button) findViewById(R.id.button_2);
        Button btn_3 = (Button) findViewById(R.id.button_3);
        Button btn_4 = (Button) findViewById(R.id.button_4);
        Button btn_5 = (Button) findViewById(R.id.button_5);
        Button btn_6 = (Button) findViewById(R.id.button_6);
        Button btn_7 = (Button) findViewById(R.id.button_7);
        Button btn_8 = (Button) findViewById(R.id.button_8);
        Button btn_9 = (Button) findViewById(R.id.button_9);
        //+,-,*,/
        Button btn_add = (Button) findViewById(R.id.button_add);
        Button btn_sub = (Button) findViewById(R.id.button_sub);
        Button btn_mul = (Button) findViewById(R.id.button_mul);
        Button btn_div = (Button) findViewById(R.id.button_div);
        //"mod" "^" "."   "  π"
        Button btn_pow = (Button) findViewById(R.id.button_pow);
        Button btn_MOD = (Button) findViewById(R.id.button_mod);
        Button btn_point = (Button) findViewById(R.id.button_point);
        Button btn_pi = (Button) findViewById(R.id.button_pi);
        //sin,cos,tan
        Button btn_sin = (Button) findViewById(R.id.button_sin);
        Button btn_cos = (Button) findViewById(R.id.button_cos);
        Button btn_tan = (Button) findViewById(R.id.button_tan);
        //  (,  )
        Button btn_left = (Button) findViewById(R.id.button_left);
        Button btn_right = (Button) findViewById(R.id.button_right);
        //  Clear,BackSpace,Equal
        Button btn_clear = (Button) findViewById(R.id.button_clear);
        Button btn_back = (Button) findViewById(R.id.button_back);
        Button btn_result = (Button) findViewById(R.id.button_equal);



        final TextView textView = (TextView) findViewById(R.id.textView);



        btn_left.setOnClickListener(new Click());
        btn_right.setOnClickListener(new Click());
        btn_sin.setOnClickListener(new Click());
        btn_cos.setOnClickListener(new Click());
        btn_tan.setOnClickListener(new Click());
        btn_pi.setOnClickListener(new Click());
        btn_pow.setOnClickListener(new Click());



        btn_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textView.setText("");
            }
        });

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str = (String) textView.getText();
                if (textView.getText().length() > 0)
                    textView.setText(((String) textView.getText()).subSequence(0,
                            textView.getText().length() - 1));
            }
        });

        btn_result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = textView.getText().toString();
                Log.i(this.getClass().getName(), String.format("the expression: %s", str));
                String result = Expression.calculator(Expression.StringQue(str));

                if (result == "error") {
                    Log.w("Calculator", "current activity");
                } else

                    textView.setText(result);
            }
        });


        //number
        btn_0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String temp = textView.getText().toString();
                if (temp.length() == 0)
                    textView.setText(textView.getText() + "0");
                else {
                    if (temp.charAt(temp.length() - 1) == ')')
                        textView.setText(textView.getText() + "*0");
                    else
                        textView.setText(textView.getText() + "0");
                }
            }
        });

        btn_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String temp = textView.getText().toString();
                if (temp.length() == 0)
                    textView.setText(textView.getText() + "1");
                else {
                    if (temp.charAt(temp.length() - 1) == ')')
                        textView.setText(textView.getText() + "1");
                    else
                        textView.setText(textView.getText() + "1");
                }
            }
        });
        btn_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String temp = textView.getText().toString();
                if (temp.length() == 0)
                    textView.setText(textView.getText() + "2");
                else {
                    if (temp.charAt(temp.length() - 1) == ')')
                        textView.setText(textView.getText() + "*2");
                    else
                        textView.setText(textView.getText() + "2");
                }
            }
        });
        btn_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String temp = textView.getText().toString();
                if (temp.length() == 0)
                    textView.setText(textView.getText() + "3");
                else {
                    if (temp.charAt(temp.length() - 1) == ')')
                        textView.setText(textView.getText() + "*3");
                    else
                        textView.setText(textView.getText() + "3");
                }
            }
        });
        btn_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String temp = textView.getText().toString();
                if (temp.length() == 0)
                    textView.setText(textView.getText() + "4");
                else {
                    if (temp.charAt(temp.length() - 1) == ')')
                        textView.setText(textView.getText() + "*4");
                    else
                        textView.setText(textView.getText() + "4");
                }
            }
        });
        btn_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String temp = textView.getText().toString();
                if (temp.length() == 0)
                    textView.setText(textView.getText() + "5");
                else {
                    if (temp.charAt(temp.length() - 1) == ')')
                        textView.setText(textView.getText() + "*5");
                    else
                        textView.setText(textView.getText() + "5");
                }
            }
        });
        btn_6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String temp = textView.getText().toString();
                if (temp.length() == 0)
                    textView.setText(textView.getText() + "6");
                else {
                    if (temp.charAt(temp.length() - 1) == ')')
                        textView.setText(textView.getText() + "*6");
                    else
                        textView.setText(textView.getText() + "6");
                }
            }
        });
        btn_7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String temp = textView.getText().toString();
                if (temp.length() == 0)
                    textView.setText(textView.getText() + "7");
                else {
                    if (temp.charAt(temp.length() - 1) == ')')
                        textView.setText(textView.getText() + "*7");
                    else
                        textView.setText(textView.getText() + "7");
                }
            }
        });
        btn_8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String temp = textView.getText().toString();
                if (temp.length() == 0)
                    textView.setText(textView.getText() + "8");
                else {
                    if (temp.charAt(temp.length() - 1) == ')')
                        textView.setText(textView.getText() + "*8");
                    else
                        textView.setText(textView.getText() + "8");
                }
            }
        });
        btn_9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String temp = textView.getText().toString();
                if (temp.length() == 0)
                    textView.setText(textView.getText() + "9");
                else {
                    if (temp.charAt(temp.length() - 1) == ')')
                        textView.setText(textView.getText() + "*9");
                    else
                        textView.setText(textView.getText() + "9");
                }
            }
        });



        btn_point.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str = textView.getText().toString();
                int i = 0;
                if (str.length() == 0)
                    textView.setText(textView.getText() + "0.");
                else {
                    if (str.charAt(str.length() - 1) == '+' || str.charAt(str.length() - 1) == '-'
                            || str.charAt(str.length() - 1) == '*' ||
                            str.charAt(str.length() - 1) == '/' || str.charAt(str.length() - 1) == '(')
                        textView.setText(textView.getText() + "0.");
                    else {
                        if (str.charAt(str.length() - 1) == ')')
                            textView.setText(textView.getText() + "*0.");
                        else {
                            for (i = str.length() - 1; i > 0; i--) {
                                if (str.charAt(i) == '(' || str.charAt(i) == ')' || str.charAt(i) == '+'
                                        || str.charAt(i) == '-' ||
                                        str.charAt(i) == '*' || str.charAt(i) == '/')
                                    break;
                            }
                            String temp = str.substring(i, str.length());
                            if (temp.indexOf(".") > 0)
                                Toast.makeText(MainActivity.this,
                                        "非法输入", Toast.LENGTH_LONG).show();
                            else
                                textView.setText(textView.getText() + ".");
                        }
                    }
                }
            }
        });


        //add,sub,mulity,divide
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String temp = textView.getText().toString();
                if (temp.length() == 0)
                    textView.setText("0");
                if (temp.charAt(temp.length() - 1) == '+' ||
                        temp.charAt(temp.length() - 1) == '-' ||
                        temp.charAt(temp.length() - 1) == '*' ||
                        temp.charAt(temp.length() - 1) == '/' ||
                        temp.charAt(temp.length() - 1) == '(')
                    Toast.makeText(MainActivity.this, "非法输入", Toast.LENGTH_LONG).show();
                else
                    textView.setText(textView.getText() + "+");
            }
        });

        btn_sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String temp = textView.getText().toString();
                if (temp.length() == 0)
                    textView.setText("0");

                if (temp.length() == 0
                        || temp.charAt(temp.length() - 1) == '+'
                        || temp.charAt(temp.length() - 1) == '-'
                        || temp.charAt(temp.length() - 1) == '*'
                        || temp.charAt(temp.length() - 1) == '/'
                        || temp.charAt(temp.length() - 1) == '(')
                    Toast.makeText(MainActivity.this, "非法输入", Toast.LENGTH_LONG).show();
                else
                    textView.setText(textView.getText() + "-");
            }
        });

        btn_mul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String temp = textView.getText().toString();
                if (temp.length() == 0)
                    textView.setText("1");
                if (temp.charAt(temp.length() - 1) == '+'
                        || temp.charAt(temp.length() - 1) == '-'
                        || temp.charAt(temp.length() - 1) == '*'
                        || temp.charAt(temp.length() - 1) == '/'
                        || temp.charAt(temp.length() - 1) == '(')
                    Toast.makeText(MainActivity.this, "非法输入", Toast.LENGTH_LONG).show();
                else
                    textView.setText(textView.getText() + "*");
            }
        });

        btn_div.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String temp = textView.getText().toString();
                if (temp.length() == 0)
                    textView.setText("0");
                temp = textView.getText().toString();
                if (temp.charAt(temp.length() - 1) == '+'
                        || temp.charAt(temp.length() - 1) == '-'
                        || temp.charAt(temp.length() - 1) == '*'
                        || temp.charAt(temp.length() - 1) == '/'
                        || temp.charAt(temp.length() - 1) == '('
                        || temp.charAt(temp.length() - 1) == '%')
                    Toast.makeText(MainActivity.this, "非法输入", Toast.LENGTH_LONG).show();
                else
                    textView.setText(textView.getText() + "/");
            }
        });

        btn_MOD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String temp = textView.getText().toString();
                if (temp.length() == 0)
                    textView.setText("0");

                if (temp.charAt(temp.length() - 1) == '+'
                        || temp.charAt(temp.length() - 1) == '-'
                        || temp.charAt(temp.length() - 1) == '*'
                        || temp.charAt(temp.length() - 1) == '/'
                        || temp.charAt(temp.length() - 1) == '('
                        || temp.charAt(temp.length() - 1) == '%')
                    Toast.makeText(MainActivity.this, "非法输入", Toast.LENGTH_LONG).show();
                else
                    textView.setText(textView.getText() + "%");
            }
        });

    }






    class  Click implements View.OnClickListener {
        TextView txtResult = findViewById(R.id.textView);

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.button_sin:
                {
                    double sin;
                    String temp = txtResult.getText().toString();
                    String result;
                    result = Expression.calculator(Expression.StringQue(temp));
                    if(result == "error"){

                    }else{
                        sin = Math.sin(Double.valueOf(result));
                        txtResult.setText(String.valueOf(sin));
                    }

                }
                break;
                case R.id.button_cos:{
                    double cos;
                    String temp = txtResult.getText().toString();
                    String result;
                    result = Expression.calculator(Expression.StringQue(temp));
                    if(result == "error"){
                    }else{
                        cos= Math.cos(Double.valueOf(result));
                        txtResult.setText(String.valueOf(cos));
                    }

                }
                break;
                case R.id.button_tan:{
                    double tan;
                    String temp = txtResult.getText().toString();
                    String result;
                    result = Expression.calculator(Expression.StringQue(temp));
                    if(result == "error"){
                    }else{
                        tan= Math.tan(Double.valueOf(result));
                        txtResult.setText(String.valueOf(tan));
                    }

                }
                break;

                case R.id.button_left: {
                    String temp = txtResult.getText().toString();
                    if (temp.length() == 0)
                        txtResult.setText(txtResult.getText() + "(");
                    else {
                        if (temp.charAt(temp.length() - 1) > '1'
                                && temp.charAt(temp.length() - 1) < '9'
                                || temp.charAt(temp.length() - 1) == '.')
                            txtResult.setText(txtResult.getText() + "*(");
                        else
                            txtResult.setText(txtResult.getText() + "(");
                    }

                }
                break;

                case R.id.button_right: {
                    String temp = txtResult.getText().toString();
                    if (temp.length() == 0) {
                        Toast.makeText(MainActivity.this, "非法输入", Toast.LENGTH_LONG).show();
                        //txtResult.setText(txtResult.getText() + ")");
                    }
                    else {
                        if (temp.charAt(temp.length() - 1) == '+'
                                || temp.charAt(temp.length() - 1) == '-'
                                || temp.charAt(temp.length() - 1) == '*'
                                || temp.charAt(temp.length() - 1) == '/'
                                || temp.charAt(temp.length() - 1) == '('
                                || temp.charAt(temp.length() - 1) == '%') {
                            Toast.makeText(MainActivity.this, "非法输入", Toast.LENGTH_LONG).show();
                        }
                        else
                            txtResult.setText(txtResult.getText() + ")");
                    }

                }
                break;
                case R.id.button_pi:{
                    String temp = txtResult.getText().toString();
                    if(temp.length() == 0
                            || temp.charAt(temp.length() - 1) == '+'
                            || temp.charAt(temp.length() - 1) == '-'
                            || temp.charAt(temp.length() - 1) == '*'
                            || temp.charAt(temp.length() - 1) == '/'
                            || temp.charAt(temp.length() - 1) == '('
                            || temp.charAt(temp.length() - 1) == '%')
                        txtResult.setText(txtResult.getText()+"3.14");
                    else
                        txtResult.setText(txtResult.getText()+"*3.14");
                }
                break;
                case R.id.button_pow:{
                    double xx;
                    String temp = txtResult.getText().toString();
                    String result;
                    result = Expression.calculator(Expression.StringQue(temp));
                    System.out.println(result);
                    if(result == "error"){

                    }else{
                        xx= Math.pow(Double.valueOf(result),2);
                        txtResult.setText(String.valueOf(xx));
                    }

                }
                break;

            }

        }
    }




}











