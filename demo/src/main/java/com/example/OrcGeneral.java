package com.example;

import java.util.List;

public class OrcGeneral extends Orc{
    protected boolean shield;

    public OrcGeneral(String name){
        super(name);
        this.maxHp = 80;
        this.attack = 3;
        this.defence = 6;
        this.speed = 6;
        this.rage = false;
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
            this.heavyAttack(target);
            target.checkLife();
        //shield down, then attack 
        } else {
            //return null value for no target?
    }
    }
    else if (roundNum % 3 == 0){
       Creature target = this.autoSelect(targetList);
        if (target != null){
            this.shieldDown();
            this.attack(target);
            target.checkLife();
        } else {
            //return null value for no target?
    }}
    else if (roundNum % 2 == 0){
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
}

}
