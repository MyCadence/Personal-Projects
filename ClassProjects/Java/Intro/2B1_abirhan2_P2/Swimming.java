
public class Swimming extends Aerobic{
  
  private SwimmingType type;
  public Swimming(SwimmingType type){
    this.type = type;
  }
  public Swimming(){
    this.type = SwimmingType.Freestyle;
  }
  
  public void setSwimmingType(SwimmingType type){
    this.type = type;
  }
  
  public SwimmingType getSwimmingType(){
    return this.type;
  }
  
  @Override public Muscle[] muscleTargeted(){
    if(this.type==SwimmingType.Butterflystroke){
      return new Muscle[] {Muscle.Abs,Muscle.Back,Muscle.Shoulders,Muscle.Biceps,Muscle.Triceps};
    }
    if(this.type==SwimmingType.Breaststroke){
      return new Muscle[] {Muscle.Glutes,Muscle.Cardio};
    }
    if(this.type==SwimmingType.Freestyle){
      return new Muscle[] {Muscle.Arms,Muscle.Legs,Muscle.Cardio};
    }
    return null;
  }
  
  @Override public double calorieLoss(Intensity intensity, double weight, int duration){
    if(intensity==Intensity.HIGH){
      return 10.0*weight*duration/60;
    }
    if(intensity==Intensity.MEDIUM){
      return 8.3*weight*duration/60;
    }
    if(intensity==Intensity.LOW){
      return 6.0*weight*duration/60;
    }
    return 0.0;
  }
      
  @Override public String description(){
    return "Swimming";
  }
  
}