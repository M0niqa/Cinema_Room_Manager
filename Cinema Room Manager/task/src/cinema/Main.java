package cinema;

import java.util.Scanner;

public class Main {

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
