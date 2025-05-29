// TO DO: add your implementation and JavaDocs.

/**
 * Instances of the event class build the data stored within MySortedArray.
 * @author Arron
 */

public class Event implements Comparable<Event> {

	// starting and ending time of the event
	/**
	 * duration time.
	 */
	private int dur = 0;
	/**
	 * starting time member of mytime class.
	 */
	private MyTime startTime;
	/**
	 * ending time member of mytime class.
	 */
	private MyTime endTime;

	// description of the event
	/**
	 * description of string type of what event is.
	 */
	private String description;

	/**
	 *taking in the starttime and endtime arguments if necessary.
	 * @param startTime beginning of event
	 * @param endTime ending of event
	 */
	public Event(MyTime startTime, MyTime endTime) {
		description = "";
		if (startTime.compareTo(endTime) == 1) {
			throw new IllegalArgumentException("End Time cannot come before Start Time!");
		}
		if (startTime == null || endTime == null) {
			throw new IllegalArgumentException("Null Time object!");
		}
		this.startTime = startTime;
		this.endTime = endTime;
		// constructor with start and end times
		// set description to be empty string ""

		// Throw IllegalArgumentException if endTime comes before startTime
		// - Use this _exact_ error message for the exception
		// (quotes are not part of the message):
		// "End Time cannot come before Start Time!"
		// - Assume that the start time can be the same as the end time
		// (0-duration event allowed)

		// Throw IllegalArgumentException if either time is null.
		// - Use this _exact_ error message for the exception
		// (quotes are not part of the message):
		// "Null Time object!"

	}

	/**
	 *taking in not only startime and endtime arguments, but also the description when creating instance.
	 * @param startTime - beginning of event
	 * @param endTime - ending of event
	 * @param description - what the event is
	 */
	public Event(MyTime startTime, MyTime endTime, String description) {
		if (startTime.compareTo(endTime) == 1)
			throw new IllegalArgumentException("End Time cannot come before Start Time!");

		if (startTime == null || endTime == null)
			throw new IllegalArgumentException("Null Time object!");

		if (description == null)
			description = "";
		this.startTime = startTime;
		this.endTime = endTime;
		this.description = description;
		// constructor with start time, end time, and description

		// perform the same checking of start/end times and
		// throw the same exception as the constructor above

		// if description argument is null,
		// set description of the event to be empty string ""

	}

	/**
	 *returns startTime with public accessiblity.
	 * @return returns a MyTime datatype (startTime)
	 */
	public MyTime getStart() {
		// report starting time
		return startTime; // default return, remove/change as needed
	}

	/**
	 *returns endTime with public acessibility.
	 * @return returns a MyTime datatype (endTime)
	 */
	public MyTime getEnd() {
		// report starting time

		return endTime; // default return, remove/change as needed
	}

	/**
	 *returns the description of the event with public accessiblity.
	 * @return returns a String called description
	 */
	public String getDescription() {
		// report description

		return description; // default return, remove/change as needed
	}

	/**
	 *compares two events with one another by overriding comparable interface method - compareTo.
	 * @param otherEvent the event being compared with the current instance event.
	 * @return returns an int value determining which Event holds greater value - earlier startTime.
	 */
	@Override
	public int compareTo(Event otherEvent) {
		// compare two times for ordering
		if (this.startTime.compareTo(otherEvent.startTime) >= 1) {
			return 1;
		}
		if (this.startTime.compareTo(otherEvent.startTime) <= -1) {
			return -1;
		}
		if (this.startTime.compareTo(otherEvent.startTime) == 0) {
			return 0;
		}
		if (otherEvent == null)
			throw new IllegalArgumentException("Null Event object!");
		// The ordering of two events is the same as the ordering of their start times

		// Throw IllegalArgumentException if otherEvent is null.
		// - Use this _exact_ error message for the exception
		// (quotes are not part of the message):
		// "Null Event object!"

		return 0; // default return, remove/change as needed

	}

