package javaapplication3;

public class Passengers {

    private String firstName;
    private String surname;
    private int secondsInQueue;

    Passengers(String firstName, String surname) {
        this.firstName = firstName;
        this.surname = surname;
        this.secondsInQueue = 0;
    }

    public String getName() {
        return firstName + " " + surname;
    }

    public void setName(String firstName, String surname) {
        this.firstName = firstName;
        this.surname = surname;
    }
    
    public int getSeconds() {
        return secondsInQueue;
    }
    
    public void setSecondsInQueue(int seconds) {
        this.secondsInQueue = secondsInQueue + seconds;
    }

    public int getSecondsInQueue() {
        return secondsInQueue;
    }
    
    public String display(){
            return firstName + " " + surname + " Seconds in queue: " + secondsInQueue;
    } 
           
    public String getSurname(){
        return surname;
    }
    
    public String getFirstName(){
        return firstName;
    }
    
   
}
