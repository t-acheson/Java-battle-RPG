package com.example;

import java.util.*;

public class Boss extends Orc{
       // Constructor
   public Boss(String name){
    super(name);
    this.hp = 200;
    this.attack = 5;
    this.defence = 8;
    this.speed = 5;
    this.rage = false;
}

 public Creature autoSelect(List<Creature> targetList, String mode){
    List<Creature> possibleTargets = new ArrayList<>();
    
    if (mode == "strong"){
            
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
               return possibleTargets.get(possibleTargets.size() - 1);
           } else {
               return null;
           }
        } 
        else if (mode == "weak"){
            
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
        } else if (mode == "random"){
        Random random = new Random ();
       int roll = random.nextInt(2)+ 1;
       String chosenMode;
       if (roll == 1){
        chosenMode = "weak";
       } else{
        chosenMode = "strong";
       }
       return this.autoSelect(targetList, chosenMode);
    }
    else { return null;}
}

 public void turn(int roundNum, List<Creature> targetList){
    Creature target;
    if (this.hp >0){
        if (roundNum % 4 == 0 || roundNum % 3 == 0 || roundNum % 2 == 0 ){
            target = this.autoSelect(targetList, "strong");
            this.heavyAttack(target);
        }
        else if (roundNum % 1 == 0){
            for (int i =0; i < 3; i++){
                if (i ==0){
                    target = this.autoSelect(targetList, "weak");
                    this.attack(target);
                    target.checkLife();
                if (i == 1 || i == 2){
                    if (target.hp > 0){
                        this.attack(target);
                    target.checkLife();
                    } else{
                        target = this.autoSelect(targetList, "random");
                    this.attack(target);
                    target.checkLife();
                    }
                }
                } 
            
        }
        }

    }
 }



}

