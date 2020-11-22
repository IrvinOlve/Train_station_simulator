// Program created by Irvin Nahin Olvera Garces
// Student ID: w1669460
// Programming Principles II


package javaapplication3;

import java.util.*;
import java.io.*;

public class TrainStation {

    private static final Scanner input = new Scanner(System.in);

    public static final int TRAIN_QUEUE_MAX_CAPACITY = 30;
    public static final int WAITING_ROOM_MAX_CAPACITY = 50;
    private static final Passengers[] waitingRoom = new Passengers[WAITING_ROOM_MAX_CAPACITY];
    private static final PassengerQueue trainQueue = new PassengerQueue();
    private static int indexWaitingRoom = 0;
    private static int processedPassengers = 0;


    public static void main(String[] args) {
        System.out.println("** TRAIN STATION QUEUE MENU **");
        displayMenu();
    }

    public static void displayMenu() {

        // Menu with all options
        System.out.println("\nA: Add a passenger to the train queue.");
        System.out.println("V: View the train queue.");
        System.out.println("D: Delete passenger from the train queue.");
        System.out.println("S: Store trainQueue data into a plain text file.");
        System.out.println("L: Load data back from the file into the train queue.");
        System.out.println("R: Run the simulation and produce report.");
        System.out.println("Q: Quit program.");
        System.out.print("\nEnter option: ");

        String option = input.next();

        try {
            if (option.equalsIgnoreCase("A")) {
                addPassengerToQueue();
            } else if (option.equalsIgnoreCase("V")) {
                viewQueue();
            } else if (option.equalsIgnoreCase("D")) {
                removeCustomerFromQueue();
            } else if (option.equalsIgnoreCase("S")) {
                store_trainQueueToFile();
            } else if (option.equalsIgnoreCase("L")) {
                load_trainQueueFromFile(waitingRoom);
            } else if (option.equalsIgnoreCase("R")) {
                runSimulation(waitingRoom);
            } else if (option.equalsIgnoreCase("Q")) {
                System.exit(0);
            }
        } catch (FileNotFoundException ex) {
            System.out.println("Files not found ");
        }

    }

    private static void addPassengerToQueue() {

        System.out.println();

        // We don't want to add customers to the queue if it's full.
        if (trainQueue.isFull()) {
            System.out.println("The queue is full.");
        } else {

            // We take customer's first and last name.
            System.out.print("Enter first name: ");
            String firstName = input.next();

            System.out.print("Enter surname: ");
            String surname = input.next();

            // And add them to the queue.
            Passengers newPassenger = new Passengers(firstName, surname);
            trainQueue.add(newPassenger);

            // I use a custom index number to add customer to the waiting room (indexWaitingRoom),
            // So when I add a customer to the queue from the external file,
            // it doesn't override the customers already added to the queue. 
            // As it will add customers starting from the int stored in indexWaitingRoom.
            indexWaitingRoom++;

            // Message: Passenger joined the queue, if all the code above ran accordingly. 
            System.out.println("\n" + firstName + " " + surname + " joined the queue.");
        }
        displayMenu();
    }

    private static void viewQueue() {
        System.out.println();

        if (!trainQueue.isEmpty()) {
            trainQueue.display();
        } else {
            System.out.println("The queue is empty.");
        }
        displayMenu();
    }

    private static void removeCustomerFromQueue() {

        // System.out.println(trainQueue.remove().getName());
        Passengers removedPassenger;
        int noPassengers = 0;

        System.out.println();
        if (trainQueue.size() == noPassengers) {
            System.out.println("No more passengers left in the queue.");
        } else {
            removedPassenger = trainQueue.remove();
            System.out.println(removedPassenger.getName() + " left the queue.");
        }
        displayMenu();
    }

