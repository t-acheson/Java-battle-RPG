package com.example;

import java.util.List;

public class Orc extends Creature {
    protected boolean rage;
   // Constructor
   public Orc(String name){
       super(name, 50);
       this.attack = 3;
       this.defence = 6;
       this.speed = 6;
       this.rage = false;
   }
   //heavy attack method 
   public void heavyAttack(Creature target){
    //check rage flag
    if (!this.rage){
        System.out.println(this.name + " is in heavy attack mode");
        this.attack += 5;
        this.defence -= 3;
        this.attack(target);
        this.rage = true;
    } else{
        this.attack(target);
    }}

    public void attack(Creature target){
        if (this.rage){
       this.attack -= 5;
        this.defence += 3;
        super.attack(target);
        this.rage = false;
    } else{
        super.attack(target);
    }
   }

   public void turn(int roundNum, List<Creature> targetList){
    if (this.hp >0){
        if (roundNum % 4 == 0){
            Creature target = this.autoSelect(targetList);
            if (target != null){
                this.heavyAttack(target);
                target.checkLife();
                // figure out return value 
            } else{
                //return value for no target 
            }
        }
    }
        else if (roundNum % 3 == 0 || roundNum % 2 == 0 || roundNum % 1 == 0){
            Creature target = this.autoSelect(targetList);
            if (target != null){
                this.attack(target);
                target.checkLife();
                // figure out return value 
            } else{
                //return value for no target 
            }
        }
   }

}