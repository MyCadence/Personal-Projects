public class TaiChi extends Flexibility{
  @Override public Muscle[] muscleTargeted(){
    return new Muscle[] {Muscle.Arms, Muscle.Legs};
  }
  
  @Override public String description(){
    return "TaiChi";
  }
  
  @Override public double calorieLoss(Intensity intensity, double weight, int duration){
    if(intensity==Intensity.HIGH){
      return 5.0*weight*duration/60;
    }
    if(intensity==Intensity.MEDIUM){
      return 3.0*weight*duration/60;
    }
    if(intensity==Intensity.LOW){
      return 1.5*weight*duration/60;
    }
    return 0.0;
  }
  
}