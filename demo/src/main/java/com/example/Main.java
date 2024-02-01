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
        // goodGuys.add(new Wizard("Wizard"));

        
        
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
        System.out.println("Round: " + roundNum);
        System.out.println("================================");
      
        for (Creature i : sortedCreatures) {
            if (player.hp < 0) {
            //    return false;
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
                // System.out.println("Player: " + player.name + " HP: " + player.hp + "/" + player.maxHp + " Mana: " + player.getMana() + "/" + player.getMaxMana());
                System.out.println("Allies:");
                for (Creature x : allies) {
                    System.out.println(x.name + " HP: " + x.hp + "/" + x.maxHp);
                }
                ((Wizard) i).Turn(roundNum, allies, enemies, goodGuys);
            }else if (allies.contains(i)){
               i.turn(roundNum, enemies );
            }
            else if (enemies.contains(i)){
                i.turn(roundNum, allies);
            }
            
            }
            roundNum =+ 1;
    }
      
       // Simulate a battle for 20 rounds
//        for (int roundNum = 1; roundNum <= 20; roundNum++) {
//         //print round num 
//         System.out.println("Round No. " + roundNum);
//            // Each creature in team1 takes a turn
//            for (Creature creature : team1) {
//             if (creature instanceof Wizard){
//                 ((Wizard) creature).Turn(roundNum, allies, enemies, goodGuys);
//             }else {
//                creature.turn(roundNum, team2);}
//                // Check if team2 has been defeated
//                boolean team2Defeated = true;
//                for (Creature creature2 : team2) {
//                  if (creature2.hp > 0) {
//                      team2Defeated = false;
//                      break;
//                  }
//                }
//                if (team2Defeated) {
//                  System.out.println("Team2 has been defeated!");
//                  return;
//                }
//            }

//            // Each creature in team2 takes a turn
//            for (Creature creature : team2) {
//             if (creature instanceof Wizard){
//                 ((Wizard) creature).Turn(roundNum, allies, enemies, goodGuys);
//             }else{
//                creature.turn(roundNum, team1);}
//                // Check if team1 has been defeated
//                boolean team1Defeated = true;
//                for (Creature creature1 : team1) {
//                  if (creature1.hp > 0) {
//                      team1Defeated = false;
//                      break;
//                  }
//                }
//                if (team1Defeated) {
//                  System.out.println("Team1 has been defeated!");
//                  return;
//                }
//            }
//        }

//        System.out.println("The battle ended in a draw!");
//    }




   
}}
