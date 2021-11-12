package com.example.empilateurtp1sma;

import com.example.empilateurtp1sma.environment.Report;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.DoubleStream;

/**
 *
 */
public class MassiveExecution {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_CYAN = "\u001B[36m";


    /**
     * Le nombre de scénarios à exécuter
     */
    public static final int nbScenario = 100;
    /**
     * Le type de scénario à exécuter, par défaut exécute le scénario de base :
     *  - 1 : scénario de base
     *  - 2 : amélioration numéro 1
     *  - 3 : amélioration numéro 2
     */
    public static final int scenario = 1;


    public static void main(String[] args) {

        List<Integer> moves = new ArrayList<>();
        List<Boolean> objectifs = new ArrayList<>();

        int nbRow = (int)Math.sqrt(nbScenario);
        int nbColumn = nbScenario / nbRow;

        for(int i = 0; i < nbScenario; i++){
            System.out.println("RUN: " + i);
            Report report = null;
            switch (scenario){
                case 2:
                    report = Main2.run();
                    break;
                case 3:
                    report = Main3.run();
                    break;
                case 0:
                case 1:
                default:
                    report = Main.run();
                    break;
            }
            moves.add(report.getCount());
            objectifs.add(report.checkEndCondition());
        }

        long nbFailed = objectifs.stream().filter(o -> !o).count();
        double average = moves.stream().mapToDouble(move -> (double)move).average().getAsDouble();

        DoubleStream sortedMoves = moves.stream().mapToDouble(move -> (double)move).sorted();
        double mediane = moves.size()%2 == 0 ?
                sortedMoves.skip(moves.size() / 2 - 1).limit(2).average().getAsDouble():
                sortedMoves.skip(moves.size() / 2).findFirst().getAsDouble();



        // DISPLAY
        System.out.print("\033[H\033[2J");
        System.out.flush();

        System.out.println();
        System.out.println(" Résultat des 100 lancers :");
        int sizeMax = (int)Math.log10(moves.stream().max(Integer::compare).get());
        for (int i = 0; i < nbScenario; i++){
            if (i%nbColumn == 0) System.out.println();
            if (objectifs.get(i)){
                System.out.print(" " + moves.get(i));
            }else{
                System.out.print(" " + ANSI_RED + moves.get(i) + ANSI_RESET);
            }
            int x = sizeMax - (int)Math.log10(moves.get(i));
            for (int k = 0; k < x; k++){
                System.out.print(" ");
            }
        }
        System.out.println();

        System.out.println();
        System.out.println(" Objectifs atteints sur les 100 lancers :");
        for (int i = 0; i < nbScenario; i++){
            if (i%nbColumn == 0) System.out.println();
            if (objectifs.get(i)){
                System.out.print(" " + ANSI_GREEN + "T" + ANSI_RESET);
            }else{
                System.out.print(" " + ANSI_RED + "F" + ANSI_RESET);
            }
        }
        System.out.println();

        System.out.println();
        System.out.println(" Nombre de raté          : " + ANSI_CYAN + nbFailed + ANSI_RESET);
        System.out.println(" Pourcentage de réussite : " + ANSI_CYAN + (1 - ( (double)nbFailed / nbScenario)) * 100 + " %" + ANSI_RESET);
        System.out.println(" Moyenne de déplacement  : " + ANSI_CYAN + average + ANSI_RESET);
        System.out.println(" Médiane de déplacement  : " + ANSI_CYAN + mediane + ANSI_RESET);
    }
}
