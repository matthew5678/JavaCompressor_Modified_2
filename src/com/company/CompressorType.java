package com.company;


public class CompressorType {


    // set outlet pressure
    private double inletPressure;
    private double outletPressure;

    private double inlet_Flow;
    private double efficiency;
    boolean compressorState;
    //Constriant
    private double ChokeFlow;
    private double StoneWallFlow;
    private double minimumPower;
    private double maximumPower;
    // compute Properties
    private double power;
    private double speed;
    private double workDone_adiabaticEthalphy;
    //


    private Gas gas = new Gas();  // bar
    private double isentropicHead;
   // private double efficiency;


        CompressorType(double efficiency){
            this.efficiency = efficiency;
            outletPressure = 30;
            inletPressure = 15;
    }


    //1
    public void setInlet_Flow(double inlet_Flow) {
        this.inlet_Flow = inlet_Flow;
    }

    //2
    public double computeIsentropicHead(){ // 1
        // workDone_adiabaticEthalphy =
       double  hs =  gas.getSpecificHeat_cp() *gas.getInletTemperature() * (Math.pow((outletPressure/inletPressure),
               (gas.getIsentropicExponent()-1/gas.getIsentropicExponent()))- 1);
      //System.out.println("isentropic head " + gas.getSpecificHeat_cp());
       return isentropicHead = hs;
    }

    //3
    public double computePower(){ //2
        double p = (inlet_Flow * isentropicHead)/efficiency;
        //not computing because isentropicHead = 0;
      // System.out.println("Power " + p);


        return  power = p;
    }

    public void setOutletPressure(double outletPressure) {
        this.outletPressure = outletPressure;
    }

    public void setInletPressure(double inletPressure) {
        this.inletPressure = inletPressure;
    }


    public double getPower() {
        return power;
    }

    public double getInlet_Flow() {
        return inlet_Flow;
    }
}



//  1)Compressor machine admit certain feasible combinations of throughput (volumetric flows) and specific change in adiabatic enthalpy.
//  The set of all possible combinations of throughput and specific change in adiabatic enthalpy is called the feasible operating rage of the machine {

