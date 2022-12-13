package Work30_1.Task1;

import java.util.Scanner;

public class Main {
    public static void main (String[] args) {
        Scanner scanner = new Scanner(System.in);

        BinaryTree binaryTree = new BinaryTree();
        int operationCommand = 0;
        int data = 0;
        while (true) {
            System.out.println("Input 1 to add element, 2 to delete, 3 to search, 4 to get max, 5 to get min, 0 to exit");
            operationCommand = scanner.nextInt();
            switch (operationCommand) {
                case 0:
                    scanner.close();
                    return;
                case 1:
                    System.out.println("Input element");
                    data = scanner.nextInt();
                    binaryTree.insert(data);
                    break;
                case 2:
                    System.out.println("Input element");
                    data = scanner.nextInt();
                    binaryTree.delete(data);
                    break;
                case 3:
                    System.out.println("Input element");
                    data = scanner.nextInt();
                    if (binaryTree.find(data) != null)
                        System.out.println("OK");
                    else
                        System.out.println("Not found");
                    break;
                case 4:
                    System.out.println(binaryTree.getMaximum(binaryTree.getRoot()));
                    break;
                case 5:
                    System.out.println(binaryTree.getMinimum(binaryTree.getRoot()));
                    break;
                default:
                    System.out.println("Another key to continue");
                    break;
            }
        }
    }
}