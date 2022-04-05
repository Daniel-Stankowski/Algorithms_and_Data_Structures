package pl.edu.pw.ee;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import pl.edu.pw.ee.services.Sorting;

public class InsertionSortEfficiencyTests {
    private Sorting sorting;
    private Random random;
    private final long seed = 2138;
    private final int maxNumberOfValues = 10000;
    private static File randomEfficiencyFile;
    private static File optimisticEfiiciencyFile;
    private static File pesimisticEfficiencyFile;
    private long startPoint;
    private long endPoint;
    private PrintWriter writer;

    @Before
    public void setUp() {
        sorting = new InsertionSort();
        random = new Random(seed);
        randomEfficiencyFile = new File(
                "src\\test\\java\\pl\\edu\\pw\\ee\\sortingEfficiency\\insertionSortEfficiency\\randomEfficiency.txt");
        optimisticEfiiciencyFile = new File(
                "src\\test\\java\\pl\\edu\\pw\\ee\\sortingEfficiency\\insertionSortEfficiency\\optimisticEfficiency.txt");
        pesimisticEfficiencyFile = new File(
                "src\\test\\java\\pl\\edu\\pw\\ee\\sortingEfficiency\\insertionSortEfficiency\\pesimisticEfficiency.txt");
    }

    @Test
    public void randomArrayEfficiencyTest() {
        if (randomEfficiencyFile.delete())
            ;
        try {
            writer = new PrintWriter(randomEfficiencyFile);

        } catch (FileNotFoundException e) {
            assertTrue(false);
        }

        for (int i = 1000; i <= maxNumberOfValues; i += 500) {
            double[] randomEfficiencyArray = new double[i];

            for (int j = 0; j < i; j++) {
                randomEfficiencyArray[j] = random.nextDouble();
            }
            startPoint = System.nanoTime();
            sorting.sort(randomEfficiencyArray);
            endPoint = System.nanoTime();
            writer.println((int) ((endPoint - startPoint) / 1000) + " " + i);
        }
        writer.close();
        assertTrue(true);
    }

    @Test
    public void optimisticArrayEfficiencyTest() {
        if (optimisticEfiiciencyFile.delete())
            ;
        try {
            writer = new PrintWriter(optimisticEfiiciencyFile);

        } catch (FileNotFoundException e) {
            assertTrue(false);
        }

        for (int i = 1000; i <= maxNumberOfValues; i += 500) {
            double[] optimisticEfficiencyArray = new double[i];

            for (int j = 0; j < i; j++) {
                optimisticEfficiencyArray[j] = random.nextDouble() + j;
            }
            startPoint = System.nanoTime();
            sorting.sort(optimisticEfficiencyArray);
            endPoint = System.nanoTime();
            writer.println((int) ((endPoint - startPoint) / 1000) + " " + i);
        }
        writer.close();
        assertTrue(true);
    }

    @Test
    public void pesimisticArrayEfficiencyTest() {
        if (pesimisticEfficiencyFile.delete())
            ;
        try {
            writer = new PrintWriter(pesimisticEfficiencyFile);

        } catch (FileNotFoundException e) {
            assertTrue(false);
        }

        double[] pesimisticEfficiencyArray = new double[1];

        for (int i = 1000; i <= maxNumberOfValues; i += 500) {
            pesimisticEfficiencyArray = new double[i];

            for (int j = 0; j < i; j++) {
                pesimisticEfficiencyArray[j] = random.nextDouble() - j;
            }
            startPoint = System.nanoTime();
            sorting.sort(pesimisticEfficiencyArray);
            endPoint = System.nanoTime();
            writer.println((int) ((endPoint - startPoint) / 1000) + " " + i);
        }
        writer.close();
        assertTrue(true);
    }

}
