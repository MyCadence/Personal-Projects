public class WeeklyExercise{
  private Fitness[] exerciseList;
  private int days;
  private double weeklyCalorieTarget;
  private Profile profile;
  private DailyExercise dailyExercise;
  public WeeklyExercise(Fitness[] exerciseList, int days, double weeklyCalorieTarget, Profile profile){
    this.exerciseList = exerciseList;
    this.days = days;
    this.weeklyCalorieTarget = weeklyCalorieTarget;
    this.profile = profile;
  }
  public WeeklyExercise(Fitness[] exerciseList, Profile profile){
    this.exerciseList = exerciseList;
    this.profile = profile;
    this.days = 7;
    this.weeklyCalorieTarget = 3500;
  }
  
  public void addExercise(Fitness ex){
    int counterNull=0;
    for(int i=0; i<exerciseList.length; i++){
      if(exerciseList[i]==null){
        exerciseList[i] = ex;
        counterNull++;
      }
    }
    if(counterNull==0){
      Fitness[] updated = new Fitness[exerciseList.length+1];
      for(int i=0; i<exerciseList.length; i++){
        updated[i] = exerciseList[i];
      }
      updated[exerciseList.length] = ex;
      exerciseList = updated.clone();
    }
  }
  
  public void removeExercise(Fitness ex){
    for(int i=0; i<exerciseList.length; i++){
      if(exerciseList[i]==ex){
        exerciseList[i]=null;
      }
    }
  }
  
  public void setExerciseList(Fitness[] list){
    this.exerciseList = list;
  }
  public void setDays(int days){
    this.days = days;
  }
  public void setWeeklyCalorieTarget(double target){
    this.weeklyCalorieTarget = target;
  }
  public void setProfile(Profile profile){
    this.profile = profile;
  }
  
  public Fitness[] getExerciseList(){
    return exerciseList;
  }
  public int getSize(){
    return exerciseList.length;
  }
  public int getDays(){
    return days;
  }
  public Profile getProfile(){
    return profile;
  }
  public double getWeeklyCalorieTarget(){
    return weeklyCalorieTarget;
  }
  
  public DailyExercise[] getWeeklyExercises(Intensity intensity){
    double splitCal = weeklyCalorieTarget/days;
    DailyExercise[] Array1 = new DailyExercise[days];
    for(int i=0; i<Array1.length; i++){
      Array1[i] = new DailyExercise(exerciseList, (int) (splitCal/exerciseList[i].calorieLoss(intensity, profile.getWeight(), 1)), weeklyCalorieTarget, profile);
    }
    return Array1;
  }
  public DailyExercise[] getWeeklyExercises(){
    double splitCal = weeklyCalorieTarget/days;
    DailyExercise[] Array1 = new DailyExercise[days];
    for(int i=0; i<Array1.length; i++){
      Array1[i] = new DailyExercise(exerciseList, (int) (splitCal/exerciseList[i].calorieLoss(Intensity.LOW, profile.getWeight(), 1)), weeklyCalorieTarget, profile);
    }
    return Array1;
  }
  public String targetedCalorieLoss(double targetWeight, int withInDays){
    double currentWeight = profile.getWeight();
    double loseWeight = currentWeight-targetWeight;
    if(currentWeight<targetWeight){
      return "Only works to lose weight";
    }
    double calories = 7000.00*loseWeight/(double)withInDays;
    double fCalories = profile.dailyCalorieIntake()-calories;
    return String.format("You need to lose %.2f calories per day or decrease your intake from %.2f to %.2f in order to lose %.2f kg of weight", calories,profile.dailyCalorieIntake(),fCalories,loseWeight);
  }
}