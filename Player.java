package extra;

import strategy.*;
import factory.*;

import java.util.InputMismatchException;
import java.util.Scanner;


public class Player extends Entity {

    private static Scanner scanner = new Scanner(System.in);

    public static int maxValue = 50;

    private ActionStrategy actionStrategy;
    private EnemyAbstractFactory currentWorld; // Agregar una propiedad para almacenar el mundo actual

    public Player() {
        super();
        createPlayer();
    }

    public void createPlayer() {
        Narrator narrator = new Narrator();

        int pATK = 0;
        int pHP = 0;
        int pDEF = 0;

        narrator.addText("What is your name, traveller? ");
        narrator.startNarration();
        String nombre = scanner.nextLine();
        super.setName(nombre);

        while(!validInput(pATK, pHP, pDEF)){
            narrator.addText("Hello, " + nombre + ". You have " + maxValue*3 + " points to distribute to your attack, life and defense. Your defense will be a percentage out of 100, so that the damage done to you is a percentage of your enemy's attack.");
            narrator.addText("Choose carefully, or you will be asked again.");
            narrator.startNarration();
            narrator.startNarration();
            
            pATK = askForPoints("Choose your attack points: ");
            pHP = askForPoints("Choose your life points: ");
            pDEF = askForPoints("Choose your defense points: ");
        }

        super.setATK(pATK);
        super.setHP(pHP);
        super.setDEF(pDEF);

        narrator.addText(nombre + " has " + super.getATK() + " ATK, " + super.getHP() + " HP y " + super.getDEF() + " DEF.");
        narrator.startNarration();
    }

    private boolean validInput(int pATK, int pHP, int pDEF){
        if(pATK <=0 || pATK > Player.maxValue) return false;
        if(pHP <=0 || pHP > Player.maxValue) return false;
        if(pDEF <0 || pDEF > Player.maxValue) return false;
        if(pATK + pDEF + pHP > maxValue*3) return false;

        return true;
    }

    // Los puntos no tienen que ser negativos
    private int askForPoints(String message) {
        int points = 0;
        boolean validInput = false;
        while (!validInput) {
            try {
                Narrator narrator = new Narrator();
                narrator.addText(message);
                narrator.startNarration();
                points = scanner.nextInt();
                
                if (points <= 0) {
                    throw new InputMismatchException("The points cannot be negative.");
                } if ( points > Player.maxValue) {
                    throw new InputMismatchException("You cannot assign more than " + Player.maxValue + " points in one place.");
                } 
                validInput = true;
            } catch (InputMismatchException e) {
                System.out.println("Please introduce a valid number.");
                scanner.nextLine(); // Descarta la entrada incorrecta
            }
        }
        return points;
    }

    public void setActionStrategy(ActionStrategy actionStrategy) {
        this.actionStrategy = actionStrategy;
    }

    public ActionStrategy getActionStrategy() {
        return actionStrategy;
    }

    

    public void executeAction(Entity target) {

        if (actionStrategy != null) {
            actionStrategy.action(target);
        } else {
            System.out.println("There has been no attack strategy assigned.");
        }
    }



}