import java.io.*;
import java.util.Scanner;

public class MainProgram {
    private static final String input = "C:\\Users\\admin\\IdeaProjects\\CSC8012\\src\\input.txt";
    private static final String output = "C:\\Users\\admin\\IdeaProjects\\CSC8012\\src\\letters.txt";
    private SortedArrayList<Event> events = new SortedArrayList<>();
    private SortedArrayList<Client> clients = new SortedArrayList<>();
    private PrintWriter p = null;

    /*
     * Main method creates MainProgram object to run program. If it can't find the input variable, runs printStackTrace
     * method on IOException.
     */
    public static void main(String[] args) {
        MainProgram m = new MainProgram();
        try {
            m.p = new PrintWriter(new FileWriter(output));
        } catch (IOException e) {
            e.printStackTrace();
        }
        m.readInput();
        m.menuRun();
        m.p.close();
    }

    /*
     * Writes a letter to a text file using the PrintWriter class to apologise to a client for the lack of tickets.
     */
    public void letterPrinter(Client c, Event e, PrintWriter p) {
            p.println("Dear " + c.getForename() + " " + c.getSurname());
            p.println("Unfortunately there are no available tickets for the " + e.getName() + " event.");
            p.println("Best wishes, \nyour favourite clerk.");
    }

    /*
     * Allows user to return tickets that a client has already purchased. Checks validity of both client and event
     * based on the objects in those respective ArrayLists, then returns the tickets to the event object.
     */
    public void returnTickets() {
        Scanner s = new Scanner(System.in);
        System.out.println("Please input valid customer's first name and surname.");
        String f = s.next();
        String l = s.next();
        boolean check = false;
        boolean check2 = false;
        for (Client c : clients) {
            if (c.getForename().equals(f) && c.getSurname().equals(l)) {
                check = true;
                System.out.println("What event would they like to return tickets for, and how many tickets would" +
                        " they like to return?");
                s.nextLine();
                String event = s.nextLine();
                int tickets = s.nextInt();
                for (Event e : events) {
                    if (c.getEventAndTickets().get(event) == null) {
                        System.out.println("Client has no tickets to return.");
                        break;
                    }
                    else if (event.equals(e.getName()) && tickets == c.getEventAndTickets().get(event)) {
                        check2 = true;
                        c.returnTicket(event, tickets);
                        e.setTickets(e.getTickets() + tickets);
                        System.out.println(tickets + " " + e.getName() + " tickets returned.");
                    }
                } if (!check2) {
                    System.out.println("Not a valid event.");
                }
            }
        } if (!check) {
            System.out.println("Not valid client.");
        }
    }

    /*
     * Method to buy tickets based on user input. Takes in client name, and checks if they are valid client in the
     * Client ArrayList. If so, a similar check is performed for Events. If both client and event are valid, tickets
     * are either purchased and assigned to the client, or letterPrinter method is run if not enough tickets are
     * available.
     */
    public void buyTickets() {
        Scanner s = new Scanner(System.in);
        System.out.println("Please input valid customer's first name and surname.");
        String f = s.next();
        String l = s.next();
        boolean check = true;
        boolean check2 = true;
        for (Client c : clients) {
            if (c.getForename().equals(f) && c.getSurname().equals(l)) {
                check = false;
                System.out.println("What event would they like to buy tickets for and how many?");
                s.nextLine();
                String event = s.nextLine();
                int tickets = s.nextInt();
                for (Event e : events) {
                    if (event.equals(e.getName())) {
                        check2 = false;
                        if (tickets > e.getTickets()) {
                            System.out.println("Sorry, no tickets available for " + e.getName() + ".");
                            letterPrinter(c, e, p);
                        } else {
                            e.setTickets(e.getTickets() - tickets);
                            System.out.println("Tickets purchased. Enjoy the " + e.getName() + " event!");
                            c.purchaseTicket(event, tickets);
                        }
                    }
                    } if (check2) {
                    System.out.println("Not a valid event.");
                }
            }
        } if (check) {
            System.out.println("Not valid client.");
        }
    }

    /*
     * Reads text file using Scanner class. Adds values from the file into ArrayLists for Event objects and Client
     * objects respectively.
     */
    public void readInput() {
        try {
            Scanner s = new Scanner(new FileReader(input));
            int totalEvents = s.nextInt();
            s.nextLine();
            for (int i = 0; i < 6; i++) {
                Event e = new Event(s.nextLine(), Integer.parseInt(s.nextLine()));
                events.add(e);
            }
            int totalClients = s.nextInt();
            s.nextLine();
            while (s.hasNextLine()) {
                String[] name = s.nextLine().split(" ");
                Client c = new Client(name[0], name[1]);
                clients.add(c);
            }
            clients.insertionSort(clients);
            events.insertionSort(events);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /*
     * Method menuRun allows user input using Scanner class. Runs menu method to show user options, then uses switch
     * statement to run option.
     */
    public void menuRun() {
        Scanner s = new Scanner(System.in);
        menu();
        char c = s.next().charAt(0);
        s.nextLine();
        while(c != 'f') {
            switch (c) {
                case 'e' -> System.out.println(events);
                case 'c' -> System.out.println(clients);
                case 'b' -> buyTickets();
                case 'r' -> returnTickets();
            }
            menu();
            c = s.next().charAt(0);
            s.nextLine();
        }
    }

    /*
     * Prints menu to console for user input.
     */
    public static void menu() {
        System.out.println("f - quit");
        System.out.println("e - display event info");
        System.out.println("c - display client info");
        System.out.println("b - buy tickets");
        System.out.println("r - return tickets");
    }
}