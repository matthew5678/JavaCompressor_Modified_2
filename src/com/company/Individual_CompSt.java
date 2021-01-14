package com.company;

import java.util.Random;

public class Individual_CompSt {     // dont create

    public int[] genes;
    private Random randomGenerator;

    private int fitness;

    private double[] flowInEachCompressors;

    private double total_Flow;
    private double total_power;

    public CompressorType[] Compressor_Group;




    Individual_CompSt(){
        this.genes = new int[Constant.CHROMOSOME_LENGTH];
        randomGenerator = new Random();
        flowInEachCompressors = new double[Constant.NUMBER_COMPRESSOR];   ///initialize
        Compressor_Group = new CompressorType[Constant.NUMBER_COMPRESSOR];
        total_Flow = 0;
        total_power = 0;

        // initializing object that belongs to this object only works int he compressor
        for (int i = 0; i < Constant.NUMBER_COMPRESSOR; ++i){

            switch ( i){
                case 0:
                    Compressor_Group[i] = new CompressorType(0.7);
                    break;
                case 1:
                    Compressor_Group[i] = new CompressorType(0.95);
                    break;
                case 2:
                    Compressor_Group[i] = new CompressorType(0.95);
                    break;
                    default:
                        break;
            }
        }
    }

    // modify method for compressor
    public void generateIndividual_ComStation() {
        for (int i = 0; i < Constant.CHROMOSOME_LENGTH; ++i){
            int gene = randomGenerator.nextInt(2);
            genes[i] = gene;

        }


       // System.out.println();

    }


    // modify method for compressor
    public int getFitness(){
        return fitness;
    }


    //1
    public  void computeFlowEachCompressor(){
        double[] doublevalues = new double[Constant.NUMBER_COMPRESSOR];
        int temp = 0;
        int[] temp_array = new int[Constant.CHROMOSOME_LENGTH/ Constant.NUMBER_COMPRESSOR];  // temp array to store the binary for each compressor


        int temp_index = 0;
        int ii = 0;
        for (int i = 0; i < Constant.CHROMOSOME_LENGTH; ++i){
             temp_array[ii] = genes[i];
            //System.out.print(genes[i]);

             if(ii == (temp_array.length-1)){  // reached the end of the temp_Array

                 doublevalues[temp_index] = binaryValue_To_Double(temp_array);
                ++temp_index;
                ii = -1;
            }
             ++ii;
         }
        flowInEachCompressors = doublevalues;
     //System.out.println("Flow in each compressor " + flowInEachCompressors[0] + ", "+  flowInEachCompressors[1] + ", " +  flowInEachCompressors[2] );

    }


    //2
     public void set_Inflow_EachCompressor(){
        for (int i = 0; i < Constant.NUMBER_COMPRESSOR; ++i){
            Compressor_Group[i].setInlet_Flow(flowInEachCompressors[i]);
        }
    }




    //3
    public double compute_Head_Power(){
        double temp_power = 0;
        for (int i = 0; i < Constant.NUMBER_COMPRESSOR; ++i){
//            System.out.println(" computing isentropic head: " + Compressor_Group[i].computeIsentropicHead());
            Compressor_Group[i].computeIsentropicHead();
            Compressor_Group[i].computePower();
        }

        for (int i = 0; i < Constant.NUMBER_COMPRESSOR; ++i){
            temp_power +=  Compressor_Group[i].getPower();
        }

        //System.out.println("Computing head and power " + temp_power);

        return total_power = temp_power;
    }

    //4
    public double compute_Station_Flow(){
        double temp_Flow = 0;
        for (int i = 0; i < Constant.NUMBER_COMPRESSOR; ++i){
            temp_Flow +=  Compressor_Group[i].getInlet_Flow();
        }
        return total_Flow = temp_Flow;
    }

    private double binaryValue_To_Double(int[] temp_array) {
        int base = 1;
        double geneInDouble = 0;
       // System.out.println("genes sent to converter ");
        for (int i = temp_array.length-1; i > 0; --i){

            //System.out.println();
            if (temp_array[i] == 1)
                geneInDouble += base;
            base = base * 2;
//            System.out.println("BASE IS : " + base);
        }
//         System.out.println(" GENE in DOUBLE " + geneInDouble);
        return geneInDouble;
    }



    // modify method for compressor
    int getGene(int i) {
        return this.genes[i];
    }

    // modify method for compressor
    public void setGenes(int index, int value) {
        this.genes[index] = value;
    }

    public double getTotal_Flow() {
        return total_Flow;
    }

    public double getTotal_power() {
        return total_power;
    }

    public boolean allCompressor_hasFlow(){
        boolean hasFlow = true;
        for (int i = 0; i < Compressor_Group.length; ++i){
            if (Compressor_Group[i].getInlet_Flow() == 0) {
                hasFlow = false;
                break;
            }
        }
        return hasFlow;
    }


    @Override
    public String toString() {
        String s =  "";

        for (int i = 0; i < Constant.CHROMOSOME_LENGTH; i++)
            s += getGene(i);

        s += "\n Compressor total flow: " + total_Flow + "\t";

        s += "  while flow in each compressor: ";

        for (int i = 0; i < Constant.NUMBER_COMPRESSOR; i++)
            s +=   Compressor_Group[i].getInlet_Flow() + "\t,";


        s  += "\n  total Power  in each compressor: ";
            s +=  ":  " + getTotal_power();



        return s;
    }


}
