package com.example.calculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class Help extends AppCompatActivity
{

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menumain,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
        int itemId = item.getItemId();
        if(itemId == R.id.menuItem_calculator)
        {
            Intent intent = new Intent(Help.this, MainActivity.class);
            startActivity(intent);
        }
        else if(itemId == R.id.menuItem_length)
        {
            Intent intent = new Intent(Help.this, LengthConverter.class);
            startActivity(intent);
        }
        else if(itemId == R.id.menuItem_volume)
        {
            Intent intent = new Intent(Help.this, VolumeConverter.class);
            startActivity(intent);
        }
        else if(itemId == R.id.menuItem_numberSystem)
        {
            Intent intent = new Intent(Help.this, NumberConverter.class);
            startActivity(intent);
        }
        else
        {

        }
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.help);
    }
}
