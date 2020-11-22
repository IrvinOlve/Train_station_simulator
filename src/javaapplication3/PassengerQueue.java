package javaapplication3;

public class PassengerQueue {

    // Fields
    private final Passengers[] queueArray = new Passengers[TrainStation.TRAIN_QUEUE_MAX_CAPACITY];
    private int first = 0;
    private int last = 0;
    private int size = 0;
    private int maxLength = 0;
    private int longestWaitingTime = 0;
    private int minimumWaitingTime = 0;
    private int totalSeconds = 0;


    public void add(Passengers passenger) {
        queueArray[last] = passenger;
        last = (last + 1) % queueArray.length;
        size++;
        maxLength();
    }

    public boolean isFull() {
        return size == TrainStation.TRAIN_QUEUE_MAX_CAPACITY;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void display() {
        for (int i = 0; i < size; i++) {
            System.out.println(queueArray[(first + i) % queueArray.length].getName());
        }
    }



    public int maxLength() {
        if (size() > maxLength) {
            maxLength++;
        }
        return maxLength;
    }

    public int size() {
        return size;
    }

    public Passengers passengerInQueue(int index) {
        Passengers passengerName = queueArray[index];
        return passengerName;
    }

    public Passengers remove() {
        maxWaitingTime();
        Passengers removedPassenger = queueArray[first];
        size--;
        first = (first + 1) % queueArray.length;
        return removedPassenger;
    }

    public int averageWaitedTime(){
        // take queue size and divide by the seconds in total by all customers
        return  totalSeconds;
    }
    
    public void resetQueueMain() {
        first = 0;
        last = 0;
        size = 0;
        maxLength = 0;
        longestWaitingTime = 0;
        minimumWaitingTime = 0;
        totalSeconds = 0;
    }

    public void maxWaitingTime() {
        
        int seconds = queueArray[first].getSeconds();
        totalSeconds = totalSeconds + seconds;

        if (seconds > longestWaitingTime) {
            longestWaitingTime = seconds;
        }
        minimumWaitingTime();

    }

    public int maxWaitingTimeValue(){
        return longestWaitingTime;
    }

    public void minimumWaitingTime() {
        int seconds = queueArray[first].getSeconds();

        if (minimumWaitingTime == 0) {
            minimumWaitingTime = seconds;
        } 
        else if (seconds < minimumWaitingTime) {
            minimumWaitingTime = seconds;
        }
    }

    public int minimumWaitingTimeValue() {
        return minimumWaitingTime;
    }

}
