package controllers;

import play.data.validation.Constraints.Required;

public class TwitterUserData {
    @Required
    protected String pin;

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }
}