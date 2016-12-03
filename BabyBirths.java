
import java.io.File;

import org.apache.commons.csv.CSVRecord;

import edu.duke.DirectoryResource;
import edu.duke.FileResource;

public class BabyBirths {
    public void printNames () {
        FileResource fr = new FileResource();
        for (CSVRecord rec : fr.getCSVParser(false)) {
            int numBorn = Integer.parseInt(rec.get(2));
            if( numBorn <= 100) {
                System.out.println("Name " + rec.get(0) +
                                   " Gender " + rec.get(1) +
                                   " Num Born " + rec.get(2));
            }
        }
    }
    
    public void totalBirths (FileResource fr) {
        int totalBirths = 0;
        int totalBoys = 0;
        int totalGirls = 0;
        int totalNameOfBoys = 0;
        int totalNameOfGirls = 0;
        int totalNames = 0;
        for (CSVRecord rec : fr.getCSVParser(false)) {
            int numBorn = Integer.parseInt(rec.get(2));
            totalBirths = totalBirths + numBorn;
            if (rec.get(1).equals("M")) {
                totalBoys = totalBoys + numBorn;
                totalNameOfBoys ++;
            }
            else {
                 totalGirls = totalGirls + numBorn;
                  totalNameOfGirls ++;
            }
        }
        totalNames = totalNameOfBoys + totalNameOfGirls;
        System.out.println("total births = " + totalBirths);
        System.out.println("total girls = " + totalGirls);
        System.out.println("total boys = " + totalBoys);
        System.out.println("total girlsNames = " + totalNameOfGirls);
        System.out.println("total boysNames = " + totalNameOfBoys);
        System.out.println("total names of the file = " + totalNames);
    }
    
    public void testTotalBirths () {
        FileResource fr = new FileResource("C:\\Users\\steve\\Desktop\\Projects BJ\\CSVfiles\\us_babynames\\us_babynames_by_year\\yob1905.csv");
        totalBirths(fr);
    }
    
    public int getRank(int year, String name , String gender) {
        String fileName = "C:\\Users\\steve\\Desktop\\Projects BJ\\CSVfiles\\us_babynames karkinos\\us_babynames_by_year\\yob" + year +".csv";
        FileResource fr = new FileResource(fileName);
        int rankOfGirls = 0;
        int rankOfBoys = 0;
        for(CSVRecord rec : fr.getCSVParser(false)) {
                if(rec.get(1).equals("M")) {
                    rankOfBoys ++;
                }
                
                if(rec.get(1).equals("F")) {
                    rankOfGirls ++;
                }
                
                if(rec.get(0).equals(name) && rec.get(1).equals(gender)) {
                    if(rec.get(1).equals("M")){
                        return rankOfBoys;
                    }
                    
                    return rankOfGirls;
                }
        }

        return -1;
    }
    
     public int getRankFast(int year, String name , String gender, FileResource fr) {
         
        int rankOfGirls = 0;
        int rankOfBoys = 0;
        for(CSVRecord rec : fr.getCSVParser(false)) {
                if(rec.get(1).equals("M")) {
                    rankOfBoys ++;
                }
                
                if(rec.get(1).equals("F")) {
                    rankOfGirls ++;
                }
                
                if(rec.get(0).equals(name) && rec.get(1).equals(gender)) {
                    if(rec.get(1).equals("M")){
                        return rankOfBoys;
                    }
                    
                    return rankOfGirls;
                }
        }

        return -1;
    }
    
    public void testInt () {
        String name = "Jacob";
        int checkName = Integer.parseInt(name);
        System.out.println(checkName);
    }
    
    public void testGetRank() {
        
        System.out.println("The rank of the name for this gender is = " + getRank(1990,"Drew","M"));
    }
    
