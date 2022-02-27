import com.donny.sort.SortSolution;
import org.junit.Test;

public class TestSort {

    int[] array = {1, 52, 525, 23, 57, 7, 68, 8, 6, 97, 12, 5445, 4};

    @Test
    public void sortTest() {
        SortSolution sortUtil = new SortSolution();
        sortUtil.setSortArray(array);
        sortUtil.quickSort(sortUtil.getSortArray(), 1, sortUtil.getSortArray().length - 1);
        for (int one : sortUtil.getSortArray()) {
            System.out.print(" " + one);
        }
    }


}
