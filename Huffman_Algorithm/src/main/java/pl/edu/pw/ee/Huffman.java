package pl.edu.pw.ee;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Comparator;

public class Huffman {
    private BufferedReader bf;
    private String line;
    private ArrayList<Node> nodeList;
    private ArrayList<CodeNode> codeNodeList;
    private Node head;
    private PrintWriter pw;
    private int codedBitCounter;
    private int decodedBitCounter;

    public int huffman(String pathToRootDir, boolean compress) {
        if (compress == true) {
            nodeList = new ArrayList<>();
            codeNodeList = new ArrayList<>();
            codedBitCounter = 0;
            readFileCoding(pathToRootDir);
            formTree();
            printKey(pathToRootDir);
            codeFile(pathToRootDir);
            return codedBitCounter;
        } else {
            decodedBitCounter = 0;
            codeNodeList = new ArrayList<>();
            readKey(pathToRootDir);
            decodeFile(pathToRootDir);
            return decodedBitCounter;
        }
    }

    // compress
    private void readFileCoding(String pathToRootDir) {
        File file = new File(pathToRootDir + "\\input.txt");
        try {
            bf = new BufferedReader(new InputStreamReader(new FileInputStream(file), Charset.forName("UTF-8")));
            line = bf.readLine();
            readLineCoding(line);
            while (line != null) {
                line = bf.readLine();
                if (line != null) {
                    readLineCoding(line);
                }
            }
            bf.close();
        } catch (IOException e) {
            throw new IllegalArgumentException("Input file does not exist or can not be read");
        }

    }

    private void readLineCoding(String line) {
        if (line == null) {
            throw new IllegalArgumentException("Input file empty");
        }

        for (char c : line.toCharArray()) {
            putToListOfNodes(c);
        }
        putToListOfNodes((char) 10);

    }

    private void putToListOfNodes(char charToPut) {
        for (Node node : nodeList) {
            if (node.compareToCharacter(charToPut) == 0) {
                node.incrementNOfEncounters();
                return;
            }
        }
        nodeList.add(new Node(charToPut));
    }

    private void formTree() {
        Comparator<Node> comparator = new Comparator<>() {
            @Override
            public int compare(Node n1, Node n2) {
                return n1.getNOfEncounters() - n2.getNOfEncounters();
            }
        };

        while (nodeList.size() > 1) {
            nodeList.sort(comparator);
            Node left = nodeList.remove(0);
            Node right = nodeList.remove(0);
            nodeList.add(new Node(left.getNOfEncounters() + right.getNOfEncounters(), left, right));

        }
        head = nodeList.get(0);

    }

    public void printKey(String pathToRootDir) {
        File file = new File(pathToRootDir + "\\key.txt");
        try {
            pw = new PrintWriter(new OutputStreamWriter(new FileOutputStream(file), "UTF-8"));
        } catch (IOException e) {
            throw new IllegalArgumentException("Could not create a key file");
        }
        printKeyRecursion("", head);
        pw.close();
    }

    public void printKeyRecursion(String prefix, Node node) {
        if (node.getLeft() != null) {
            String prefixLeft = prefix + "0";
            if ((int) node.getLeft().getCharacter() != 0) {
                CodeNode codeNode = new CodeNode(node.getLeft().getCharacter(), prefixLeft);
                codeNodeList.add(codeNode);
                pw.println(codeNode);
                codedBitCounter += prefixLeft.length() * node.getLeft().getNOfEncounters();
            }
            printKeyRecursion(prefixLeft, node.getLeft());
        }
        if (node.getRight() != null) {
            String prefixRight = prefix + "1";
            if ((int) node.getRight().getCharacter() != 0) {
                CodeNode codeNode = new CodeNode(node.getRight().getCharacter(), prefixRight);
                codeNodeList.add(codeNode);
                pw.println(codeNode);
                codedBitCounter += prefixRight.length() * node.getRight().getNOfEncounters();
            }
            printKeyRecursion(prefixRight, node.getRight());
        }
    }

