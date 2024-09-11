import java.util.Scanner;

class Reservation {
    private String passengerName;
    private String mobileNumber;
    private int flightNumber;
    private int reservedSeatNumber;

    
    static String[] flight1Seats = new String[10];
    static String[] flight2Seats = new String[10];

    
    static {
        for (int i = 0; i < 10; i++) {
            flight1Seats[i] = "Available";
            flight2Seats[i] = "Available";
        }
    }

    
    public Reservation(String passengerName, String mobileNumber, int flightNumber, int reservedSeatNumber) {
        this.passengerName = passengerName;
        this.mobileNumber = mobileNumber;
        this.flightNumber = flightNumber;
        this.reservedSeatNumber = reservedSeatNumber;
    }

    
    public static void displayFlightDetails() {
        System.out.println("Flight-1");
        for (int i = 0; i < 10; i++) {
            System.out.println((i + 1) + "-" + flight1Seats[i]);
        }
        System.out.println("Flight-2");
        for (int i = 0; i < 10; i++) {
            System.out.println((i + 1) + "-" + flight2Seats[i]);
        }
    }

    
    public static int booking(int flightNumber, String travelClass) {
        String[] selectedFlight = (flightNumber == 1) ? flight1Seats : flight2Seats;
        int start = (travelClass.equalsIgnoreCase("First")) ? 0 : 5;
        int end = (travelClass.equalsIgnoreCase("First")) ? 5 : 10;

        for (int i = start; i < end; i++) {
            if (selectedFlight[i].equals("Available")) {
                selectedFlight[i] = "Reserved";
                return i + 1;
            }
        }
        return -1; 
    }

    
    public void displayReservationDetails() {
        System.out.println("Passenger Name: " + passengerName);
        System.out.println("Mobile Number: " + mobileNumber);
        System.out.println("Flight Number: " + flightNumber);
        System.out.println("Reserved Seat Number: " + reservedSeatNumber);
    }

    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Reservation[] reservations = new Reservation[20];
        int reservationCount = 0;

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Display Flight Details");
            System.out.println("2. Display Reserved Passenger Details");
            System.out.println("3. Reserve a seat");
            System.out.println("4. Stop");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    displayFlightDetails();
                    break;

                case 2:
                    for (int i = 0; i < reservationCount; i++) {
                        reservations[i].displayReservationDetails();
                    }
                    break;

                case 3:
                    System.out.print("Enter Flight Number (1 or 2): ");
                    int flightNumber = scanner.nextInt();
                    System.out.print("Enter Travel Class (First or Economy): ");
                    String travelClass = scanner.next();

                    int seatNumber = booking(flightNumber, travelClass);
                    if (seatNumber != -1) {
                        System.out.print("Enter Passenger Name: ");
                        String passengerName = scanner.next();
                        System.out.print("Enter Mobile Number: ");
                        String mobileNumber = scanner.next();

                        
                        reservations[reservationCount] = new Reservation(passengerName, mobileNumber, flightNumber, seatNumber);
                        reservationCount++;

                        System.out.println("Seat Reserved Successfully. Seat Number: " + seatNumber);
                    } else {
                        System.out.println("Next Flight leaves in 3 hours");
                    }
                    break;

                case 4:
                    System.out.println("Exiting the system.");
                    scanner.close();
                    return;

                default:
                    System.out.println("Choice is wrong, try again.");
                    break;
            }
        }
    }
}