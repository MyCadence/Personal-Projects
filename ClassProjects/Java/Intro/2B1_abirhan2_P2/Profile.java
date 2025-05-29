enum Gender{MALE,FEMALE;}

public class Profile{
  private int age;
  private double height;
  private double weight;
  private Gender gender;
  public Profile(){}
  
  public Profile(int age, double height, double weight, Gender gender){
    this.age = age;
    this.height = height;
    this.weight = weight;
    this.gender = gender;
    
  }
  
  public void setHeight(double height){
    this.height = height;
  }
  public void setWeight(double weight){
    this.weight = weight;
  }
  public void setAge(int age){
    this.age = age;
  }
  public void setGender(Gender gender){
    this.gender = gender;
  }
  
  public double getHeight(){
    return height;
  }
  public double getWeight(){
    return weight;
  }
  public int getAge(){
    return age;
  }
  public Gender getGender(){
    return gender;
  }
  
  @Override public String toString(){
    return String.format("Age %d, Weight %.1fkg, Height %.1fm, Gender %s", age, weight, height, gender);
  }
  
  public double calcBMI(){
    return weight/(height*height);
  }
  
  public double dailyCalorieIntake(){
    if(gender==Gender.MALE){
      return 66.47 + (13.75 * weight) + (5.003*height*100) - (6.755*age);
    }
    if(gender==Gender.FEMALE){
      return 655.1 + (9.563 * weight) + (1.85*height*100) - (4.676*age);
    }
    return 0.0;
  }
    
  
}