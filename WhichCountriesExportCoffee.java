
import edu.duke.*;
import org.apache.commons.csv.*;

public class WhichCountriesExportCoffee {
    public void listExporters(CSVParser parser, String exportOfInterest) {
        int howManyCountrysGold = 0;
        for (CSVRecord record : parser) {
            String export = record.get("Exports");
            
          //if (export.indexOf(exportOfInterest) != -1) do the same thing like below.
            if (export.contains(exportOfInterest)) {
                String country = record.get("Country");
                howManyCountrysGold = howManyCountrysGold + 1;
                System.out.println(country);
                
            }
        }
          System.out.print(howManyCountrysGold);
    }
    public void whoExportsCoffe() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        listExporters(parser, "cocoa");
    }
    public void tester() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        for(CSVRecord record : parser){
            System.out.print(record.get("Country") + ": ");
            System.out.print(record.get("Exports") + ": ");
            System.out.println(record.get("Value (dollars)"));
        }
        
    }
    public void listExportersTwoProducts(CSVParser parser,String exportItem1, String exportItem2) {
        for (CSVRecord record : parser) {
            String exportItems = record.get("Exports");
            if(exportItems.contains(exportItem1) && exportItems.contains(exportItem2)) {
                String country = record.get("Country");
                System.out.println(country);
            }
        }
    }
    public void whoExportsGoldAndDiamond() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        listExportersTwoProducts(parser, "cotton" , "flowers");
        for(CSVRecord record : parser) {
           System.out.println(record.get("Country")); 
        }
    }
    
    public void testerQuiz() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        String dna = ("999,999,999,999");
        int indexCount = dna.indexOf("999,999,999,999");
        for(CSVRecord record : parser){
            if(indexCount > 0) {
                indexCount = indexCount + 1;
                
            }
           // else {
               // double currentValue = Double.parseDouble(record.get("Value (dollars)"));
           // }
            System.out.print(record.get("Country") + ": ");
            //System.out.print(record.get("Exports") + ": ");
            System.out.println(record.get("Value (dollars)"));
        }
        
    }
}
