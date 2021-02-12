package com.lambdaschool.piggybank.models;

import javax.persistence.*;

@Entity
@Table(name = "coins")

public class Coin
{
    @Id @GeneratedValue(strategy = GenerationType.AUTO) private long coinid;

    private String name;
    private String nameplural;
    private double value;
    private int quantity;

    public Coin()
    {
        //default with no values
    }

    public Coin (
          String name,
          String nameplural,
          double value,
          int quantity)
    {
        this.name = name;
        this.nameplural = nameplural;
        this.value = value;
        this.quantity = quantity;
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
        return nameplural;
    }

    public void setNamesPlural(String names)
    {
        this.nameplural = nameplural;
    }

    public double getValue()
    {
        return value;
    }

    public void setValue(double value)
    {
        this.value = value;
    }

    public int getQuantity()
    {
        return quantity;
    }

    public void setQuantity(int num)
    {
        this.quantity = quantity;
    }

    @Override
    public String toString()
    {
        return "Coin{" +
                "name=" + name +
                "namesPlural=" + nameplural +
                "value=" + value +
                "Quantity=" + quantity +
                "}";
    }


}
