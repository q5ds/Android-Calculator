package com.example.calculator;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import  java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;
import java.math.*;
import  java.util.*;


public class Expression {



    private static boolean prierator(String pri) {    //用来判断程序当中的操作符是否正确
        if (pri.equals("+") || pri.equals("-") || pri.equals("*") || pri.equals("/")
                || pri.equals("%") || pri.equals("^")
                || pri.equals("sin") || pri.equals("cos") || pri.equals("tan")) {
            return true;
        } else {
            return false;
        }
    }

    private static int Priority(String pri){           //操作符的优先级的赋予
        if (pri.equals("+") || pri.equals("-")) {
            return 1;
        } else if (pri.equals("*") || pri.equals("/") || pri.equals("%")) {
            return 2;
        } else if (pri.equals("^")) {
            return 3;
        } else if (pri.equals("sin") || pri.equals("cos") || pri.equals("tan")) {
            return 4;
        } else {
            return -1;
        }
    }

    public  static  Queue<String> StringQue(String str){        //将字符串转换成队列的形式
        str = str.replaceAll("","");
        Queue<String> que = new LinkedList<String>();
        int i;
        int j=0;
        for (i = 0; i < str.length(); ++i){
            if ((str.charAt(i) < '0' || str.charAt(i) > '9') && str.charAt(i) != '.'){

                if (j != i)
                    que.add(str.substring(j,i));
                //如果该数字是个复数,并且第一个是-或者其它的是-
                if((i == 0 && str.charAt(i) == '-') ||
                        (str.charAt(i) == '-' && (str.charAt(i - 1) < '0'
                                || str.charAt(i - 1) > '9')))
                    j = i;
                else {
                    que.add("" + str.charAt(i));
                    j = i + 1;
                }
            }
        }
        if (!(str.charAt(str.length() - 1) < '0' || str.charAt(str.length() - 1) > '9')) {
            que.add(str.substring(j, str.length()));
        }
        return que;
    }

    public  static Queue<String> infixtosuffix(Queue<String> que){
        Stack<String> temp = new Stack<>();
        Queue<String> result = new LinkedList<String>();


        // 将中缀转换成后缀表达式
        String str;
        while (!que.isEmpty()) {
            str = que.poll();
            // 如果是右括号，则弹出堆栈直到左括号
            if (str.equals(")")) {
                String str1 = temp.pop();
                while (!str1.equals("(")) {
                    result.add(str1);
                    str1 = temp.pop();
                }
                // 如果是左括号，只需将其推入操作符堆栈
            } else if (str.equals("(")) {
                temp.push(str);

                //必须保证栈中的运算符的优先级是递增的
            } else if (prierator(str)) {
                while (!temp.isEmpty() && !temp.peek().equals("(")
                        && Priority(temp.peek()) >= Priority(str)) {
                    result.add(temp.pop());
                }
                temp.push(str);

                //是数字直接进队列
            } else {
                result.add(str);
            }
        }
        //最后将符号栈清空
        while (!temp.isEmpty()) {
            result.add(temp.pop());
        }

        return result;
    }




    public static String calculator(Queue<String> que) {
        String str;
        Stack<String> stack = new Stack<String>();
        que=infixtosuffix(que);

        while (!que.isEmpty()) {
            try {

                Double.parseDouble(que.peek());
                stack.push(que.poll());

            } catch (NumberFormatException e) {
                if (que.peek().equals("sin")) {
                    stack.push(Math.sin(Double.parseDouble(stack.pop())) + "");
                } else if (que.peek().equals("cos")) {
                    stack.push(Math.cos(Double.parseDouble(stack.pop())) + "");
                } else if (que.peek().equals("tan")) {
                    stack.push(Math.tan(Double.parseDouble(stack.pop())) + "");
                } else {
                    //当使用-或/的时候，运算顺序是栈顶的元素充当被除数或被减数
                    Double opNumb = Double.parseDouble(stack.pop());
                    Double opNuma = Double.parseDouble(stack.pop());
                    if (que.peek().equals("+")) {
                        stack.push(opNuma + opNumb + "");
                    } else if (que.peek().equals("-")) {
                        stack.push(opNuma - opNumb + "");
                    } else if (que.peek().equals("*")) {
                        stack.push(opNuma * opNumb + "");
                    } else if (que.peek().equals("/")) {
                        stack.push(opNuma / opNumb + "");
                    } else if (que.peek().equals("^")) {
                        stack.push(Math.pow(opNuma, opNumb) + "");
                    } else if (que.peek().equals("%")) {
                        stack.push(opNuma % opNumb + "");
                    }
                }
                que.poll();
            }
        }
        str = Double.parseDouble(stack.peek())+"";
        return  str;
    }

}
