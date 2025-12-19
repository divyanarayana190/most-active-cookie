import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

public class Solution {
    public static void main(String[] args) {

        //Exit if invalid number of arguments
        if (args.length!=3 && !args[1].equals("-d")){
            System.out.println("Invalid number of arguments");
            return;
        }

        String filepath = args[0];
        String targetDate = args[2];

        //LinkedHashMap is used here to maintain the order of insertion
        Map<String, Integer> cookieCount = new LinkedHashMap<>();

        try(BufferedReader br = new BufferedReader(new FileReader(filepath))){

            br.readLine();
            String lineByLine;
            while((lineByLine = br.readLine()) != null){
                String[] parts = lineByLine.split(",");
                String cookie = parts[0];
                String timeStamp = parts[1];
                String date = timeStamp.split("T")[0];

                if (date.equals(targetDate)){
                    cookieCount.put(cookie, cookieCount.getOrDefault(cookie, 0) + 1);
                }
            }
        }
        catch (IOException e){
            System.out.println("Error reading file: " + e.getMessage());
        }catch (Exception e){
            System.out.println("An error occurred: " + e.getMessage());
        }

        //If no cookies found for the given date
        if (cookieCount.isEmpty()){
            System.out.println("No cookies found for the given date");
            return;
        }

        //Find the maximum count using stream API
        int maxCount = cookieCount.values().stream().max(Integer::compareTo).orElse(0);

        for (Map.Entry<String, Integer> entry : cookieCount.entrySet()) {
            if (entry.getValue() == maxCount) {
                System.out.println(entry.getKey());
            }
        }
    }
}