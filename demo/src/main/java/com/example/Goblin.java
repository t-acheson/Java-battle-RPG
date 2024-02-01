package com.example;

public class Goblin extends Creature {
   // Constructor
   public Goblin(String name){
       super(name, 15);
       this.attack = 3;
       this.defence = 6;
       this.speed = 6; 
   }
}
