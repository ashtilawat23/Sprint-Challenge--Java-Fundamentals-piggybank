package com.lambdaschool.piggybank.controllers;

// ------ Auto Imports ------
import com.lambdaschool.piggybank.models.Coin;
import com.lambdaschool.piggybank.repositories.CoinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@RestController // tells Spring that there will be endpoints
public class CoinController
{
    @Autowired
    CoinRepository coinrepo;

    private List<Coin> findCoins(List<Coin> myList, CheckCoin tester)
    {
        List<Coin> tempList = new ArrayList<>();
        for(Coin c : myList)
        {
            if(tester.test(c))
            {
                tempList.add(c);
            }
        }
        return tempList;
    }

    // -------- Web Endpoints ----------

    // http://localhost:2019/total

    @GetMapping(value = "/total", produces = "application/json")
    public ResponseEntity<?> findTotalCoins()
    {
        List<Coin> myList = new ArrayList<>();
        coinrepo.findAll().iterator().forEachRemaining(myList::add);


        for(Coin c : myList)
        {
            // Find total value of every coin summed together
            // getQuantity() * getName()
            if(c.getQuantity() == 1)
            {
                System.out.println(c.getQuantity() + " " + c.getName());
            }
            else
            {
                System.out.println(c.getQuantity() + " " + c.getNamesPlural());
            }
        }

        double total = 0.0;
        for(Coin c : myList)
        {
            total = total + (c.getQuantity() * c.getValue());
        }
        System.out.println("The piggy bank holds " + total);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // Stretch:
    // http://localhost:2019/money/{amount}

    @GetMapping(value = "/money/{amount}", produces = "application/json")
    public ResponseEntity<?> removeCoins(@PathVariable double amount)
    {
        // This is creating an Array List of coins
        List<Coin> myList = new ArrayList<>();
        coinrepo.findAll().iterator().forEachRemaining(myList::add);
        // problem is that when it sorts it is putting 10 pennies before 3 nickles
        myList.sort((c1, c2) -> (c1.getQuantity() + c2.getQuantity()));

        // For loop derives total value of myList
        double total = 0.0;
        for(Coin c : myList)
        {
            total = total + (c.getQuantity() * c.getValue());

        }
        System.out.println("Line 89 ------------> " + total); // will be total in piggy bank

        // Below this line is logic to substract from total and myList via amount
        double amountTotal = amount;
        // Check to see that there is enough money to make a withdrawal
        if(total < amountTotal)
        {
            System.out.println("Not enough funds in piggy bank for this transaction.");
        }
        else
        {
            for(Coin c : myList)
            {
                if(amountTotal >= c.getValue() * c.getQuantity())
                {
                    amountTotal = amountTotal - (c.getValue() * c.getQuantity());
                    // System.out.println("Line 105 --------> " + String.format("%.2f",amountTotal));
                    System.out.println(c.getValue());
                    c.setQuantity(0);
                }
                else
                {
                    continue;
                }

            }
        }

        for(Coin c : myList)
        {
            // Find total value of every coin summed together
            if(c.getQuantity() == 1)
            {
                System.out.println(c.getQuantity() + " " + c.getName());

            }
            else if(c.getQuantity() > 1)
            {
                System.out.println(c.getQuantity() + " " + c.getNamesPlural());
            }
            else
            {
                continue;
            }
        }

        System.out.println("The piggy bank holds $" + (total - amount));

        return new ResponseEntity<>(HttpStatus.OK);
    }

}