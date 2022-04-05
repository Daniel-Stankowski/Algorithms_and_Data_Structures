package pl.edu.pw.ee;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class RedBlackTreePerformanceTest {
    File dataFile, resultFile;
    private BufferedReader br;
    private PrintWriter pw;
    private RedBlackTree<String, String> tree;
    private String[] arrayWithWords;

    @Before
    public void setUp() throws FileNotFoundException {
        arrayWithWords = new String[100000];
        tree = new RedBlackTree<>();
        dataFile = new File("src\\test\\java\\pl\\edu\\pw\\ee\\data.txt");
        resultFile = new File("src\\test\\java\\pl\\edu\\pw\\ee\\result\\performance.txt");

        try {
            pw = new PrintWriter(resultFile);
        } catch (Exception e) {
            throw new FileNotFoundException("Can not open result file");
        }
        try {
            br = new BufferedReader(new FileReader(dataFile));
            for (int i = 0; i < 100000; i++) {
                arrayWithWords[i] = br.readLine();
            }
        } catch (Exception e) {
            throw new FileNotFoundException("Can not open data file");
        }

        pw.println("nOfData\trecursiveCalls");
    }

    @Test
    public void performanceTest() {
        for (int i = 0; i < 100000; i++) {
            for (int j = 0; j < 10; j++) {
                tree.put(arrayWithWords[i] + j, arrayWithWords[i] + j);
                pw.println(i * 10 + j + 1 + "\t" + tree.getRecursionCounter());
            }
        }
    }

    @After
    public void cleanUp() {
        pw.close();
    }
}
