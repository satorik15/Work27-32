package Work30_1.Task1;

public class Node {
    private int data;
    private Node leftChild;
    private Node rightChild;

    public Node(int newData) {
        data = newData;
    }

    public void setLeftChild(Node newNode) {
        leftChild = newNode;
    }

    public void setRightChild(Node newNode) {
        rightChild = newNode;
    }

    public Node getLeftChild() {
        return leftChild;
    }

    public Node getRightChild() {
        return rightChild;
    }

    public int getData() {
        return data;
    }
}