package pl.edu.pw.ee;

import static pl.edu.pw.ee.Color.BLACK;
import static pl.edu.pw.ee.Color.RED;

public class RedBlackTree<K extends Comparable<K>, V> {

    private Node<K, V> root;
    private long recurtionCounter;
    private String printOrderValue;

    public V get(K key) {
        validateKey(key);
        Node<K, V> node = root;

        V result = null;

        while (node != null) {

            if (shouldCheckOnTheLeft(key, node)) {
                node = node.getLeft();

            } else if (shouldCheckOnTheRight(key, node)) {
                node = node.getRight();

            } else {
                result = node.getValue();
                break;
            }
        }
        return result;
    }

    public void deleteMax() {
        if (root == null) {
            throw new IllegalArgumentException();
        }
        root = deleteRecursive(root);
        if (root != null) {
            root.setColor(BLACK);
        }
    }

    private Node<K, V> deleteRecursive(Node<K, V> node) {
        if (node.getLeft() != null && node.getLeft().isRed()) {
            node = rotateRight(node);
        }
        if (node.getRight() == null) {
            return null;
        }
        if ((node.getRight() == null || !node.getRight().isRed())
                && (node.getRight().getLeft() == null || !node.getRight().getLeft().isRed())) {
            swapColors(node);
            if (node.getLeft() != null && node.getLeft().getLeft() != null && node.getLeft().getLeft().isRed()) {
                node = rotateRight(node);
                swapColors(node);
            }
        }
        node.setRight(deleteRecursive(node.getRight()));
        if (node.getRight() != null && node.getRight().isRed()) {
            node = rotateLeft(node);
        }
        if (node.getLeft() != null && node.getLeft().isRed() && node.getLeft().getLeft() != null
                && node.getLeft().getLeft().isRed()) {
            node = rotateRight(node);
        }
        if (node.getLeft() != null && node.getLeft().isRed() && node.getRight() != null && node.getRight().isRed()) {
            changeColors(node);
        }
        return node;
    }

    public void put(K key, V value) {
        recurtionCounter = 0;
        validateParams(key, value);
        root = put(root, key, value);
        root.setColor(BLACK);
    }

