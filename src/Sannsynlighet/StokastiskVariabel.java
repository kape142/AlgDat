package Sannsynlighet;

class StokastiskVariabel {
    private double[] x;
    private double[] px;
    private int length;
    private double expectation=Double.MAX_VALUE;
    private double variance=Double.MAX_VALUE;
    private double standarddeviation=Double.MAX_VALUE;


    StokastiskVariabel(double[] x, double[] px){
        if(x.length != px.length){
            throw new IllegalArgumentException();
        }
        this.px = px;
        this.x = x;
        this.length=x.length;
    }

    double expectation(){
        double expectation = 0.0;
        for (int i = 0; i < length; i++) {
            expectation+=x[i]*px[i];
        }
        this.expectation=expectation;
        return expectation;
    }

    double variance(){
        if(expectation==Double.MAX_VALUE){
            expectation();
        }
        double variance = 0.0;
        for (int i = 0; i < length; i++) {
            variance+=Math.pow(x[i]-expectation,2)*px[i];
        }
        this.variance=variance;
        return variance;
    }

    double standardDeviation(){
        if(standarddeviation!= Double.MAX_VALUE){
            return standarddeviation;
        }
        if(variance==Double.MAX_VALUE){
            variance();
        }
        standarddeviation = Math.sqrt(variance);
        return standarddeviation;
    }

    double distributiveFunction(double input){
        double output = 0.0;
        for (int i = 0; i < length; i++) {
              if(x[i]>input){
                  return output;
              }
              output+=px[i];
        }
        return output;
    }
}
