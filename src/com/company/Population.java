package com.company;

import java.util.ArrayList;
import java.util.LinkedList;

public class Population {

    private Individual_CompSt[] individuals_CompStations;

    private List_Of_Fittest list_of_fittest;
    public LinkedList<Individual_CompSt> fittest_Individuals;
    private ArrayList<Individual_CompSt>  fittest_Individuals_ArrayList;

    Population(int populationSize){
        individuals_CompStations = new Individual_CompSt[populationSize];
        fittest_Individuals = new LinkedList<>();


        fittest_Individuals_ArrayList = new ArrayList<>();
        list_of_fittest = new List_Of_Fittest();
    }

    public void initialize(){
        for(int i = 0; i < individuals_CompStations.length; ++i){
            Individual_CompSt newIndividual = new Individual_CompSt();
            newIndividual.generateIndividual_ComStation();
            newIndividual.compute_Station_Flow();
            newIndividual.set_Inflow_EachCompressor();
            saveIndividual(i, newIndividual);
        }
    }

    public Individual_CompSt getIndividual(int index) {
        return individuals_CompStations[index];
    }




    public Individual_CompSt getFittestIndividual(){   /////////

        Individual_CompSt fittest_Compressor = individuals_CompStations[0];

        for (int i = 0 ; i < individuals_CompStations.length; ++i){
            individuals_CompStations[i].computeFlowEachCompressor();
            individuals_CompStations[i].set_Inflow_EachCompressor();
            individuals_CompStations[i].compute_Head_Power();
            individuals_CompStations[i].compute_Station_Flow();
        }

        for (int i = 1 ; i < individuals_CompStations.length; ++i){

            if ( fittest_Compressor.getTotal_Flow()  < Constant.MAXIMUM_FLOW  && (fittest_Compressor.getTotal_Flow() < individuals_CompStations[i].getTotal_Flow())
                     ){
                individuals_CompStations[0] = individuals_CompStations[i];
                fittest_Compressor  = individuals_CompStations[i];
            }
        }

        for (int i = 1 ; i < individuals_CompStations.length; ++i){
            if(individuals_CompStations[0].getTotal_power() > individuals_CompStations[i].getTotal_Flow() &&
                    individuals_CompStations[0].getTotal_Flow() < Constant.MAXIMUM_FLOW ){
                individuals_CompStations[0] = individuals_CompStations[i];
                fittest_Compressor  = individuals_CompStations[i];
            }
        }
        fittest_Individuals.add(fittest_Compressor);
        fittest_Individuals_ArrayList.add(fittest_Compressor);
        list_of_fittest.addIndividual(fittest_Compressor);
       // System.out.println("value in linked list " + fittest_Individuals.peek() );

        return   fittest_Compressor;        ///       not correct testing
    }


    public String printFittest(){
        String ss = "";
        System.out.println("lenght " + fittest_Individuals_ArrayList.size());
//       for (Individual_CompSt fittest: fittest_Individuals_ArrayList ){
//           ss += "Individual : " + fittest + "/n";
//       }
        ss = list_of_fittest.getIndividualStation();
       return ss;
    }

    public int getSize(){
        return this.individuals_CompStations.length;
    }

    public  void saveIndividual(int i, Individual_CompSt newIndividual)
    {
        individuals_CompStations[i] = newIndividual;
    }
}
