package com.example.calculatrice1;


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class MainActivity extends AppCompatActivity
{

    TextView TextViewOpe; // Text view pour afficher les operations ecrites par lutilisateur
    TextView TextViewResult;
    String encours = "";// text
    String formule = "";// finqle
    String tempformule= ""; // formule temporqire

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextViewOpe = (TextView)findViewById(R.id.TextViewOpe);
        TextViewResult = (TextView)findViewById(R.id.TextViewResult);
    }
    boolean GueillemetGauche = true;

    public void GueillesOnClick(View view)
    {
        if(GueillemetGauche)
        {
            setencours("(");
            GueillemetGauche = false;
        }
        else
        {
            setencours(")");
            GueillemetGauche = true;
        }
    }


    private void setencours(String ValeurObtenue)
    {
        encours = encours + ValeurObtenue;
        TextViewOpe.setText(encours);
    }

    private boolean isNumeric(char c)
    {
        if((c <= '9' && c >= '0') || c == '.')
            return true;

        return false;
    }

    public void clearOnClick(View view)
    {
        TextViewOpe.setText("");
        encours = "";
        TextViewResult.setText("");
        GueillemetGauche = true;
    }
    public void equalsOnClick(View view)
    {
        Double result = null;
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("rhino");
        checkForPowerOf();

        try {
            result = (double)engine.eval(formule);
        } catch (ScriptException e)
        {
            Toast.makeText(this, "Invalid Input", Toast.LENGTH_SHORT).show();
        }

        if(result != null)
            TextViewResult.setText(String.valueOf(result.doubleValue()));

    }
    private void checkForPowerOf()
    {
        ArrayList<Integer> indexOfPowers = new ArrayList<>();
        for(int i = 0; i < encours.length(); i++)
        {
            if (encours.charAt(i) == '^')
                indexOfPowers.add(i);
        }

        formule = encours;
        tempformule= encours;
        for(Integer index: indexOfPowers)
        {
            changeFormula(index);
        }
        formule = tempformule;
    }

    private void changeFormula(Integer index)
    {
        String numberLeft = "";
        String numberRight = "";

        for(int i = index + 1; i< encours.length(); i++)
        {
            if(isNumeric(encours.charAt(i)))
                numberRight = numberRight + encours.charAt(i);
            else
                break;
        }

        for(int i = index - 1; i >= 0; i--)
        {
            if(isNumeric(encours.charAt(i)))
                numberLeft = numberLeft + encours.charAt(i);
            else
                break;
        }
        String original = numberLeft + "^" + numberRight;
        String changed = "Math.pow("+numberLeft+","+numberRight+")";
        tempformule= tempformule.replace(original,changed);
    }







    public void powerOfOnClick(View view)
    {
        setencours("^");
    }

    public void divisionOnClick(View view)
    {
        setencours("/");
    }

    public void sevenOnClick(View view)
    {
        setencours("7");
    }

    public void eightOnClick(View view)
    {
        setencours("8");
    }

    public void nineOnClick(View view)
    {
        setencours("9");
    }

    public void timesOnClick(View view)
    {
        setencours("*");
    }

    public void fourOnClick(View view)
    {
        setencours("4");
    }

    public void fiveOnClick(View view)
    {
        setencours("5");
    }

    public void sixOnClick(View view)
    {
        setencours("6");
    }

    public void minusOnClick(View view)
    {
        setencours("-");
    }

    public void oneOnClick(View view)
    {
        setencours("1");
    }

    public void twoOnClick(View view)
    {
        setencours("2");
    }

    public void threeOnClick(View view) {setencours("3"); }

    public void plusOnClick(View view)
    {
        setencours("+");
    }

    public void decimalOnClick(View view)
    {
        setencours(".");
    }

    public void zeroOnClick(View view)
    {
        setencours("0");
    }



}