package com.company;

import java.util.LinkedList;

public class Main {

    public static void main(String[] args) {
	//\

        {
            LinkedList<Individual_CompSt> linkedList = new LinkedList();

            GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithm();
            Population population = new Population(100);
            population.initialize();

            int generationCounter = 0;


            while (generationCounter < Constant.MAXIMUM_GENERATION){
                ++generationCounter;
                // System.out.println("Generation: " + generationCounter + " - fittest is: " + population.getFittestIndividual().getFitness());

                //  System.out.println(population.getFittestIndividual() + "\n");
                population = geneticAlgorithm.evolvePopulation(population);

                //playing with algorithm
                if (population.getFittestIndividual().getTotal_Flow() == 3200 && population.getFittestIndividual().allCompressor_hasFlow()){
                    linkedList.add(population.getFittestIndividual());
                }
                //playing with algorithm

            }

            System.out.println("list of fittest individuals ");
            for (Individual_CompSt  ii :linkedList){
                System.out.println(ii);
            }
            System.out.println("Solution found!!!");
            System.out.println(population.getFittestIndividual());

            Individual_CompSt  best_Individual_inBest = linkedList.get(0);
            for (Individual_CompSt  ii :linkedList){
                if(best_Individual_inBest.getTotal_power() > ii.getTotal_power()){
                    best_Individual_inBest = ii;
                }
            }


            System.out.println("best Solution found!!!");
            System.out.println(best_Individual_inBest);
        }
    }
}
