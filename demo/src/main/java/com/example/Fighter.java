package com.example;
import java.util.*;

public class Fighter extends Creature{
    protected boolean flag;
    //constructor
    public Fighter(String name){
    super(name, 50);
    this.attack = 5;
    this.defence = 8;
    this.speed = 5; 
    this.flag = false;
    }

    
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
               return possibleTargets.get(possibleTargets.size() - 1);
           } else {
               return null;
           }
        }

    public void turn(int roundNum, List<Creature> targetList){
        if (this.hp > 0){
            Creature target = this.autoSelect(targetList);
            if (target != null){
                System.out.println(this.name + " unleases a flurry of attacks");
                for (int i =0; i < 3; i++){
                    if (i ==0){
                        this.attack(target);
                        target.checkLife();
                    } else {
                        this.attack -= 3; 
                        if (target.hp > 0) {
                            attack(target);
                            target.checkLife();
                        } else {
                            target = autoSelect(targetList);
                            attack(target);
                            target.checkLife();
                    }
                    this.attack +=3;
                }
                
            }
        }

    }
    }}


