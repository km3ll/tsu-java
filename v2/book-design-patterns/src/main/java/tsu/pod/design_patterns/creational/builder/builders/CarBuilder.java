package tsu.pod.design_patterns.creational.builder.builders;

import tsu.pod.design_patterns.creational.builder.cars.Car;
import tsu.pod.design_patterns.creational.builder.cars.CarType;
import tsu.pod.design_patterns.creational.builder.components.Engine;
import tsu.pod.design_patterns.creational.builder.components.GPSNavigator;
import tsu.pod.design_patterns.creational.builder.components.Transmission;
import tsu.pod.design_patterns.creational.builder.components.TripComputer;

/**
 * Concrete builders implement steps defined in the common interface.
 */
public class CarBuilder implements Builder {
    private CarType type;
    private int seats;
    private Engine engine;
    private Transmission transmission;
    private TripComputer tripComputer;
    private GPSNavigator gpsNavigator;

    public void setCarType(CarType type) {
        this.type = type;
    }

    @Override
    public void setSeats(int seats) {
        this.seats = seats;
    }

    @Override
    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    @Override
    public void setTransmission(Transmission transmission) {
        this.transmission = transmission;
    }

    @Override
    public void setTripComputer(TripComputer tripComputer) {
        this.tripComputer = tripComputer;
    }

    @Override
    public void setGPSNavigator(GPSNavigator gpsNavigator) {
        this.gpsNavigator = gpsNavigator;
    }

    public Car getResult() {
        return new Car(type, seats, engine, transmission, tripComputer, gpsNavigator);
    }
}
