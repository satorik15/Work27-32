package Work27;

public class Tester
{
    public static void main(String[] args)
    {
        MyHashMap myHashMap = new MyHashMap();
        for(int i=0;i<100;i++)
        {
            myHashMap.put(""+i,"value"+i);

        }
        for(int i=0;i<100;i++)
        {
            System.out.println(myHashMap.get(""+i));
        }
    }
}
