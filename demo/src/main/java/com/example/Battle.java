package com.example;

import java.util.*;

public class Battle {
   private List<Creature> enemies;
   private List<Creature> allies;
   private Creature boss;
   private Wizard player;
   private List<Creature> goodGuys;

   public Battle(List<Creature> enemies, List<Creature> allies, Creature boss, Wizard player) {
       this.enemies = enemies;
       this.allies = allies;
       this.boss = boss;
       this.player = player;
       this.goodGuys = new ArrayList<>(allies);
       this.goodGuys.add(player);
   }

   public boolean start() {
       System.out.println("The Battle Begins");
       System.out.println("================================");
       List<Creature> everyone = new ArrayList<>(allies);
       everyone.addAll(enemies);
       everyone.add(player);
       everyone.sort(Comparator.comparingInt(x -> x.speed));
       List<Creature> sortedCreatures = everyone;

       int roundNo = 1;
       boolean bossEntered = false;

       while (player.hp > 0) {
           System.out.println("Round: " + roundNo);
           System.out.println("================================");
           for (Creature i : sortedCreatures) {
               if (player.hp < 0) {
                  return false;
               }
               if (!bossEntered && roundNo == 3) {
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
        
           }
           System.out.println("================================");
           System.out.println("Round " + roundNo + " over.");
           System.out.println("================================");
           roundNo++;
       }
       return false;
   }

   public void playerTurn() {
       System.out.println("================================");
       System.out.println("Player: " + player.name + " HP: " + player.hp + "/" + player.maxHp + " Mana: " + player.getMana() + "/" + player.getMaxMana());
       System.out.println("Allies:");
       for (Creature i : allies) {
           System.out.println(i.name + " HP: " + i.hp + "/" + i.maxHp);
       }
       System.out.println("================================");
       System.out.println("Actions. S: Show Player Stats F: Attack R: Recharge Mana");
       System.out.println("Spells. 1: Heal 2: Firebolt 3: Mass Heal 4: Fire Storm");
       System.out.println("To Quit game type: Quit");
       System.out.println("================================");

       boolean winLose = start();
       if (winLose) {
           System.out.println("Enemies defeated and you live to fight another day");
       } else {
           System.out.println("The enemy has taken over Middle Earth, you have lost the battle.");
       }
   }
}
