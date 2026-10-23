package service;

import exception.DuplicatePlateException;
import exception.VehicleNotFoundException;
import model.Vehicle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.IntSummaryStatistics;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class RegistrationService {
    private List<Vehicle> vehicleList = new ArrayList<>();
    private Map<String, Vehicle> plateIndex = new HashMap<>();
    private Set<String> registeredPlates = new HashSet<>();

    public void registerVehicle(Vehicle vehicle) {
        if (vehicle == null) {
            throw new IllegalArgumentException("Vehicle cannot be null");
        }

        String plate = vehicle.getPlateNumber().toUpperCase();
        if (registeredPlates.contains(plate)) {
            throw new DuplicatePlateException(plate);
        }

        vehicleList.add(vehicle);
        plateIndex.put(plate, vehicle);
        registeredPlates.add(plate);
    }

    public Vehicle findByPlate(String plate) {
        String normalizedPlate = plate.toUpperCase();
        Vehicle vehicle = plateIndex.get(normalizedPlate);
        if (vehicle == null) {
            throw new VehicleNotFoundException(normalizedPlate);
        }
        return vehicle;
    }

    public void deleteVehicle(String plate) {
        Vehicle vehicle = findByPlate(plate);
        String normalizedPlate = vehicle.getPlateNumber().toUpperCase();

        vehicleList.remove(vehicle);
        plateIndex.remove(normalizedPlate);
        registeredPlates.remove(normalizedPlate);
    }

    public void updateOwner(String plate, String newOwner) {
        Vehicle vehicle = findByPlate(plate);
        vehicle.setOwnerName(newOwner);
    }

    public List<Vehicle> getAllVehicles() {
        return Collections.unmodifiableList(vehicleList);
    }

    public List<Vehicle> filterByType(String type) {
        return vehicleList.stream()
                .filter(v -> v.getVehicleType().equalsIgnoreCase(type))
                .collect(Collectors.toList());
    }

    public List<Vehicle> getVehiclesByOwner(String ownerName) {
        return vehicleList.stream()
                .filter(v -> v.getOwnerName().toLowerCase()
                        .contains(ownerName.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<Vehicle> getExpiredRegistrations(int currentYear) {
        return vehicleList.stream()
                .filter(v -> (currentYear - v.getRegistrationYear()) > 5)
                .sorted(Comparator.comparingInt(Vehicle::getRegistrationYear))
                .collect(Collectors.toList());
    }

    public List<Vehicle> getSortedByYear(boolean ascending) {
        Comparator<Vehicle> comparator =
                Comparator.comparingInt(Vehicle::getRegistrationYear);
        if (!ascending) {
            comparator = comparator.reversed();
        }
        return vehicleList.stream()
                .sorted(comparator)
                .collect(Collectors.toList());
    }

    public void printStatistics() {
        IntSummaryStatistics stats = vehicleList.stream()
                .mapToInt(Vehicle::getRegistrationYear)
                .summaryStatistics();

        Map<String, Long> byType = vehicleList.stream()
                .collect(Collectors.groupingBy(
                        Vehicle::getVehicleType,
                        LinkedHashMap::new,
                        Collectors.counting()));

        Map<Boolean, Long> byStatus = vehicleList.stream()
                .collect(Collectors.partitioningBy(
                        v -> v.getStatus().equals("ACTIVE"),
                        Collectors.counting()));

        long total = stats.getCount();
        int newest = total == 0 ? 0 : stats.getMax();
        int oldest = total == 0 ? 0 : stats.getMin();

        System.out.println("========== REGISTRATION STATISTICS ==========");
        System.out.printf("Total Vehicles   : %d%n", total);
        System.out.printf("Average Year     : %.0f%n", stats.getAverage());
        System.out.printf("Newest Vehicle   : %d%n", newest);
        System.out.printf("Oldest Vehicle   : %d%n", oldest);
        System.out.println("----------------------------------------------");
        System.out.println("Vehicles by Type:");
        for (Map.Entry<String, Long> entry : byType.entrySet()) {
            System.out.printf("  %-12s: %2d%n", entry.getKey(), entry.getValue());
        }
        System.out.println("----------------------------------------------");
        System.out.printf("%-16s: %2d%n", "ACTIVE vehicles", byStatus.getOrDefault(true, 0L));
        System.out.printf("%-16s: %2d%n", "EXPIRED vehicles", byStatus.getOrDefault(false, 0L));
        System.out.println("==============================================");
    }
}
