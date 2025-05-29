// TO DO: add your implementation and JavaDocs.

/*
 *
 * @author Arron Birhanu
 */

/**
 * class MyTime represents military time in digital form.
 */
public class MyTime implements Comparable<MyTime> {
    // Hour and minute of a time.
	
    /**
     * hour of object.
     */
    private int hour;
    
    /**
     * minute of object.
     */
    private int min;
    /**
     * difference in hours.
     */
    private int hrDiff = 0;
    /**
     * total minutes combined.
     */
    private int totalMin = 0;
    /**
     * placeholder for getDuration.
     */
    private int tempDur = 0;
    /**
     * minutes rounded down into hours.
     */
    private int minAdd=0;
    /**
     * decimal minutes non-rounded minutes.
     */
    private int minDiff=0;
    /**
     * minute of object in String.
     */
    private String minS;
    /**
     * hour of object in String.
     */
    private String hourS;
        
    /**
     * MyTime default constructor.
     */
    public MyTime(){
        hour = 0;
        min = 0;
        // Constructor
        // initialize time to be 00:00


    }
	
    /**
     * MyTime constructor passes hour in.
     * @param hour the hours passed in
     */
    public MyTime(int hour){

        if(hour<0||hour>23){
            throw new IllegalArgumentException("Hour must be within [0,23]!");
        }
        this.hour = hour;
        // Constructor with hour specified
        // initialize time to be hour:00

        // A valid hour can only be within [0, 23].
        // For an invalid hour, throw IllegalArgumentException.
        // Use this _exact_ error message for the exception 
        //  (quotes are not part of the message):
        // "Hour must be within [0, 23]!"
        min = 0;


    }

    /**
     * MyTime constructor takes both hours and minutes in.
     * @param hour the hour being passed in
     * @param min the minute being passed in
     */
    public MyTime(int hour, int min){
        if(hour<0||hour>23||min<0||min>59){
            throw new IllegalArgumentException("Hour must be within [0,23]!; Minute must be within [0,59]!");
        }
        this.hour = hour;

        this.min = min;
        // Constructor with hour and minutes specified
        // initialize time to be hour:minute

        // A valid hour can only be within [0, 23].
        // A valid minute can only be within [0, 59].

        // For an invalid hour / minute, throw IllegalArgumentException.
        // Use this _exact_ error message for the exception 
        //  (quotes are not part of the message):
        // "Hour must be within [0, 23]; Minute must be within [0, 59]!");


    }

    /**
     * returns the object's hour.
     * @return hour instance variable
     */
    public int getHour(){
        // report hour

        return hour; //default return, remove/change as needed
    }

    /**
     * returns the object's minute.
     * @return min instance variable
     */
    public int getMin(){
        // report minute

        return min; //default return, remove/change as needed
    }

    /**
     * Comparing to times one another in whichever precedes.
     * @param otherTime compares the current time to a passed in
     * @return returns int after comparing (ex: 1 is greater than)
     */
    @Override 
    public int compareTo(MyTime otherTime){
        if(otherTime==null)
            throw new IllegalArgumentException("Null Time object!");

        if(this.hour<otherTime.hour){
            return -1;
        }
        //7:05 7:10
        if(this.hour>otherTime.hour){
            return 1;
        }
        if(this.hour==otherTime.hour){
            if(this.min==otherTime.min){
                return 0;
            }
            if(this.min<otherTime.min){
                return -1;
            }
            if(this.min>otherTime.min){
                return 1;
            }
        }
        // compare two times for ordering
        // return the value 0 if the argument Time has the same hour and minute of this Time;
        // return a value less than 0 if this Time is before the otherTime argument; 
        // return a value greater than 0 if this Time is after the otherTime argument.

        // Throw IllegalArgumentException if otherTime is null. 
        // - Use this _exact_ error message for the exception 
        //  (quotes are not part of the message):
        //    "Null Time object!"



        return 0; //default return, remove/change as needed
    }

