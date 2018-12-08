package main.java.model;

import java.util.ArrayList;
import java.util.List;

public class ParkingLot {

	List<Car> cars;
	Integer maximumCarCount;

	public ParkingLot(Integer maximumCarCount) {
		super();
		this.maximumCarCount = maximumCarCount;
		this.cars = new ArrayList<>(maximumCarCount);
	}

	public List<Car> getCars() {
		return cars;
	}

	public void setCars(List<Car> cars) {
		this.cars = cars;
	}

	public Integer getMaximumCarCount() {
		return maximumCarCount;
	}

	public void setMaximumCarCount(Integer maximumCarCount) {
		this.maximumCarCount = maximumCarCount;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cars == null) ? 0 : cars.hashCode());
		result = prime * result + ((maximumCarCount == null) ? 0 : maximumCarCount.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ParkingLot other = (ParkingLot) obj;
		if (cars == null) {
			if (other.cars != null)
				return false;
		} else if (!cars.equals(other.cars))
			return false;
		if (maximumCarCount == null) {
			if (other.maximumCarCount != null)
				return false;
		} else if (!maximumCarCount.equals(other.maximumCarCount))
			return false;
		return true;
	}
}
