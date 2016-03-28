import java.lang.UnsupportedOperationException;

public class TemperatureConverter 
{
	public static double convert(char fromUnit, char toUnit, double temperature) throws UnsupportedOperationException
	{
		//Convert to Celcius
		switch(fromUnit)
		{
			case 'C':
				break;
			case 'K':
				temperature -= 273.15;
				break;
			case 'F':
				temperature = (temperature - 32) * (5.0/9.0);
				break;
		}

		switch(toUnit)
		{
			case 'C':
				break;
			case 'K':
				temperature += 273.15;
				break;
			case 'F':
				temperature = (temperature + 32) * 1.8;
				break;
		}

		return temperature;
	}
} 
