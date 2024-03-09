
public class YorkAirCondition implements UniversalRemote
{
	boolean on = false;
	boolean swing = false;
	int temperature = 20; //min:16, max:30
	int hours=0;
    int mins=0;
	public void on() 
	{
		 this.on = true;
		 System.out.println(" >>>The York Air Condition is ON.");
	}

	public void off() 
	{
		 this.on = false;
		 System.out.println(" >>>The York Air Condition is OFF.");
	}
	public void timer(int hrs, int min)
	{
		if(on && hrs <=12 && min <=60)
		{
			this.hours=hrs;
			this.mins=min;
		}
		if (this.on == false) 
	    {
	        System.out.println("The Air Cond is not ON");
	    }
	}

	public String get_timer()
	{
		return this.hours+":"+this.mins;
	}
	public void TempUP(int value)
	{
		for(int i=0; i<value; i++)
		{
		    if (this.on == true && temperature < 30) 
		    {
		        this.temperature++;
		    }
		}
		if (this.on == false) 
	    {
	        this.temperature = 0;
	    }
	}
	
	public void TempDOWN(int value)
	{
		for(int i=0; i<value; i++)
		{
		    if (this.on == true && temperature > 16) 
		    {
		        this.temperature--;
		    }
		}
	    if (this.on == false) 
	    {
	        this.temperature = 0;
	    }
	}
	
	public void swingON()
	{
		if(this.on == true)
		{
			this.swing = true;
			System.out.println(" >>>The swing mode of Air Cond is ON.");
		}
		else
			System.out.println(" >>>The Air Cond is not switched on yet.");
		
	}
	
	public void swingOFF()
	{
		if(this.on == true)
		{
			this.swing = false;
			System.out.println(" >>>The swing mode of Air Cond is OFF.");
		}
		else
			System.out.println(" >>>The Air Cond is not switched on yet.");
	}
	
	public int getTemp()
	{
		return this.temperature;
	}
	
	public String getSwing()
	{
		if(this.swing==true)
			return "The Swing mode of Air Cond is ON.";
		else
			return "The Swing mode of Air Cond is OFF.";
	}
	
	public String toString()
	{
		return "York Air Condition";
	}
}
