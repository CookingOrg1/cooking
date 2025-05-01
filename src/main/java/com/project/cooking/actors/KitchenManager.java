package com.project.cooking.actors;

import java.util.ArrayList;
import java.util.List;

public class KitchenManager {
    private List<String> alerts = new ArrayList<>();

    public List<String> getAlerts() {
        return alerts;
    }

    public void addAlert(String alert) {
        alerts.add(alert);
    }
}