    /**
     * How long from this time to endTime passed in.
     * @param endTime the time passed in or the endpoint
     * @return returns the time difference in minutes (int)
     */
    public int getDuration(MyTime endTime){
        totalMin=0;
        if(endTime==null)
            throw new IllegalArgumentException("Null Time object!");
        if(hour>=endTime.hour&&min>endTime.min||hour>endTime.hour)
            return -1;

        hrDiff=endTime.hour-hour;
        totalMin += hrDiff*60;
        if(min>endTime.min)
            return totalMin-(60-(min-endTime.min))-60;
        if(min<endTime.min)
            return totalMin+(endTime.min-min);

        return totalMin;//10:09 to 11:14 //to get min do 60-(14-9) 60-(this-end)
        // return the number of minutes starting from this Time and ending at endTime
        // return -1 if endTime is before this Time

        // Throw IllegalArgumentException if endTime is null. 
        // - Use this _exact_ error message for the exception 
        //  (quotes are not part of the message):
        //    "Null Time object!"	
    }

    /**
     * Returning the time after minutes pass.
     * @param duration how many minutes have passed is passed in.
     * @return returns the time after x minutes from ##:## time.
     */
    public MyTime getEndTime(int duration){
        MyTime dur = new MyTime();
        if(duration<0)
            throw new IllegalArgumentException("Duration must be non-negative!");
        // return a Time object that is duration minute from this Time
        tempDur = duration+this.min;//1
        if(tempDur>59){//if added passes an hour
            if(hour+(tempDur/60)>23){
                return null;
            }

            minAdd=(tempDur/60)*60;
            minDiff=tempDur-minAdd;
            dur.hour = (tempDur/60)+hour;
            dur.min = minDiff;//5
            if(dur.min>=60&&dur.hour+1>23){
                return null;
            }

            return dur;
        }

        dur.hour = hour;
        dur.min = tempDur;
        return dur;
        // Throw IllegalArgumentException if duration is negative. 
        // Use this _exact_ error message for the exception 
        //  (quotes are not part of the message):
        // "Duration must be non-negative!"			

        // return null if endTime passes 23:59 given this Time and duration argument

    }

    /**
     * overrides the Object class's toString for customization.
     * @return the string representation of MyTime class
     */
    public String toString() {

        if(hour-10<0){
            hourS = "0"+hour;
        }
        else{
            hourS = ""+hour;
        }

        if(min-10<0){
            minS = "0"+min;
        }
        else{
            minS = ""+min;
        }
        return hourS+":"+minS;
        // return a String representation of this object in the form of hh:mm
        // hh is the hour of the time (00 through 23), as two decimal digits
        // mm is the minute of the time (00 through 59), as two decimal digits

        // Hint: String.format() can be helpful here...
        /*if(time-10<0){
        return "0"+time+etc...;
        */
    }

    /**
     * the main method of MyTime (tester).
     * @param args the arguments passed through a String array
     */
    public static void main(String[] args){
        //This method is provided for testing 
        //(use/modify as much as you'd like)

        //time objects
        MyTime time1 = new MyTime(7);
        MyTime time2 = new MyTime(9,30);

        //checking hour/minute
        if (time1.getHour() == 7 && time1.getMin() == 0 && time2.getHour() == 9
                && time2.getMin() == 30){
            System.out.println("Yay 1");			
        }		
        //System.out.println(time1.toString());
        //compareTo, duration
        if (time1.compareTo(time2) < 0 && time1.compareTo(new MyTime(7,0)) == 0
                && time2.compareTo(time1) > 0 && time1.getDuration(time2) == 150){
            System.out.println("Yay 2");						
        }
        //System.out.println(time2.toString());
        //getEndTime
        MyTime time3 = time1.getEndTime(500);
        if (time3!=null && time3.getHour() == 15 && time3.getMin() == 20 
                && time2.getEndTime(870) == null){
            System.out.println("Yay 3");								
        }
        //System.out.println(time3.toString());

    }
}