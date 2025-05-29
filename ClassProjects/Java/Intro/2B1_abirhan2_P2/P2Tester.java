/** 
 * On Mac/Linux:
 *  javac -cp .:junit-cs211.jar *.java         # compile everything
 *  java -cp .:junit-cs211.jar P2Tester        # run tests
 * 
 * On windows replace colons with semicolons: (: with ;)
 *  javac -cp .;junit-cs211.jar *.java         # compile everything
 *  java -cp .;junit-cs211.jar P2Tester        # run tests
 */
import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

public class P2Tester {
  public static void main(String args[]){
      org.junit.runner.JUnitCore.main("P2Tester");
    }
  
 
           private final double ERR = 0.0001;
    @Test(timeout =1000) public void swimming_construct_def_01() { 
      Swimming s1 = new Swimming();
      assertTrue("Swimming should be Aerobic", (Object)s1 instanceof Aerobic);
      assertTrue("Swimming should be Fitness", (Object)s1 instanceof Fitness);
      assertEquals("Default constructor Swimming() is incorrect", SwimmingType.Freestyle, s1.getSwimmingType());
    }
      @Test(timeout =1000) public void swimming_construct_def_02() { 
          Swimming s1 = new Swimming(SwimmingType.Breaststroke);
          Swimming s2 = new Swimming(SwimmingType.Butterflystroke);
          Swimming s3 = new Swimming(SwimmingType.Freestyle);
          assertEquals("Constructor Swimming(Breastsstroke) is incorrect", SwimmingType.Breaststroke, s1.getSwimmingType());
          assertEquals("Constructor Swimming(Butterflystroke) is incorrect", SwimmingType.Butterflystroke, s2.getSwimmingType());
          assertEquals("Constructor Swimming(Freestyle) is incorrect", SwimmingType.Freestyle, s3.getSwimmingType());
        }

      Swimming s1[] = {new Swimming(SwimmingType.Breaststroke),new Swimming(SwimmingType.Butterflystroke),new Swimming(SwimmingType.Freestyle)};
      Intensity i[] = {Intensity.LOW,Intensity.HIGH,Intensity.MEDIUM};
      double weight[] = {65,89,125};
      int duration[]= {25,97,45};
      double result_Swimming[] = {162.5,1438.8334,778.125};
      Muscle [][] mus = {{Muscle.Glutes,Muscle.Cardio},{Muscle.Abs,Muscle.Back,Muscle.Shoulders,Muscle.Biceps,Muscle.Triceps},{Muscle.Arms,Muscle.Legs,Muscle.Cardio}};
      
     void swimming_calorieLoss(int a)
      {
       double res = s1[a].calorieLoss(i[a], weight[a], duration[a]);
         String errMsg1 = String.format("Swimming calorieLoss( %f,%s)incorrect", weight[a], duration[a]);
         assertEquals(errMsg1, result_Swimming[a], res,ERR);
           
      } 
      @Test(timeout =1000) public void swimming_calorieLoss_00() { swimming_calorieLoss(0); }
      @Test(timeout =1000) public void swimming_calorieLoss_01() { swimming_calorieLoss(1); }
      @Test(timeout =1000) public void swimming_calorieLoss_02() { swimming_calorieLoss(2); }
      
      void swimming_muscleTargeted(int a)
      {
       Muscle [] res = s1[a].muscleTargeted();
         String errMsg1 = String.format("Swimming muscleTargeted( ) incorrect");
         assertArrayEquals(errMsg1, mus[a], res);
      }
      
      @Test(timeout =1000) public void swimming_muscleTargeted_00() { swimming_muscleTargeted(0); }
      @Test(timeout =1000) public void swimming_muscleTargeted_01() { swimming_muscleTargeted(1); }
      @Test(timeout =1000) public void swimming_muscleTargeted_02() { swimming_muscleTargeted(2); }
   
      @Test(timeout =1000) public void swimming_description()
      {
          String errMsg1 = String.format("Swimming description() incorrect");
         assertTrue(errMsg1, "Swimming".equals(s1[0].description()));
      }

      
      
      Fitness c = new Cycling();
      
      @Test(timeout =1000) public void cycling_inheritance() {
         Object o = new Cycling();
         assertTrue("Cycling should be Aerobic", o instanceof Aerobic);
         assertTrue("Cycling should be Fitness", o instanceof Fitness);
      }
      
      double result_Cycling[] = {108.333336,2014.3667,796.875};
      
      void cycling_calorieLoss(int a)
      {
       double res = c.calorieLoss(i[a], weight[a], duration[a]);
         String errMsg1 = String.format("Cycling calorieLoss( %f,%s)incorrect", weight[a], duration[a]);
         assertEquals(errMsg1, result_Cycling[a], res, ERR);
           
      } 
      @Test(timeout =1000) public void cycling_calorieLoss_00() { cycling_calorieLoss(0); }
      @Test(timeout =1000) public void cycling_calorieLoss_01() { cycling_calorieLoss(1); }
      @Test(timeout =1000) public void cycling_calorieLoss_02() { cycling_calorieLoss(2); }
      
      
      void cycling_muscleTargeted(int a)
      {
       Muscle [] res = c.muscleTargeted();
       Muscle[] m = {Muscle.Glutes,Muscle.Cardio,Muscle.Legs};
         String errMsg1 = String.format("Cycling muscleTargeted( ) incorrect");
         assertArrayEquals(errMsg1, m, res);
      }
      
      @Test(timeout =1000) public void cycling_muscleTargeted_00() { cycling_muscleTargeted(0); }
      
   
      @Test(timeout =1000) public void cycling_description()
      {
          String errMsg1 = String.format("Cycling description() incorrect");
         assertEquals(errMsg1, "Cycling", c.description());
      }
      
      
      Plyometrics p = new Plyometrics();
      
      @Test(timeout =1000) public void plyometrics_inheritance() {
         Object o = new Plyometrics();
         assertTrue("Plyometrics should be Anaerobic", o instanceof Anaerobic);
         assertTrue("Plyometrics should be Fitness", o instanceof Fitness);
      }

