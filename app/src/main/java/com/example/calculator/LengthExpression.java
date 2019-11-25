package com.example.calculator;

enum LengthUnit //in 英寸 ft英尺
{
    mm,cm,dm,m,km,in,ft;
}


public class LengthExpression
{
    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    public LengthUnit getStrUnit() {
        return strUnit;
    }

    public void setstrUnit(LengthUnit strUnit) {
        this.strUnit = strUnit;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
    public LengthUnit getResUnit() {
        return resUnit;
    }

    public void setResUnit(LengthUnit resUnit) {
        this.resUnit = resUnit;
    }

    private String str;
    private LengthUnit strUnit;
    private String result;
    private LengthUnit resUnit;




    public LengthExpression() {
        str = new String("0");
        strUnit = LengthUnit.m;
        result = new String("0");
        resUnit = LengthUnit.m;
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
            result = Double.toString(a);
            return;
        }
        if(strUnit == LengthUnit.mm)
        {
            temp = a / 1000;
        }
        else if(strUnit == LengthUnit.cm)
        {
            temp = a / 100;
        }
        else if(strUnit == LengthUnit.dm)
        {
            temp = a / 10;
        }
        else if(strUnit == LengthUnit.m)
        {
            temp = a;
        }
        else if(strUnit == LengthUnit.km)
        {
            temp = a * 1000;
        }
        else if(strUnit == LengthUnit.in)
        {
            temp = a * 0.0254;
        }
        else
        {
            temp = a * 0.3048;
        }
        if(resUnit == LengthUnit.mm)
        {
            b = temp * 1000;
        }
        else if(resUnit == LengthUnit.cm)
        {
            b = temp * 100;
        }
        else if(resUnit == LengthUnit.dm)
        {
            b = temp * 10;
        }
        else if(resUnit == LengthUnit.m)
        {
            b = temp;
        }
        else if(resUnit == LengthUnit.km)
        {
            b = temp / 1000;
        }
        else if(resUnit == LengthUnit.in)
        {
            b = temp * 39.3700787;
        }
        else
        {
            b = temp * 3.2808399;
        }
        result = Double.toString(b);
    }

}
