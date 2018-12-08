package main.java.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import main.java.model.Car;
import main.java.model.CarParkingStatus;
import main.java.model.ParkingLot;
import main.java.model.ParkingStatus;
import main.java.service.ParkingService;

public class ParkingServiceImpl implements ParkingService {

	ParkingLot parkingLot;
	List<Boolean> freeSlots;

	@Override
	public ParkingStatus createParking(Integer capacity) {

		ParkingStatus parkingStatus;
		if (parkingLot == null) {
			parkingLot = new ParkingLot(capacity);
			parkingStatus = new ParkingStatus(true, "Created a parking lot with " + capacity + " slots");
			freeSlots = initializeFreeSlots(capacity);

		} else {
			parkingStatus = new ParkingStatus(false, "Parking lot already created");
		}
		return parkingStatus;
	}

	private List<Boolean> initializeFreeSlots(Integer capacity) {
		List<Boolean> freeSlots = new ArrayList<>();
		for (Integer i = 0; i < capacity; i++)
			freeSlots.add(Boolean.TRUE);
		return freeSlots;
	}

	@Override
	public CarParkingStatus parkCar(Car car) {

		Integer freeSlot = getFreeSlot();
		CarParkingStatus carParkingStatus;
		if (freeSlot == -1) {
			carParkingStatus = new CarParkingStatus(false, "Sorry, parking lot is full", freeSlot);
		} else {
			List<Car> cars = parkingLot.getCars();
			car.setSlotNumber(freeSlot);
			cars.add(freeSlot, car);
			carParkingStatus = new CarParkingStatus(true, "Allocated slot number:" + (freeSlot + 1), freeSlot);
		}

		return carParkingStatus;
	}

	private Integer getFreeSlot() {

		for (Integer i = 0; i < freeSlots.size(); i++) {
			if (freeSlots.get(i)) {
				freeSlots.set(i, Boolean.FALSE);
				return i;
			}
		}
		return -1;
	}

	@Override
	public CarParkingStatus leaveCar(Integer parkingId) {
		parkingId--;
		Boolean isFreeSlot = isFreeSlot(parkingId);
		CarParkingStatus carParkingStatus;
		if (isFreeSlot) {
			carParkingStatus = new CarParkingStatus(false, "Sorry, slot is already empty", parkingId);
		} else {
			freeSlots.set(parkingId, Boolean.TRUE);
			carParkingStatus = new CarParkingStatus(true, "Slot number " + (parkingId + 1) + " is free", parkingId);
		}
		return carParkingStatus;
	}

	private Boolean isFreeSlot(Integer parkingId) {
		return freeSlots.get(parkingId);
	}

	@Override
	public String getStatus() {
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append("No\tRegistration\tSlot No.\tColour\n");
		List<Car> cars = parkingLot.getCars();
		for (Car car : cars) {
			stringBuffer
					.append(car.getSlotNumber() + "\t" + car.getRegistrationNumber() + "\t\t" + car.getColor() + "\n");
		}
		return stringBuffer.toString();
	}

	@Override
	public List<Integer> getCarSlotNumbersForAColor(String color) {
		List<Car> cars = parkingLot.getCars();
		List<Integer> slotNumbers = cars.stream().filter(car -> car.getColor().equals(color))
				.map(car -> car.getSlotNumber()).map(sn -> sn + 1).collect(Collectors.toList());
		return slotNumbers;
	}

	@Override
	public CarParkingStatus getCarSlotNumberForARegistrationNumber(String registationNumber) {
		List<Car> cars = parkingLot.getCars();
		List<Integer> slotNumbers = cars.stream().filter(car -> car.getRegistrationNumber().equals(registationNumber))
				.map(car -> car.getSlotNumber()).collect(Collectors.toList());
		if (slotNumbers.isEmpty()) {
			return new CarParkingStatus(false, "No car available with given registration number:" + registationNumber,
					null);
		} else if (slotNumbers.size() > 1) {
			return new CarParkingStatus(false,
					"More than one slot occupied with given registration number" + registationNumber, null);
		}
		return new CarParkingStatus(true, "Car is available" + registationNumber, slotNumbers.get(0) + 1);
	}

	@Override
	public List<String> getCarRegistrationNumbersForAColor(String color) {
		List<Car> cars = parkingLot.getCars();
		List<String> registrationNumbers = cars.stream().filter(car -> car.getColor().equals(color))
				.map(car -> car.getRegistrationNumber()).collect(Collectors.toList());
		return registrationNumbers;
	}
}