      double result_Plyometrics[] = {67.708336,1064.7367,450.0};
      void plyometrics_calorieLoss(int a)
      {
       double res = p.calorieLoss(i[a], weight[a], duration[a]);
         String errMsg1 = String.format("Plyometrics calorieLoss( %f,%s)incorrect", weight[a], duration[a]);
         assertEquals(errMsg1, result_Plyometrics[a], res, ERR);
           
      } 
      @Test(timeout =1000) public void plyometrics_calorieLoss_00() { plyometrics_calorieLoss(0); }
      @Test(timeout =1000) public void plyometrics_calorieLoss_01() { plyometrics_calorieLoss(1); }
      @Test(timeout =1000) public void plyometrics_calorieLoss_02() { plyometrics_calorieLoss(2); }
      
      void plyometrics_muscleTargeted(int a)
      {
       Muscle [] res = p.muscleTargeted();
      Muscle[] m =  {Muscle.Abs,Muscle.Legs,Muscle.Glutes};
         String errMsg1 = String.format("Plyometrics muscleTargeted( ) incorrect");
         assertArrayEquals(errMsg1, m, res);
      }
      
      @Test(timeout =1000) public void plyometrics_muscleTargeted_00() { plyometrics_muscleTargeted(0); }
      
   
     @Test(timeout =1000) public void plyometrics_description()
      {
          String errMsg1 = String.format("Plyometrics description() incorrect");
         assertEquals(errMsg1, "Plyometrics", p.description());
      }
      
      
      WeightLifting w = new WeightLifting();
     
      @Test(timeout =1000) public void weightlifting_inheritance() {
         Object o = new WeightLifting();
         assertTrue("WeightLifting should be Anaerobic", o instanceof Anaerobic);
         assertTrue("WeightLifting should be Fitness", o instanceof Fitness);
      }

      double result_WeightLifting[] = {94.791664,863.3,468.75};
      void weightLifting_calorieLoss(int a)
      {
       double res = w.calorieLoss(i[a], weight[a], duration[a]);
         String errMsg1 = String.format("WeightLifting calorieLoss( %f,%s)incorrect", weight[a], duration[a]);
         assertEquals(errMsg1, result_WeightLifting[a], res, ERR);
           
      } 
      @Test(timeout =1000) public void weightLifting_calorieLoss_00() { weightLifting_calorieLoss(0); }
      @Test(timeout =1000) public void weightLifting_calorieLoss_01() { weightLifting_calorieLoss(1); }
      @Test(timeout =1000) public void weightLifting_calorieLoss_02() { weightLifting_calorieLoss(2); }
      
      void weightLifting_muscleTargeted(int a)
      {
       Muscle [] res = w.muscleTargeted();
      Muscle[] m =  {Muscle.Shoulders,Muscle.Legs,Muscle.Arms,Muscle.Triceps};
         String errMsg1 = String.format("weightLifting muscleTargeted( ) incorrect");
         assertArrayEquals(errMsg1, m, res);
      }
      
      @Test(timeout =1000) public void weightLifting_muscleTargeted_00() { weightLifting_muscleTargeted(0); }
      
   
      @Test(timeout =1000) public void  weightLifting_description()
      {
          String result = w.description();
          String st = new String("WeightLifting");
       String errMsg1 = String.format("weightLifting description incorrect");
         assertTrue(errMsg1, result.equals(st)  );
      }
      
      
      PullUp pu = new PullUp();
      
      @Test(timeout =1000) public void pullup_inheritance() {
         Object o = new PullUp();
         assertTrue("PullUp should be Endurance", o instanceof Endurance);
         assertTrue("PullUp should be Fitness", o instanceof Fitness);
      }
      
      double result_PullUp[] = {130.0,1079.125,562.5};
      void pullUp_calorieLoss(int a)
      {
       double res = pu.calorieLoss(i[a], weight[a], duration[a]);
         String errMsg1 = String.format("PullUp calorieLoss( %f,%s)incorrect", weight[a], duration[a]);
         assertEquals(errMsg1, result_PullUp[a], res, ERR);
           
      } 
      @Test(timeout =1000) public void pullUp_calorieLoss_00() {pullUp_calorieLoss(0); }
      @Test(timeout =1000) public void pullUp_calorieLoss_01() { pullUp_calorieLoss(1); }
      @Test(timeout =1000) public void pullUp_calorieLoss_02() { pullUp_calorieLoss(2); }
      
      void pullUp_muscleTargeted(int a)
      {
       Muscle [] res = pu.muscleTargeted();
      Muscle[] m =  {Muscle.Biceps,Muscle.Arms};
         String errMsg1 = String.format("PullUp muscleTargeted( ) incorrect");
         assertArrayEquals(errMsg1, m, res);
      }
      
      @Test(timeout =1000) public void pullUp_muscleTargeted_00() { pullUp_muscleTargeted(0); }
      
   
      @Test(timeout =1000) public void pullUp_description()
      {
          String errMsg1 = String.format("PullUp description incorrect");
         assertEquals(errMsg1, "PullUp", pu.description());
      }
      
      Squat s = new Squat();
      
      @Test(timeout =1000) public void squat_inheritance() {
         Object o = new Squat();
         assertTrue("Squat should be Endurance", o instanceof Endurance);
         assertTrue("Squat should be Fitness", o instanceof Fitness);
      }

      double result_Squat[] = {67.708336,1007.18335, 468.75};
      void squat_calorieLoss(int a)
      {
       double res = s.calorieLoss(i[a], weight[a], duration[a]);
         String errMsg1 = String.format("Squat calorieLoss( %f,%s)incorrect", weight[a], duration[a]);
         assertEquals(errMsg1, result_Squat[a], res, ERR);
           
      } 
      @Test(timeout =1000) public void squat_calorieLoss_00() {squat_calorieLoss(0); }
      @Test(timeout =1000) public void squat_calorieLoss_01() { squat_calorieLoss(1); }
      @Test(timeout =1000) public void squat_calorieLoss_02() { squat_calorieLoss(2); }
      
      void squat_muscleTargeted(int a)
      {
       Muscle [] res = s.muscleTargeted();
      Muscle[] m =  {Muscle.Glutes,Muscle.Abs,Muscle.Back};
         String errMsg1 = String.format("Squat muscleTargeted( ) incorrect");
         assertArrayEquals(errMsg1, m, res);
      }
      
