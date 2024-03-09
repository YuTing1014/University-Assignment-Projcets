
public class HuaweiSpeaker 
{
	boolean on = false;
	int volume = 1;
	int hours = 0;
	int mins =0;
	
	public void on() 
	{
		 this.on = true;
		 System.out.println(" >>>The Huawei Speaker is ON.");
	}

	public void off() 
	{
		 this.on = false;
		 System.out.println(" >>>The Huawei Speaker is OFF.");
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
	        System.out.println("The Speaker is not ON");
	    }
	}

	public String get_timer()
	{
		return this.hours+":"+this.mins;
	}
	public void volumeUP(int value)
	{
		for(int i=0; i<value; i++)
		{
		    if (this.on == true && volume < 10) 
		    {
		        this.volume++;
		    }
		}
		if (this.on == false) 
	    {
	        this.volume = 0;
	    }
	}
	
	public void volumeDOWN(int value)
	{
		for(int i=0; i<value; i++)
		{
		    if (this.on == true && volume > 0) 
		    {
		        this.volume--;
		        if(volume ==0)
		        {
		        	System.out.println(" >>>The Speaker is muted.");
		        }
		    }
		    
		}
		
	    if (this.on == false) 
	    {
	        this.volume = 0;
	        System.out.println(" >>>The Speaker is not switch on.");
	    }
	}
	
	public void muteON()
	{
		if(this.on== true)
		{
			this.volume=0;
			System.out.println(" >>>The Speaker is muted.");
		}
		else
			System.out.println(" >>>The Speaker is not switch on.");
	}
	
	
	public int getVolume()
	{
		return this.volume;
	}
	
	public String toString()
	{
		return "Huawei Speaker";
	}
}
