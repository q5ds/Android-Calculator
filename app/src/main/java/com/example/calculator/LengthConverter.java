package com.example.calculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;



public class LengthConverter extends AppCompatActivity
{

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menumain, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
        int itemId = item.getItemId();
        if(itemId == R.id.menuItem_calculator)
        {
            Intent intent = new Intent(LengthConverter.this, MainActivity.class);
            startActivity(intent);
        }
        else if(itemId == R.id.menuItem_length)
        {

        }
        else if(itemId == R.id.menuItem_volume)
        {
            Intent intent = new Intent(LengthConverter.this, VolumeConverter.class);
            startActivity(intent);
        }
        else if(itemId == R.id.menuItem_numberSystem)
        {
            Intent intent = new Intent(LengthConverter.this, NumberConverter.class);
            startActivity(intent);
        }
        else
        {
            Intent intent = new Intent(LengthConverter.this, Help.class);
            startActivity(intent);
        }
        return true;
    }


    LengthExpression lengthExpression = new LengthExpression();
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.converter);
        if(lengthExpression == null)
            lengthExpression = new LengthExpression();

        final TextView inputValue = (TextView)findViewById(R.id.textView_inputValue);
        final TextView outputValue = (TextView)findViewById(R.id.textView_outputValue);
        synchronize(inputValue, outputValue);

        Spinner spinner_input = (Spinner) findViewById(R.id.spinner_input);
        Spinner spinner_output = (Spinner) findViewById(R.id.spinner_output);

        LengthUnit strUnit = lengthExpression.getStrUnit();
        LengthUnit resUnit = lengthExpression.getResUnit();
        if(strUnit == LengthUnit.mm)   //和后台数据保持一致
        {
            spinner_input.setSelection(0);
        }
        else if(strUnit == LengthUnit.cm)
        {
            spinner_input.setSelection(1);
        }
        else if(strUnit == LengthUnit.dm)
        {
            spinner_input.setSelection(2);
        }
        else if(strUnit == LengthUnit.m)
        {
            spinner_input.setSelection(3);
        }
        else if(strUnit == LengthUnit.km)
        {
            spinner_input.setSelection(4);
        }
        else if(strUnit == LengthUnit.in)
        {
            spinner_input.setSelection(5);
        }
        else
        {
            spinner_input.setSelection(6);
        }
        if(resUnit == LengthUnit.mm)
        {
            spinner_output.setSelection(0);
        }
        else if(resUnit == LengthUnit.cm)
        {
            spinner_output.setSelection(1);
        }
        else if(resUnit == LengthUnit.dm)
        {
            spinner_output.setSelection(2);
        }
        else if(resUnit == LengthUnit.m)
        {
            spinner_output.setSelection(3);
        }
        else if(resUnit == LengthUnit.km)
        {
            spinner_output.setSelection(4);
        }
        else if(resUnit == LengthUnit.in)
        {
            spinner_output.setSelection(5);
        }
        else
        {
            spinner_output.setSelection(6);
        }
        spinner_input.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l)
            {
                if(i == 0)
                {
                    lengthExpression.setstrUnit((LengthUnit.mm));
                }
                else if(i == 1)
                {
                    lengthExpression.setstrUnit(LengthUnit.cm);
                }
                else if(i == 2)
                {
                    lengthExpression.setstrUnit(LengthUnit.dm);
                }
                else if(i == 3)
                {
                    lengthExpression.setstrUnit(LengthUnit.m);
                }
                else if(i == 4)
                {
                    lengthExpression.setstrUnit(LengthUnit.km);
                }
                else if(i == 5)
                {
                    lengthExpression.setstrUnit(LengthUnit.in);
                }
                else
                {
                    lengthExpression.setstrUnit(LengthUnit.ft);
                }
                synchronize(inputValue, outputValue);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView)
            {

            }
        });
        spinner_output.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l)
            {
                if(i == 0)
                {
                    lengthExpression.setResUnit(LengthUnit.mm);
                }
                else if(i == 1)
                {
                    lengthExpression.setResUnit(LengthUnit.cm);
                }
                else if(i == 2)
                {
                    lengthExpression.setResUnit(LengthUnit.dm);
                }
                else if(i == 3)
                {
                    lengthExpression.setResUnit(LengthUnit.m);
                }
                else if(i == 4)
                {
                    lengthExpression.setResUnit(LengthUnit.km);
                }
                else if(i == 5)
                {
                    lengthExpression.setResUnit(LengthUnit.in);
                }
                else
                {
                    lengthExpression.setResUnit(LengthUnit.ft);
                }
                synchronize(inputValue, outputValue);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView)
            {

            }
        });

        initializeNumber(R.id.btn_0, inputValue, outputValue);
        initializeNumber(R.id.btn_1, inputValue, outputValue);
        initializeNumber(R.id.btn_2, inputValue, outputValue);
        initializeNumber(R.id.btn_3, inputValue, outputValue);
        initializeNumber(R.id.btn_4, inputValue, outputValue);
        initializeNumber(R.id.btn_5, inputValue, outputValue);
        initializeNumber(R.id.btn_6, inputValue, outputValue);
        initializeNumber(R.id.btn_7, inputValue, outputValue);
        initializeNumber(R.id.btn_8, inputValue, outputValue);
        initializeNumber(R.id.btn_9, inputValue, outputValue);

        Button btn_point = (Button) findViewById(R.id.btn_point);

        btn_point.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str = inputValue.getText().toString();
                int i = 0;
                String temp = str.substring(i, str.length());
                if (temp.indexOf(".") > 0)
                    Toast.makeText(LengthConverter.this,
                            "非法输入", Toast.LENGTH_LONG).show();
                else
                    inputValue.setText(inputValue.getText() + ".");

            }
        });

        Button button = (Button) findViewById(R.id.btn_backspace);
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if(inputValue.getText().length() > 1) {
                    inputValue.setText(inputValue.getText().subSequence(0, inputValue.getText().length() - 1));
                    lengthExpression.setStr(inputValue.getText().toString());
                    lengthExpression.calculate();
                    outputValue.setText(lengthExpression.getResult());
                }
                else if(inputValue.getText().length() == 1)
                {
                    inputValue.setText("0");
                    outputValue.setText("0");
                }
            }
        });
        button = (Button) findViewById(R.id.btn_clear);
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                inputValue.setText("0");
                outputValue.setText("0");
            }
        });

    }
    private void initializeNumber(int id, final TextView inputValue, final TextView outputValue)
    {
        final Button button = (Button) findViewById(id);
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                StringBuffer sb = new StringBuffer(inputValue.getText());
                if(sb.toString().equals("0"))
                    sb = new StringBuffer("");
                sb.append(button.getText());
                Log.w("LengthUnitConverter", sb.toString());
                System.out.println(sb.toString());
                lengthExpression.setStr(sb.toString());
                synchronize(inputValue, outputValue);

            }
        });

    }

    private void synchronize(TextView inputValue, TextView outputValue)   //保持数据同步
    {
        lengthExpression.calculate();
        inputValue.setText(lengthExpression.getStr());
        outputValue.setText(lengthExpression.getResult());
    }


}