      @Test(timeout =1000) public void squat_muscleTargeted_00() { squat_muscleTargeted(0); }
      
   
      @Test(timeout =1000) public void squat_description()
      {
          String errMsg1 = String.format("Squat description incorrect");
         assertEquals(errMsg1, "Squat", s.description());
      }
      
      TaiChi t = new TaiChi();
      
      @Test(timeout =1000) public void taichi_inheritance() {
         Object o = new TaiChi();
         assertTrue("TaiChi should be Flexibility", o instanceof Flexibility);
         assertTrue("TaiChi should be Fitness", o instanceof Fitness);
      }


      
      double result_TaiChi[] = {40.625, 719.4167, 281.25};
      void taiChi_calorieLoss(int a)
      {
       double res = t.calorieLoss(i[a], weight[a], duration[a]);
         String errMsg1 = String.format("TaiChi calorieLoss( %f,%s)incorrect", weight[a], duration[a]);
         assertEquals(errMsg1, result_TaiChi[a], res, ERR);
           
      } 
      @Test(timeout =1000) public void taiChi_calorieLoss_00() {taiChi_calorieLoss(0); }
      @Test(timeout =1000) public void taiChi_calorieLoss_01() { taiChi_calorieLoss(1); }
      @Test(timeout =1000) public void taiChi_calorieLoss_02() { taiChi_calorieLoss(2); }
      
      void taiChi_muscleTargeted(int a)
      {
       Muscle [] res = t.muscleTargeted();
      Muscle[] m =  {Muscle.Arms,Muscle.Legs};
         String errMsg1 = String.format("TaiChi muscleTargeted( ) incorrect");
         assertArrayEquals(errMsg1, m, res);
      }
      
      @Test(timeout =1000) public void taiChi_muscleTargeted_00() { taiChi_muscleTargeted(0); }
      
   
      @Test(timeout =1000) public void taiChi_description()
      {
          String errMsg1 = String.format("TaiChi description incorrect");
         assertEquals(errMsg1, "TaiChi", t.description());
      }
      
      
      Yoga y = new Yoga();

      @Test(timeout =1000) public void yoga_inheritance() {
         Object o = new Yoga();
         assertTrue("Yoga should be Flexibility", o instanceof Flexibility);
         assertTrue("Yoga should be Fitness", o instanceof Fitness);
      }

      double result_Yoga[] = {54.166668, 575.5333, 281.25};

      void yoga_calorieLoss(int a)
      {
       double res = y.calorieLoss(i[a], weight[a], duration[a]);
         String errMsg1 = String.format("Yoga calorieLoss( %f,%s)incorrect", weight[a], duration[a]);
         assertEquals(errMsg1, result_Yoga[a], res, ERR);
           
      } 
      @Test(timeout =1000) public void yoga_calorieLoss_00() {yoga_calorieLoss(0); }
      @Test(timeout =1000) public void yoga_calorieLoss_01() { yoga_calorieLoss(1); }
      @Test(timeout =1000) public void yoga_calorieLoss_02() { yoga_calorieLoss(2); }
      
      void yoga_muscleTargeted(int a)
      {
       Muscle [] res = y.muscleTargeted();
      Muscle[] m =  {Muscle.Triceps,Muscle.Biceps};
         String errMsg1 = String.format("Yoga muscleTargeted( ) incorrect");
         assertArrayEquals(errMsg1, m, res);
      }
      
      @Test(timeout =1000) public void yoga_muscleTargeted_00() { yoga_muscleTargeted(0); }
      
   
      @Test(timeout =1000) public void yoga_description()
      {
          String errMsg1 = String.format("Yoga description() incorrect");
         assertEquals(errMsg1, "Yoga", y.description());
      }
      
      
      
  //***************Profile class Tests ********************************* 
      
      
      Profile pro = new Profile(36,1.7f, 87,Gender.MALE);
      
      @Test(timeout =1000) public void profile_construct_def_02() { 
          assertEquals("Constructor Profile() is incorrect", 36, pro.getAge());
          assertEquals("Constructor Profile() is incorrect", 1.7f, pro.getHeight(),ERR);
          assertEquals("Constructor Profile() is incorrect", 87, pro.getWeight(),ERR);
          assertEquals("Constructor Profile() is incorrect", Gender.MALE, pro.getGender());
        }
    
      
      
 
      int [] xValues = {20,70,65,25};
    double [] yValues=  {2.0,1.65,1.5,1.9};
    double [] zValues=  {72,63,120.5f,45};
    Gender [] g = {Gender.MALE,Gender.FEMALE,Gender.MALE,Gender.FEMALE};
    double BMI [] = {18.0f,23.140497,53.555557,12.465374};
    double cal[]= {1921.9700000000003, 1235.499,2034.72,  1320.0349999999999};
   
    
    void checkSetAge(int a) {
     int x = xValues[a];
     pro.setAge(x);
       String errMsg1 = String.format("Profile setAge() incorrect for  %d,", xValues[a]);
         assertEquals(errMsg1, x, pro.getAge());
     
    }
     
    @Test(timeout =1000) public void checkSetAge_00() { checkSetAge(0); }
    @Test(timeout =1000) public void checkSetAge_01() { checkSetAge(1); }
    @Test(timeout =1000) public void checkSetAge_02() { checkSetAge(2); }
    @Test(timeout =1000) public void checkSetAge_03() { checkSetAge(3); }
   

    void checkSetHeight(int a) {
              
      double y = yValues[a];
      pro.setHeight(y);
      String errMsg1 = String.format("Profile setHeight() incorrect for  %f,", yValues[a]);
       
       assertEquals(errMsg1, y, pro.getHeight(),ERR);
       
      }
       
      @Test(timeout =1000) public void checkSetHeight_00() { checkSetHeight(0); }
      @Test(timeout =1000) public void checkSetHeight_01() { checkSetHeight(1); }
      @Test(timeout =1000) public void checkSetHeight_02() { checkSetHeight(2); }
      @Test(timeout =1000) public void checkSetHeight_03() { checkSetHeight(3); }
       
      
      void checkSetWeight(int a) {
        
        double z = zValues[a];
        pro.setWeight(z);
       
           String errMsg1 = String.format("Profile setWeight() incorrect for  %f,", zValues[a]);
         
         assertEquals(errMsg1, z, pro.getWeight(),ERR);
         
        }
         
