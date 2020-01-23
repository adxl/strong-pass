package com.adxl.strongpass;

import jdk.nashorn.internal.runtime.JSONFunctions;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@RestController
@CrossOrigin
public class SpController
{

    Random r=new Random();

    @PostMapping("/generate")
    public ResponseEntity<String> generatePassword(@RequestBody Request request)
    {
        System.out.println(request);

        String result;
        int length=request.getLength();

        if (!request.hasNumbers() && !request.hasSymbols())
            result= generateAlphaString(length);
        else if (!request.hasNumbers() && request.hasSymbols())
            result= generateAlphaStringWithSymbols(length);
        else if (request.hasNumbers() && !request.hasSymbols())
            result= generateAlphaNumString(length);
        else
            result= generateAlphaNumStringWithSymbols(length);

        return ResponseEntity.ok().body("{\"result\":\""+result+"\"}");
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
