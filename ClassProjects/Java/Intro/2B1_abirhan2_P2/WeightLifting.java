public class WeightLifting extends Anaerobic{
  @Override public Muscle[] muscleTargeted(){
    return new Muscle[] {Muscle.Shoulders, Muscle.Legs, Muscle.Arms, Muscle.Triceps};
  }
  
  @Override public String description(){
    return "WeightLifting";
  }
  
  @Override public double calorieLoss(Intensity intensity, double weight, int duration){
    if(intensity==Intensity.HIGH){
      return 6.0*weight*duration/60;
    }
    if(intensity==Intensity.MEDIUM){
      return 5.0*weight*duration/60;
    }
    if(intensity==Intensity.LOW){
      return 3.5*weight*duration/60;
    }
    return 0.0;
  }
  
}