        @Test(timeout =1000) public void checkSetWeight_00() { checkSetWeight(0); }
        @Test(timeout =1000) public void checkSetWeight_01() { checkSetWeight(1); }
        @Test(timeout =1000) public void checkSetWeight_02() { checkSetWeight(2); }
        @Test(timeout =1000) public void checkSetWeight_03() { checkSetWeight(3); }
       
        void checkSetGender(int a) {
          
          Gender gs= g[a];
          pro.setGender(gs);
             String errMsg1 = String.format("Profile setGender() incorrect ");
           
           assertEquals(errMsg1, gs, pro.getGender());
           
          }
           
          @Test(timeout =1000) public void checkSetGender_00() { checkSetGender(0); }
          @Test(timeout =1000) public void checkSetGender_01() { checkSetGender(1); }
          @Test(timeout =1000) public void checkSetGender_02() { checkSetGender(2); }
          @Test(timeout =1000) public void checkSetGender_03() { checkSetGender(3); }
          
          
          void checkDailyCalorieIntake(int a) {
           
           Profile pro = new Profile(xValues[a],yValues[a],zValues[a],g[a]);
              String errMsg1 = String.format("Profile dailyCalorieIntake() incorrect ");
            
            assertEquals(errMsg1, cal[a], pro.dailyCalorieIntake(),ERR);
            
           }
            
           @Test(timeout =1000) public void checkDailyCalorieIntake_00() { checkDailyCalorieIntake(0); }
           @Test(timeout =1000) public void checkDailyCalorieIntake_01() { checkDailyCalorieIntake(1); }
           @Test(timeout =1000) public void checkDailyCalorieIntake_02() { checkDailyCalorieIntake(2); }
           @Test(timeout =1000) public void checkDailyCalorieIntake_03() { checkDailyCalorieIntake(3); }
           
           void checkCalcBMI(int a) {
             
            Profile pro = new Profile(xValues[a],yValues[a],zValues[a],g[a]);
               String errMsg1 = String.format("Profile calcBMI() incorrect ");
             
             assertEquals(errMsg1, BMI[a], pro.calcBMI(),ERR);
             
            }
             
            @Test(timeout =1000) public void checkCalcBMI_00() { checkCalcBMI(0); }
            @Test(timeout =1000) public void checkCalcBMI_01() { checkCalcBMI(1); }
            @Test(timeout =1000) public void checkCalcBMI_02() { checkCalcBMI(2); }
            @Test(timeout =1000) public void checkCalcBMI_03() { checkCalcBMI(3); }            

            
            void checkProfile_toString(int a) {
            Profile p = new Profile(xPValues[a],yPValues[a],zPValues[a],gP[a]); 
         String result  =String.format("Age %d, Weight %.1fkg, Height %.1fm, Gender %s",xPValues[a],zPValues[a],yPValues[a],gP[a]);
               String errMsg1 = String.format("Profile toString() incorrect");
                 assertEquals(errMsg1, result, p.toString());
             
            }
             
            @Test(timeout =1000) public void checkProfile_toString_00() { checkProfile_toString(0); }
            @Test(timeout =1000) public void checkProfile_toString_01() { checkProfile_toString(1); }
            @Test(timeout =1000) public void checkProfile_toString_02() { checkProfile_toString(2); }
            @Test(timeout =1000) public void checkProfile_toString_03() { checkProfile_toString(3); }
         
            
  //****************DailyExercise class Tests *********************************   
            Muscle[] []targetMuscle = {{Muscle.Biceps,Muscle.Arms},{Muscle.Glutes,Muscle.Legs,Muscle.Cardio},{Muscle.Glutes,Muscle.Legs,Muscle.Abs},{Muscle.Legs},{Muscle.Glutes,Muscle.Legs,Muscle.Abs,Muscle.Triceps,Muscle.Biceps}};
            Fitness fit [] = {new Cycling(),new WeightLifting(),new TaiChi(),new Yoga(),new PullUp()};
            Fitness newFit[] = {new Squat(), new Swimming(SwimmingType.Breaststroke), new Swimming(), new Squat()};
      int sizeAdd[] = {6,6,6,6};
      int sizeAddWeekly[] = {6,6,6,6};
      Profile pro2 = new Profile(36,1.7f, 87,Gender.MALE);
     
            
      @Test(timeout =1000) public void dailyExercise_construct_def_01() { 
         
      DailyExercise d = new DailyExercise(fit,45,450,pro2);
            assertArrayEquals("constructor DailyExercise() is incorrect", fit, d.getExerciseList());
            assertEquals("constructor DailyExercise() is incorrect", 45, d.getDuration());
            assertEquals("constructor DailyExercise() is incorrect", 450, d.getCalorieTarget(),ERR);
            assertEquals("constructor DailyExercise() is incorrect", pro2, d.getProfile());
      }
      
      @Test(timeout =1000) public void dailyExercise_construct_def_02() { 
             
          DailyExercise d = new DailyExercise(fit,pro2);
                assertArrayEquals("constructor DailyExercise() is incorrect", fit, d.getExerciseList());
                assertEquals("constructor DailyExercise() is incorrect", 60, d.getDuration());
                assertEquals("constructor DailyExercise() is incorrect", 500, d.getCalorieTarget(),ERR);
                assertEquals("constructor DailyExercise() is incorrect", pro2, d.getProfile());
          }
          
     
      int [] xDuration = {20,70,65,25};
         double [] yCalorie=  {455,1000,360,75};

         int [] xPValues = {20,70,65,25};
            double [] yPValues=  {2.0,1.65,1.5,1.9};
            double [] zPValues=  {72,63,120.5,45};
            Gender [] gP = {Gender.MALE,Gender.FEMALE,Gender.MALE,Gender.FEMALE};
            
             
        
         
         void checkDailyExerciseSet_GetProfile(int a) {
         Profile p = new Profile(xPValues[a],yPValues[a],zPValues[a],gP[a]); 
         DailyExercise d = new DailyExercise(fit,xDuration[a],yCalorie[a],p);
            String errMsg1 = String.format("DailyExercise get/setProfile() incorrect");
              assertEquals(errMsg1, p, d.getProfile());
          
         }
          
