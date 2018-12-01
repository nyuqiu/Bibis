package com.kodilla.exception.test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class FindFlight {

    public Map<String, Boolean> findFlight(Flight flight) throws RouteNotFoundException {
        Map<String, Boolean> canFlyTo = new HashMap<>();
        canFlyTo.put("Amsterdam", false);
        canFlyTo.put("Frankfurt", false);
        canFlyTo.put("Warsaw", false);
        canFlyTo.put("New York", false);
        canFlyTo.put("London", false);
        canFlyTo.put("Dubai", false);

        Map<String, Boolean> canFlyToTrueValues = canFlyTo;
        canFlyTo.entrySet().stream()
                .filter(entry -> entry.getKey().equals(flight.getArrivalAirport()) || entry.getKey().equals(flight.getDepartureAirport()))
                .forEach(entry -> canFlyToTrueValues.replace(entry.getKey(), entry.getValue(), entry.setValue(true)));

        Set<String> airportsNotExsist = new HashSet<>();

        try {
            for (String key : canFlyTo.keySet()) {
                if (flight.getArrivalAirport() != null || flight.getDepartureAirport() != null) {
                    if (!flight.getArrivalAirport().equals(key)) {
                        airportsNotExsist.add(flight.getArrivalAirport());
                    }
                    if (!flight.getDepartureAirport().equals(key)) {
                        airportsNotExsist.add(flight.getDepartureAirport());
                    }
                } else {
                    System.out.println("Arrival and departure airport are unknown.");
                }
            }
            System.out.println("Flight from " + flight.getDepartureAirport() + " to " + flight.getArrivalAirport());

            airportsNotExsist = airportsNotExsist.stream()
                    .filter(n -> !canFlyTo.keySet().contains(n))
                    .collect(Collectors.toSet());

            if (airportsNotExsist.size() > 0) {
                throw new RouteNotFoundException();
            }
        } catch (RouteNotFoundException e) {
            for (String airport : airportsNotExsist) {
                System.out.println("Airport " + airport + " not avaiable on map");
            }
        }
        Map<String,Boolean> resultCanFlyTo = new HashMap<>();
        canFlyToTrueValues.entrySet().stream()
                .filter(entry -> entry.getValue())
                .forEach(entry -> resultCanFlyTo.put(entry.getKey(), entry.getValue()));
        return resultCanFlyTo;
    }
}


