package Exercitiul1;
import java.util.Stack;

public class FormaPoloneza
{
    public static void main(String[] args){
        String calcul="1+2+4*(3+4)+3+4";
        calcul=CreareFromaPoloneza(calcul);
        System.out.println("Forma poloneză: " + calcul);
        System.out.println("Rezultat: " + Rezultat(calcul));
    }

    static int PrioritateOperanzi(char ch)
    {
        switch (ch)
        {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
        }
        return -1;
    }

    static String CreareFromaPoloneza(String exp)
    {
        String rez = new String();
        int length = exp.length();
        Stack<Character> s = new Stack<Character>();
        for (int i = 0; i < length; ++i)
        {
            char c = exp.charAt(i);
            if (Character.isDigit(c))
            {
                rez = rez + c + ' ';
            }
            else if (c == '(')
            {
                s.push(c);
            }
            else if (c == ')')
            {
                while((!s.empty()) && s.peek() != '(')
                {
                    rez = rez + s.pop() + ' ';
                }
                if(s.empty())
                {
                    return "Expresie invalida";
                }
                s.pop();
            }
            else
            {
                while((!s.empty()) && PrioritateOperanzi(c) <= PrioritateOperanzi(s.peek()))
                {
                    rez = rez + s.pop() + ' ';
                }
                s.push(c);
            }
        }

        while(!s.empty())
        {
            if(s.peek() == '(')
            {
                return "Expresie invalida";
            }
            rez = rez + s.pop() + ' ';
        }

        return rez.trim();
    }

    static double Rezultat(String exp)
    {
        Stack<Double> s = new Stack<Double>();
        int len = exp.length();
        for(int i = 0; i < len; ++i)
        {
            char c = exp.charAt(i);
            if (Character.isDigit(c))
            {
                s.push((double) (c - '0'));
            }
            else if (c == ' ')
            {
                continue;
            }
            else
            {
                double a = s.pop();
                double b = s.pop();
                switch (c)
                {
                    case '+':
                        s.push(a + b);
                        break;
                    case '-':
                        s.push(b - a);
                        break;
                    case '*':
                        s.push(a * b);
                        break;
                    case '/':
                        s.push(b / a);
                        break;
                }
            }
        }
        return s.pop();
    }
}