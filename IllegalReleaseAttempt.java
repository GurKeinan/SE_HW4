public class IllegalReleaseAttempt extends IllegalMonitorStateException
{
    /**
     * Constructs a new TaskAlreadyExistsException exception with null as its detail message.
     */
    public IllegalReleaseAttempt(){}

    /**
     * Constructs a new TaskAlreadyExistsException exception with the specified detail message.
     * @param message- the given message
     */
    public IllegalReleaseAttempt(String message) {
        super(message);
    }

}
