
/*
 * @author Arron Birhanu
 */
/**
 * Class Planner - makes use of MyTime, SortedArray, and Event.
 */
public class Planner {


	/**
	 * Array of events within planner.
	 */
	private MySortedArray<Event> events;

	/**
	 * constructor for planner class.
	 */
	public Planner() {
		this.events = (MySortedArray<Event>) new MySortedArray<Event>();

	}


	/**
	 * returns the size of array events.
	 * @return returns size method for events array
	 */
	public int size() {
		// return the number of events in the list.
		// O(1)

		return events.size(); // default return, remove/change as needed
	}


	/**
	 * returns the String representation of events array.
	 * @return returns the String of elements within event
	 */
	public String toString() {
		String output = "";
		if (events.size() == 0) {
			return "";
		}
		for (int i = 0; i < events.size(); i++) {
			output += "[" + i + "]";
			output += events.get(i).toString() + "\n";
		}
		output = output.substring(0, output.length() - 1);
		// return the string representation of the planner with this format:
		// - include all events in the list in ascending order of the indexes;
		// - each event goes into a separate line;
		// - each line except for the last uses this format (quotes excluded):
		// "[index]event\n"
		// - the last line does not end with a new line and uses this format:
		// "[index]event"

		// The format of an event is the same as .toString() of Event class

		// Hint: String.format() can be helpful here...

		// Note: use StringBuilder instead of String concatenation for a better
		// efficiency

		return output; // default return, remove/change as needed
	}

	/**
	 * Adds an event of Event type into events array.
	 * @param event passes in the Event event into events array
	 */
	public void addEvent(Event event) {
		if (event == null)
			throw new IllegalArgumentException("Null Event object!");
		events.add(event);
		// Add a new event into the list
		// - make sure events are sorted after addition

		// Throw IllegalArgumentException if event is null.
		// - Use this _exact_ error message for the exception
		// (quotes are not part of the message):
		// "Null Event object!"

		// O(N) where N is the number of events in the list

	}


	/**
	 * Moves the time frame of the event to a new or similar time.
	 * @param index the location of the event element being moved
	 * @param newStart the newStarting time for the event
	 * @return true or false, successful operation or not
	 */
	public boolean moveEvent(int index, MyTime newStart) {
		if (index < 0 || index > events.size() - 1 || newStart == null)
			return false;

		return events.get(index).moveStart(newStart);
		// Move the event at index to be start at newStart.
		// Hint: we will keep the same duration but the end time may need to be changed.

		// Return true if event can be updated; return false otherwise.
		// - return false for an invalid index
		// - return false if event cannot be moved to newStart
		// - return false if newStart is null

		// If with the updated starting time, the events are still sorted in ascending
		// order of their starting times, do not change the index of the event.
		// Otherwise, fix the ordering by first removing the updated event,
		// then adding it back.

		// O(N) where N is the number of events currently in list

	}

	/**
	 * Changes the duration of event by adding onto endTime.
	 * @param index the location of the element event changed
	 * @param minute the amount of minutes being changed to
	 * @return true or false, successful operation or not
	 */
	public boolean changeDuration(int index, int minute) {
		// Change the duration of event at index to be the given number of minutes.
		if (minute < 0 || index < 0 || index > events.size() - 1)
			return false;
		// Return true if the duration can be changed.
		// Return false if:
		// - index is invalid; or
		// - minute is negative; or
		// - the duration of event at index can not be updated with the specified minute

		// O(1)
		return events.get(index).changeDuration(minute); // default return, remove/change as needed

	}


	/**
	 * Returns a new description (String member) for event.
	 * @param index the event's location in the array events
	 * @param description the new description being changed to
	 * @return true or false, successful or not
	 */
	public boolean changeDescription(int index, String description) {
		if (index < 0 || index > events.size() - 1)
			return false;
		if (description == null) {
			events.get(index).setDescription("");
			return true;
		}

		events.get(index).setDescription(description);
		// Change the description of event at index.

		// Return true if the event can be changed.
		// Return false for an invalid index.

		// If description argument is null,
		// set description of the event to be empty string ""

		// O(1)
		return true; // default return, remove/change as needed
	}

	/**
	 * Removal of an event value from array events.
	 * @param index the location of the event being removed
	 * @return true or false. successful or not
	 */
	public boolean removeEvent(int index) {
		if (index < 0 || index > events.size() - 1) {
			return false;
		}
		// Remove the event at index.

		// Return true if the event can be removed
		// Return false for an invalid index.

		// O(N) where N is the number of elements currently in the storage

		events.delete(index);
		return true; // default return, remove/change as needed
	}

	/**
	 * Retrieving the value element at location index.
	 * @param index the location of the element (event)
	 * @return returns the event that was located
	 */
	public Event getEvent(int index) {
		if (index < 0 || index > events.size() - 1) {
			return null;
		}
		// Return the event at index

		// Return null for an invalid index.

		// O(1)
		return events.get(index); // default return, remove/change as needed
	}

	/**
	 * The main method (tester).
	 * @param args passing in String array of arguments
	 */
	public static void main(String[] args) {

		// creating a planner
		Planner day1 = new Planner();

		// adding two events
		Event breakfast = new Event(new MyTime(7), new MyTime(7, 30), "breakfast");
		Event jogging = new Event(new MyTime(5), new MyTime(6), "jogging");
		day1.addEvent(breakfast);
		day1.addEvent(jogging);
		if (day1.size() == 2 && day1.getEvent(0) == jogging && day1.getEvent(1) == breakfast) {
			System.out.println("Yay 1");
		}

		// toString
		if (day1.toString().equals("[0]05:00-06:00/jogging\n[1]07:00-07:30/breakfast")) {
			System.out.println("Yay 2");
		}

		// System.out.println(day1.toString());
		// move start of breakfast
		MyTime newBFTime = new MyTime(6, 30);

		if (day1.moveEvent(1, newBFTime) && day1.getEvent(1).getStart().getHour() == 6
				&& day1.getEvent(1).getStart().getMin() == 30) {
			System.out.println("Yay 3");
		}
		// System.out.println(day1);

		// change duration
		if (day1.changeDuration(0, 45) && day1.getEvent(0).getEnd().getHour() == 5
				&& day1.getEvent(0).getEnd().getMin() == 45 && day1.changeDuration(1, 60)
				&& day1.getEvent(1).getEnd().getHour() == 7 && day1.getEvent(1).getEnd().getMin() == 30) {
			System.out.println("Yay 4");
		}
		// System.out.println(day1);

		// change description, remove
		if (day1.changeDescription(1, "sleeping") && !day1.removeEvent(3) && !day1.removeEvent(-2)
				&& day1.removeEvent(0)) {
			System.out.println("Yay 5");
		}

	}
}
