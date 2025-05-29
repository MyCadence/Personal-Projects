public class PullUp extends Endurance{
  @Override public Muscle[] muscleTargeted(){
    return new Muscle[] {Muscle.Biceps, Muscle.Arms};
  }
  
  @Override public String description(){
    return "PullUp";
  }
  
  @Override public double calorieLoss(Intensity intensity, double weight, int duration){
    if(intensity==Intensity.HIGH){
      return 7.5*weight*duration/60;
    }
    if(intensity==Intensity.MEDIUM){
      return 6.0*weight*duration/60;
    }
    if(intensity==Intensity.LOW){
      return 4.8*weight*duration/60;
    }
    return 0.0;
  }
  
}