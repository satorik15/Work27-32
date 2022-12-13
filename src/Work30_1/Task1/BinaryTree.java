package Work30_1.Task1;

public class BinaryTree {
    private Node root;

    public Node find(int findData) {
        Node current = root;
        while (current.getData() != findData) {
            if (findData < current.getData())
                current = current.getLeftChild();
            else
                current = current.getRightChild();
            if (current == null)
                return null;
        }
        return current;
    }

    public void insert(int insertData) {
        Node current = root;
        Node parrent;
        Node newNode = new Node(insertData);
        if (root == null)
            root = newNode;
        else {
            while (true) {
                parrent = current;
                if (insertData < current.getData()) {
                    current = current.getLeftChild();
                    if (current == null) {
                        parrent.setLeftChild(newNode);
                        return;
                    }
                }
                else {
                    current = current.getRightChild();
                    if (current == null) {
                        parrent.setRightChild(newNode);
                        return;
                    }
                }
            }
        }
    }

    public Node getMinimum(Node startPoint) {
        Node current = startPoint;
        Node parrent = current;
        while (current != null) {
            parrent = current;
            current = current.getLeftChild();
        }
        return parrent;
    }

    public Node getMaximum(Node startPoint) {
        Node current = startPoint;
        Node parrent = current;
        while (current != null) {
            parrent = current;
            current = current.getRightChild();
        }
        return parrent;
    }

    public Node getRoot() {
        return root;
    }

    public Node getSuccessor(Node deleteNode) {
        Node parrentSuccessor = deleteNode;
        Node successor = deleteNode;
        Node current = successor.getRightChild();
        while (current != null) {
            parrentSuccessor = successor;
            successor = current;
            current = current.getLeftChild();
        }

        if (successor != deleteNode.getRightChild()) {
            parrentSuccessor.setLeftChild(successor.getRightChild());
            successor.setRightChild(deleteNode.getRightChild());
        }
        return successor;
    }

    public boolean delete(int deleteData) {
        Node current = root;
        Node parent = current;
        boolean isLeftChild = false;
        while (current.getData() != deleteData) {
            parent = current;
            if (deleteData < current.getData()) {
                current = current.getLeftChild();
                isLeftChild = true;
            } else {
                isLeftChild = false;
                current = current.getRightChild();
            }
            if (current == null)
                return false;
        }

        if (current.getLeftChild() == null && current.getRightChild() == null) {
            if (current == root)
                current = null;
            else if (isLeftChild)
                parent.setLeftChild(null);
            else
                parent.setRightChild(null);
        }
        else if (current.getRightChild() == null) {
            if (current == root)
                root = current.getLeftChild();
            else if (isLeftChild)
                parent.setLeftChild(current.getLeftChild());
            else
                current.setRightChild(current.getLeftChild());
        } else if (current.getLeftChild() == null) {
            if (current == root)
                root = current.getRightChild();
            else if (isLeftChild)
                parent.setLeftChild(current.getRightChild());
            else
                parent.setRightChild(current.getRightChild());
        }
        else {
            Node successor = getSuccessor(current);
            if (current == root)
                root = successor;
            else if (isLeftChild)
                parent.setLeftChild(successor);
            else
                parent.setRightChild(successor);
        }
        return true;
    }

    public void inOrder(Node current) {
        if (current != null) {
            inOrder(current.getLeftChild());
            System.out.println(current.getData() + " ");
            inOrder(current.getRightChild());
        }
    }
}
