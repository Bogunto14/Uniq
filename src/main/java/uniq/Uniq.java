package uniq;

import java.io.*;

class Uniq {

    private boolean ignoreCase;
    private int skipNCharacters;
    private boolean onlyUniq;
    private boolean numberOfString;

     public Uniq(boolean ignoreCase, int skipNCharacters, boolean onlyUniq, boolean numberOfString) {
        this.ignoreCase = ignoreCase;
        this.skipNCharacters = skipNCharacters;
        this.onlyUniq = onlyUniq;
        this.numberOfString = numberOfString;
    }

    void createUniq(String inputFile, String outputFile) throws IOException {
         try (
                InputStream inputStream = new FileInputStream(inputFile);
                OutputStream outputStream = new FileOutputStream(outputFile)
        ) {
            createUniq(inputStream, outputStream);
        }
    }

    private void createUniq(InputStream inputStream, OutputStream outputStream) throws  IOException {
        String pastLine = null;

        try(
                BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(outputStream))
        ) {
            int count = 0;
            String currentLine;
            while ((currentLine = br.readLine()) != null) {
                if (areEqual(currentLine, pastLine)) {
                    count++;
                } else {
                    printCountedStrings(pastLine, count, bw);
                    pastLine = currentLine;
                    count = 1;
                }
            }
            printCountedStrings(pastLine, count, bw);
        }
     }

    private boolean areEqual(String currentLine,String pastLine) {
        if (pastLine == null) return false;
        currentLine = currentLine.substring(skipNCharacters);
        pastLine = pastLine.substring(skipNCharacters);

        if (currentLine.equals(pastLine))
            return true;
        if (ignoreCase) {
            if (currentLine.equalsIgnoreCase(pastLine))
                return true;
        }
        return false;
    }

    private void printCountedStrings(String str, int count, BufferedWriter bw) throws IOException {
        if (str != null) {
            if (onlyUniq && count > 1) return;
            if (numberOfString)
                if (count > 1)
                    bw.write(count + " ");
                if (ignoreCase) {
                    str.toLowerCase();
                }
                bw.write(str);
                bw.newLine();
                bw.flush();
        }
    }
}





