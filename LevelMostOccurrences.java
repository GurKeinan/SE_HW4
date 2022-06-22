import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.*;
public class LevelMostOccurrences {



    public static int getLevelWithMostOccurrences(BinNode<Integer> node, int num) {

        ArrayDeque<BinNode<Integer>> q = new ArrayDeque<BinNode<Integer>>();
        int counter = 0;
        int nextPower2 = 2;
        int numOfNulls = 0;
        int levelCount = 0;
        int level = 1;
        int max = (node.getData() == num) ? 1:0;
        int maxLevel = (node.getData() == num) ? 0:-1;
        q.add(node);
        while(!q.isEmpty()){
            if(counter + numOfNulls == nextPower2)
            {
                counter = 0;
                nextPower2 *=2;
                numOfNulls *= 2;
                if(levelCount > max){
                    max = levelCount;
                    maxLevel = level;
                }
                level++;
                levelCount = 0;
            }
            BinNode<Integer> t = q.removeFirst();
            if(t.getLeft() != null){
                q.add(t.getLeft());
                if(t.getLeft().getData() == num) {
                    levelCount++;
                }
                counter++;
            }
            else{
                numOfNulls++;
            }
            if(t.getRight() != null){
                q.add(t.getRight());
                if(t.getRight().getData() == num) {
                    levelCount++;
                }
                counter++;
            }
            else{
                numOfNulls++;
            }
        }
        return maxLevel;
    }
}


