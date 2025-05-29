public class Plyometrics extends Anaerobic{
  @Override public Muscle[] muscleTargeted(){
    return new Muscle[] {Muscle.Abs, Muscle.Legs, Muscle.Glutes};
  }
  
  @Override public String description(){
    return "Plyometrics";
  }
  
  @Override public double calorieLoss(Intensity intensity, double weight, int duration){
    if(intensity==Intensity.HIGH){
      return 7.4*weight*duration/60;
    }
    if(intensity==Intensity.MEDIUM){
      return 4.8*weight*duration/60;
    }
    if(intensity==Intensity.LOW){
      return 2.5*weight*duration/60;
    }
    return 0.0;
  }
  
}