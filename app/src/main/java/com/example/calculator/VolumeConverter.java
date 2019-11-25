package com.example.calculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


public class VolumeConverter extends AppCompatActivity
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
            Intent intent = new Intent(VolumeConverter.this, MainActivity.class);
            startActivity(intent);
        }
        else if(itemId == R.id.menuItem_length)
        {
            Intent intent = new Intent(VolumeConverter.this, LengthConverter.class);
            startActivity(intent);
        }
        else if(itemId == R.id.menuItem_volume)
        {

        }
        else if(itemId == R.id.menuItem_numberSystem)
        {
            Intent intent = new Intent(VolumeConverter.this, NumberConverter.class);
            startActivity(intent);
        }
        else
        {
            Intent intent = new Intent(VolumeConverter.this, Help.class);
            startActivity(intent);
        }
        return true;
    }

    VolumeExpression volumeexpression = new VolumeExpression();
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.converter);

        if(volumeexpression == null)
            volumeexpression = new VolumeExpression();

        final TextView inputValue = (TextView)findViewById(R.id.textView_inputValue);
        final TextView outputValue = (TextView)findViewById(R.id.textView_outputValue);
        synchronize(inputValue, outputValue);

        String[] arr = new String[]{"立方厘米（cm³）", "立方米（m³）", "毫升（ml）", "升（l）"};
        //数组适配器，将长度换算代替成上面的体积换算。
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, arr);
        Spinner spinner_input = (Spinner) findViewById(R.id.spinner_input);
        Spinner spinner_output = (Spinner) findViewById(R.id.spinner_output);
        spinner_input.setAdapter(arrayAdapter);
        spinner_output.setAdapter(arrayAdapter);

        VolumeUnit strUnit = volumeexpression.getstrUnit();
        VolumeUnit resUnit = volumeexpression.getResUnit();

        if(strUnit == VolumeUnit.cm3)
            spinner_input.setSelection(0);
        else if(strUnit == VolumeUnit.m3)
            spinner_input.setSelection(1);
        else if(strUnit == VolumeUnit.ml)
            spinner_input.setSelection(2);
        else
            spinner_input.setSelection(3);
        if(resUnit == VolumeUnit.cm3)
            spinner_output.setSelection(0);
        else if(resUnit == VolumeUnit.m3)
            spinner_output.setSelection(1);
        else if(resUnit == VolumeUnit.ml)
            spinner_output.setSelection(2);
        else
            spinner_output.setSelection(3);

        spinner_input.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l)
            {
                if(i == 0)
                    volumeexpression.setstrUnit(VolumeUnit.cm3);
                else if(i == 1)
                    volumeexpression.setstrUnit(VolumeUnit.m3);
                else if(i == 2)
                    volumeexpression.setstrUnit(VolumeUnit.ml);
                else
                    volumeexpression.setstrUnit(VolumeUnit.l);
                synchronize(inputValue, outputValue);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) { }
        });

        spinner_output.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l)
            {
                if(i == 0)
                    volumeexpression.setResUnit(VolumeUnit.cm3);
                else if(i == 1)
                    volumeexpression.setResUnit(VolumeUnit.m3);
                else if(i == 2)
                    volumeexpression.setResUnit(VolumeUnit.ml);
                else
                    volumeexpression.setResUnit(VolumeUnit.l);
                synchronize(inputValue, outputValue);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) { }
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
                    Toast.makeText(VolumeConverter.this,
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
                if(inputValue.getText().length() > 1)
                {
                    volumeexpression.setstr(inputValue.getText().toString().substring(0, inputValue.getText().length() - 1));
                    synchronize(inputValue, outputValue);
                }
                else if(inputValue.getText().length() == 1)
                {
                    volumeexpression.setstr("0");
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
                volumeexpression.setstr("0");
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
                volumeexpression.setstr(sb.toString());
                synchronize(inputValue, outputValue);
            }
        });
    }

    private void synchronize(TextView inputValue, TextView outputValue)
    {
        volumeexpression.calculate();
        inputValue.setText(volumeexpression.getstr());
        outputValue.setText(volumeexpression.getResult());
    }

}
