package main.java.service.impl;

import java.util.List;

import main.java.model.Car;
import main.java.model.CarParkingStatus;
import main.java.model.ParkingLotStatus;
import main.java.service.OperatorService;
import main.java.service.ParkingService;

public class OperatorServiceImpl implements OperatorService {

	ParkingService parkingService;

	public OperatorServiceImpl() {
		super();
		this.parkingService = new ParkingServiceImpl();
	}

	@Override
	public String operate(List<String> inputs) {

		if (inputs.isEmpty()) {
			throw new IllegalArgumentException("Provide non empty strings");
		} else if (inputs.get(0).equals("create_parking_lot")) {
			if (inputs.size() > 2) {
				throw new IllegalArgumentException("Expected only parking slot size as input");
			}
			Integer capacitySize;
			String capacitySizeStr = inputs.get(1);
			try {
				capacitySize = Integer.parseInt(capacitySizeStr);
			} catch (Exception e) {
				throw new IllegalArgumentException("Unable to parse:" + capacitySizeStr + "as integer");
			}
			ParkingLotStatus parkingStatus = parkingService.createParking(capacitySize);
			if (parkingStatus.getIsSuccess()) {
				return "Created a parking lot with " + capacitySizeStr + " slots";
			} else {
				throw new IllegalStateException("Parking slot is alredy created");
			}
		} else if (inputs.get(0).equals("park")) {
			if (inputs.size() != 3) {
				throw new IllegalArgumentException("Expected car registration number and color as input");
			}
			String registrationNumber = inputs.get(1);
			String color = inputs.get(2);
			Car car = new Car(registrationNumber, color);
			CarParkingStatus carParkingStatus = parkingService.parkCar(car);
			return carParkingStatus.getMessage();
		} else if (inputs.get(0).equals("leave")) {
			if (inputs.size() > 2) {
				throw new IllegalArgumentException("Expected only slot id as input");
			}
			Integer slotId;
			String slotIdStr = inputs.get(1);
			try {
				slotId = Integer.parseInt(slotIdStr);
			} catch (Exception e) {
				throw new IllegalArgumentException("Unable to parse:" + slotIdStr + "as integer");
			}
			CarParkingStatus carParkingStatus = parkingService.leaveCar(slotId);
			if (carParkingStatus.getIsSuccess()) {
				return carParkingStatus.getMessage();
			}
			throw new IllegalStateException(carParkingStatus.getMessage());
		} else if (inputs.get(0).equals("Status")) {
			if (inputs.size() > 1) {
				throw new IllegalArgumentException("Expected no other arguments in input");
			}
			return parkingService.getStatus();
		} else if (inputs.get(0).equals("registration_number_for_cars_with_color")) {
			if (inputs.size() != 2) {
				throw new IllegalArgumentException("Expected only color as input");
			}
			List<String> carRegistrationNumbersForAColor = parkingService
					.getCarRegistrationNumbersForAColor(inputs.get(1));
			if (carRegistrationNumbersForAColor.isEmpty()) {
				return "Not found";
			}
			return String.join(", ", carRegistrationNumbersForAColor);
		} else if (inputs.get(0).equals("slot_numbers_for_cars_with_color")) {
			if (inputs.size() != 2) {
				throw new IllegalArgumentException("Expected only color as input");
			}
			List<Integer> carSlotNumbersForAColor = parkingService.getCarSlotNumbersForAColor(inputs.get(1));
			if (carSlotNumbersForAColor.isEmpty()) {
				return "Not found";
			}
			return carSlotNumbersForAColor.toString();
		} else if (inputs.get(0).equals("slot_number_for_registration_number")) {
			if (inputs.size() != 2) {
				throw new IllegalArgumentException("Expected only registration number as input");
			}
			String registrationNumber = inputs.get(1);
			CarParkingStatus carSlotNumberForARegistrationNumber = parkingService
					.getCarSlotNumberForARegistrationNumber(registrationNumber);
			if (carSlotNumberForARegistrationNumber.getIsSuccess()) {
				return carSlotNumberForARegistrationNumber.getSlotId().toString();
			} else {
				return "Not found";
			}
		} else {
			throw new IllegalArgumentException("Invalid Input");
		}
	}

}