         @Test(timeout =1000) public void checkDailyExerciseSet_GetProfile_00() { checkDailyExerciseSet_GetProfile(0); }
         @Test(timeout =1000) public void checkDailyExerciseSet_GetProfile_01() { checkDailyExerciseSet_GetProfile(1); }
         @Test(timeout =1000) public void checkDailyExerciseSet_GetProfile_02() { checkDailyExerciseSet_GetProfile(2); }
         @Test(timeout =1000) public void checkDailyExerciseSet_GetProfile_03() { checkDailyExerciseSet_GetProfile(3); }
    
         
         
         
         void checkDailyExerciseSet_GetDuration(int a) {
            Profile p = new Profile(xPValues[a],yPValues[a],zPValues[a],gP[a]); 
            DailyExercise d = new DailyExercise(fit,xDuration[a],yCalorie[a],p);
               String errMsg1 = String.format("DailyExercise get/setDuration() incorrect");
                 assertEquals(errMsg1, xDuration[a], d.getDuration());
             
            }
         
             
            @Test(timeout =1000) public void checkDailyExerciseSet_GetDuration_00() { checkDailyExerciseSet_GetDuration(0); }
            @Test(timeout =1000) public void checkDailyExerciseSet_GetDuration_01() { checkDailyExerciseSet_GetDuration(1); }
            @Test(timeout =1000) public void checkDailyExerciseSet_GetDuration_02() { checkDailyExerciseSet_GetDuration(2); }
            @Test(timeout =1000) public void checkDailyExerciseSet_GetDuration_03() { checkDailyExerciseSet_GetDuration(3); }
        
            
         void checkDailyExerciseSet_GetCalorieTarget(int a) {
            Profile p = new Profile(xPValues[a],yPValues[a],zPValues[a],gP[a]); 
            DailyExercise d = new DailyExercise(fit,xDuration[a],yCalorie[a],p);
               String errMsg1 = String.format("DailyExercise get/setCalorieTarget() incorrect");
                 assertEquals(errMsg1, yCalorie[a], d.getCalorieTarget(),ERR);
             
            }
         
             
            @Test(timeout =1000) public void checkDailyExerciseSet_GetCalorieTarget_00() { checkDailyExerciseSet_GetCalorieTarget(0); }
            @Test(timeout =1000) public void checkDailyExerciseSet_GetCalorieTarget_01() { checkDailyExerciseSet_GetCalorieTarget(1); }
            @Test(timeout =1000) public void checkDailyExerciseSet_GetCalorieTarget_02() { checkDailyExerciseSet_GetCalorieTarget(2); }
            @Test(timeout =1000) public void checkDailyExerciseSet_GetCalorieTarget_03() { checkDailyExerciseSet_GetCalorieTarget(3); }
   
         Fitness fitData [] []= {{new Swimming(),new WeightLifting(),new TaiChi(),new Squat()},{new Cycling(),new WeightLifting(),new TaiChi(),new Yoga(),new PullUp()},{new Cycling(),new Plyometrics(),new Yoga(),new PullUp()},{new PullUp()}};
            
        void checkDailyExerciseSet_GetExerciseList(int a) {
           Profile p = new Profile(xPValues[a],yPValues[a],zPValues[a],gP[a]); 
         
           DailyExercise d = new DailyExercise(fitData[a],xDuration[a],yCalorie[a],p);
              String errMsg1 = String.format("DailyExercise get/setExerciseList() incorrect");
                assertArrayEquals(errMsg1, fitData[a], d.getExerciseList());
            
           }
        
            
           @Test(timeout =1000) public void checkDailyExerciseSet_GetExerciseList_00() { checkDailyExerciseSet_GetExerciseList(0); }
           @Test(timeout =1000) public void checkDailyExerciseSet_GetExerciseList_01() { checkDailyExerciseSet_GetExerciseList(1); }
           @Test(timeout =1000) public void checkDailyExerciseSet_GetExerciseList_02() { checkDailyExerciseSet_GetExerciseList(2); }
           @Test(timeout =1000) public void checkDailyExerciseSet_GetExerciseList_03() { checkDailyExerciseSet_GetExerciseList(3); }
            
           
         void checkDailyExerciseAddExercise(int a) {
            Profile p = new Profile(xPValues[a],yPValues[a],zPValues[a],gP[a]); 
             
               DailyExercise d = new DailyExercise(fit,xDuration[a],yCalorie[a],p);
               d.addExercise(newFit[a]);
              
               String errMsg1 = String.format("DailyExercise AddExercise() incorrect");
                 assertEquals(errMsg1, sizeAdd[a], d.getSize());
             
            }
         
             
            @Test(timeout =1000) public void checkDailyExerciseAddExercise_00() { checkDailyExerciseAddExercise(0); }
            @Test(timeout =1000) public void checkDailyExerciseAddExercise_01() { checkDailyExerciseAddExercise(1); }
            @Test(timeout =1000) public void checkDailyExerciseAddExercise_02() { checkDailyExerciseAddExercise(2); }
            @Test(timeout =1000) public void checkDailyExerciseAddExercise_03() { checkDailyExerciseAddExercise(3); }
           
           
         void checkDailyExerciseRemoveExercise(int a) {
            Profile p = new Profile(xPValues[a],yPValues[a],zPValues[a],gP[a]); 
              
               DailyExercise d = new DailyExercise(fit,xDuration[a],yCalorie[a],p);
               d.removeExercise(newFit[a]);
               ArrayList<Fitness> exercise4 =new ArrayList<Fitness>(Arrays.asList(d.getExerciseList()));
               String errMsg1 = String.format("DailyExercise removeExercise() incorrect");
                 assertEquals(errMsg1, false, exercise4.contains(newFit[a]));
             
            }
         
             
            @Test(timeout =1000) public void checkDailyExerciseRemoveExercise_00() { checkDailyExerciseRemoveExercise(0); }
            @Test(timeout =1000) public void checkDailyExerciseRemoveExercise_01() { checkDailyExerciseRemoveExercise(1); }
            @Test(timeout =1000) public void checkDailyExerciseRemoveExercise_02() { checkDailyExerciseRemoveExercise(2); }
            @Test(timeout =1000) public void checkDailyExerciseRemoveExercise_03() { checkDailyExerciseRemoveExercise(3); }
           
           
       
