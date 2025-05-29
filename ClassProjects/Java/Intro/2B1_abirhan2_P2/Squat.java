public class Squat extends Endurance{
  @Override public Muscle[] muscleTargeted(){
    return new Muscle[] {Muscle.Glutes, Muscle.Abs, Muscle.Back};
  }
  
  @Override public String description(){
    return "Squat";
  }
  
  @Override public double calorieLoss(Intensity intensity, double weight, int duration){
    if(intensity==Intensity.HIGH){
      return 7.0*weight*duration/60;
    }
    if(intensity==Intensity.MEDIUM){
      return 5.0*weight*duration/60;
    }
    if(intensity==Intensity.LOW){
      return 2.5*weight*duration/60;
    }
    return 0.0;
  }
  
}