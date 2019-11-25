package com.example.calculator;


enum Number {  //二进制，八进制，十进制，十六进制
    BIN,OCT,DEC,HEX
}


public class NumberExpression {
    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    public Number getStrSystem() {
        return strSystem;
    }

    public void setStrSystem(Number strSystem) {
        this.strSystem = strSystem;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Number getResSystem() {
        return resSystem;
    }

    public void setResSystem(Number resSystem) {
        this.resSystem = resSystem;
    }

    private String str;
    private Number strSystem;
    private String result;
    private Number resSystem;



    public NumberExpression()
    {
        this.str = new String("0");
        this.strSystem = Number.DEC;
        this.result = new String("0");
        this.resSystem = Number.DEC;
    }


    public void calculate()
    {
        try
        {
            Integer temp;
            if(strSystem == Number.BIN)
                temp = Integer.parseInt(str, 2);
            else if(strSystem == Number.OCT)
                temp = Integer.parseInt(str, 8);
            else if(strSystem == Number.DEC)
                temp = Integer.valueOf(str);
            else
                temp = Integer.parseInt(str, 16);
            if(resSystem == Number.BIN)
                result = Integer.toBinaryString(temp.intValue());
            else if(resSystem == Number.OCT)
                result = Integer.toOctalString(temp.intValue());
            else if(resSystem == Number.DEC)
                result = Integer.toString(temp.intValue());
            else
                result = Integer.toHexString(temp.intValue());
        }
        catch(Exception e)
        {
            str = new String("0");
            result = new String("错误");
        }
    }


}
