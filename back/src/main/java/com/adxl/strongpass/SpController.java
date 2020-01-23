package com.adxl.strongpass;

import jdk.nashorn.internal.runtime.JSONFunctions;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
public class SpController
{

    Random r=new Random();

    @GetMapping("/")
    public String getPassword()
    {
        System.out.println(generateAlphaString(100));
        System.out.println(generateAlphaNumString(100));
        System.out.println(generateAlphaStringWithSymbols(100));
        System.out.println(generateAlphaNumStringWithSymbols(100));
        return "";
    }

    @PostMapping("/generate")
    public String generatePassword(@RequestBody Request request)
    {
        int length=request.getLength();

        if (!request.hasNumbers() && !request.hasSymbols())
            return generateAlphaString(length);
        if (!request.hasNumbers() && request.hasSymbols())
            return generateAlphaStringWithSymbols(length);
        if (request.hasNumbers() && !request.hasSymbols())
            return generateAlphaNumString(length);
        else
            return generateAlphaNumStringWithSymbols(length);
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

    private String generateAlphaStringWithSymbols(int length)
    {
        StringBuilder string=new StringBuilder();
        do
        {
            if (r.nextBoolean())
                string.append(generateOneSymbol());
            else
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

    private String generateAlphaNumStringWithSymbols(int length)
    {
        StringBuilder string=new StringBuilder();
        do
        {
            if (r.nextBoolean())
                string.append(generateOneLetter());
            else string.append(generateOneDigit());
            if (r.nextBoolean())
                string.append(generateOneSymbol());
        } while (string.length()<length);

        return string.toString();
    }

    private char generateOneSymbol()
    {
        char[] symbols={'#', '@', '$', '%', '*', '+', '-', '?', '!'};
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
