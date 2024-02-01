package com.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class Archer extends Creature {
    protected boolean flag;
    // Constructor
   public Archer(String name){
    super(name, 30);
    this.attack = 7;
    this.defence = 9;
    this.speed = 8; 
    this.flag = false; //false == no mods 
}

public void powerShot(Creature target){
    System.out.println(this.name + " goes for a power shot at " + target.name);
    Random random = new Random ();
       int roll1 = random.nextInt(20)+ 1; //generates random number [1,20]
       int roll2 = random.nextInt(20)+ 1;
    int roll ;
    if (roll1 > roll2){
        roll = roll1;
    } else {
        roll = roll2;
    }

    if (this.speed > target.speed){
        roll += (this.speed - target.speed);
    }

    if (!this.flag){
        this.attack += 3;
        this.defence -= 3;
        this.flag = true;
    }
       if (roll >= target.defence + target.speed){
           int damage = this.attack + (random.nextInt(8)+1);
           target.hp -= damage;
           System.out.println(this.name + " attacked " + target.name + " causing " + damage + " damage.");
       } else{
           System.out.println(this.name + " attacked " + target.name + " but missed...");
       }
   }

   public void attack(Creature target){
    if (this.flag){
         this.attack -= 3;
        this.defence += 3;
        this.flag = false;
    }
    super.attack(target);
   }

    //auto select target 
   public Creature autoSelect(List<Creature> targetList){
    List<Creature> possibleTargets = new ArrayList<>();
    
    for (Creature i : targetList){
        if (i.hp >0){
            possibleTargets.add(i);
        }
    }

      Collections.sort(possibleTargets, new Comparator<Creature>() {
       @Override
       public int compare(Creature c1, Creature c2) {
           return Integer.compare(c1.hp, c2.hp);
       }
   });

   if (!possibleTargets.isEmpty()) {
       return possibleTargets.get(0);
   } else {
       return null;
   }
}
    
public void turn(int roundNum, List<Creature> targetList){
    if (this.hp > 0){
        if (roundNum % 4 == 0 || roundNum % 3 == 0 || roundNum % 2 == 0){
        Creature target = this.autoSelect(targetList);
                if (target != null){
                    this.powerShot(target);
                    target.checkLife();   
                }
            }
            else if (roundNum % 1 == 0){
                Creature target = this.autoSelect(targetList);
                if (target != null){
                    this.attack(target);
                    target.checkLife();
                
                } else {
                    //return null value for no target?
            }
            }
        } else {
        //skipping round as archer dead 
    }
}

}

