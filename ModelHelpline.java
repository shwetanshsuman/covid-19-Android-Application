package com.shwetansh.covid_19tracker;

public class ModelHelpline {
    private String state,helpline;

    public ModelHelpline() {
    }

    public ModelHelpline(String state, String helpline) {
        this.state = state;
        this.helpline = helpline;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getHelpline() {
        return helpline;
    }

    public void setHelpline(String helpline) {
        this.helpline = helpline;
    }
}


