package com.adxl.strongpass;

public class Request
{
    private int length;
    private boolean numbers;
    private boolean symbols;

    public Request(int length,boolean numbers, boolean symbols)
    {
        this.length=length;
        this.numbers=numbers;
        this.symbols=symbols;
    }

    public int getLength()
    {
        return length;
    }

    public boolean hasNumbers()
    {
        return numbers;
    }

    public boolean hasSymbols()
    {
        return symbols;
    }

    @Override
    public String toString()
    {
        return "Request{"+
                "length="+length+
                ", numbers="+numbers+
                ", symbols="+symbols+
                '}';
    }
}
