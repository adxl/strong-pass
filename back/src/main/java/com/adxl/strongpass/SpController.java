package com.adxl.strongpass;

import jdk.nashorn.internal.runtime.JSONFunctions;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
public class SpController
{

    Random r=new Random();

    @GetMapping("/")
    public String getPassword()
    {
        for (int i=0; i<100; i++)
        {
            System.out.print(generateOneSymbol());
        }
        return generateAlphaNumString(100);
    }


    private String generateAlphaString(int length)
    {
        StringBuilder string=new StringBuilder();
        do
        {
            string.append(generateOneLetter());
        } while (string.length()<length);
        return string.toString();
    }

    private String generateAlphaNumString(int length)
    {
        StringBuilder string=new StringBuilder();
        do
        {
            if (r.nextBoolean())
                string.append(generateOneLetter());
            else
                string.append(generateOneDigit());
        } while (string.length()<length);
        return string.toString();
    }

    private char generateOneSymbol()
    {
        char[] symbols = {'#','@','$','%','*','+','-','?','!'};
        return symbols[(r.nextInt(symbols.length))];
    }

    private char generateOneLetter()
    {
        if (r.nextBoolean())
            return Character.toUpperCase((char)(r.nextInt(26)+'a'));
        return (char)(r.nextInt(26)+'a');
    }

    private char generateOneDigit()
    {
        return Character.forDigit((r.nextInt(9)+1), 10);
    }



}