         Fitness [][] fitData2 = {{new PullUp()},{new Cycling()},null,{new Cycling(), new WeightLifting(), new TaiChi()}, null};
         
        private static boolean equalFit(Fitness[] a, Fitness[] b)
      {
       boolean result = true;
       if (a==null && b==null)
        result=true;
       else if (a==null || b == null) result=false;
       else if (a.length!=b.length)
          result=false;
       else {
        for (int i=0;i<a.length;i++)
        {
         if(!((a[i].description()).equals(b[i].description())))
           result=false;
        }
        
       }
      return result;  
      }
        void checkDailyExerciseGetExercises(int a) {
            Profile p = new Profile(xPValues[a],yPValues[a],zPValues[a],gP[a]); 
             DailyExercise d = new DailyExercise(fit,xDuration[a],yCalorie[a],p);
              Fitness f[]= d.getExercises(targetMuscle[a]);
               String errMsg1 = String.format("DailyExercise getExercises() incorrect");
                 assertTrue(errMsg1, equalFit(fitData2[a], f));
             
            }
         
              
            @Test(timeout =1000) public void checkDailyExerciseGetExercises_00() { checkDailyExerciseGetExercises(0); }
            @Test(timeout =1000) public void checkDailyExerciseGetExercises_01() { checkDailyExerciseGetExercises(1); }
            @Test(timeout =1000) public void checkDailyExerciseGetExercises_02() { checkDailyExerciseGetExercises(2); }
            @Test(timeout =1000) public void checkDailyExerciseGetExercises_03() { checkDailyExerciseGetExercises(3); }

Fitness [][] fitResultt = {{new WeightLifting(), new TaiChi(),new Yoga(),new PullUp()},{new Cycling(),new WeightLifting(), new TaiChi()},{new Cycling(),new WeightLifting(), new TaiChi()},{new Cycling(),new WeightLifting(), new TaiChi()},{new Cycling(),new WeightLifting(), new TaiChi(), new Yoga(), new PullUp()}};              
                         void checkDailyExerciseGetAllExercises(int a) {
                             Profile p = new Profile(xPValues[a],yPValues[a],zPValues[a],gP[a]); 
                              DailyExercise d = new DailyExercise(fit,xDuration[a],yCalorie[a],p);
                               Fitness f[]= d.getAllExercises(targetMuscle[a]);
                                String errMsg1 = String.format("DailyExercise getAllExercises() incorrect");
                                  assertTrue(errMsg1, equalFit(fitResultt[a], f));
                              
                             }
                          
                               
                             @Test(timeout =1000) public void checkDailyExerciseGetAllExercises_00() { checkDailyExerciseGetAllExercises(0); }
                             @Test(timeout =1000) public void checkDailyExerciseGetAllExercises_01() { checkDailyExerciseGetAllExercises(1); }
                             @Test(timeout =1000) public void checkDailyExerciseGetAllExercises_02() { checkDailyExerciseGetAllExercises(2); }
                             @Test(timeout =1000) public void checkDailyExerciseGetAllExercises_03() { checkDailyExerciseGetAllExercises(3); }
            
   //****************WeeklyExercise class Tests *********************************
        
            
            @Test(timeout =1000) public void weeklyExercise_construct_def_01() { 
          
         WeeklyExercise d = new WeeklyExercise(fit,45,450,pro2);
             assertArrayEquals("constructor DailyExercise() is incorrect", fit, d.getExerciseList());
             assertEquals("constructor DailyExercise() is incorrect", 45, d.getDays());
             assertEquals("constructor DailyExercise() is incorrect", 450, d.getWeeklyCalorieTarget(),ERR);
             assertEquals("constructor DailyExercise() is incorrect", pro2, d.getProfile());
       }
       
       @Test(timeout =1000) public void weeklyExercise_construct_def_02() { 
              
        WeeklyExercise d = new WeeklyExercise(fit,pro2);
                 assertArrayEquals("constructor WeeklyExercise() is incorrect", fit, d.getExerciseList());
                 assertEquals("constructor WeeklyExercise() is incorrect", 7, d.getDays());
                 assertEquals("constructor WeeklyExercise() is incorrect", 3500, d.getWeeklyCalorieTarget(),ERR);
                 assertEquals("constructor WeeklyExercise() is incorrect", pro2, d.getProfile());
           }
           
       
              
              
         
          
          void checkWeeklyExerciseSet_GetProfile(int a) {
          Profile p = new Profile(xPValues[a],yPValues[a],zPValues[a],gP[a]); 
         WeeklyExercise d = new WeeklyExercise(fit,xDuration[a],yCalorie[a],p);
             String errMsg1 = String.format("WeeklyExercise get/setProfile() incorrect");
               assertEquals(errMsg1, p, d.getProfile());
           
          }
           int a;
          @Test(timeout =1000) public void checkWeeklyExerciseSet_GetProfile_00() { checkDailyExerciseSet_GetProfile(0); }
          @Test(timeout =1000) public void checkWeeklyExerciseSet_GetProfile_01() { checkDailyExerciseSet_GetProfile(1); }
          @Test(timeout =1000) public void checkWeeklyExerciseSet_GetProfile_02() { checkDailyExerciseSet_GetProfile(2); }
          @Test(timeout =1000) public void checkWeeklyExerciseSet_GetProfile_03() { checkDailyExerciseSet_GetProfile(3); }
     
