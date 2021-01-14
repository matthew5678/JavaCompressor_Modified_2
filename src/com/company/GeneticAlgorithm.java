package com.company;

import java.util.Random;

public class GeneticAlgorithm {

    private  Random randomGenerator;

    GeneticAlgorithm(){
        this.randomGenerator = new Random();
    }


    public Population evolvePopulation(Population population){

        Population newPopulation = new Population(population.getSize());
        for (int i = 0; i < population.getSize(); i++){
            Individual_CompSt firstIndividual = randomSelection(population);
            Individual_CompSt secondIndividual = randomSelection(population);
            Individual_CompSt newIndividual = crossOver(firstIndividual, secondIndividual);
            newPopulation.saveIndividual(i, newIndividual );
        }

        for (int i = 0; i < newPopulation.getSize(); i++){
            mutate(newPopulation.getIndividual(i));
        }
        return newPopulation;
    }

    private void mutate(Individual_CompSt individual) {

        for (int i = 0; i < Constant.CHROMOSOME_LENGTH; ++i){
            if(Math.random() <= Constant.MUTATION_RATE){
                int gene = randomGenerator.nextInt(10);
                individual.setGenes(i, gene);
            }
        }
    }

    // i may cnage this
    private Individual_CompSt crossOver(Individual_CompSt firstIndividual, Individual_CompSt secondIndividual) {
        Individual_CompSt newSolution = new Individual_CompSt();
            for (int i = 0; i < Constant.TOURNAMENT_SIZE; i++){
                if (Math.random() <= Constant.CROSSOVER_RATE){
                    newSolution.setGenes(i, firstIndividual.getGene(i));
                }else{
                    newSolution.setGenes(i, secondIndividual.getGene(i));
                }
            }
            
            return newSolution;
    }
    
    

    private Individual_CompSt randomSelection(Population population) {
//        creating a new population with the TOURNAMENT_SIZE
        Population newPopulation = new Population(Constant.TOURNAMENT_SIZE);
        for (int i = 0; i < Constant.TOURNAMENT_SIZE; i++){
            // randomly selecting new individual form population
            // which is inserted into another population
            // of tournament size
            int randomIndex = (int) (Math.random() * population.getSize());
            newPopulation.saveIndividual(i,population.getIndividual(randomIndex) );
        }

        Individual_CompSt fittestIndividual = newPopulation.getFittestIndividual();
        return fittestIndividual;
    }
}
