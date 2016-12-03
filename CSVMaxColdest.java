

import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;
public class CSVMaxColdest {
    public CSVRecord coldestHourInFile(CSVParser parser) {
        CSVRecord coldestSoFar = null;
        for(CSVRecord currentRow : parser) {
            if (coldestSoFar == null) {
                coldestSoFar = currentRow;
            }
            else {
                double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
                double coldestTemp = Double.parseDouble(coldestSoFar.get("TemperatureF"));
                if (currentTemp > coldestTemp) {
                    coldestSoFar = currentRow;
                }
            }
       }
       return coldestSoFar;
       }
    
    public void testColdestHourInFile() {
        FileResource fr = new FileResource("C:\\Users\\steve\\Desktop\\Projects BJ\\CSVfiles\\nc_weather\\nc_weather\\2014\\weather-2014-01-01.csv");
        CSVRecord coldest = coldestHourInFile(fr.getCSVParser());
        System.out.println("coldest temperature was " + coldest.get("TemperatureF")
                            + " at " + coldest.get("TimeEST"));
    }
    
    public CSVRecord fileWithColdestTemperature() {
        CSVRecord coldestTempSoFar = null;
        DirectoryResource dr = new DirectoryResource();
        for(File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            CSVRecord currentRow = coldestHourInFile(fr.getCSVParser());
            if(coldestTempSoFar == null) {
                coldestTempSoFar = currentRow;
            }
            else {
                double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
                double coldestTemp = Double.parseDouble(coldestTempSoFar.get("TemperatureF"));
                if (currentTemp > coldestTemp) {
                    coldestTempSoFar = currentRow;
                }
            }
        }
        return coldestTempSoFar;
    }
    
    public void testFileWithColdestTemperature() {
        CSVRecord coldest = fileWithColdestTemperature();
        System.out.println("coldest temperature was " + coldest.get("TemperatureF") + " at " 
                            + coldest.get("DateUTC"));
        //call the method coldestHourInFile() to determine the coldest temperature on that day.
    }
    
    public CSVRecord lowestHumidityInFile(CSVParser parser) {
        CSVRecord lowestHumiditySoFar = null;
        for(CSVRecord currentRow : parser) {
            if(lowestHumiditySoFar == null) {
                lowestHumiditySoFar = currentRow;
            }
            else {
                double currentHumidity = Double.parseDouble(currentRow.get("Humidity"));
                double lowestHumidity = Double.parseDouble(lowestHumiditySoFar.get("Humidity"));
                if(currentHumidity <= lowestHumidity) {
                    lowestHumiditySoFar = currentRow;
                }
            }
       }
       return lowestHumiditySoFar;
    }

    public void testLowestHumidityInFile() {
    FileResource fr = new FileResource();
    CSVParser parser = fr.getCSVParser();
    CSVRecord lowest = lowestHumidityInFile(parser);
    System.out.println("lowest humidity was " + lowest.get("Humidity") + " at "
                        + lowest.get("DateUTC"));
    }

    public CSVRecord lowestHumidityInManyFiles() {
        CSVRecord lowestHumiditySoFar = null;
        DirectoryResource dr = new DirectoryResource();
        for(File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            CSVRecord currentRow = lowestHumidityInFilee(fr.getCSVParser());
            if(lowestHumiditySoFar == null) {
                lowestHumiditySoFar = currentRow;
            }
            else {
                double currentHumidity = Double.parseDouble(currentRow.get("Humidity"));
                double lowestHumidity = Double.parseDouble(lowestHumiditySoFar.get("Humidity"));
                if(currentHumidity <= lowestHumidity) {
                    lowestHumiditySoFar = currentRow;
                }
            }
        }
        return lowestHumiditySoFar;
    }
    
    public void testLowestHumidityInManyFiles() {
        CSVRecord lowest = lowestHumidityInManyFiles();
        System.out.println("lowest humidity was " + lowest.get("Humidity") + " at " 
                            + lowest.get("DateUTC"));
    }
    
    public double averageTemperatureInFile(CSVParser parser) {
        int count = 0;
        double total = 0;
        for(CSVRecord record : parser) {
            double currTemp = Double.parseDouble(record.get("TemperatureF"));
            total = total + currTemp;
            count = count + 1;
        }
        return total/count;
    }
    
    public void testAverageTemperatureInFile() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        double temp = averageTemperatureInFile(parser);
        System.out.println("The average Temperature in that file is : " + temp);
    }
    
    public double averageTemperatureWithHighHumidityInFile (CSVParser parser, int value) {
        double totalAVG = 0;
        double avg = 0;
        int count = 1;
        for(CSVRecord record : parser) {
            double currentHumidity = Double.parseDouble(record.get("Humidity"));
            double currentTemp = Double.parseDouble(record.get("TemperatureF"));
            if (currentHumidity >= value){
                totalAVG = totalAVG + currentTemp;
                avg = totalAVG/count;
                count = count + 1;
            }
        }
        return avg;
    }
    
    public void testAverageTemperatureWithHighHumidityInFile() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        double avg = averageTemperatureWithHighHumidityInFile(parser,80);
        if(avg == 0)System.out.println("No temperatures with that humidity " );
        System.out.println("The average temperature with high humidity is : " + avg);
    }
    
    public CSVRecord lowestHumidityInFilee (CSVParser parser){
		
		//generate a null lowHumid CSV record
		CSVRecord lowHumid = null;
		
		for(CSVRecord record : parser){
			
			String currHumid = record.get("Humidity");
			
			if(!currHumid.equals("N/A")){

				double numHumid = Double.parseDouble( record.get("Humidity") );
				
				if(lowHumid == null || numHumid < Double.parseDouble( lowHumid.get("Humidity") ) )
					lowHumid = record;
			}
			
			
		}//end for CSVRecord loop;
		
		
		return lowHumid;
	}//end lowestHumidityInFile() method
}
