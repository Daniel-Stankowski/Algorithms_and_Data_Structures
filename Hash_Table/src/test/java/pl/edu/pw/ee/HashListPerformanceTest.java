package pl.edu.pw.ee;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

public class HashListPerformanceTest {
    private File dataFile;
    private File resultsFile;
    private BufferedReader bReader;
    private PrintWriter pWriter;
    private String singleLine;
    private String[] sourceDataArray;
    private int size;
    private int iterator;
    private long timeStart;
    private long timeStop;
    private long[] timeDataArray;

    @Before
    public void setUp() throws FileNotFoundException {
        timeDataArray = new long[30];
        iterator = 0;
        sourceDataArray = new String[100000];
        size = 4096;
        dataFile = new File("src\\test\\java\\pl\\edu\\pw\\ee\\data.txt");
        resultsFile = new File("src\\test\\java\\pl\\edu\\pw\\ee\\testingData\\hashListTestResults.txt");
        try {
            bReader = new BufferedReader(new FileReader(dataFile));
        } catch (Exception e) {
            throw new FileNotFoundException("Source file does not exist");
        }
        try {
            while ((singleLine = bReader.readLine()) != null) {
                sourceDataArray[iterator] = singleLine;
                iterator++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            pWriter = new PrintWriter(resultsFile);
        } catch (Exception e) {
            throw new FileNotFoundException("Can not create result file");
        }
    }

    @Test
    public void performanceTest() {
        pWriter.println("nOfData\t\tTime");
        for (int i = 0; i < 7; i++, size *= 2) {
            HashListChaining<String> hashList = new HashListChaining<>(size);
            for (int j = 0; j < 100000; j++) {
                hashList.add(sourceDataArray[j]);
            }
            for (int j = 0; j < 30; j++) {
                timeStart = System.nanoTime();
                for (int k = 0; k < 100000; k++) {
                    hashList.get(sourceDataArray[k]);
                }
                timeStop = System.nanoTime();
                timeDataArray[j] = (timeStop - timeStart) / 1000;
            }
            Arrays.sort(timeDataArray);
            int average = 0;
            for (int j = 10; j < 20; j++) {
                average += timeDataArray[j];
            }
            average /= 10;
            pWriter.println(size + "\t\t" + average);
        }
        pWriter.close();
    }
}
