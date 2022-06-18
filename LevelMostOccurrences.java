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

        if (level == 1) { //setting up the counter array and adding an element for the 1'st level
            counter_arr = new ArrayDeque<BinNode<Integer>>();
            counter_arr.add(new BinNode<>(0));
        }
        BinNode<Integer> o = new BinNode<>(0); // temp BinNode for future uses
        //if the data is num updating the counter array
        if (node.getData() == num) { // needing to update the counter array
            Iterator counter_iter = counter_arr.iterator();


            for (int counter_to_add_to = 1; counter_to_add_to < level+1; counter_to_add_to++) {

                o = (BinNode) counter_iter.next();
            }

            o.setData((int) o.getData() + 1);
        }


        //running the recursion on the right and left nodes.

        int right_result;
        int left_result;
        //checking if there's another level in the tree, if there's adding element to the counter array
        if((node.getRight() != null) || (node.getLeft() != null)) counter_arr.add(new BinNode<>(0));
        if (node.getRight() != null) {//checking if to enter recursion on each branch.
            level++;//we need to increase the level for the new recursion, it will be on higher level.
            right_result = getLevelWithMostOccurrences(node.getRight(), num);
            level--;//we need to decrease the level after we finished, so it will match the current.
        }

        if (node.getLeft() != null) {
            level++;
            left_result = getLevelWithMostOccurrences(node.getLeft(), num);
            level--;
        }


        //the recursion stops here. let's find maximum and wrap it up.
        //the recursion on the branch won't return something meaningful, it's only so the counter array will be updated
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