    private static void store_trainQueueToFile() throws FileNotFoundException {
        String fileName = "trainQueue.dat";

        PrintWriter outToFile = new PrintWriter(fileName);

        System.out.println();
        for (int i = 0; i < TRAIN_QUEUE_MAX_CAPACITY; i++) {
            if (trainQueue.isEmpty()) {
                System.out.println("There is not data to export - Add a passenger");
                break;
            } else {
                try {
                    outToFile.println(trainQueue.passengerInQueue(i).getName());
                } catch (Exception noMorePassengers) {
                    // No more names to print in file
                    System.out.println("Data exported to trainQueue.dat");
                    break;
                }
            }
        }
        outToFile.close();
        trainQueue.resetQueueMain();
        displayMenu();

    }

    private static void load_trainQueueFromFile(Passengers[] passenger) throws FileNotFoundException {
        System.out.println();

        // File to read data from
        String inputFileName = "trainQueue.dat";

        File inputFile = new File(inputFileName);
        Scanner inFromFile = new Scanner(inputFile);

        while (inFromFile.hasNextLine()) {
            for (int i = 0; i < TRAIN_QUEUE_MAX_CAPACITY; i++) {
                try {

                    // Reads a line at a time and stores it in "line" variable
                    String line = inFromFile.nextLine();

                    // Splits line when when delimiter is detected 
                    // Then stores the values in arrays temp[0] and temp[1]
                    // Finally, stores values inside class object.
                    String delimiter = " ";
                    String[] temp = line.split(delimiter);

                    // Adds the passenger to the waiting room.
                    // Important for simulation's report.
                    passenger[indexWaitingRoom] = new Passengers(temp[0], temp[1]);

                    // Adds the passenger to the queue.
                    trainQueue.add(passenger[indexWaitingRoom]);

                    // I use a custom index number to add customer to the waiting room.
                    // So when I add a customer to the queue from the external file,
                    // it doesn't override the customers already added to the queue. 
                    indexWaitingRoom++;

                } catch (Exception noMorePassengers) {
                    System.out.println("Data imported from trainQueue.dat");
                    break;
                }
            }
        }
        displayMenu();
    }

    private static void loadToWaitingRoom(Passengers[] passenger) throws FileNotFoundException {

        // File to read data from
        String inputFileName = "passengers.dat";

        File inputFile = new File(inputFileName);
        Scanner inFromFile = new Scanner(inputFile);
        while (inFromFile.hasNextLine()) {
            for (int i = 0; i <= WAITING_ROOM_MAX_CAPACITY; i++) {
                try {

                    // Reads a line at a time and stores it in "line" variable
                    String line = inFromFile.nextLine();

                    // Splits line when when delimiter is detected 
                    // Then stores the values in arrays temp[0] and temp[1]
                    // Finally, stores values inside class object.
                    String delimiter = " ";
                    String[] temp = line.split(delimiter);

                    // Adds the passenger to the waiting room.
                    // Important for simulation's report.
                    Passengers newPassenger = new Passengers(temp[0], temp[1]);
                    passenger[indexWaitingRoom] = newPassenger;

                    // I use a custom index number to add customer to the waiting room (indexWaitingRoom),
                    // So when I add a customer to the queue from the external file,
                    // it doesn't override the customers already added to the queue. 
                    // As it will add customers starting from the int stored in indexWaitingRoom.
                    indexWaitingRoom++;

                } catch (Exception noMorePassengers) {
                    // Quit loop as there are not more passengers to load.
                    break;
                }
            }
        }
    }

    public static int dieRoll(int rolls) {

        // Random number generator
        Random ran = new Random();
        int randomNumber = ran.nextInt(6) + 1;
        return (randomNumber * rolls);
    }

