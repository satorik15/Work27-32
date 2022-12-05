package Work28.Task1;
import java.util.*;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
public class ExampleSortSet
{
    public static void main(String[] args)
    {
        HashSet<String> set = new HashSet<>();
        set.add("java");
        set.add("is");
        set.add("the best");
        set.add("language");
        Set<String> treeSet = getInstance(set);
        System.out.println(treeSet);

    }
    public static <T> Set<T> getInstance(Set<T> hashSet)
    {
        Set<T> treeSet = hashSet.stream().collect(Collectors.toCollection(TreeSet::new));
        return treeSet;
    }
}

