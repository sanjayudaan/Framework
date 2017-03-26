package MakeMyTripAutomation;

import org.testng.annotations.Test;
import com.relevantcodes.extentreports.LogStatus;

public class HotelBooking extends MakeMyTripAllFunctions {
	
	@Test
	public void Hotel_Booking () throws InterruptedException {
		//Set method name to MethodName variable
		methodName = new Object(){}.getClass().getEnclosingMethod().getName();
		//initialize method mane to reporter and test data sheet
		test = extent.startTest(methodName);
		dataSheet.storeSpecificRowDataToHashMap(methodName);

		//launch application
		String browserType = (String) dataSheet.getValue("BrowserType");
		launchMakeMytrip(browserType);
		
		// Click on Hotel 
		clickElement(xPathDesc.getValue("MMT_FlightPage","Hotel"),"Hotel");
		test.log(LogStatus.PASS, "Button Clicked "+ "Hotel" );
		
		//Select City
			String City = (String) dataSheet.getValue("City");
			String CityCode = (String) dataSheet.getValue("CityCode");
			selectLocation("City",City,CityCode);
		
		// Select Check In Date		
			selectDateStartAndreturn((String) dataSheet.getValue("Check_In_Date"),"CheckIn");
			test.log(LogStatus.PASS, "Select Check In Date as "+ "Check_In_Date" );
		
		
		//Select Check Out Date
			selectDateStartAndreturn((String) dataSheet.getValue("Check_Out_Date"),"CheckOut");
			test.log(LogStatus.PASS, "Select Check Out Date as "+ "Check_Out_Date" );
			
		// Select Room and No. Of Guests
			
			
			
			
			
			
		
	} // End of Hotel Booking Function
	
	
	

}// End of Class
