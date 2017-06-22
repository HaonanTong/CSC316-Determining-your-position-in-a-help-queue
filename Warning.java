/**
 * Handles all warnings in the program.
 */
public class Warning extends Exception{
	public Warning(){}
	/**
	 * Outputs a custom Warning message.
	 */
	public String custom(String custom) {
		String warning = (custom + "\n");
		return warning;
	}
	
	public String priorityExists(int priority) {
		String warning = ("Warning: a ticket with priority " + priority + " is already in the queue" + "\n");
		return warning;
	}
	
	public String removeDoesNotExist(int id) {
		String warning = ("Warning: there is no ticket with id = " + id + " in the queue" + "\n");
		return warning;
	}
	
	public String queueEmpty() {
		String warning = ("Warning: removal attempted when queue is empty" + "\n");
		return warning;
	}

	public String invalidCommand(String com) {
		String warning = ("Warning: invalid command " + com + "\n");
		return warning;
	}

	public String invalidId(String id) {
		String warning = ("Warning: id " + id + " is not an integer" + "\n");
		return warning;
	}
	
	public String invalidPriority(String priority) {
		String warning = ("Warning: priority " + priority + " is not an integer" + "\n");
		return warning;
	}
}