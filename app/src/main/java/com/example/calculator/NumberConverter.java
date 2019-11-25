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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;


public class NumberConverter extends AppCompatActivity
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
            Intent intent = new Intent(NumberConverter.this, MainActivity.class);
            startActivity(intent);
        }
        else if(itemId == R.id.menuItem_length)
        {
            Intent intent = new Intent(NumberConverter.this, LengthConverter.class);
            startActivity(intent);
        }
        else if(itemId == R.id.menuItem_volume)
        {
            Intent intent = new Intent(NumberConverter.this, VolumeConverter.class);
            startActivity(intent);
        }
        else if(itemId == R.id.menuItem_numberSystem)
        {

        }
        else
        {
            Intent intent = new Intent(NumberConverter.this, Help.class);
            startActivity(intent);
        }
        return true;
    }

    NumberExpression numberexpression = new NumberExpression();
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.number);

        final TextView inputValue = (TextView)findViewById(R.id.textView_inputValue);
        final TextView outputValue = (TextView)findViewById(R.id.textView_outputValue);

        if(numberexpression == null)
            numberexpression = new NumberExpression();
        synchronize(inputValue, outputValue);

        String[] arr = new String[]{"二进制", "八进制", "十进制", "十六进制"};
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, arr);
        Spinner spinner_input = (Spinner) findViewById(R.id.spinner_input);
        Spinner spinner_output = (Spinner) findViewById(R.id.spinner_output);
        spinner_input.setAdapter(arrayAdapter);
        spinner_output.setAdapter(arrayAdapter);

        if(numberexpression.getResSystem() == Number.BIN)
            spinner_input.setSelection(0);
        else if(numberexpression.getStrSystem() == Number.OCT)
            spinner_input.setSelection(1);
        else if(numberexpression.getStrSystem() == Number.DEC)
            spinner_input.setSelection(2);
        else
            spinner_input.setSelection(3);
        if(numberexpression.getResSystem() == Number.BIN)
            spinner_output.setSelection(0);
        else if(numberexpression.getResSystem() == Number.OCT)
            spinner_output.setSelection(1);
        else if(numberexpression.getResSystem() == Number.DEC)
            spinner_output.setSelection(2);
        else
            spinner_output.setSelection(3);


        spinner_input.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l)
            {
                if(i == 0)
                    numberexpression.setStrSystem(Number.BIN);
                else if(i == 1)
                    numberexpression.setStrSystem(Number.OCT);
                else if(i == 2)
                    numberexpression.setStrSystem(Number.DEC);
                else
                    numberexpression.setStrSystem(Number.HEX);
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
                    numberexpression.setResSystem(Number.BIN);
                else if(i == 1)
                    numberexpression.setResSystem(Number.OCT);
                else if(i == 2)
                    numberexpression.setResSystem(Number.DEC);
                else
                    numberexpression.setResSystem(Number.HEX);
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
        initializeNumber(R.id.btn_A, inputValue, outputValue);
        initializeNumber(R.id.btn_B, inputValue, outputValue);
        initializeNumber(R.id.btn_C, inputValue, outputValue);
        initializeNumber(R.id.btn_D, inputValue, outputValue);
        initializeNumber(R.id.btn_E, inputValue, outputValue);
        initializeNumber(R.id.btn_F, inputValue, outputValue);

        Button button = (Button) findViewById(R.id.btn_backspace);
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if(inputValue.getText().length() > 1)
                {
                    numberexpression.setStr(inputValue.getText().toString().substring(0, inputValue.getText().length() - 1));
                    synchronize(inputValue, outputValue);
                }
                else if(inputValue.getText().length() == 1)
                {
                    numberexpression.setStr("0");
                    synchronize(inputValue, outputValue);
                }
            }
        });
        button = (Button) findViewById(R.id.btn_clear);
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                numberexpression.setStr("0");
                synchronize(inputValue, outputValue);
            }
        });
    }
    private void initializeNumber(int id, final TextView inputValue, final TextView outputValue)
    {
        final Button button = (Button) findViewById(id);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StringBuffer sb = new StringBuffer(inputValue.getText());
                if (sb.toString().equals("0"))
                    sb = new StringBuffer("");
                sb.append(button.getText());
                Log.w("LengthUnitConverter", sb.toString());
                System.out.println(sb.toString());
                numberexpression.setStr(sb.toString());
                synchronize(inputValue, outputValue);
            }
        });
    }

    private void synchronize(TextView inputValue, TextView outputValue)
    {
        numberexpression.calculate();
        inputValue.setText(numberexpression.getStr());
        outputValue.setText(numberexpression.getResult());
    }
}
