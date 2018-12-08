package main.java.service;

import java.util.List;

import main.java.model.Car;
import main.java.model.CarParkingStatus;
import main.java.model.ParkingStatus;

public interface ParkingService {

	public ParkingStatus createParking(Integer capacity);

	public CarParkingStatus parkCar(Car car);

	public CarParkingStatus leaveCar(Integer parkingId);

	public String getStatus();

	public List<Integer> getCarSlotNumbersForAColor(String color);

	public List<String> getCarRegistrationNumbersForAColor(String color);

	public CarParkingStatus getCarSlotNumberForARegistrationNumber(String registrationNumber);
}
