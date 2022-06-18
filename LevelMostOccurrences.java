import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.*;
public class LevelMostOccurrences {
    //queue in which the n item represent the number of occurrences in level n
    public static ArrayDeque<BinNode<Integer>> counter_arr;
    //    public static ArrayDeque<BinNode<Integer>> stop_flags_arr = new ArrayDeque<BinNode<Integer>>();
//    //represent the level in which the recursion is currently in , a little degrading to BinNode but we need mutible
//    //instances
    public static int level = 1;


    public static int getLevelWithMostOccurrences(BinNode<Integer> node, int num) {


//        //updating that the level exist in the relevant queue
//        Iterator flags_iter = stop_flags_arr.iterator();
//
//
//        for (int flag_to_change = 1; flag_to_change < level; flag_to_change++) {
//
//            if (!flags_iter.hasNext())
//            {
//                stop_flags_arr.add(new BinNode<>(0, null, null));
//            }
//        }
//        BinNode o = (BinNode) flags_iter.next();
//        o.setData((int) 1);
        if (level == 1) {
            counter_arr = new ArrayDeque<BinNode<Integer>>();
            counter_arr.add(new BinNode<>(0));
        }
        BinNode<Integer> o = new BinNode<>(0);
        //if the data is num updating the counter array
        if (node.getData() == num) {
            Iterator counter_iter = counter_arr.iterator();


            for (int counter_to_add_to = 1; counter_to_add_to < level+1; counter_to_add_to++) {

                o = (BinNode) counter_iter.next();
            }

            o.setData((int) o.getData() + 1);
        }


        //running the recursion on the right and left nodes.

        int right_result;
        int left_result;
        if((node.getRight() != null) || (node.getLeft() != null)) counter_arr.add(new BinNode<>(0));
        if (node.getRight() != null) {
            level++;
            right_result = getLevelWithMostOccurrences(node.getRight(), num);
            level--;
        }

        if (node.getLeft() != null) {
            level++;
            left_result = getLevelWithMostOccurrences(node.getLeft(), num);
            level--;
        }


        //the recursion stops here. let's find maximum and wrap it up.
        int max_index = 0;
        int max_times = 0;
        int cur_index = 1;
        for (Iterator iter = counter_arr.iterator(); iter.hasNext(); ) {
            o = (BinNode<Integer>) iter.next();
            if (o.getData() > max_times) {
                max_index = cur_index;
                max_times = o.getData();
            }
            cur_index++;
        }
        if (max_times == 0) return -1;
        return max_index-1;


    }
}


