package com.lambdaschool.piggybank.models;

import javax.persistence.*;

@Entity
@Table(name = "coins")

public class Coin
{
    @Id
    @GenerateValue(strategy = GenerationType.AUTO)
    private long coinid;

    private String name;
    private String namesPlural;
    private double value;
    private int numberOf;

    public Coin()
    {
        //default with no values
    }

    public Coin (
          String name,
          String namesPlural,
          double value,
          int numberOf)
    {
        this.name = name;
        this.namesPlural = namesPlural;
        this.value = value;
        this.numberOf = numberOf;
    }

    public long getCoinId()
    {
        return coinid;
    }

    public void setCoinId(long coinid)
    {
        this.coinid = coinid;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getNamesPlural()
    {
        return namesPlural;
    }

    public void setNamesPlural(String names)
    {
        this.namesPlural = namesPlural;
    }

    public double getValue()
    {
        return value;
    }

    public void setValue(double value)
    {
        this.value = value;
    }

    public int getNumberOf()
    {
        return numberOf;
    }

    public void setNumberOf(int num)
    {
        this.numberOf = numberOf;
    }

    @Override
    public String toString()
    {
        return "Coin{" +
                "name=" + name +
                "namesPlural=" + namesPlural +
                "value=" + value +
                "Number of=" + numberOf +
                "}";
    }


}
