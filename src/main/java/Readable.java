/**
 * Note that the refactoring will add an extra import above this comment
 * @author Kyle
 *
 */

import java.util.ArrayList;
import java.util.List;
import org.eclipse.jdt.core.dom.AST;


public class Readable {

    public static void main(String[] args) {
        int a;
        int b;
        int c;
        a=1;
        b=2;
        c=3;
        a=a+c;

        System.out.println("Hello World");
        List list = new ArrayList();
        a = 123;
        System.out.println(a + " " + list);
    }

    public int BinSearch(char[] item, char[][] table, int n) {
        int bot = 0;
        int top = n - 1;
        int mid, cmp;
        float f_foo;
        long l_bar;
        double d_bag = 10;
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
//
    private boolean foobar() { return (0 > 1)? true: false; }

    private void sort ( int[] a, int n ) {
        int i, j, t;
        if ( n < 2 ) return;
        for ( i=0 ; i < n-1; i++ ) {
            for ( j=i+1 ; j < n ; j++ ) {
                if ( a[i] > a[j] ) {
                    t = a[i];
                    a[i] = a[j];
                    a[j] = t;
                }
            }
        }
    }



}
