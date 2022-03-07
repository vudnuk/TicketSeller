import java.util.HashMap;

    /*
     * Client object has String fields for forename, surname and the amount of tickets a client has bought for a specific event
     * via a HashMap. Implements the comparable interface to allow alphabetical listing of client objects using sorting class.
     */

public class Client implements Comparable<Client>{
    private String forename;
    private String surname;
    private HashMap<String, Integer> eventAndTickets;

    public Client(String forename, String surname) {
        this.forename = forename;
        this.surname = surname;
        eventAndTickets = new HashMap<>();
    }

    public String getForename() {
        return forename;
    }

    public String getSurname() {
        return surname;
    }

    public HashMap<String, Integer> getEventAndTickets() {
        return eventAndTickets;
    }

    /*
     * Adds to eventsAndTickets field using String for event, int for tickets.
     */
    public void purchaseTicket(String event, int tickets) {
        eventAndTickets.put(event,tickets);
    }

    /*
     * Removes tickets from client object based on params. If this removal results in the client having no tickets for an
     * event, it removes the key/value pair from the eventsAndTickets HashMap entirely.
     */
    public void returnTicket(String event, int tickets) {
        int i = eventAndTickets.get(event) - tickets;
        if (i > 0) {
            eventAndTickets.put(event, i);
        } else {
            eventAndTickets.remove(event);
        }
    }

    @Override
    public int compareTo(Client c) {
        if (!surname.equals(c.surname)) {
            return surname.compareTo(c.surname);
        } else {
            return forename.compareTo(c.forename);
        }
    }

    @Override
    public String toString() {
        if (eventAndTickets.isEmpty()) {
            return "Client: " + forename + " " + surname;
        } else return "\nClient: " + forename + " " + surname + " Events and tickets: " + eventAndTickets;
    }
}
