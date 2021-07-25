package com.shwetansh.covid_19tracker;

public class ModelVaccinated {
    private String country, timeline;

    public ModelVaccinated() {
    }

    public ModelVaccinated(String country, String timeline) {
        this.country = country;
        this.timeline = timeline;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getTimeline() {
        return timeline;
    }

    public void setTimeline(String timeline) {
        this.timeline = timeline;
    }
}

