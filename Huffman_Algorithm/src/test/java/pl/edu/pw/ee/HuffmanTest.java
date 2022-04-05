package pl.edu.pw.ee;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

import org.junit.Before;
import org.junit.Test;

public class HuffmanTest {
    private Huffman huffman;

    @Before
    public void setUp() {
        huffman = new Huffman();
    }

    @Test
    public void ShouldThrowExeptionWhenWrongDirPathCompress() {
        boolean isCatched = false;
        try {
            huffman.huffman("src\\test\\java\\pl\\edu\\pw\\ee\\test_folders\\nothing", true);
        } catch (IllegalArgumentException e) {
            isCatched = true;
        }
        assertTrue(isCatched);
    }

    @Test
    public void ShouldThrowExeptionWhenWrongDirPathDecompress() {
        boolean isCatched = false;
        try {
            huffman.huffman("src\\test\\java\\pl\\edu\\pw\\ee\\test_folders\\nothing", false);
        } catch (IllegalArgumentException e) {
            isCatched = true;
        }
        assertTrue(isCatched);
    }

    @Test
    public void ShouldThrowExeptionWhenInputFileDoesNotExistCompress() {
        boolean isCatched = false;
        try {
            huffman.huffman("src\\test\\java\\pl\\edu\\pw\\ee\\test_folders\\no_input_both_compress_decompress", true);
        } catch (IllegalArgumentException e) {
            isCatched = true;
        }
        assertTrue(isCatched);
    }

    @Test
    public void ShouldThrowExeptionWhenInputFileDoesNotExistDecompress() {
        boolean isCatched = false;
        try {
            huffman.huffman("src\\test\\java\\pl\\edu\\pw\\ee\\test_folders\\no_input_both_compress_decompress", false);
        } catch (IllegalArgumentException e) {
            isCatched = true;
        }
        assertTrue(isCatched);
    }

    @Test
    public void ShouldThrowExeptionWhenKeyFileDoesNotExistDecompress() {
        boolean isCatched = false;
        try {
            huffman.huffman("src\\test\\java\\pl\\edu\\pw\\ee\\test_folders\\no_key_file", false);
        } catch (IllegalArgumentException e) {
            isCatched = true;
        }
        assertTrue(isCatched);
    }

    @Test
    public void ShouldThrowExeptionWhenInputFileEmptyCompress() {
        boolean isCatched = false;
        try {
            huffman.huffman("src\\test\\java\\pl\\edu\\pw\\ee\\test_folders\\empty_files", true);
        } catch (IllegalArgumentException e) {
            isCatched = true;
        }
        assertTrue(isCatched);
    }

    @Test
    public void ShouldThrowExeptionWhenInputFileEmptyDecompress() {
        boolean isCatched = false;
        try {
            huffman.huffman("src\\test\\java\\pl\\edu\\pw\\ee\\test_folders\\empty_files", false);
        } catch (IllegalArgumentException e) {
            isCatched = true;
        }
        assertTrue(isCatched);
    }

    @Test
    public void ShouldThrowExeptionWhenKeyFileEmptyDecompress() {
        boolean isCatched = false;
        try {
            huffman.huffman("src\\test\\java\\pl\\edu\\pw\\ee\\test_folders\\empty_files", false);
        } catch (IllegalArgumentException e) {
            isCatched = true;
        }
        assertTrue(isCatched);
    }

    @Test
    public void ShouldThrowExeptionWhenInputFileWrongDecompress() {
        boolean isCatched = false;
        try {
            huffman.huffman("src\\test\\java\\pl\\edu\\pw\\ee\\test_folders\\wrong_input_file_decompress", false);
        } catch (IllegalArgumentException e) {
            isCatched = true;
        }
        assertTrue(isCatched);
    }

    @Test
    public void ShouldThrowExeptionWhenKeyHasWrongForm_NoSemicoln() {
        boolean isCatched = false;
        try {
            huffman.huffman("src\\test\\java\\pl\\edu\\pw\\ee\\test_folders\\no_semicoln_key", false);
        } catch (IllegalArgumentException e) {
            isCatched = true;
        }
        assertTrue(isCatched);
    }

    @Test
    public void ShouldThrowExeptionWhenKeyHasWrongForm_CharInCode() {
        boolean isCatched = false;
        try {
            huffman.huffman("src\\test\\java\\pl\\edu\\pw\\ee\\test_folders\\wrong_key_format1", false);
        } catch (IllegalArgumentException e) {
            isCatched = true;
        }
        assertTrue(isCatched);
    }

    @Test
    public void ShouldThrowExeptionWhenKeyHasWrongForm_CharacterValueBellowOrEqual0() {
        boolean isCatched = false;
        try {
            huffman.huffman("src\\test\\java\\pl\\edu\\pw\\ee\\test_folders\\wrong_key_format2", false);
        } catch (IllegalArgumentException e) {
            isCatched = true;
        }
        assertTrue(isCatched);
    }

    @Test
    public void ShouldThrowExeptionWhenKeyHasWrongForm_Both() {
        boolean isCatched = false;
        try {
            huffman.huffman("src\\test\\java\\pl\\edu\\pw\\ee\\test_folders\\wrong_key_format3", false);
        } catch (IllegalArgumentException e) {
            isCatched = true;
        }
        assertTrue(isCatched);
    }

    @Test
    public void FilesShouldBeSameAfterCompressionAndDecompression1() throws IOException {
        String file = "src\\niemanie";
        huffman.huffman(file, true);
        huffman.huffman(file, false);
        assertTrue(areTheSame(new File(file + "\\input.txt"), new File(file + "\\outputDecoded.txt")));

    }

    @Test
    public void FilesShouldBeSameAfterCompressionAndDecompression2() throws IOException {
        String file = "src\\niemanie_refren";
        huffman.huffman(file, true);
        huffman.huffman(file, false);
        assertTrue(areTheSame(new File(file + "\\input.txt"), new File(file + "\\outputDecoded.txt")));

    }

    @Test
    public void FilesShouldBeSameAfterCompressionAndDecompression3() throws IOException {
        String file = "src\\Pan_Tadeusz";
        huffman.huffman(file, true);
        huffman.huffman(file, false);
        assertTrue(areTheSame(new File(file + "\\input.txt"), new File(file + "\\outputDecoded.txt")));

    }

    private boolean areTheSame(File f1, File f2) throws IOException {
        BufferedReader bf1 = new BufferedReader(
                new InputStreamReader(new FileInputStream(f1), Charset.forName("UTF-8")));
        BufferedReader bf2 = new BufferedReader(
                new InputStreamReader(new FileInputStream(f2), Charset.forName("UTF-8")));
        String l1, l2;
        do {
            l1 = bf1.readLine();
            l2 = bf2.readLine();
            if (!((l1 == null && l2 != null) || (l2 == null && l1 != null) || l1 == l2) && !l1.equals(l2)) {
                bf1.close();
                bf2.close();
                return false;
            }
        } while (l1 != null || l2 != null);
        bf1.close();
        bf2.close();
        return true;
    }
}