    private void validateKey(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Key cannot be null.");
        }
    }

    private boolean shouldCheckOnTheLeft(K key, Node<K, V> node) {
        return key.compareTo(node.getKey()) < 0;
    }

    private boolean shouldCheckOnTheRight(K key, Node<K, V> node) {
        return key.compareTo(node.getKey()) > 0;
    }

    private void validateParams(K key, V value) {
        if (key == null || value == null) {
            throw new IllegalArgumentException("Input params (key, value) cannot be null.");
        }
    }

    private Node<K, V> put(Node<K, V> node, K key, V value) {
        recurtionCounter++;
        if (node == null) {
            return new Node(key, value);
        }

        if (isKeyBiggerThanNode(key, node)) {
            putOnTheRight(node, key, value);

        } else if (isKeySmallerThanNode(key, node)) {
            putOnTheLeft(node, key, value);

        } else {
            node.setValue(value);
        }

        node = reorganizeTree(node);

        return node;
    }

    private boolean isKeyBiggerThanNode(K key, Node<K, V> node) {
        return key.compareTo(node.getKey()) > 0;
    }

    private void putOnTheRight(Node<K, V> node, K key, V value) {
        Node<K, V> rightChild = put(node.getRight(), key, value);
        node.setRight(rightChild);
    }

    private boolean isKeySmallerThanNode(K key, Node<K, V> node) {
        return key.compareTo(node.getKey()) < 0;
    }

    private void putOnTheLeft(Node<K, V> node, K key, V value) {
        Node<K, V> leftChild = put(node.getLeft(), key, value);
        node.setLeft(leftChild);
    }

    private Node<K, V> reorganizeTree(Node<K, V> node) {
        node = rotateLeftIfNeeded(node);
        node = rotateRightIfNeeded(node);
        changeColorsIfNeeded(node);

        return node;
    }

    private Node<K, V> rotateLeftIfNeeded(Node<K, V> node) {
        if (isBlack(node.getLeft()) && isRed(node.getRight())) {
            node = rotateLeft(node);
        }
        return node;
    }

    private Node<K, V> rotateLeft(Node<K, V> node) {
        Node<K, V> head = node.getRight();
        node.setRight(head.getLeft());
        head.setLeft(node);
        head.setColor(node.getColor());
        node.setColor(RED);

        return head;
    }

    private Node<K, V> rotateRightIfNeeded(Node<K, V> node) {
        if (isRed(node.getLeft()) && isRed(node.getLeft().getLeft())) {
            node = rotateRight(node);
        }
        return node;
    }

    private Node<K, V> rotateRight(Node<K, V> node) {
        Node<K, V> head = node.getLeft();
        Node<K, V> rightChild = null;
        if (head.getRight() != null) {
            rightChild = head.getRight();
        }

        node.setLeft(rightChild);
        head.setRight(node);
        head.setColor(head.getRight().getColor());
        head.getRight().setColor(RED);
        return head;
    }

    private void changeColorsIfNeeded(Node<K, V> node) {
        if (isRed(node.getLeft()) && isRed(node.getRight())) {
            changeColors(node);
        }
    }

    private void changeColors(Node<K, V> node) {
        node.setColor(RED);
        node.getLeft().setColor(BLACK);
        node.getRight().setColor(BLACK);
    }

    private void swapColors(Node<K, V> node) {
        if (node != null) {
            if (isRed(node)) {
                node.setColor(BLACK);
            } else {
                node.setColor(RED);
            }
        }
        if (node.getLeft() != null) {

            if (isRed(node.getLeft())) {
                node.getLeft().setColor(BLACK);
            } else
                node.getLeft().setColor(RED);

        }

        if (node.getRight() != null) {

            if (isRed(node.getRight())) {
                node.getRight().setColor(BLACK);
            } else
                node.getRight().setColor(RED);

        }
    }

    public long getRecursionCounter() {
        return recurtionCounter;
    }

    private boolean isBlack(Node<K, V> node) {
        return !isRed(node);
    }

    private boolean isRed(Node<K, V> node) {
        return node == null
                ? false
                : node.isRed();
    }

    public String getPreOrder() {
        printOrderValue = "";
        if (root == null) {
            return printOrderValue;
        }
        preOrder(root);
        printOrderValue = printOrderValue.substring(0, printOrderValue.length() - 1);
        return printOrderValue;
    }

    private void preOrder(Node<K, V> node) {
        printOrderValue = printOrderValue + node.getKey() + ":" + node.getValue() + " ";
        if (node.getLeft() != null) {
            preOrder(node.getLeft());
        }
        if (node.getRight() != null) {
            preOrder(node.getRight());
        }

    }

    public String getInOrder() {
        printOrderValue = "";
        if (root == null) {
            return printOrderValue;
        }
        inOrder(root);
        printOrderValue = printOrderValue.substring(0, printOrderValue.length() - 1);
        return printOrderValue;
    }

    private void inOrder(Node<K, V> node) {

        if (node.getLeft() != null) {
            preOrder(node.getLeft());
        }
        printOrderValue = printOrderValue + node.getKey() + ":" + node.getValue() + " ";
        if (node.getRight() != null) {
            preOrder(node.getRight());
        }

    }

    public String getPostOrder() {
        printOrderValue = "";
        if (root == null) {
            return printOrderValue;
        }
        postOrder(root);
        printOrderValue = printOrderValue.substring(0, printOrderValue.length() - 1);
        return printOrderValue;
    }

    private void postOrder(Node<K, V> node) {

        if (node.getLeft() != null) {
            preOrder(node.getLeft());
        }
        if (node.getRight() != null) {
            preOrder(node.getRight());
        }
        printOrderValue = printOrderValue + node.getKey() + ":" + node.getValue() + " ";

    }

    public static void main(String[] args) {
        RedBlackTree<Integer, String> tree = new RedBlackTree<>();
        for (int i = 0; i < 6; i++) {
            tree.put(i, "Something" + i);
        }
        System.out.println(tree.getPostOrder());
    }
}
