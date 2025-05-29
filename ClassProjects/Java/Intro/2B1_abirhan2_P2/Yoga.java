public class Yoga extends Flexibility{
  @Override public Muscle[] muscleTargeted(){
    return new Muscle[] {Muscle.Triceps, Muscle.Biceps};
  }
  
  @Override public String description(){
    return "Yoga";
  }
  
  @Override public double calorieLoss(Intensity intensity, double weight, int duration){
    if(intensity==Intensity.HIGH){
      return 4.0*weight*duration/60;
    }
    if(intensity==Intensity.MEDIUM){
      return 3.0*weight*duration/60;
    }
    if(intensity==Intensity.LOW){
      return 2.0*weight*duration/60;
    }
    return 0.0;
  }
  
}