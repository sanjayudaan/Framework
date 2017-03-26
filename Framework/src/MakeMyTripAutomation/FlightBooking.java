package MakeMyTripAutomation;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

public class FlightBooking extends MakeMyTripAllFunctions {

	@Test
	public void BookFlight_OneWay() throws InterruptedException{
		//Set method name to MethodName variable
		methodName = new Object(){}.getClass().getEnclosingMethod().getName();
		//initialize method mane to reporter and test data sheet
		test = extent.startTest(methodName);
		dataSheet.storeSpecificRowDataToHashMap(methodName);

		//launch application
		String browserType = (String) dataSheet.getValue("BrowserType");
		launchMakeMytrip(browserType);

		//Select From Location
		String fromLocation = (String) dataSheet.getValue("FromLocation");
		String fromlocationCode = (String) dataSheet.getValue("FromLocationCode");
		selectLocation("From", fromLocation, fromlocationCode);

		//Select To Location
		String toLocation = (String) dataSheet.getValue("ToLocation");
		String tolocationCode = (String) dataSheet.getValue("ToLocationCode");
		selectLocation("To", toLocation, tolocationCode);

		//Select Depart Date
		selectDateStartAndreturn((String) dataSheet.getValue("DepartDate"),"Depart");
		test.log(LogStatus.PASS, "Select Depert Date as "+ "DepartDate" );

		// Select Passenger
		String N_AdultPassenger = (String) dataSheet.getValue("No_AdultPassengers");
		String N_ChildPassenger = (String) dataSheet.getValue("No_ChildPassengers");
		String N_InfantPassenger = (String) dataSheet.getValue("No_InfantsPassengers");
		String N_TotalNoOfPassengers = (String) dataSheet.getValue("Total_NoOfPassengers");
		selectPassengerDetails(N_AdultPassenger,N_ChildPassenger,N_InfantPassenger,N_TotalNoOfPassengers);
		test.log(LogStatus.PASS, "Total Passenger Selected "+ N_TotalNoOfPassengers );

		// Select Class
		String Class = (String) dataSheet.getValue("Class");
		selectClassDetails(Class);

		//Click On Search Button
		clickElement(xPathDesc.getValue("MMT_FlightPage","Search"),"Search");
		test.log(LogStatus.PASS, "Button Clicked "+ "Search" );


		// Select AirLine
		String Airline = (String) dataSheet.getValue("SelectAirline");
		SelectAirline(Airline);
		System.out.println("Change");
		// Click on Price Button to select lowest cost flight
		clickElement(xPathDesc.getValue("MMT_FlightPage","Price"),"Price");
		test.log(LogStatus.PASS, "Button Clicked"+ "Price" );

		//Click on Book button to book ticket
		clickElement(xPathDesc.getValue("MMT_FlightPage","Book"),"Book");
		test.log(LogStatus.PASS, "Button Clicked "+ "Book" );

		// COunt number of buttons and select middle button 
		List <WebElement> bookFlightButton = driver.findElements(By.xpath("//*[@id='content']//a[text()='Book']"));
		int count = bookFlightButton.size();
		int avg = count/2;
		bookFlightButton.get(avg).click();
		
		//




	}// End of Function 

	@Test
	public void BookFlight_TwoWay() throws InterruptedException{
		//Set method name to MethodName variable
		methodName = new Object(){}.getClass().getEnclosingMethod().getName();
		//initialize method mane to reporter and test data sheet
		test = extent.startTest(methodName);
		dataSheet.storeSpecificRowDataToHashMap(methodName);

		//launch application
		String browserType = (String) dataSheet.getValue("BrowserType");
		launchMakeMytrip(browserType);

		// Click on Round Trip button
		clickElement(xPathDesc.getValue("MMT_FlightPage","RoundTrip"),"RoundTrip");
		test.log(LogStatus.PASS, "Button Clicked "+ "Round Trip" );

		//Select From Location
		String fromLocation = (String) dataSheet.getValue("FromLocation");
		String fromlocationCode = (String) dataSheet.getValue("FromLocationCode");
		selectLocation("From", fromLocation, fromlocationCode);

		//Select To Location
		String toLocation = (String) dataSheet.getValue("ToLocation");
		String tolocationCode = (String) dataSheet.getValue("ToLocationCode");
		selectLocation("To", toLocation, tolocationCode);

		//Select Depart Date
		selectDateStartAndreturn((String) dataSheet.getValue("DepartDate"),"Depart");
		test.log(LogStatus.PASS, "Select Depert Date as "+ "DepartDate" );

		//Select Return Date
		selectDateStartAndreturn((String) dataSheet.getValue("ReturnDate"),"Return");
		test.log(LogStatus.PASS, "Select Return Date Date as "+ "Retun Date" );


		// Select Passenger
		String N_AdultPassenger = (String) dataSheet.getValue("No_AdultPassengers");
		String N_ChildPassenger = (String) dataSheet.getValue("No_ChildPassengers");
		String N_InfantPassenger = (String) dataSheet.getValue("No_InfantsPassengers");
		String N_TotalNoOfPassengers = (String) dataSheet.getValue("Total_NoOfPassengers");
		selectPassengerDetails(N_AdultPassenger,N_ChildPassenger,N_InfantPassenger,N_TotalNoOfPassengers);
		test.log(LogStatus.PASS, "Total Passenger Selected - "+ N_TotalNoOfPassengers );

		// Select Class
		String Class = (String) dataSheet.getValue("Class");
		selectClassDetails(Class);

		//Click On Search Button
		clickElement(xPathDesc.getValue("MMT_FlightPage","Search"),"Search");
		test.log(LogStatus.PASS, "Button Clicked - "+ "Search" );

		// Click on Depart Duration 
		clickElement(xPathDesc.getValue("MMT_FlightPage","DepartDuration"),"DepartDuration");
		test.log(LogStatus.PASS, "Button Clicked - "+ "Depart Duration" );


		//Click on Return Duration

		clickElement(xPathDesc.getValue("MMT_FlightPage","ReturnDuration"),"ReturnDuration");
		test.log(LogStatus.PASS, "Button Clicked - "+ "Return Duration" );

		// Select Depart flight
		clickElement(xPathDesc.getValue("MMT_FlightPage","DepartFlight"),"DepartFlight");
		test.log(LogStatus.PASS, "Button Clicked - "+ "Depart Flight" );


		// Select Return Flight

		clickElement(xPathDesc.getValue("MMT_FlightPage","ReturnFlight"),"ReturnFlight");
		test.log(LogStatus.PASS, "Button Clicked - "+ "Return Flight" );


		//Click on Book button to book ticket
		clickElement(xPathDesc.getValue("MMT_FlightPage","Book_ReturnFlight"),"Book_ReturnFlight");
		test.log(LogStatus.PASS, "Button Clicked "+ "Book Return Flight");

	}// End of Function 






}// End of Class
