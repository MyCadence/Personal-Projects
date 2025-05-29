public class Cycling extends Aerobic{
  @Override public Muscle[] muscleTargeted(){
    return new Muscle[] {Muscle.Glutes, Muscle.Cardio, Muscle.Legs};
  }
  
  @Override public String description(){
    return "Cycling";
  }
  
  @Override public double calorieLoss(Intensity intensity, double weight, int duration){
    if(intensity==Intensity.HIGH){
      return 14.0*weight*duration/60;
    }
    if(intensity==Intensity.MEDIUM){
      return 8.5*weight*duration/60;
    }
    if(intensity==Intensity.LOW){
      return 4.0*weight*duration/60;
    }
    return 0.0;
  }
  
}