package com.example.calculator;


enum VolumeUnit
{
    cm3, m3, ml, l;
}


public class VolumeExpression
{


    private String str;
    private VolumeUnit strUnit;
    private String result;
    private VolumeUnit resUnit;


    public String getstr()
    {
        return str;
    }

    public void setstr(String str)
    {
        this.str = str;
    }

    public VolumeUnit getstrUnit()
    {
        return strUnit;
    }

    public void setstrUnit(VolumeUnit strUnit)
    {
        this.strUnit = strUnit;
    }

    public String getResult()
    {
        return result;
    }

    public void setResult(String result)
    {
        this.result = result;
    }

    public VolumeUnit getResUnit()
    {
        return resUnit;
    }

    public void setResUnit(VolumeUnit resUnit)
    {
        this.resUnit = resUnit;
    }

    public VolumeExpression()
    {
        str = new String("0");
        strUnit = VolumeUnit.cm3;
        result = new String("0");
        resUnit = VolumeUnit.cm3;
    }

    public void calculate()
    {
        double a,temp,b;
        if(str.charAt(str.length() - 1) == '.')
            a = Double.valueOf(str.substring(0, str.length() - 1));
        else
            a = Double.valueOf(str);
        if(strUnit == resUnit)
        {
            result = format(Double.toString(a));
            return;
        }
        if(strUnit == VolumeUnit.cm3)
            temp = a;
        else if(strUnit == VolumeUnit.m3)
            temp = a * Math.pow(10, 6);
        else if(strUnit == VolumeUnit.ml)
            temp = a;
        else
            temp = a * 1000;
        if(resUnit == VolumeUnit.cm3)
            b = temp;
        else if(resUnit == VolumeUnit.m3)
            b = temp * Math.pow(10, -6);
        else if(resUnit == VolumeUnit.ml)
            b = temp;
        else
            b = temp / 1000;
        result = format(Double.toString(b));
    }

    private String format(String str)
    {
        boolean judge = false;
        for(int a = 0; a < str.length(); a++)
        {
            if(str.charAt(a) == '.')
            {
                judge = true;
                break;
            }
        }
        if(!judge)
            return str;
        int a;
        for(a = str.length() - 1; a >=0 && str.charAt(a) == '0'; a--);
        if(str.charAt(a) == '.')
            a--;
        return str.substring(0, a + 1);
    }



}
