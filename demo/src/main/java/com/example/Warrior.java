package com.example;

import java.util.List;

public class Warrior extends Creature{
    protected boolean shield;
    //constructor 
    public Warrior(String name){
        super(name, 50);
        this.attack = 5;
        this.defence = 10;
        this.speed = 4;
        this.shield = false; //false == down, true == up 
}

public void shieldUp(){
    if (!shield){
        this.attack -= 4;
        this.defence += 4;
        this.shield = true;
    }
}

public void shieldDown(){
    if (shield){
        this.attack += 4;
        this.defence -= 4;
        this.shield = false;
    }
}

public void turn(int roundNum, List<Creature> targetList){
    if (this.hp >0){

    if (roundNum % 4 ==0){
        Creature target = this.autoSelect(targetList);
        if (target != null){
            this.shieldDown();
            this.attack(target);
            target.checkLife();
        //shield down, then attack 
        } else {
            //return null value for no target?
    }
    }
    else if (roundNum % 3 == 0 || roundNum % 2 == 0){
       Creature target = this.autoSelect(targetList);
        if (target != null){
            this.attack(target);
            target.checkLife();
        } else {
            //return null value for no target?
    }}
    else if (roundNum % 1 == 0){
        Creature target = this.autoSelect(targetList);
        if (target != null){
            
            this.attack(target);
            target.checkLife();
            this.shieldUp();
        } else {
            //return null value for no target?
    }
    }
}
}}
