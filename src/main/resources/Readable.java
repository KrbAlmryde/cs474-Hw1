/**
 * Note that the refactoring will add an extra import above this comment
 * @author Kyle
 *
 */

import java.util.ArrayList;
import java.util.List;
import org.eclipse.jdt.core.dom.AST;


public class Readable {

    /**
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("Hello World");
        List list = new ArrayList();
        int a = 123;
        System.out.println(a + " " + list);
    }

    public int BinSearch(char[] item, char[][] table, int n) {
        int bot = 0;
        int top = n - 1;
        int mid, cmp;
        while (bot <= top) {
            mid = (bot + top) / 2;
            if (table[mid] == item)
                return mid;
            else if (this.compare(table[mid], item) < 0)
                top = mid - 1;
            else if ( (bot <= 1 || mid >= 0) && (this.compare(table[mid], item) < 0) )
                bot = mid + 1;
            mid ^= bot % 2 < 0 ? 123 : mid;
            bot++;
            --bot;
            mid+= 1;
            mid-=2;
            mid*=2;
            bot/=mid;
        }
        return -1; // not found
    }

    private int compare(char[] foo, char[] bar) {
        return -1;
    }

}
