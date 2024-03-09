// Group Member :  
// 1. Tan Yu Ting  19000505  COE
// 2. Rayan Roy A/L Edward  19000652  COE

import java.util.ArrayList;
import java.util.Scanner;

public class DriverRemote
{
	public static void main(String[] args) 
	{
		Scanner myObj = new Scanner(System.in); 
		
		ArrayList<Object> DevicesList = new ArrayList<Object>();
		
		SamsungTV TV = new SamsungTV();
		YorkAirCondition Aircond = new YorkAirCondition();
		PanasonicRemoteFan Fan = new PanasonicRemoteFan();
		SonyRadio Radio = new SonyRadio();
		HuaweiSpeaker Speaker = new HuaweiSpeaker();
		
		DevicesList.add(TV);
		DevicesList.add(Aircond);
		DevicesList.add(Fan);
		DevicesList.add(Radio);
		DevicesList.add(Speaker);
		
		for(Object connect: DevicesList)// use the object in the list to call the method
		{
			if(connect.getClass() == SamsungTV.class)
			{
				SamsungTV connected = (SamsungTV) connect;
				while(true)
				{
			    	System.out.printf(" Samsumg TV (ON/OFF) : ");
				    String option = myObj.nextLine();	
				    
				    if(option.toUpperCase().equalsIgnoreCase("ON"))  
				    {
				    	connected.on();
				    	System.out.printf("\n");
				    	System.out.println(" >>>The TV volume now is " + connected.getVolume());
				    	System.out.println(" >>>The TV channel now is " + connected.getChannel());
						System.out.println("--------------------------------- Menu -------------------------------------------");
						System.out.println(" Case -1  : Terminate the program ");
						System.out.println(" Case  0  : Adjust the volume     ");
						System.out.println(" Case  1  : Set Timer 		      ");
						System.out.println(" Case  2  : Set Channel           ");
						System.out.println(" Case  3  : Mute                  ");
						System.out.println("---------------------------------------------------------------------------------");				
						System.out.printf("\n PLEASE CHOOSE OPTION :  ");			
						int user = myObj.nextInt();
						//myObj.nextLine();
						while(user != -1)
						{
							switch(user)
							{
									case -1:{
												System.out.println(" \n TERMINATED");
												break;
											}
									
									case 0:{
												myObj.nextLine();
												System.out.printf("\n please choose increase the volume [Press +]  or  decrease the volume [Press -] :  ");
												String prompt = myObj.nextLine();
												while(true)
												{												
													if(prompt.equalsIgnoreCase("+")) 
													{
														System.out.printf(" How many times you want to increase the volume? (MAX : 10)  ");
														int Volup = myObj.nextInt();	
														connected.volumeUP(Volup);
														System.out.println(" >>>The TV volume now is " + connected.getVolume());
														break;
													}
													if(prompt.equalsIgnoreCase("-")) 
													{
														System.out.printf(" How many times you want to decrease the volume?  ");
														int Voldown = myObj.nextInt();	
														connected.volumeDOWN(Voldown);
														System.out.println(" >>>The TV volume now is " + connected.getVolume());
														break;
													}
													System.out.printf("\n Invalid input! Please choose increase the volume [Press +]  or  decrease the volume [Press -] :  ");
													prompt = myObj.nextLine();
												}
											}
											break;
											
									case 1:{ 
												myObj.nextLine();
												int hours;
												System.out.printf("\n");
												System.out.printf(" Please enter number for hours [ Max 12 hours ]  :");
											    hours =  myObj.nextInt();
												do {
												    if(hours<0 || hours> 12)
												    {
												    	System.out.printf(" Invalid input! Please enter number for hours [ Max 12 hours ]  :");
													    hours =  myObj.nextInt();
												    }
												}while(hours<0 || hours> 12);
												
												int mins;
												System.out.printf(" Please enter number for mins  [Max 59 minutes]  :");
												mins =  myObj.nextInt();
												do {
													if(mins<0 || mins> 59)
												    {
														System.out.printf(" Invalid input! Please enter number for mins  [Max 59 minutes]  :");
														mins =  myObj.nextInt();
												    }

												}while(mins<0 || mins>59);
												
												connected.timer(hours, mins);
												System.out.println(" The Timer is "+connected.get_timer());
										   }
											break;
											
									case 2:{
												myObj.nextLine();
												System.out.printf("\n");
												int TVch;
												System.out.printf(" Please enter  channel for your TV [1 to 120] :");
												TVch = myObj.nextInt();
												do 
												{
													if(TVch <0 || TVch >121)
													{
														System.out.printf(" Invalid input! Please enter channel for your TV [1 to 120] :");
														TVch = myObj.nextInt();
													}
													connected.setChannel(TVch);
													
												}while(TVch <0 || TVch >121);
										   }
											break;
											
									case 3:{																											
												connected.muteON();
										   }
											break;
							}//switch							
							System.out.printf("\n PLEASE CHOOSE OPTION :");			
							user = myObj.nextInt();
						}//while for case
						System.out.println("End for Samsung TV\n");
						myObj.nextLine();
				    	break;
				    }
				    else if(option.toUpperCase().equalsIgnoreCase("OFF"))  
				    {
				    	connected.off();
				    	System.out.printf("\n");
				    	break;
				    }
				    else
				    	System.out.println(" Invalid Input. Please try again!\n");
			    }				
			}//if samsung			
			
			if(connect.getClass() == SonyRadio.class)
			{
				SonyRadio connected = (SonyRadio) connect;
				while(true)
				{
			    	System.out.printf("Sony Radio (ON/OFF) : ");
				    String option = myObj.nextLine();	
				    
				    if(option.toUpperCase().equalsIgnoreCase("ON"))  
				    {
				    	connected.on();
				    	System.out.printf("\n");
				    	System.out.println(" >>>The Radio volume now is " + connected.getVolume());
				    	System.out.println(" >>>The Radio channel now is " + connected.getChannel());
				    	System.out.println("--------------------------------- Menu -------------------------------------------");
						System.out.println(" Case -1  : Terminate the program ");
						System.out.println(" Case  0  : Adjust the volume     ");
						System.out.println(" Case  1  : Set Timer 		      ");
						System.out.println(" Case  2  : Set Channel           ");
						System.out.println(" Case  3  : Mute                  ");
						System.out.println("---------------------------------------------------------------------------------");				
						System.out.printf("\n PLEASE CHOOSE OPTION :");			
						int user = myObj.nextInt();
						//myObj.nextLine();
						while(user != -1)
						{
							switch(user)
							{
									case -1:{
												System.out.println(" \n TERMINATED");
												break;
											}
									
									case 0:{
										
												myObj.nextLine();
												System.out.printf("\n please choose increase the volume [Press +]  or  decrease the volume [Press -] :  ");
												String prompt = myObj.nextLine();
												while(true)
												{																	
													if(prompt.equalsIgnoreCase("+")) 
													{
														System.out.printf(" How many times you wan to increase the volume? (MAX : 10)  ");
														int Volup = myObj.nextInt();	
														connected.volumeUP(Volup);
														System.out.println(" >>>The Radio volume now is " + connected.getVolume());
														break;
													}
													if(prompt.equalsIgnoreCase("-")) 
													{
														System.out.printf(" How many times you wan to decrease the volume?  ");
														int Voldown = myObj.nextInt();	
														connected.volumeDOWN(Voldown);
														System.out.println(" >>>The Radio volume now is " + connected.getVolume());
														break;
													}
													System.out.printf("\n Invalid input! Please choose increase the volume [Press +]  or  decrease the volume [Press -] :  ");
													prompt = myObj.nextLine();
												}
											}
											break;
											
									case 1:{ 
												myObj.nextLine();
												int hours;
												System.out.printf("\n");
												System.out.printf(" Please enter number for hours [ Max 12 hours ]  :");
											    hours =  myObj.nextInt();
												do {
												    if(hours<0 || hours> 12)
												    {
												    	System.out.printf(" Invalid input! Please enter number for hours [ Max 12 hours ]  :");
													    hours =  myObj.nextInt();
												    }
												}while(hours<0 || hours> 12);
												
												int mins;
												System.out.printf(" Please enter number for mins  [Max 59 minutes]  :");
												mins =  myObj.nextInt();
												do {
													if(mins<0 || mins> 59)
												    {
														System.out.printf(" Invalid input! Please enter number for mins  [Max 59 minutes]  :");
														mins =  myObj.nextInt();
												    }
		
												}while(mins<0 || mins>59);
												
												connected.timer(hours, mins);
												System.out.println(" The Timer is "+connected.get_timer());
										   }
											break;
											
									case 2:{
												myObj.nextLine();
												System.out.printf("\n");
												double Radioch;
												System.out.printf(" Please enter  channel for your Radio [0 to 99.99] :  ");
												Radioch = myObj.nextDouble();
												do 
												{
													if(Radioch <0 || Radioch >99.99)
													{
														System.out.printf(" Invalid input! Please enter channel for your Radio [0 to 99.99] :  ");
														Radioch = myObj.nextDouble();
													}
													connected.setChannel(Radioch);													
												}while(Radioch <0 || Radioch >99.99);
										   }
											break;
											
									case 3:{																											
												connected.muteON();
										   }
											break;
							}//switch							
							System.out.printf("\n PLEASE CHOOSE OPTION :");			
							user = myObj.nextInt();
						}//while for case
						System.out.println(" >>>End for Sony Radio\n");
						myObj.nextLine();
				    	break;
				    }
				    else if(option.toUpperCase().equalsIgnoreCase("OFF"))  
				    {
				    	connected.off();
				    	System.out.printf("\n");
				    	break;
				    }
				    else
				    	System.out.println(" Invalid Input. Please try again!\n");
			    }		
			}//if RADIO
			
			if(connect.getClass() == HuaweiSpeaker.class)
			{
				HuaweiSpeaker connected = (HuaweiSpeaker) connect;
				while(true)
				{
			    	System.out.printf(" Huawei Speaker (ON/OFF) : ");
				    String option = myObj.nextLine();	
				    
				    if(option.toUpperCase().equalsIgnoreCase("ON"))  
				    {
				    	connected.on();
				    	System.out.printf("\n");
				    	System.out.println(" >>>The Speaker volume now is " + connected.getVolume());
				    	System.out.println("--------------------------------- Menu -------------------------------------------");
						System.out.println(" Case -1  : Terminate the program ");
						System.out.println(" Case  0  : Adjust the volume     ");
						System.out.println(" Case  1  : Set Timer 		      ");
						System.out.println(" Case  2  : Mute                  ");
						System.out.println("---------------------------------------------------------------------------------");				
						System.out.printf("\n PLEASE CHOOSE OPTION :");			
						int user = myObj.nextInt();
						//myObj.nextLine();
						while(user != -1)
						{
							switch(user)
							{
									case -1:{
												System.out.println(" \n TERMINATED");
												break;
											}
									
									case 0:{
										
												myObj.nextLine();
												System.out.printf("\n please choose increase the volume [Press +]  or  decrease the volume [Press -] :  ");
												String prompt = myObj.nextLine();
												while(true)
												{																	
													if(prompt.equalsIgnoreCase("+")) 
													{
														System.out.printf(" How many times you wan to increase the volume? (MAX : 10)  ");
														int Volup = myObj.nextInt();	
														connected.volumeUP(Volup);
														System.out.println(" >>>The Speaker volume now is " + connected.getVolume());
														break;
													}
													if(prompt.equalsIgnoreCase("-")) 
													{
														System.out.printf(" How many times you wan to decrease the volume?  ");
														int Voldown = myObj.nextInt();	
														connected.volumeDOWN(Voldown);
														System.out.println(" >>>The Speaker volume now is " + connected.getVolume());
														break;
													}
													System.out.printf("\n Invalid input! Please choose increase the volume [Press +]  or  decrease the volume [Press -] :  ");
													prompt = myObj.nextLine();
												}
											}
											break;
											
									case 1:{ 
												myObj.nextLine();
												int hours;
												System.out.printf("\n");
												System.out.printf(" Please enter number for hours [ Max 12 hours ]  :");
											    hours =  myObj.nextInt();
												do {
												    if(hours<0 || hours> 12)
												    {
												    	System.out.printf(" Invalid input! Please enter number for hours [ Max 12 hours ]  :");
													    hours =  myObj.nextInt();
												    }
												}while(hours<0 || hours> 12);
												
												int mins;
												System.out.printf(" Please enter number for mins  [Max 59 minutes]  :");
												mins =  myObj.nextInt();
												do {
													if(mins<0 || mins> 59)
												    {
														System.out.printf(" Invalid input! Please enter number for mins  [Max 59 minutes]  :");
														mins =  myObj.nextInt();
												    }
		
												}while(mins<0 || mins>59);
												
												connected.timer(hours, mins);
												System.out.println(" The Timer is "+connected.get_timer());
										   }
											break;
									
									case 2:{																											
												connected.muteON();
										   }
											break;
							}//switch							
							System.out.printf("\n PLEASE CHOOSE OPTION :");			
							user = myObj.nextInt();
						}//while for case
						System.out.println("End for Huawei Speaker\n");
						myObj.nextLine();
				    	break;
				    }
				    else if(option.toUpperCase().equalsIgnoreCase("OFF"))  
				    {
				    	connected.off();
				    	System.out.printf("\n");
				    	break;
				    }
				    else
				    	System.out.println(" Invalid Input. Please try again!\n");
			    }		
			}//if speaker
						
			if(connect.getClass() == PanasonicRemoteFan.class)
			{
				PanasonicRemoteFan connected = (PanasonicRemoteFan) connect;
				while(true)
				{
			    	System.out.printf(" Panasonic Remote Fan (ON/OFF) : ");
				    String option = myObj.nextLine();	
				    
				    if(option.toUpperCase().equalsIgnoreCase("ON"))  
				    {
				    	connected.on();
				    	System.out.printf("\n");
				    	System.out.println(" >>>The Fan speed now is " + connected.getSpeed());
				    	System.out.println(" >>>" + connected.getSwing());
				    	System.out.println("--------------------------------- Menu -------------------------------------------");
						System.out.println(" Case -1  : Terminate the program ");
						System.out.println(" Case  0  : Adjust the Speed      ");
						System.out.println(" Case  1  : Set Timer 		      ");
						System.out.println(" Case  2  : Swing Mode Fan        ");
						System.out.println("---------------------------------------------------------------------------------");				
						System.out.printf("\n PLEASE CHOOSE OPTION :");			
						int user = myObj.nextInt();
						//myObj.nextLine();
						while(user != -1)
						{
							switch(user)
							{
									case -1:{
												System.out.println(" \n TERMINATED");
												break;
											}
									
									case  0:{
												myObj.nextLine();
												System.out.printf("\n please choose increase the volume [Press +]  or  decrease the speed of fan [Press -] :");
												String prompt = myObj.nextLine();
												
												
												if(prompt.equalsIgnoreCase("+")) 
												{
													System.out.printf(" How many times you wan to increase the speed of fan? (MAX : 5)  ");
													int Spdup = myObj.nextInt();	
													connected.SpeedUP(Spdup);
													System.out.println(" >>>The fan speed now is " + connected.getSpeed());
												}
												if(prompt.equalsIgnoreCase("-")) 
												{
													System.out.printf(" How many times you wan to decrease the speed of fan? ");
													int Spddown = myObj.nextInt();	
													connected.SpeedDOWN(Spddown);
													System.out.println(" >>>The fan speed now is " + connected.getSpeed());
												}
									  		}
											break;
											
									case 1:{ 
												myObj.nextLine();
												int hours;
												System.out.printf("\n");
												System.out.printf(" Please enter number for hours [ Max 12 hours ]  :");
											    hours =  myObj.nextInt();
												do {
												    if(hours<0 || hours> 12)
												    {
												    	System.out.printf(" Invalid input! Please enter number for hours [ Max 12 hours ]  :");
													    hours =  myObj.nextInt();
												    }
												}while(hours<0 || hours> 12);
												
												int mins;
												System.out.printf(" Please enter number for mins  [Max 59 minutes]  :");
												mins =  myObj.nextInt();
												do {
													if(mins<0 || mins> 59)
												    {
														System.out.printf(" Invalid input! Please enter number for mins  [Max 59 minutes]  :");
														mins =  myObj.nextInt();
												    }
		
												}while(mins<0 || mins>59);
												
												connected.timer(hours, mins);
												System.out.println(" The Timer is "+connected.get_timer());
										   }
											break;
									
									case 2:{
												myObj.nextLine();
												System.out.printf(" Swing Mode Fan [ON/OFF] ");
												String prompt = myObj.nextLine();
												while(true)
												{
													if(prompt.toUpperCase().equalsIgnoreCase("ON"))
													{
														connected.swingON();
														break;
													}
													if(prompt.toUpperCase().equalsIgnoreCase("OFF"))
													{
														connected.swingOFF();
														break;
													}
													System.out.printf(" Invalid input. Swing Mode [ON/OFF] ");
													prompt = myObj.nextLine();
												}
										   }
											break;
							}//switch							
							System.out.printf("\n PLEASE CHOOSE OPTION :");			
							user = myObj.nextInt();
						}//while for case
						System.out.println("End for Panasonic Remote Fan\n");
						myObj.nextLine();
				    	break;
				    }
				    else if(option.toUpperCase().equalsIgnoreCase("OFF"))  
				    {
				    	connected.off();
				    	System.out.printf("\n");
				    	break;
				    }
				    else
				    	System.out.println(" Invalid Input. Please try again!\n");
			    }		
			}//if Panasonic Remote Fan		
			
			if(connect.getClass() == YorkAirCondition.class)
			{
				YorkAirCondition connected = (YorkAirCondition) connect;
				while(true)
				{
			    	System.out.printf(" York Air Condition (ON/OFF) : ");
				    String option = myObj.nextLine();	
				    
				    if(option.toUpperCase().equalsIgnoreCase("ON"))  
				    {
				    	connected.on();
				    	System.out.printf("\n");
				    	System.out.println(" >>>The Air Cond temperature now is " + connected.getTemp());
				    	System.out.println(" >>>" + connected.getSwing());
				    	System.out.println("--------------------------------- Menu -------------------------------------------");
						System.out.println(" Case -1  : Terminate the program     ");
						System.out.println(" Case  0  : Adjust the  Temperature   ");
						System.out.println(" Case  1  : Set Timer 		          ");
						System.out.println(" Case  2  : Swing Mode Air Condition  ");
						System.out.println("---------------------------------------------------------------------------------");				
						System.out.printf("\n PLEASE CHOOSE OPTION :");			
						int user = myObj.nextInt();
						myObj.nextLine();
						while(user != -1)
						{
							switch(user)
							{
									case -1:{
												System.out.println(" \n TERMINATED");
												break;
											}
									
									case  0:{
												//myObj.nextLine();
												System.out.printf("\n please choose increase the volume [Press +]  or  decrease the volume [Press -] :  ");
												String prompt_1 = myObj.nextLine();	
												while(true)
												{
													if(prompt_1.equalsIgnoreCase("+")) 
													{														
														System.out.printf(" How many times you want to increase the temparature? (MAX 30) :  ");
														int TempUp = myObj.nextInt();	
														connected.TempUP(TempUp);
														System.out.println(" >>>The air cond temperature now is " + connected.getTemp());
														break;
													}
													if(prompt_1.equalsIgnoreCase("-")) 
													{
														System.out.printf(" How many times you wan to decrease the temperature? (MIN : 16)  ");
														int Tempdown = myObj.nextInt();	
														connected.TempDOWN(Tempdown);
														System.out.println(" >>>The air cond temperature now is " + connected.getTemp());
														break;
													}
													System.out.printf("\n Invalid input! Please choose increase the volume [Press +]  or  decrease the volume [Press -] :  ");
													prompt_1 = myObj.nextLine();
												}											
									  		}
											break;
											
									case 1:{ 
												int hours;
												System.out.printf("\n");
												System.out.printf(" Please enter number for hours [ Max 12 hours ]  :");
											    hours =  myObj.nextInt();
												do {
												    if(hours<0 || hours> 12)
												    {
												    	System.out.printf(" Invalid input! Please enter number for hours [ Max 12 hours ]  :");
													    hours =  myObj.nextInt();
												    }
												}while(hours<0 || hours> 12);
												
												int mins;
												System.out.printf(" Please enter number for mins  [Max 59 minutes]  :");
												mins =  myObj.nextInt();
												do {
													if(mins<0 || mins> 59)
												    {
														System.out.printf(" Invalid input! Please enter number for mins  [Max 59 minutes]  :");
														mins =  myObj.nextInt();
												    }
		
												}while(mins<0 || mins>59);
												
												connected.timer(hours, mins);
												System.out.println(" The Timer is "+connected.get_timer());
										   }
											break;
									
									case 2:{
												System.out.printf(" Swing Mode [ON/OFF] ");
												String prompt = myObj.nextLine();
												while(true)
												{
													if(prompt.toUpperCase().equalsIgnoreCase("ON"))
													{
														connected.swingON();
														break;
													}
													if(prompt.toUpperCase().equalsIgnoreCase("ON"))
													{
														connected.swingOFF();
														break;
													}
													System.out.printf(" Invalid input. Swing Mode [ON/OFF] ");
													prompt = myObj.nextLine();
												}
										   }
											break;								
							}//switch							
							System.out.printf("\n PLEASE CHOOSE OPTION :");			
							user = myObj.nextInt();
							myObj.nextLine();
						}//while for case
						System.out.println("End for York Air Condition\n");
				    	break;
				    }
				    else if(option.toUpperCase().equalsIgnoreCase("OFF"))  
				    {
				    	connected.off();
				    	System.out.printf("\n");
				    	break;
				    }
				    else
				    	System.out.println(" Invalid Input. Please try again!\n");
			    }		
			}//if York Air Condition 			
		}//for list
		myObj.close();
	}//main
}//class