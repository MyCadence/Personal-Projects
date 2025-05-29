public class DailyExercise{
  private Fitness[] exerciseList;
  private int duration;
  private double calorieTarget;
  private Profile profile;
  
  public DailyExercise(Fitness[] exerciseList, int duration, double calorieTarget, Profile profile){
    this.exerciseList = exerciseList;
    this.duration = duration;
    this.calorieTarget = calorieTarget;
    this.profile = profile;
  }
  public DailyExercise(Fitness[] exerciseList, Profile profile){
    this.duration = 60;
    this.calorieTarget = 500;
    this.exerciseList = exerciseList;
    this.profile = profile;
  }
  
  public int getSize(){
    return exerciseList.length;
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
  public void setDuration(int duration){
    this.duration = duration;
  }
  public void setCalorieTarget(double target){
    this.calorieTarget = target;
  }
  public void setProfile(Profile profile){
    this.profile = profile;
  }
  
  public Fitness[] getExerciseList(){
    return exerciseList;
  }
  public int getDuration(){
    return duration;
  }
  public double getCalorieTarget(){
    return calorieTarget;
  }
  public Profile getProfile(){
    return profile;
  }
  
  public Fitness[] getAllExercises(Muscle[] targetMuscle){
    Muscle[][] muscleContainer = new Muscle[exerciseList.length][];
    for(int i=0; i<muscleContainer.length; i++){
      muscleContainer[i] = exerciseList[i].muscleTargeted();
    }//make a 2d array of muscles, each list is an exercise of a list of muscle targets
    
    int equalCounter=0;
    Fitness[] updated;
    int updatedCounter=0;
    
    for(int i=0; i<muscleContainer.length; i++){
      for(int j=0; j<muscleContainer[i].length; j++){
        for(int h=0; h<targetMuscle.length; h++){
          if(muscleContainer[i][j]==targetMuscle[h]){
            equalCounter++;
          }
        }
      }
      if(equalCounter>=1){
        updatedCounter++;
      }
      equalCounter=0;
    }
    if(updatedCounter==0){
      return null;
    }
    
    updated = new Fitness[updatedCounter];
    updatedCounter=0;
    for(int i=0; i<muscleContainer.length; i++){
      for(int j=0; j<muscleContainer[i].length; j++){
        for(int h=0; h<targetMuscle.length; h++){
          if(muscleContainer[i][j]==targetMuscle[h]){
            equalCounter++;
          }
        }
      }
      if(equalCounter>=1){
        updated[updatedCounter] = exerciseList[i];
        updatedCounter++;
      }
      equalCounter=0;
    }
    return updated;
    
  }
  
  public Fitness[] getExercises(Muscle[] targetMuscle){//arms,legs,back
    Muscle[][] muscleContainer = new Muscle[exerciseList.length][];
    for(int i=0; i<muscleContainer.length; i++){
      muscleContainer[i] = exerciseList[i].muscleTargeted();
    }//make a 2d array of muscles, each list is an exercise of a list of muscle targets
    
    int equalCounter=0;
    Fitness[] updated;
    int updatedCounter=0;
    
    for(int i=0; i<muscleContainer.length; i++){
      for(int j=0; j<muscleContainer[i].length; j++){
        for(int h=0; h<targetMuscle.length; h++){
          if(muscleContainer[i][j]==targetMuscle[h]){
            equalCounter++;
          }
        }
      }
      if(equalCounter>=targetMuscle.length){
        updatedCounter++;
      }
      equalCounter=0;
    }
    if(updatedCounter==0){
      return null;
    }
    
    updated = new Fitness[updatedCounter];
    updatedCounter=0;
    for(int i=0; i<muscleContainer.length; i++){
      for(int j=0; j<muscleContainer[i].length; j++){
        for(int h=0; h<targetMuscle.length; h++){
          if(muscleContainer[i][j]==targetMuscle[h]){
            equalCounter++;
          }
        }
      }
      if(equalCounter>=targetMuscle.length){
        updated[updatedCounter] = exerciseList[i];
        updatedCounter++;
      }
      equalCounter=0;
    }
    return updated;
    
  }
}