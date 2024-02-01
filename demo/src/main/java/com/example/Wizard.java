package com.example;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Wizard extends Creature {
    public int arcana;
    public int mana;
    public int maxMana;
    // Constructor
   public Wizard(String name){
    super(name, 20);
    this.hp = 20;
    this.attack = 5;
    this.defence = 8;
    this.speed = 5;
    this.arcana = 10;
    this.abilities.put("arcana", 10);
    this.mana = 100;
    this.maxMana = this.mana;
}

//get mana 
    public int getMana(){
        return this.mana;
    }

    public int getMaxMana(){
        return this.maxMana;
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
        this.mana += 20;
        if (this.mana >100){ this.mana = 100;}
        System.out.println(this.name + " has " + this.mana +" mana points.");
   }

   public void recharge(){
    System.out.println(this.name + " channels magical energy...");
    this.mana=+30;
    System.out.println(this.name + " recharged for 30 mana points."); 
    if (this.mana >100){
         this.mana = 100; 
         System.out.println("Mana is full!");
}
    
   }

   public void fireBolt(Creature target){
        Random random = new Random ();
       int roll = random.nextInt(20)+ 1 + (this.arcana/2); //generates random number [1,20]
       if (roll >= (target.getAbility("defence")) + (target.getAbility("speed"))){
        int x = this.arcana; // Set the maximum value
        int damage = random.nextInt(x) + 1;
           target.hp -= damage;
           System.out.println(this.name + " attacked " + target.name + " causing " + damage + " damage.");
           this.mana += 10;
        if (this.mana >100){ this.mana = 100;}
        System.out.println(this.name + " has " + this.mana +" mana points.");
       } else{
           System.out.println(this.name + " attacked " + target.name + " but missed...");
       }
   }

   public void heal(Creature target){
    this.mana -= 20;
    if (this.mana > 0){
    Random random = new Random ();
    int healing = random.nextInt(8)+ 1 + (this.arcana/2);
    target.hp += healing; 
    if (target.hp > target.maxHp){ target.hp = target.maxHp;}
    if (target.hp > 0){
        System.out.println(this.name + "heals " + target.name + " for " + healing + " hp points.");
    } else { System.out.println(this.name + " tried to heal " + target.name + " but failed..");
        this.mana += 20; 
}} else { System.out.println(this.name + " tried to heal " + target.name + " but failed..");
        this.mana += 20;}
   }

   public void massHeal(List<Creature> targetList){
    this.mana -= 30; 
    if (this.mana > 0){
    Random random = new Random ();
    int healing = random.nextInt(10)+ 1 + (this.arcana);
    for (Creature i: targetList){
        i.hp += healing; 
        if (i.hp > i.maxHp){ i.hp = i.maxHp;}
    } 
    System.out.println(this.name + " heals their allies");
   } else {System.out.println(this.name + "tried to heal their allies but failed because they are too weak");
    this.mana += 30;        
}
}

    public void fireStorm(List<Creature> enemies){
        this.mana -= 50;
        if (this.mana > 0 ){
            System.out.println(this.name + " summons a firestorm");
            Random random = new Random();
            int damage = random.nextInt(20)+ 1 + this.speed; 
            if (damage >= this.arcana){
                for (Creature i : enemies){
                    i.hp -= (damage/2);
                    i.checkLife();
                }
            } 
            else{ 
                for (Creature i : enemies){
                    i.hp -= (damage);
            }}

        } else{
            System.out.println(this.name + " tried to use a fireStorm but failed");
            this.mana += 50;
        }
    }

    public Creature selectTarget(List<Creature> targetList){
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < targetList.size(); i++){
            System.out.println((i + 1) + ": " + targetList.get(i).name + ", HP: " + targetList.get(i).hp + "/" + targetList.get(i).maxHp);
        }
        System.out.println("Enter choice of target: ");
        int num = scanner.nextInt();
        Creature choice = targetList.get(num-1);
        System.out.println("You selected: " + choice);
        scanner.close();
        Creature target = choice;
        return target;
        }



    public boolean Turn(int roundNum, List<Creature> allies, List<Creature> enemies, List<Creature> goodGuys){
        // System.out.println("Enter action: ");

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter action: ");
        String action = scanner.nextLine().toUpperCase();

        while (true){
            switch (action){
                case "QUIT":
                System.exit(0);

                case "S":
                System.out.println("Player: " + this.name + " HP: " + this.hp + "/" + this.maxHp + "Mana: " + this.mana + "/" + this.maxMana);
                System.out.println("Enter action: ");
                // action = scanner.nextLine();
                return true;

                case "F":
                Creature targetEnemy = this.selectTarget(enemies);
                this.attack(targetEnemy);
                targetEnemy.checkLife();
                scanner.close();
                return false;

                case "R":
                this.recharge();
                //scanner.close();
                return false;

                case "1":
                Creature target = this.selectTarget(goodGuys);
                this.heal(target);
               // scanner.close();
                return false;

                case "2":
                Creature targetEnemyFire = this.selectTarget(enemies);
                this.fireBolt(targetEnemyFire);
                //scanner.close();
                return false;

                case "3":
                this.massHeal(allies);
                //scanner.close();
                return false;

                case "4":
                this.fireStorm(enemies);
                //scanner.close();
                return false;

                default:
                action = scanner.nextLine();

            }
            scanner.close();
        }



    }

    }







