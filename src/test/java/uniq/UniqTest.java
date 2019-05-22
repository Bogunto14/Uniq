package uniq;

import org.junit.Test;
import java.io.*;
import static org.junit.Assert.*;

public class UniqTest {
    private String inputName = "input.txt";
    private String outputName = "output.txt";

    private void createInputFile(String[] lines) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(inputName))) {
            for (String line : lines) {
                bw.write(line);
                bw.newLine();
            }
        }
    }

    @Test
    public void createUniqTest1() throws IOException {
        String[] lines = {"aa", "bb", "bb", "bb"};
        String[] expectedLines = {"aa"};
        createInputFile(lines);
        Uniq u = new Uniq(false, 0, true, false);
        u.createUniq(inputName, outputName);
        checkOutputFile(expectedLines);
    }

    @Test
    public void createUniqTest2() throws IOException {
        String[] lines = {"aaaaaaAA", "AAAAAAaa", "hbbb", "cbbb"};
        String[] expectedLines = {"aaaaaaAA", "hbbb"};
        createInputFile(lines);
        Uniq u = new Uniq(true, 1, false, false);
        u.createUniq(inputName, outputName);
        checkOutputFile(expectedLines);
    }

    @Test
    public void createUniqTest3() throws IOException {
        String[] lines = {"Bogdan", "BogdAN", "bogdan", "Dzyuba"};
        String[] expectedLines = {"Bogdan", "Dzyuba"};
        createInputFile(lines);
        Uniq u = new Uniq(true, 0, false, false);
        u.createUniq(inputName, outputName);
        checkOutputFile(expectedLines);
    }

    @Test
    public void createUniqTest4() throws IOException {
        String[] lines = {"haha", "aaabogdan", "bbbbogdan", "cccbogdan", "Dzyuba"};
        String[] expectedLines = {"haha", "3 aaabogdan", "Dzyuba"};
        createInputFile(lines);
        Uniq u = new Uniq(true, 3, false, true);
        u.createUniq(inputName, outputName);
        checkOutputFile(expectedLines);
    }

    private void checkOutputFile(String[] expectedLines) throws IOException {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(outputName))) {
            String line;
            int i = 0;
            while ((line = bufferedReader.readLine()) != null) {
                assertEquals(line, expectedLines[i++]);
            }
        }
    }
}