    public String getName(int year, int rank , String gender) {
        FileResource fr = new FileResource("C:\\Users\\steve\\Desktop\\Projects BJ\\CSVfiles\\us_babynames\\us_babynames_by_year\\yob"+year+".csv");
        int eachFile = 0;
        for(CSVRecord record : fr.getCSVParser(false)) {
            if(record.get(1).equals(gender)){
                eachFile = eachFile + 1;
                if(eachFile == rank)
                return record.get(0);
            }
        }
        //System.out.println("the rank: " + rank + " The last one rank " + eachFile + ".");
        return "NO NAME";
    }
    
    public void testGetName() {
        System.out.println("The name of this rank and for this gender is = " + getName(2014,430,"M"));
    }

    public void whatIsNameInYear(String name, int year, int newYear, String gender) {
        int rank = getRank(year, name , gender);
        String equalName = getName(newYear, rank , gender);
        System.out.println(name + "born in " + year + "would be " + equalName + " if she/he was born in " + newYear);
    }
    
    public int yearOfHighestRank(String name, String gender) {
        
        int yearHigh = 0;
        DirectoryResource dr = new DirectoryResource();
        //int year = 0;
        int maximumRank = 0;
        
        for(File file : dr.selectedFiles()){
            final Integer currYear = Integer.parseInt(file.getPath().replaceAll("[^\\d]", ""));
            int currentRank = getRank(currYear,name,gender);
            if( currentRank > maximumRank){
                yearHigh = currYear;
                maximumRank = currentRank;
                System.out.println(yearHigh + "-" + maximumRank);
                
            }
        }
        if(yearHigh > 0){
            return yearHigh;
        }
        return -1;
    }
    

    public void testYearOfTheHighestRank() {
        //FileResource fr = new FileResource("C:\\Users\\steve\\Desktop\\Projects BJ\\CSVfiles\\us_babynames\\us_babynames_test\\yob2012short.csv");
        //yearOfHighestRank("Mason","M");
        System.out.println("The year of the highest rank for this name is : " + yearOfHighestRank("Genevieve", "F"));
        //CSVParser parser = fr.getCSVParser();
        //CSVRecord highest = yearOfHighestRank(parser);
        //System.out.println("The year of the highest rank for this name is : " + yearOfHighestRank("Mason", "M" + fr));
    }
    
    public String printHelloName(String name) {
        name = ("Hello" +" "+ name);
//         System.out.println(hello +" "+ name);
        return name;
    }
    
    public void test() {
        String result = printHelloName("Stavros");
        System.out.println("Result is: " + result);
    }
    
    public double getAverageRank(String name, String gender) {
        DirectoryResource dr = new DirectoryResource();
        int totalFiles = 0;
        double totalRank = 0;
        double averageRank = 0;
        for(File file : dr.selectedFiles()) {
            final Integer year = Integer.parseInt(file.getPath().replaceAll("[^\\d]", ""));
            int currentRank = getRank(year,name,gender);
            if(currentRank != -1){
                totalRank += currentRank;
                totalFiles ++;
            }
            else {
                return -1;
            }
        }
           averageRank = totalRank/totalFiles;
            return averageRank;
        }
    public void testGetAverageRank() {
        System.out.println("The Average Rank for these files is " + getAverageRank("Susan","F"));
    }
    
    public int getTotalBirthsRankedHigher(int givenNumBorn, String name, String gender) {
        int totalNumberOfBirths = 0;
        FileResource fr = new FileResource();
        
        for(CSVRecord record : fr.getCSVParser(false)) {
            String currentName = record.get(0);
            String currentGender = record.get(1);
            int numBorn = Integer.parseInt(record.get(2));
            if(currentGender.equals(gender)&& numBorn > givenNumBorn) {
                totalNumberOfBirths += numBorn;
            }
            
        }
        
        
        return totalNumberOfBirths;
    }
    
    public void testGetToltaBirthsRankedHigher() {
        System.out.println("The total births who are ranked higher for the given name is : " + getTotalBirthsRankedHigher(1953,"Drew","M"));
    }
}
