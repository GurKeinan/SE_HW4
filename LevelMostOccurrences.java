import java.util.ArrayDeque;

public class LevelMostOccurrences {


    /**
     * gets level with most occurrences of num, we will scan the tree with BFS and compare
     * each level with the level before
     * @param node- the root of the tree
     * @param num- the searched number
     * @return- the index of the level with the most occurrences
     */
    public static int getLevelWithMostOccurrences(BinNode<Integer> node, int num) {

        ArrayDeque<BinNode<Integer>> q = new ArrayDeque<BinNode<Integer>>(); //the array we will save to it all the nodes
        int counter = 0; //current number of searched through elements in the current level
        int nextPower2 = 2; //number of expected nodes in the current level, counting also the nulls
        // so it will be in powers of 2
        int numOfNulls = 0;//number of nulls countered, when we go to the next level it will double itself (the 2
        // branches of null will be considered also nulls.
        // when numOfNulls == nextPower2 it will mean that all of the nodes in the current level are nulls- we can stop
        //scanning
        int levelCount = 0;//number of occurrences of num
        int level = 1;//the current level we're in
        int max = (node.getData() == num) ? 1:0;//max number of occurrences in level.
        // initializing it to -the root is num or not? level of root- 0
        int maxLevel = (node.getData() == num) ? 0:-1;//the level with the most occurrences.
        // initializing it to 0 or -1 -depends on if the root is num
        q.add(node);
        while(!q.isEmpty()){
            if(counter + numOfNulls == nextPower2) // check if we reached the next level
            {
                //modify the variables to the new level
                counter = 0;
                nextPower2 *=2;
                numOfNulls *= 2;
                if(levelCount > max){ // checking for new max
                    max = levelCount;
                    maxLevel = level;
                }
                level++;
                levelCount = 0;
            }
            //adding the branches to the queue and checking for num
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


