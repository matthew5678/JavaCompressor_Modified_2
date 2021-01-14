package com.company;

// used to compute the sentropic exponent
public class Gas {
    private double universalGasconstant = 8.14;

    private double[] composition = new double[]{87.424, 5.492, 0.751, 0.100, 0.101, 0.024, 0.015, 0.005, 3.202, 2.097, 0.786, 0.002};

    private double[] heatCapacity =new double[]{35.76, 52.71, 73.93, 97.15, 98.95, 120.62, 120.62, 184.2, 29.124, 37.23, 34.22, 75.35};


    private double isentropicExponent;
    private double specificHeat_cp = 0.56;
   private double inletTemperature = 48; // 48 degree celcisus or 321.15 K

    private double inletPresure;

    Gas( ){

        sumHeatCa_multiply_composition();
        Compute_isentropic_Exponent();

    }




    private double  sumHeatCa_multiply_composition(){
        double[] temp = new  double[composition.length];
        double sumAll = 0;

        for (int i = 0; i < composition.length; i++){
            temp[i] = composition[i] * heatCapacity[i];
            sumAll += temp[i];
        }
        return sumAll;
    }

   private double  Compute_isentropic_Exponent(){
       double sumAll = sumHeatCa_multiply_composition();
        isentropicExponent = sumAll/(sumAll-universalGasconstant);
        return isentropicExponent;
    }


    public double getIsentropicExponent() {
        return isentropicExponent;
    }

    public double getSpecificHeat_cp() {
        return specificHeat_cp;
    }

    public double getInletTemperature() {
        return inletTemperature;
    }
}
