package cinema;

import java.util.*;

public class Cinema {
    int numbOfRows;
    int seatsPerRow;
    int totalNumbOfSeats;
    char[][] roomSeats;
    int numberOfTickets;
    int currentIncome;

    public Cinema(int numbOfRows, int seatsPerRow) {
        this.numbOfRows = numbOfRows;
        this.seatsPerRow = seatsPerRow;
        roomSeats = new char[numbOfRows][seatsPerRow];
        fillSeatsArray(roomSeats);
        totalNumbOfSeats = calculateTotalNumbOfSeats();
    }

    private static void fillSeatsArray(char[][] seats) {
        for (char[] row : seats) {
            Arrays.fill(row, 'S');
        }
    }

    private void updateRoomSeatsArray(int row, int seat) {
        roomSeats[row - 1][seat - 1] = 'B';
    }

    public void printCinema() {
        System.out.println("Cinema:");
        System.out.print("  ");
        for (int i = 1; i <= seatsPerRow; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int j = 0; j < numbOfRows; j++) {
            System.out.print(j + 1 + " ");
            for (char c : roomSeats[j]) {
                System.out.print(c + " ");
            }
            System.out.println();
        }
    }

    public void buyTicket(int row, int seat) {
        int price = calculateTicketPrice(row);

        System.out.println("Ticket price: $" + price);
        numberOfTickets++;
        updateRoomSeatsArray(row, seat);
        currentIncome += price;
    }

    private int calculateTicketPrice(int row) {
        int price;
        if (totalNumbOfSeats <= 60) {
            price = 10;
        } else {
            int firstHalf = numbOfRows / 2;
            price = row <= firstHalf ? 10 : 8;
        }
        return price;
    }

    private int calculateTotalNumbOfSeats() {
        return numbOfRows * seatsPerRow;
    }

    private int calculateTotalIncome() {
        if (totalNumbOfSeats <= 60) {
            return totalNumbOfSeats * 10;
        } else {
            int firstHalf = numbOfRows / 2;
            int secondHalf = numbOfRows - firstHalf;
            return (firstHalf * seatsPerRow * 10) + (secondHalf * seatsPerRow * 8);
        }
    }

    private double calculatePercentage() {
        return (double) numberOfTickets / totalNumbOfSeats * 100;
    }

    public void showStatistics() {
        System.out.printf("""
                        Number of purchased tickets:%d
                        Percentage: %.2f%%
                        Current income: $%d
                        Total income: $%d

                        """,
                numberOfTickets, calculatePercentage(), currentIncome, calculateTotalIncome());
    }

    private boolean isInputValid(int row, int seat) {
        if (row > numbOfRows || seat > seatsPerRow) {
            System.out.println("Wrong input!");
            return false;
        }
        if (roomSeats[row - 1][seat - 1] == 'B') {
            System.out.println("That ticket has already been purchased!");
            return false;
        }
        return true;
    }

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        System.out.println("Enter the number of rows:");
        int rows = scan.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int seats = scan.nextInt();

        Cinema c = new Cinema(rows, seats);

        while (true) {
            System.out.println();
            System.out.println("1. Show the seats\n2. Buy a ticket\n3. Statistics\n0. Exit");
            int choice = scan.nextInt();
            switch (choice) {
                case 1 -> c.printCinema();
                case 2 -> {
                    while (true) {
                        System.out.println("Enter a row number:");
                        int row = scan.nextInt();
                        System.out.println("Enter a seat number in that row:");
                        int seat = scan.nextInt();
                        if (c.isInputValid(row, seat)) {
                            c.buyTicket(row, seat);
                            break;
                        }
                    }
                }
                case 3 -> c.showStatistics();
                case 0 -> {
                    return;
                }
            }
        }
    }
}