package MakeMyTripAutomation;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.relevantcodes.extentreports.LogStatus;

import DriverScript.DriverScript;

public class MakeMyTripAllFunctions extends DriverScript {

	private static final String String = null;

	//#############################################################################################
	/*	Function Name: launchMakeMytrip */
	/*	Description: Lunch MMT Application */
	//#############################################################################################
	public void launchMakeMytrip(String browserType) {
		//Get Value from Environment Sheet
		String applicationURL = environmentDataSheet.getValue("MakeMyTrip_URL");
		launchApplication(applicationURL, browserType);
		if (waitfor_ElementToBePresent(10, xPathDesc.getValue("MMT_FlightPage", "MMT_Logo"))) {
			test.log(LogStatus.PASS, "Make My Trip is launched");
		}
		else{
			test.log(LogStatus.FAIL, "Make My Trip is not launched");
		}
	}

	//#############################################################################################
	/*	Function Name: selectLocation */
	/*	Description: Select Location */
	//#############################################################################################
	public void selectLocation(String locationType, String location, String locationCode){
		if(locationType.equals("From")){
			//Enter value to from location
			EnterValueToTextBox(xPathDesc.getValue("MMT_FlightPage", "FromLocation"), location, "From Location text box");
			if (waitfor_ElementToBePresent(10, "//ul[contains(@class,'hp-widget__sfrom')]//span[contains(text(),'"+ locationCode +"')]/parent::p")) {
				clickElement("//ul[contains(@class,'hp-widget__sfrom')]//span[contains(text(),'"+ locationCode +"')]/parent::p", "From Location");
				test.log(LogStatus.PASS, "Select From Location as "+location);
			}
			else{
				Assert.assertTrue(false);
			}
		}
		else if(locationType.equals("To")){
			//Enter value to to location
			EnterValueToTextBox(xPathDesc.getValue("MMT_FlightPage", "ToLocation"), location, "To Location text box");
			if (waitfor_ElementToBePresent(10, "//ul[contains(@class,'hp-widget__sTo')]//span[contains(text(),'"+ locationCode +"')]/parent::p")) {
				clickElement("//ul[contains(@class,'hp-widget__sTo')]//span[contains(text(),'"+ locationCode +"')]/parent::p", "To Location");
				test.log(LogStatus.PASS, "Select To Location as "+location);
			}
			else{
				Assert.assertTrue(false);
			}
						
		}
		
		if (locationType.equals("City")){
			EnterValueToTextBox(xPathDesc.getValue("MMT_FlightPage", "City"), location, "City text box");
			if (waitfor_ElementToBePresent(10, "//ul[contains(@class,'ui-widget-content')]//span[contains(text(),'"+ locationCode +"')]/parent::p")) {
				clickElement("//ul[contains(@class,'ui-widget-content')]//span[contains(text(),'"+ locationCode +"')]/parent::p", "To Location");
				test.log(LogStatus.PASS, "Select City Location as "+location);
			}
			else{
				Assert.assertTrue(false);
			}	
		}
	}

