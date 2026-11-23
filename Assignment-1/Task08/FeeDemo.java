interface Chargeable {
    double chargingFee(int kWh);
}

abstract class Vehicle {
    private final String regNo;

    protected Vehicle(String regNo) {
        this.regNo = regNo;
    }

    public String getRegNo() {
        return regNo;
    }

    protected abstract double ratePerHour();

    // Every started hour is charged in full
    public double calculateFee(int minutes) {
        int hours = (minutes + 59) / 60;
        return hours * ratePerHour();
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof Vehicle &&
                regNo.equals(((Vehicle) o).regNo);
    }

    @Override
    public int hashCode() {
        return regNo.hashCode();
    }
}

class TwoWheeler extends Vehicle {

    public TwoWheeler(String regNo) {
        super(regNo);
    }

    @Override
    protected double ratePerHour() {
        return 10.0;
    }
}

class Car extends Vehicle {

    public Car(String regNo) {
        super(regNo);
    }

    @Override
    protected double ratePerHour() {
        return 30.0;
    }
}

class ElectricCar extends Car implements Chargeable {

    public ElectricCar(String regNo) {
        super(regNo);
    }

    @Override
    protected double ratePerHour() {
        return 50.0;
    }

    @Override
    public double chargingFee(int kWh) {
        return kWh * 10.0;
    }
}

public class FeeDemo {

    public static void main(String[] args) {

        Vehicle[] vehicles = {
                new TwoWheeler("TN01A1"),
                new Car("TN02B2"),
                new ElectricCar("TN03C3")
        };

        for (Vehicle v : vehicles) {
            double fee = v.calculateFee(130);

            if (v instanceof Chargeable c) {
                fee += c.chargingFee(10);
            }

            System.out.println(
                    v.getClass().getSimpleName() + " " +
                    v.getRegNo() + " -> Rs " + fee
            );
        }

        System.out.println(
                new Car("TN02B2").equals(
                        new ElectricCar("TN02B2")
                )
        );
    }
}