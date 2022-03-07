    /*
     * Event object has String field for name, int for tickets.
     * Implements the comparable interface to allow alphabetical listing of event objects using sorting class.
     */

public class Event implements Comparable<Event>{
    private String name;
    public int tickets;

    public Event(String name, int tickets) {
        this.name = name;
        this.tickets = tickets;
    }

    public String getName() {
        return name;
    }

    public int getTickets() {
        return tickets;
    }

    public void setTickets(int tickets) {
        this.tickets = tickets;
    }

    @Override
    public int compareTo(Event e) {
        return name.compareTo(e.name);
    }

    @Override
    public String toString() {
        return "\nEvent: " + name + " Tickets remaining: " + tickets;
    }
}
