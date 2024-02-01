package com.example;
import java.util.Random;import java.util.Random;
import java.util.ArrayList;
import java.util.List;

public class Creature {
   // Attributes
   public String name;
   public int hp;
   public int maxHp;
   public int attack;
   public int defence;
   public int speed;

   // Constructor
   public Creature(String name, int maxHp){
       this.name = name;
       this.maxHp = maxHp;
       this.hp = this.maxHp;
       this.attack = 1;
       this.defence = 5;
       this.speed = 5;   
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
       if (roll >= target.defence + target.speed){
           int damage = this.attack + (random.nextInt(4)+1);
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

public class Main {
   public static void main(String[] args) {
       // Create two teams of creatures
       List<Creature> team1 = new ArrayList<>();
       team1.add(new Creature("Creature1", 10));
       team1.add(new Creature("Creature2", 10));
       // Add more creatures to team1 as needed...

       List<Creature> team2 = new ArrayList<>();
       team2.add(new Creature("Creature3", 10));
       team2.add(new Creature("Creature4", 10));
       // Add more creatures to team2 as needed...

       // Simulate a battle for 20 rounds
       for (int round = 1; round <= 20; round++) {
           // Each creature in team1 takes a turn
           for (Creature creature : team1) {
               creature.turn(round, team2);
               // Check if team2 has been defeated
               boolean team2Defeated = true;
               for (Creature creature2 : team2) {
                 if (creature2.hp > 0) {
                     team2Defeated = false;
                     break;
                 }
               }
               if (team2Defeated) {
                 System.out.println("Team2 has been defeated!");
                 return;
               }
           }

           // Each creature in team2 takes a turn
           for (Creature creature : team2) {
               creature.turn(round, team1);
               // Check if team1 has been defeated
               boolean team1Defeated = true;
               for (Creature creature1 : team1) {
                 if (creature1.hp > 0) {
                     team1Defeated = false;
                     break;
                 }
               }
               if (team1Defeated) {
                 System.out.println("Team1 has been defeated!");
                 return;
               }
           }
       }

       System.out.println("The battle ended in a draw!");
   }
}