	/**
	 *uses boolean to determine whether user can move the event back or forwards - without passing 23:59.
	 * @param newStart the new startTime
	 * @return returns true or false (successful or not) in reverting original time
	 */
	public boolean moveStart(MyTime newStart) {

		if (newStart == null) {
			return false;
		}
		dur = startTime.getDuration(endTime);
		if (newStart.getEndTime(dur) == null) {
			return false;
		}

		startTime = newStart;
		endTime = startTime.getEndTime(dur);

		
		// Move the start time of this Event to be newStart but keep the same duration.
		// - Remember to update the end time to ensure duration unchanged.

		// The start time can be moved forward or backward but the end time cannot
		// go beyond 23:59 of the same day. Do not update the event if this condition
		// cannot be satisfied and return false. Return false if newStart is null.

		// Return true if the start time can be moved to newStart successfully.

		// Note: a false return value means the specified newStart can not be used
		// for the current event. Hence if newSart is the same as the current
		// start, we will still return true.

		return true; // default return, remove/change as needed
	}

	/**
	 *increment/decrement duration of event, cannot once again pass 23:59.
	 * @param minute the new time duration starting from startTime
	 * @return a true or false (successful or not)
	 */
	public boolean changeDuration(int minute) {
		if (minute < 0 || startTime.getEndTime(minute) == null) {
			return false;
		}
		endTime = startTime.getEndTime(minute);
		// Change the duration of event to be the given number of minutes.
		// Update the end time of event based on the updated duration.

		// The given minute cannot be negative; and the updated end time cannot go
		// beyond 23:59 of the same day. Do not update the event if these conditions
		// cannot be satisfied and return false.
		// Return true if the duration can be changed.

		// Note: a false return value means the specified duration is invalid for some
		// reason. Hence if minute argument is the same as the current duration,
		// we will still return true.

		return true; // default return, remove/change as needed

	}

	/**
	 *setting a new description.
	 * @param newDescription the new description to replace current
	 */
	public void setDescription(String newDescription) {
		if (newDescription == null)
			description = "";
		this.description = newDescription;
		// set the description of this event

		// if newDescription argument is null,
		// set description of the event to be empty string ""

	}

	/**
	 *toString method for Event class (overrides Object's toString).
	 * @return a string in that contains the event's start/end time descr
	 */
	@Override
	public String toString() {

		// return a string representation of the event in the form of
		// startTime-endTime/description
		// example: "06:30-07:00/breakfast"

		// Hint: String.format() can be helpful here...

		// The format of start/end times is the same as .toString() of MyTime

		return startTime.toString() + "-" + endTime.toString() + "/" + this.description; // default return
		// remove/change as needed

	}

	/**
	 *main method.
	 * @param args accepts string arguments of arguments
	 */
	public static void main(String[] args) {
		// creating an event
		Event breakfast = new Event(new MyTime(7), new MyTime(7, 30), "breakfast");

		// checking start/end times
		if (breakfast.getStart() != null && breakfast.getEnd() != null && breakfast.getStart().getHour() == 7
				&& breakfast.getEnd().getHour() == 7 && breakfast.getStart().getMin() == 0
				&& breakfast.getEnd().getMin() == 30) {
			System.out.println("Yay 1");
		}
		// System.out.println(breakfast);
		// expected output (excluding quote):
		// "07:00-07:30/breakfast"

		// moveStart
		if (breakfast.moveStart(new MyTime(6, 30)) && breakfast.getStart().getHour() == 6
				&& breakfast.getStart().getMin() == 30 && breakfast.getEnd().getMin() == 0) {
			System.out.println("Yay 2");
		}
		// System.out.println(breakfast);

		// longer duration
		if (breakfast.changeDuration(45) && breakfast.getStart().getHour() == 6 && breakfast.getStart().getMin() == 30
				&& breakfast.getEnd().getMin() == 15 && breakfast.getEnd().getHour() == 7) {

			System.out.println("Yay 3");
		}
		// System.out.println(breakfast);

		// shorter duration
		if (!breakfast.changeDuration(-10) && breakfast.changeDuration(15) && breakfast.getStart().getHour() == 6
				&& breakfast.getStart().getMin() == 30 && breakfast.getEnd().getMin() == 45
				&& breakfast.getEnd().getHour() == 6) {
			System.out.println("Yay 4");
		}
		// System.out.println(breakfast);

		// compareTo
		Event jogging = new Event(new MyTime(5), new MyTime(6), "jogging");
		Event morningNews = new Event(new MyTime(6, 30), new MyTime(7), "morning news");
		if (breakfast.compareTo(jogging) > 0 && jogging.compareTo(breakfast) < 0
				&& breakfast.compareTo(morningNews) == 0) {
			System.out.println("Yay 5");
		}
	}

}