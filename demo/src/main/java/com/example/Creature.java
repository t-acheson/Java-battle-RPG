package com.example;
import java.util.*;

public class Creature {
    public int hp;
    public int maxHp;
    public int attack;
    public int defence;
    public int speed;
    public String name;
    public boolean rage;
    public HashMap<String, Integer> abilities;

   public Creature(String name, int maxHp) {
    this.name = name;
    this.maxHp = maxHp;
    this.hp = maxHp;
    this.attack = 5;
    this.defence = 5;
    this.speed = 5;
    this.rage = false;
    this.abilities = new HashMap<>();
    this.abilities.put("attack", 5);
    this.abilities.put("defence", 5);
    this.abilities.put("speed", 5);
 }
 
 
   public boolean rage() {
       return this.rage;
   }

   public void setRage(boolean rage) {
       this.rage = rage;
   }

 public int getAttack() {
    return this.attack;
 }
 
 public void setAttack(int attack) {
    this.attack = attack;
 }
 
 public int getDefence() {
    return this.defence;
 }
 
 public void setDefence(int defence) {
    this.defence = defence;
 }
 
   public int getAbility(String ability) {
       return this.abilities.get(ability);
   }

   public void setAbility(String ability, int value) {
       this.abilities.put(ability, value);
   }

   public String getName() {
       return this.name;
   }

   public int getHp() {
       return this.hp;
   }

   public void setHp(int hp) {
       this.hp = hp;
   }

   public int getMaxHp() {
       return this.maxHp;
   }

   // check life method 
   public int checkLife(){
       if (this.hp < 0){
           this.hp = 0;
           System.out.println(this.name + " fainted...");
       }
       return this.hp;
   }

   //attack method 
   public void attack(Creature target){
       Random random = new Random ();
       int roll = random.nextInt(20)+ 1; //generates random number [1,20]
       if (roll >= (target.getAbility("defence")) + (target.getAbility("speed"))){
           int damage = this.getAbility("attack") + (random.nextInt(4)+1);
           target.hp -= damage;
           System.out.println(this.name + " attacked " + target.name + " causing " + damage + " damage.");
       } else{
           System.out.println(this.name + " attacked " + target.name + " but missed...");
       }
   }

   //auto select target 
   public Creature autoSelect(List<Creature> targetList){
       Random random = new Random ();
       for (int i = 0; i < targetList.size(); i++){
           int randomIndex = random.nextInt(targetList.size());
           if (targetList.get(randomIndex).hp > 0){
               return targetList.get(randomIndex);
           }
       }
       return null; //no alive target found 
   }

   //turn method 
   public void turn(int roundNum, List<Creature> targetList) {
       Creature target = autoSelect(targetList);
       if (target != null){
           attack(target);
       }
   }
}
