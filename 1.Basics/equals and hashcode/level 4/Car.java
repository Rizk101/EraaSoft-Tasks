import java.util.Objects;

class Car {
    String plateNumber;
    String color;

    Car(String plateNumber, String color) {
        this.plateNumber = plateNumber;
        this.color = color;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Car car = (Car) obj;
        return Objects.equals(plateNumber, car.plateNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(plateNumber);
    }

    @Override
    public String toString() {
        return plateNumber + " - " + color;
    }
}