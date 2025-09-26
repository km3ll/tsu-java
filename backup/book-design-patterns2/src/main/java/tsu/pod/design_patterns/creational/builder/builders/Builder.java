package tsu.pod.design_patterns.creational.builder.builders;

import tsu.pod.design_patterns.creational.builder.cars.CarType;
import tsu.pod.design_patterns.creational.builder.components.Engine;
import tsu.pod.design_patterns.creational.builder.components.GPSNavigator;
import tsu.pod.design_patterns.creational.builder.components.Transmission;
import tsu.pod.design_patterns.creational.builder.components.TripComputer;

/**
 * Builder interface defines all possible ways to configure a product.
 */
public interface Builder {
    void setCarType(CarType type);
    void setSeats(int seats);
    void setEngine(Engine engine);
    void setTransmission(Transmission transmission);
    void setTripComputer(TripComputer tripComputer);
    void setGPSNavigator(GPSNavigator gpsNavigator);
}
