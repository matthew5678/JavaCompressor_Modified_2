package com.company;

import java.util.LinkedList;

public class List_Of_Fittest {
    private LinkedList<Individual_CompSt> list_;

            List_Of_Fittest(){
                list_ = new LinkedList();
    }

    public void addIndividual(Individual_CompSt individual_compSt){
        list_.add(individual_compSt);
    }

    public String getIndividualStation(){
                String ss =" ";
                for (Individual_CompSt s: list_){
                    ss += s;
                }

                return ss;
    }
}
