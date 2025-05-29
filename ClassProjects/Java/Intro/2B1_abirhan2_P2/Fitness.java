

public interface Fitness{
  public Muscle[] muscleTargeted();
  
  public double calorieLoss(Intensity intensity, double weight, int duration);
  
  public String description();
  
}