	//#############################################################################################
	/*	Function Name: selectDate */
	/*	Description: Select Date */
	//#############################################################################################
	public void selectDateStartAndreturn(String date,String Type) throws InterruptedException{
		//split date
		String[] arrDate = date.split("-");
		String xpathCal = null;
		//xpath for calendar
		if(Type.equals("Depart")){
			waitfor_ElementToBePresent(10, xPathDesc.getValue("MMT_FlightPage", "DepartCalender"));
			clickElement(xPathDesc.getValue("MMT_FlightPage", "DepartCalender"), "Depart Calender");
			Thread.sleep(3000);
			xpathCal = "//div[@class='dateFilter hasDatepicker']";
		}
		else if(Type.equals("Return")){
			waitfor_ElementToBePresent(10, xPathDesc.getValue("MMT_FlightPage", "ReturnCalender"));
			clickElement(xPathDesc.getValue("MMT_FlightPage", "ReturnCalender"), "Return Calender");
			Thread.sleep(3000);
			xpathCal = "//div[@class='dateFilterReturn hasDatepicker']";
		}
		//==================Code to Select Check In and Chek Out Date=====================
		if (Type.equals("CheckIn")){
			waitfor_ElementToBePresent(10, xPathDesc.getValue("MMT_FlightPage", "Check_In"));
			clickElement(xPathDesc.getValue("MMT_FlightPage", "Check_In"), "Check In Calender");
			Thread.sleep(3000);
			xpathCal = "//div[@class='dateFilter hasDatepicker']";
		}
		else if(Type.equals("CheckOut")){
			waitfor_ElementToBePresent(10, xPathDesc.getValue("MMT_FlightPage", "Check_Out"));
			clickElement(xPathDesc.getValue("MMT_FlightPage", "Check_Out"), "Check Out Calender");
			Thread.sleep(3000);
			xpathCal = "//div[@class='dateFilterReturn hasDatepicker']"; 				
		}
		
		List<WebElement> calender = driver.findElements(By.xpath(xpathCal+"//div[contains(@class,'datepicker-group')]"));
		//select date
		for(int i = 0;i<calender.size();i++){
			String getMonth = driver.findElement(By.xpath(xpathCal+"//div[contains(@class,'datepicker-group')]["+(i+1)+"]//span[@class='ui-datepicker-month']")).getText();
			String getYear = driver.findElement(By.xpath(xpathCal+"//div[contains(@class,'datepicker-group')]["+(i+1)+"]//span[@class='ui-datepicker-year']")).getText();
			System.out.println(getMonth);
			System.out.println(getYear);
			if((getMonth.equals(arrDate[1])) && (getYear.equals(arrDate[2]))){
				driver.findElement(By.xpath(xpathCal+"//div[contains(@class,'datepicker-group')]["+(i+1)+"]//tbody//td[@data-handler='selectDay']/a[text()='"+arrDate[0]+"']")).click();
				Thread.sleep(2000);
				return;
			}
		}
		//click on next button
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath(xpathCal+"//span[text()='Next']/parent::a")));
		driver.findElement(By.xpath(xpathCal+"//span[text()='Next']/parent::a")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath(xpathCal+"//span[text()='Next']/parent::a")).click();
		Thread.sleep(2000);
		selectDateStartAndreturn(date,Type);
		return;
	}
	
	//***************************************************************************
	/* Function Name: Select Passenger Details*/
	/* Description : Used to Select Passenger Details*/
	//***************************************************************************
	public void selectPassengerDetails(String Adult, String Child, String Infant,String TotalPassengers) throws InterruptedException{
		
		clickElement(xPathDesc.getValue("MMT_FlightPage","Passenger"),"Passenger");
		// Select Adult passenger
		if(!Adult.isEmpty()){
			
			if (waitfor_ElementToBePresent(10,"//*[@id='js-adult_counter']")) {
				clickElement("//*[@id='js-adult_counter']/li["+Adult+"]","Adult");
				test.log(LogStatus.PASS, "Select No. of adult as "+Adult);
			}
			else{
				Assert.assertTrue(false);
			}	
		}
		// Select Child Passenger
		if(!Child.isEmpty()){
			
			if (waitfor_ElementToBePresent(10,"//*[@id='js-child_counter']")) {
				clickElement("//*[@id='js-child_counter']/li["+Child+"]","Child");
				test.log(LogStatus.PASS, "Select No. of Child as "+Child);
			}
			else{
				Assert.assertTrue(false);
			}	
		}		
		// Select Infant Passenger
		if(!Infant.isEmpty()){
			
			if (waitfor_ElementToBePresent(10,"//*[@id='js-infant_counter']")) {
				clickElement("//*[@id='js-infant_counter']/li["+Infant+"]","Infant");
				test.log(LogStatus.PASS, "Select No. of adult as "+Infant);
			}
			else{
				Assert.assertTrue(false);
			}	
		}
		Thread.sleep(2000);
		System.out.println(driver.findElement(By.xpath("//*[@id='hp-widget__paxCounter']")).getAttribute("value"));
	}	
		//***************************************************************************
		/* Function Name: Select Passenger Class Details*/
		/* Description : Used to Select Passenger Class Details*/
		//***************************************************************************
		public void selectClassDetails(String TktClass){
			clickElement(xPathDesc.getValue("MMT_FlightPage","Class"),"Class");
			
			if(TktClass.equals("Economy")){
				
				if (waitfor_ElementToBePresent(10,"//*[@id='js-classFilters']/div[1]/label")) {
					clickElement("//*[@id='js-classFilters']/div[1]/label","Economy");
					test.log(LogStatus.PASS, "Select Class"+ TktClass);
				}
			}
			else if(TktClass.equals("Premium Economy")){
					if (waitfor_ElementToBePresent(10,"//*[@id='js-classFilters']/div[2]/label")) {
						clickElement("//*[@id='js-classFilters']/div[2]/label","Premium Economy");
						test.log(LogStatus.PASS, "Select Class"+ TktClass);
					}
			}
				else if(TktClass.equals("Business")) {
						if (waitfor_ElementToBePresent(10,"//*[@id='js-classFilters']/div[3]/label")) {
							clickElement("//*[@id='js-classFilters']/div[3]/label","Business");
							test.log(LogStatus.PASS, "Select Class"+ TktClass);
						}
						else {
							Assert.assertTrue(false);
						}
												
				}	
						
		}// End of function
	
	public void SelectGuestAndRoomDetails(){
		
		
		
		
		
	}// End of function
	
	//=========================================
	/*Function to select Airline
	 * 
	 * 
	 * 
	 */
	public void SelectAirline(String Airline){
		
		clickElement("//div[@class='filter_subdivisions aln-dep']//span[text() ='"+(Airline)+"']/parent::span/following-sibling::span",Airline);
				
	}
	
}//end of Class