    private static void runSimulation(Passengers[] passenger) throws FileNotFoundException {

        int passengersJoiningQueue;
        int processingDelay;
        int indexPassenger = 0;
        int indexPassengerSeconds = 0;
        boolean queueLoop = true;

        Passengers removedPassenger;
        String passengerName;

        // loads passengers.dat into the waitingRoom array //
        loadToWaitingRoom(passenger);

        do {

            passengersJoiningQueue = dieRoll(1);
            processingDelay = dieRoll(3);

            // simulation loop //
            for (int i = 0; i < passengersJoiningQueue; i++) {
                if (trainQueue.isFull()) {
                    System.out.println("[WARNING] - Train queue is full.");
                    break;
                } else {
                    try {
                        // adds passenger to the queue //
                        Passengers passengerJoiningTheQueue = passenger[indexPassenger];
                        trainQueue.add(passengerJoiningTheQueue);
                        System.out.println("[IN] - " + passengerJoiningTheQueue.getName() + " joined the queue.");
                        indexPassenger++;
                    } catch (Exception noPassengersLeftInWaitingRoom) {
                        break;
                    }
                }
            }

            for (int i = 0; i < passengersJoiningQueue + 1; i++) {
                    try {
                        // adds 6x3 seconds to the passengers in the queue as a processing delay //
                        passenger[processedPassengers + i].setSecondsInQueue(processingDelay);

                    } catch (Exception noPassengersLeftInWaitingRoom) {
                       break;
                    }
                }

            // removes the first passenger from the queue - FIFO //
            try {
                removedPassenger = trainQueue.remove();
                passengerName = removedPassenger.getName();

                System.out.println("[OUT] - " + passengerName + " left the queue ");

                // adds processing effect //
                Thread.sleep(130);
                processedPassengers++;
            } catch (Exception noPassengersLeftInTheQueue) {
                queueLoop = false;
            }
        } while (queueLoop);

        System.out.println();
        simulationReport(waitingRoom);
        processedPassengers = 0;

        // resets queue stats - Getting variables ready for a new simulation //
        trainQueue.resetQueueMain();
        displayMenu();
    }

//    private static void trainSimulation(Passengers[] passenger){
//        train[]
//
//    }

    private static void simulationReport(Passengers[] passenger) throws FileNotFoundException {


        System.out.println("** QUEUE'S SIMULATION REPORT **");
        System.out.println("    ** CUSTOMERS' REPORT **");

        System.out.println();
        System.out.println("CUSTOMER             SECONDS");

       // System.out.println();
        for (int i = 0; i < 100; i++) {
            if (passenger[i] != null) {
                // Print passenger's name and seconds spent in the queue.
                System.out.print(String.format("%-20.30s %7s", passenger[i].getName(), passenger[i].getSeconds()) + "\n");
                // System.out.print(String.format("%-20s", passenger[i].getSeconds()) + " \n");
            } else {
                // We break loop as there are no more passengers to display data from.
                break;
            }
        }

        System.out.println();
        System.out.println("    ** QUEUE'S REPORT **");
        System.out.println();

        System.out.println("Max length attained: " + trainQueue.maxLength() + " passengers.");
        System.out.println("Maximum waiting time: " + trainQueue.maxWaitingTimeValue() + " seconds.");
        System.out.println("Minimum waiting time: " + trainQueue.minimumWaitingTimeValue() + " seconds.");
        System.out.println("Average waiting time: " + trainQueue.averageWaitedTime()/ processedPassengers+ " seconds.");
        storeReportToFile(passenger);
    }

    private static void storeReportToFile(Passengers[] passenger) throws FileNotFoundException {
        String fileName = "report.dat";
        PrintWriter outToFile = new PrintWriter(fileName);

        for (int i = 0; i < 100; i++) {
            if (passenger[i] != null) {

                outToFile.println(passenger[i].getName() + " stayed in the queue for " + passenger[i].getSeconds() + " seconds.");

            } else {
                // We break loop as there are no more passengers to display data from.
                break;
            }
        }

        outToFile.println("Max length attained: " + trainQueue.maxLength() + " passengers.");
        outToFile.println("Maximum waiting time: " + trainQueue.maxWaitingTimeValue() + " seconds.");
        outToFile.println("Minimum waiting time: " + trainQueue.minimumWaitingTimeValue() + " seconds.");
        outToFile.println("Average waiting time: " + trainQueue.averageWaitedTime()/ processedPassengers+ " seconds.");
        outToFile.close();
    }



}
