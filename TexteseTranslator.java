import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class TexteseTranslator {
    
    public static void main(String[] args) {
        String paragraphFilename = "C:\\Users\\ttaty\\Downloads\\paragraph.txt";
        String texteseFilename = "C:\\Users\\ttaty\\Downloads\\texteses.txt";
        String outputFilename = "C:\\Users\\ttaty\\Downloads\\changed.txt";
        
        // Read the textese file and store the abbreviations in a hash map
        HashMap<String, String> texteseMap = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(texteseFilename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\s+");
                if (parts.length >= 2) {
                    texteseMap.put(parts[0], parts[1]);
                } else {
                    System.out.println("Invalid line in textese file: " + line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        
        // Read the paragraph file and replace any words with their corresponding abbreviations
        StringBuilder output = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(paragraphFilename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] words = line.split("\\s+");
                for (String word : words) {
                    if (texteseMap.containsKey(word)) {
                        output.append(texteseMap.get(word)).append(" ");
                    } else {
                        output.append(word).append(" ");
                    }
                }
                output.append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        
        // Write the modified paragraph to a new file
        try (FileWriter writer = new FileWriter(outputFilename)) {
            writer.write(output.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        // Display the modified paragraph
        System.out.println(output);
    }
}
