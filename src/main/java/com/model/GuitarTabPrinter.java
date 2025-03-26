import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class Song {
    private String name;
    private List<Measure> measures;

    public void printAllTabsToConsoleAndFile(String filename) throws IOException {
        FileWriter writer = new FileWriter(filename);
        writer.write("Song: " + name + "\n");
        System.out.println("Song: " + name);
        
        for (Measure measure : measures) {
            String[] output = measure.getFormattedMeasure();
            for (String line : output) {
                writer.write(line + "\n");
                System.out.println(line);
            }
        }
        writer.close();
    }

    // Assume these getters/setters exist
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public List<Measure> getMeasures() { return measures; }
    public void setMeasures(List<Measure> measures) { this.measures = measures; }
}

public class Measure {
    private int number;
    private List<Sound> sounds;
    private static final String[] STRING_NAMES = {"e", "B", "G", "D", "A", "E"};

    public String[] getFormattedMeasure() {
        String[][] tabArray = createTabArray();
        int measureLength = sounds.size();
        String[] output = new String[8]; // Header + 6 strings + footer
        
        // Header
        output[0] = "\nMeasure " + number + ":";
        output[1] = "|" + repeat("-----|", measureLength);
        
        // String lines
        for (int string = 0; string < 6; string++) {
            StringBuilder line = new StringBuilder(STRING_NAMES[string] + "|");
            for (int pos = 0; pos < measureLength; pos++) {
                line.append(centerPad(tabArray[string][pos], 5)).append("|");
            }
            output[string + 2] = line.toString();
        }
        
        // Footer
        output[7] = "|" + repeat("-----|", measureLength);
        
        return output;
    }

    private String[][] createTabArray() {
        int measureLength = sounds.size();
        String[][] tabArray = new String[6][measureLength];
        
        // Initialize with dashes
        for (int string = 0; string < 6; string++) {
            for (int pos = 0; pos < measureLength; pos++) {
                tabArray[string][pos] = "-";
            }
        }
        
        // Fill array with note/chord data
        for (int pos = 0; pos < measureLength; pos++) {
            Sound sound = sounds.get(pos);
            
            if (sound instanceof Note) {
                Note note = (Note)sound;
                int stringIndex = 6 - note.getStringNumber();
                tabArray[stringIndex][pos] = String.valueOf(note.getFret());
            } 
            else if (sound instanceof Chord) {
                Chord chord = (Chord)sound;
                for (Note note : chord.getNotes()) {
                    int stringIndex = 6 - note.getStringNumber();
                    tabArray[stringIndex][pos] = String.valueOf(note.getFret());
                }
                if (pos == 0) {
                    tabArray[0][pos] = chord.getType();
                }
            }
        }
        return tabArray;
    }

    private String centerPad(String s, int width) {
        if (s.length() >= width) return s;
        int padding = width - s.length();
        int left = padding / 2;
        int right = padding - left;
        return repeat("-", left) + s + repeat("-", right);
    }

    private String repeat(String str, int times) {
        return new String(new char[Math.max(0, times)]).replace("\0", str);
    }

    // Assume these getters/setters exist
    public int getNumber() { return number; }
    public void setNumber(int number) { this.number = number; }
    public List<Sound> getSounds() { return sounds; }
    public void setSounds(List<Sound> sounds) { this.sounds = sounds; }
}