    private void codeFile(String pathToRootDir) {
        File file = new File(pathToRootDir + "\\input.txt");
        File fileToWrite = new File(pathToRootDir + "\\outputCoded.txt");
        try {
            pw = new PrintWriter(new OutputStreamWriter(new FileOutputStream(fileToWrite), "UTF-8"));
            bf = new BufferedReader(new InputStreamReader(new FileInputStream(file), Charset.forName("UTF-8")));
            line = bf.readLine();
            codeLine(line);
            while (line != null) {
                line = bf.readLine();
                if (line != null) {
                    codeLine(line);
                }
            }
            pw.close();
            bf.close();
        } catch (IOException e) {
            throw new IllegalArgumentException("Input file does not exist or can not be read");
        }

    }

    private void codeLine(String line) {
        if (line == null) {
            throw new IllegalArgumentException("Input file is empty");
        }
        for (char c : line.toCharArray()) {
            for (CodeNode codeNode : codeNodeList) {
                if (codeNode.getCharacter() == c) {
                    pw.print(codeNode.getCode());
                    break;
                }
            }
        }
        for (CodeNode codeNode : codeNodeList) {
            if (codeNode.getCharacter() == 10) {
                pw.print(codeNode.getCode());
            }
        }
    }

    // decompress
    private void readKey(String pathToRootDir) {
        File fileKey = new File(pathToRootDir + "\\key.txt");
        try {
            bf = new BufferedReader(new InputStreamReader(new FileInputStream(fileKey), Charset.forName("UTF-8")));
            String line = bf.readLine();
            readLineKey(line);
            while (line != null && line.length() > 1) {
                line = bf.readLine();
                if (line != null) {
                    readLineKey(line);
                }
            }
            bf.close();
        } catch (IOException e) {
            throw new IllegalArgumentException("Key file does not exist or can not be read");
        }

    }

    private void readLineKey(String line) {
        if (line == null) {
            throw new IllegalArgumentException("Key file is empty");
        }
        int indexOfSemicoln = line.indexOf(":");
        if (indexOfSemicoln != -1) {
            if (Integer.parseInt(line.substring(0, indexOfSemicoln)) <= 0
                    || !check_if_code_proper(line.substring(indexOfSemicoln + 1))) {
                throw new IllegalArgumentException("Wrong format of key");

            } else {
                codeNodeList.add(new CodeNode((char) Integer.parseInt(line.substring(0, indexOfSemicoln)),
                        line.substring(indexOfSemicoln + 1)));
            }
        } else {
            throw new IllegalArgumentException("Wrong format of key");
        }
    }

    private boolean check_if_code_proper(String s) {
        for (char c : s.toCharArray()) {
            if (!(c == '0' || c == '1')) {
                return false;
            }

        }
        return true;
    }

    private void decodeFile(String pathToRootDir) {
        File sourceFile = new File(pathToRootDir + "\\outputCoded.txt");
        File fileToWrite = new File(pathToRootDir + "\\outputDecoded.txt");
        try {
            pw = new PrintWriter(new OutputStreamWriter(new FileOutputStream(fileToWrite), "UTF-8"));
            bf = new BufferedReader(new InputStreamReader(new FileInputStream(sourceFile), Charset.forName("UTF-8")));
            line = bf.readLine();
            if (line == null) {
                throw new IllegalArgumentException("File is empty");
            }
        } catch (IOException e) {
            throw new IllegalArgumentException("File does not exist");
        }
        Comparator<CodeNode> comparator = new Comparator<>() {
            @Override
            public int compare(CodeNode n1, CodeNode n2) {
                return n1.getCode().length() - n2.getCode().length();
            }
        };
        codeNodeList.sort(comparator);
        String toDecode = "";
        for (char c : line.toCharArray()) {
            if (!(c == '0' || c == '1')) {
                throw new IllegalArgumentException("Wrong format of compressed file");
            }
            toDecode += c;
            for (CodeNode codeNode : codeNodeList) {
                if (codeNode.getCode().equals(toDecode)) {
                    pw.print(codeNode.getCharacter());
                    decodedBitCounter++;
                    toDecode = "";
                    break;
                }
            }
        }
        pw.close();
        try {
            bf.close();
        } catch (IOException e) {
            throw new IllegalArgumentException("Could not close output file");
        }
    }
}
