package co.com.marimaro.pizzeria.service;

public class EmailAPIException extends RuntimeException{

    public EmailAPIException() {
        super("Error sending email");
    }

}