          void checkWeeklyExerciseSet_GetDays(int a) {
             Profile p = new Profile(xPValues[a],yPValues[a],zPValues[a],gP[a]); 
            WeeklyExercise d = new WeeklyExercise(fit,xDuration[a],yCalorie[a],p);
                String errMsg1 = String.format("WeeklyExercise get/setDays() incorrect");
                  assertEquals(errMsg1, xDuration[a], d.getDays());
              
             }
          
              
             @Test(timeout =1000) public void checkWeeklyExerciseSet_GetDays_00() { checkWeeklyExerciseSet_GetDays(0); }
             @Test(timeout =1000) public void checkWeeklyExerciseSet_GetDays_01() { checkWeeklyExerciseSet_GetDays(1); }
             @Test(timeout =1000) public void checkWeeklyExerciseSet_GetDays_02() { checkWeeklyExerciseSet_GetDays(2); }
             @Test(timeout =1000) public void checkWeeklyExerciseSet_GetDays_03() { checkWeeklyExerciseSet_GetDays(3); }
         
             
          void checkWeeklyExerciseSet_GetWeeklyCalorieTarget(int a) {
             Profile p = new Profile(xPValues[a],yPValues[a],zPValues[a],gP[a]); 
            WeeklyExercise d = new WeeklyExercise(fit,xDuration[a],yCalorie[a],p);
                String errMsg1 = String.format("WeeklyExercise get/setWeeklyCalorieTarget() incorrect");
                  assertEquals(errMsg1, yCalorie[a], d.getWeeklyCalorieTarget(),ERR);
              
             }
          
              
             @Test(timeout =1000) public void checkWeeklyExerciseSet_GetWeeklyCalorieTarget_00() { checkWeeklyExerciseSet_GetWeeklyCalorieTarget(0); }
             @Test(timeout =1000) public void checkWeeklyExerciseSet_GetWeeklyCalorieTarget_01() { checkWeeklyExerciseSet_GetWeeklyCalorieTarget(1); }
             @Test(timeout =1000) public void checkWeeklyExerciseSet_GetWeeklyCalorieTarget_02() { checkWeeklyExerciseSet_GetWeeklyCalorieTarget(2); }
             @Test(timeout =1000) public void checkWeeklyExerciseSet_GetWeeklyCalorieTarget_03() { checkWeeklyExerciseSet_GetWeeklyCalorieTarget(3); }
    
          
             
         void checkWeeklyExerciseSet_GetExerciseList(int a) {
            Profile p = new Profile(xPValues[a],yPValues[a],zPValues[a],gP[a]); 
          
         WeeklyExercise d = new WeeklyExercise(fitData[a],xDuration[a],yCalorie[a],p);
               String errMsg1 = String.format("WeeklyExercise get/setExerciseList() incorrect");
                 assertArrayEquals(errMsg1, fitData[a], d.getExerciseList());
             
            }
         
             
            @Test(timeout =1000) public void checkWeeklyExerciseSet_GetWeeklyExerciseList_00() { checkWeeklyExerciseSet_GetExerciseList(0); }
            @Test(timeout =1000) public void checkWeeklyExerciseSet_GetWeeklyExerciseList_01() { checkWeeklyExerciseSet_GetExerciseList(1); }
            @Test(timeout =1000) public void checkWeeklyExerciseSet_GetWeeklyExerciseList_02() { checkWeeklyExerciseSet_GetExerciseList(2); }
            @Test(timeout =1000) public void checkWeeklyExerciseSet_GetWeeklyExerciseList_03() { checkWeeklyExerciseSet_GetExerciseList(3); }
             
            
          void checkWeeklyExerciseAddExercise(int a) {
             Profile p = new Profile(xPValues[a],yPValues[a],zPValues[a],gP[a]); 
              
                WeeklyExercise d = new WeeklyExercise(fit,xDuration[a],yCalorie[a],p);
                d.addExercise(newFit[a]);
                String errMsg1 = String.format("WeeklyExercise AddExercise() incorrect");
                  assertEquals(errMsg1, sizeAddWeekly[a], d.getSize());
              
             }
          
              
             @Test(timeout =1000) public void checkWeeklyExerciseAddExercise_00() { checkWeeklyExerciseAddExercise(0); }
             @Test(timeout =1000) public void checkWeeklyExerciseAddExercise_01() { checkWeeklyExerciseAddExercise(1); }
             @Test(timeout =1000) public void checkWeeklyExerciseAddExercise_02() { checkWeeklyExerciseAddExercise(2); }
             @Test(timeout =1000) public void checkWeeklyExerciseAddExercise_03() { checkWeeklyExerciseAddExercise(3); }
            
            
          void checkWeeklyExerciseRemoveExercise(int a) {
             Profile p = new Profile(xPValues[a],yPValues[a],zPValues[a],gP[a]); 
               
                WeeklyExercise d = new WeeklyExercise(fit,xDuration[a],yCalorie[a],p);
                d.removeExercise(newFit[a]); 
                ArrayList<Fitness> exercise3 =new ArrayList<Fitness>(Arrays.asList(d.getExerciseList()));
                String errMsg1 = String.format("WeeklyExercise removeExercise() incorrect");
                  assertEquals(errMsg1, false, exercise3.contains(newFit[a]));
              
             }
          
              
             @Test(timeout =1000)public void checkWeeklyExerciseRemoveExercise_00() { checkWeeklyExerciseRemoveExercise(0); }
             @Test(timeout =1000) public void checkWeeklyExerciseRemoveExercise_01() { checkWeeklyExerciseRemoveExercise(1); }
             @Test(timeout =1000) public void checkWeeklyExerciseRemoveExercise_02() { checkWeeklyExerciseRemoveExercise(2); }
             @Test(timeout =1000) public void checkWeeklyExerciseRemoveExercise_03() { checkWeeklyExerciseRemoveExercise(3); }
            
        
          
                    
             
             
             Profile pWeekly = new Profile(36,1.7f, 87,Gender.MALE);
          int dayWeekly [] = {4,3,2,5};
          double calWeekly[] = {5000,1500,4897,2450};
         Fitness fitDataWeekly [][] = {{new Swimming(),new WeightLifting(),new TaiChi(),new Squat()},{new Cycling(),new WeightLifting(),new TaiChi()},{new Yoga(),new PullUp()},{new Cycling(),new Plyometrics(),new Yoga(),new PullUp(),new PullUp()}};
             double rWeekly[] = {1250.0,500.0,2448.5,490.0};
          Intensity in[] = {Intensity.HIGH,Intensity.MEDIUM,Intensity.HIGH,Intensity.LOW};
         int [][] resultIntensity = {{86,143,172,123},{40,68,114},{422,225},{84,135,168,70,70}};
         int [][] resultWOInt = {{143,246,574,344},{86,98,229},{844,351},{84,135,168,70,70}};
          int value[][]=new int[4][]; int r=0;
          int valueIntensity[][]=new int[4][];
         
         
         void checkWeeklyExerciseGetExercises(int a) {

      WeeklyExercise ex = new WeeklyExercise(fitDataWeekly[a], dayWeekly[a],calWeekly[a],pWeekly);
      ArrayList<DailyExercise> ls = new ArrayList<DailyExercise>(Arrays.asList(ex.getWeeklyExercises()));
       int j= 0;
       value[a] = new int[ls.size()];
          for(DailyExercise e:ls) 
             {        
                  value[a][j] = e.getDuration();
                j++;
            }
               
      String errMsg1 = String.format("WeeklyExercise getWeeklyExercises() incorrect");
          assertArrayEquals(errMsg1, resultWOInt[a], value[a]);
         }
         
