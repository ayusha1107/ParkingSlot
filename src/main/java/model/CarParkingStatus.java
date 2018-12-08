package main.java.model;

public class CarParkingStatus {

	Boolean isSuccess;
	String message;
	Integer slotId;

	public CarParkingStatus(Boolean isSuccess, String message, Integer slotId) {
		super();
		this.isSuccess = isSuccess;
		this.message = message;
		this.slotId = slotId;
	}

	public Boolean getIsSuccess() {
		return isSuccess;
	}

	public void setIsSuccess(Boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Integer getSlotId() {
		return slotId;
	}

	public void setSlotId(Integer slotId) {
		this.slotId = slotId;
	}
}
