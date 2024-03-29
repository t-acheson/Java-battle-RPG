package com.example;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Main {
   public static void main(String[] args) {
  
    Creature player = new Wizard("Wizard");
    Creature boss = new Boss("Boss");

       List<Creature> team1 = new ArrayList<>();
       team1.add(new Creature("Creature1", 10));
       team1.add(new Goblin("Goblin1"));
       team1.add(new Archer("Archer1"));
       team1.add(new Fighter("Fighter1"));
       team1.add(new OrcGeneral("OrcGeneral1"));
       

       List<Creature> team2 = new ArrayList<>();
       team2.add(new Creature("Creature2", 10));
       team2.add(new Warrior("Warrior1"));
       team2.add(new Orc("Orc1"));
       team2.add(new Archer("Archer2"));
       team2.add(new Fighter("Fighter2"));
       team2.add(new GoblinKing("GoblinKing"));
       

        List<Creature> allies = team1;
        List<Creature> enemies = team2;
        List<Creature> goodGuys = allies;
        goodGuys.add(player);
        

        
        System.out.println("================================");
        System.out.println("The Battle Begins");
        System.out.println("================================");
        // System.out.println("================================");
        System.out.println("Actions. S: Show Player Stats F: Attack R: Recharge Mana");
        System.out.println("Spells. 1: Heal 2: Firebolt 3: Mass Heal 4: Fire Storm");
        System.out.println("To Quit game type: Quit");
        System.out.println("================================");
      
    int roundNum = 1;
    boolean bossEntered = false;
    List<Creature> everyone = new ArrayList<>(allies);
    everyone.addAll(enemies);
    everyone.add(player);
    everyone.sort(Comparator.comparingInt(x -> x.speed));
    List<Creature> sortedCreatures = everyone;

    while (player.hp > 0) {
        System.out.println("================================");
        System.out.println("Round: " + roundNum);
        System.out.println("================================");
      
        for (Creature i : sortedCreatures) {
            if (player.hp < 0) {
            // System.out.println("The Wizard is dead and the battle is lost, better luck next time......");
            // break;
            }
            else if (!bossEntered && roundNum == 3) {
               System.out.println("================================");
               System.out.println(boss.name + " has turned up to fight");
               System.out.println("================================");
               enemies.add(boss);
               everyone = new ArrayList<>(allies);
               everyone.addAll(enemies);
               everyone.add(player);
               everyone.sort(Comparator.comparingInt(x -> x.speed));
               sortedCreatures = everyone;
               bossEntered = true;
            }
            if (i instanceof Wizard){
                System.out.println("================================");
                System.out.println("Allies:");
                for (Creature x : allies) {
                    System.out.println(x.name + " HP: " + x.hp + "/" + x.maxHp);
                }
                ((Wizard) i).Turn(roundNum, allies, enemies, goodGuys);
            }else if (allies.contains(i) && player.hp>0){
               i.turn(roundNum, enemies );
            }
            else if (enemies.contains(i)&& player.hp>0){
                i.turn(roundNum, allies);
            }
            else{
                System.out.println("================================");
                System.out.println("The Wizard is dead and the battle is lost, better luck next time......");
                System.out.println("================================");
                break;
            }
            
            }
            roundNum += 1;
        }
   
}}
