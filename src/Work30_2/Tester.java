package Work30_2;

public class Tester
{
    public static void main(String[] args) {
        Tree tree = new Tree();
        int[] value = new int[10];
        value[0]=6;
        value[1]=8;
        value[2]=5;
        value[3]=11;
        value[4]=2;
        value[5]=9;
        value[6]=7;
        value[7]=4;
        value[8]=10;
        value[9]=3;

        // вставляем узлы в дерево:
        tree.insertNode(value[0]);
        tree.insertNode(value[1]);
        tree.insertNode(value[2]);
        tree.insertNode(value[3]);
        tree.insertNode(value[4]);
        tree.insertNode(value[5]);
        tree.insertNode(value[6]);
        tree.insertNode(value[7]);
        tree.insertNode(value[8]);
        tree.insertNode(value[9]);
        // отображение дерева:
        tree.printTree();

        // удаляем один узел и выводим оставшееся дерево в консоли
        tree.deleteNode(5);
        tree.printTree();
        for(int i=0;i<10;i++)
        {
            try {
                tree.deleteNode(value[i]);
            }catch (NullPointerException e)
            {
                System.out.println("Tree is free");
            }
        }
        tree.printTree();
    }
}