         @Test(timeout =1000) public void checkWeeklyExerciseGetExercises_00() { checkWeeklyExerciseGetExercises(0); }
             @Test(timeout =1000) public void checkWeeklyExerciseGetExercises_01() { checkWeeklyExerciseGetExercises(1); }
             @Test(timeout =1000) public void checkWeeklyExerciseGetExercises_02() { checkWeeklyExerciseGetExercises(2); }
             @Test(timeout =1000) public void checkWeeklyExerciseGetExercises_03() { checkWeeklyExerciseGetExercises(3); }
           
             
             void checkWeeklyExerciseGetExercises_WithIntensity(int a) {
              
              WeeklyExercise ex = new WeeklyExercise(fitDataWeekly[a], dayWeekly[a],calWeekly[a],pWeekly);
              ArrayList<DailyExercise> ls =new ArrayList<DailyExercise>(Arrays.asList( ex.getWeeklyExercises(in[a])));
               int j= 0;
               valueIntensity[a] = new int[ls.size()];
                  for(DailyExercise e:ls) 
                     {        
                          valueIntensity[a][j] = e.getDuration();
                        j++;
                    }
              String errMsg1 = String.format("WeeklyExercise getWeeklyExercises_WithIntensity() incorrect");
                  assertArrayEquals(errMsg1, resultIntensity[a], valueIntensity[a]);
                 }
                 
                 @Test(timeout =1000) public void checkWeeklyExerciseGetExercises_WithIntensity_00() { checkWeeklyExerciseGetExercises_WithIntensity(0); }
                     @Test(timeout =1000) public void checkWeeklyExerciseGetExercises_WithIntensity_01() { checkWeeklyExerciseGetExercises_WithIntensity(1); }
                     @Test(timeout =1000) public void checkWeeklyExerciseGetExercises_WithIntensity_02() { checkWeeklyExerciseGetExercises_WithIntensity(2); }
                     @Test(timeout =1000) public void checkWeeklyExerciseGetExercises_WithIntensity_03() { checkWeeklyExerciseGetExercises_WithIntensity(3); }
         
                     
                     double targeted[] = {65,60,75,42};
                        int withInDays[]= {90,30,5,45};
                        
                        int [] pAge = {20,70,65,25};
                        double []pHeight=  {2.0,1.65,1.5,1.9};
                        double [] pWeight=  {72,63,120.5f,45};
                        Gender [] pGender = {Gender.MALE,Gender.FEMALE,Gender.MALE,Gender.FEMALE};
                     
                        int day [] = {4,3,2,5};
                  double calorie[] = {5000,1500,4897,2450};
                     double[] resultTarget1= {544.44,700,63700,466.67};
                     double[] resultTarget2= {1921.9700000000003, 1235.499,2034.72,  1320.0349999999999};
                     
                     double[] resultTarget3= {1377.53,535.50,-61665.28,853.37};
                     double[] resultTarget4={7,3,45.5,3}; 
                  
                        void checkWeeklyExercise_targetedCalorieLoss(int a) {
                        Profile p = new Profile(pAge[a],pHeight[a],pWeight[a],pGender[a]); 
             
                  WeeklyExercise ex = new WeeklyExercise(fitData[a], day[a],calorie[a],p);
                  String errMsg1 = String.format("WeeklyExercise targetedCalorieLoss incorrect");
                  String resultString = String.format("You need to lose %.02f calories per day or decrease your intake from %.02f to %.02f in order to lose %.2f kg of weight",resultTarget1[a],resultTarget2[a],resultTarget3[a], resultTarget4[a]);
                   assertEquals(errMsg1, resultString, ex.targetedCalorieLoss(targeted[a], withInDays[a]));
                        }
                     
                     @Test(timeout =1000) public void checkWeeklyExercise_targetedCalorieLoss_00()  {
                       checkWeeklyExercise_targetedCalorieLoss(0) ;
                     }
                         
                     @Test(timeout =1000) public void checkWeeklyExercise_targetedCalorieLoss_01() {
                       checkWeeklyExercise_targetedCalorieLoss(1) ;
                     }
                         
                     @Test(timeout =1000) public void checkWeeklyExercise_targetedCalorieLoss_02()  { 
                       checkWeeklyExercise_targetedCalorieLoss(2) ;
                      }
                         
                     @Test(timeout =1000) public void checkWeeklyExercise_targetedCalorieLoss_03()  { 
                       checkWeeklyExercise_targetedCalorieLoss(3) ;
                     }
                      
                         
                        
                         @Test(timeout =1000)
                         public void checkWeeklyExercise_targetedCalorieLoss_Exception() 
                         {
                          Profile p = new Profile(20,1.7,65,Gender.MALE); 
                         
                          WeeklyExercise ex = new WeeklyExercise(fitData[a], day[0],calorie[0],p);
                         String expected = "Only works to lose weight";
                         String errMsg1 = String.format("WeeklyExercise targetedCalorieLoss for weight gain is incorrect"); 
                         assertEquals(errMsg1, expected, ex.targetedCalorieLoss(150, withInDays[0]));
                                                   }
                         
                    
                         
                 
                        
                         
                   
}