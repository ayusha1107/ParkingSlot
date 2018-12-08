package test.java.service;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import main.java.service.OperatorService;
import main.java.service.impl.OperatorServiceImpl;

public class OperatorServiceUnitTest {

	OperatorService operatorService;

	@Before
	public void setUp() {
		if (operatorService == null) {
			operatorService = new OperatorServiceImpl();
		}
	}

	@Test
	public void testCreateParkingSlot() {
		List<String> inputs = Arrays.asList("create_parking_lot", "6");
		String gotString = operatorService.operate(inputs);

		assertEquals("Expected same o/p", "Created a parking lot with 6 slots", gotString);

		inputs = Arrays.asList("park", "1", "White");
		gotString = operatorService.operate(inputs);
		assertEquals("Expected same o/p", "Allocated slot number:1", gotString);

		inputs = Arrays.asList("park", "12", "White");
		gotString = operatorService.operate(inputs);
		assertEquals("Expected same o/p", "Allocated slot number:2", gotString);

		inputs = Arrays.asList("park", "123", "Black");
		gotString = operatorService.operate(inputs);
		assertEquals("Expected same o/p", "Allocated slot number:3", gotString);

		inputs = Arrays.asList("park", "1234", "Red");
		gotString = operatorService.operate(inputs);
		assertEquals("Expected same o/p", "Allocated slot number:4", gotString);

		inputs = Arrays.asList("park", "12345", "Blue");
		gotString = operatorService.operate(inputs);
		assertEquals("Expected same o/p", "Allocated slot number:5", gotString);

		inputs = Arrays.asList("park", "123456", "Black");
		gotString = operatorService.operate(inputs);
		assertEquals("Expected same o/p", "Allocated slot number:6", gotString);

		inputs = Arrays.asList("leave", "4");
		gotString = operatorService.operate(inputs);
		assertEquals("Expected same o/p", "Slot number 4 is free", gotString);

		inputs = Arrays.asList("Status");
		String expectedString = "No	Registration	Slot No.	Colour\n" + "0\t1\t\tWhite\n" + "1\t12\t\tWhite\n"
				+ "2\t123\t\tBlack\n" + "3\t1234\t\tRed\n" + "4\t12345\t\tBlue\n" + "5\t123456\t\tBlack\n";
		gotString = operatorService.operate(inputs);
		assertEquals("Expected same o/p", expectedString, gotString);

		inputs = Arrays.asList("park", "1234567", "White");
		gotString = operatorService.operate(inputs);
		assertEquals("Expected same o/p", "Allocated slot number:4", gotString);

		inputs = Arrays.asList("park", "12345678", "White");
		gotString = operatorService.operate(inputs);
		assertEquals("Expected same o/p", "Sorry, parking lot is full", gotString);

		inputs = Arrays.asList("registration_number_for_cars_with_color", "White");
		gotString = operatorService.operate(inputs);
		assertEquals("Expected same o/p", "1, 12, 1234567", gotString);

		inputs = Arrays.asList("slot_numbers_for_cars_with_color", "White");
		gotString = operatorService.operate(inputs);
		assertEquals("Expected same o/p", "[1, 2, 4]", gotString);

		inputs = Arrays.asList("slot_number_for_registration_number", "123456");
		gotString = operatorService.operate(inputs);
		assertEquals("Expected same o/p", "6", gotString);

		inputs = Arrays.asList("slot_number_for_registration_number", "1223456");
		gotString = operatorService.operate(inputs);
		assertEquals("Expected same o/p", "Not found", gotString);
	}
}
