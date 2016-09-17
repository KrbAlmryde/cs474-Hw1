/**
 * Created by krbalmryde on 9/15/16.
 */
/*
JavaBat Recursion 25 out of 30 recursion problems, or 30 out of 30 for extra credit
*/
class JavaBat_Recursion {
    public int factorial(int n) {
        // Base case: if n is 1, we can return the answer directly
        if (n == 1) return 1;

        // Recursion: otherwise make a recursive call with n-1
        // (towards the base case), i.e. call factorial(n-1).
        // Assume the recursive call works correctly, and fix up
        // what it returns to make our result.
        return n * factorial(n-1);
    }


    public int bunnyEars(int bunnies) {
        // Base case
        if (bunnies == 0) return 0;

        return 2 + bunnyEars(bunnies-1);
    }


    public int fibonacci(int n) {
        if (n == 0 || n == 1) return n;

        return fibonacci(n-1) + fibonacci(n-2);
    }


    public int bunnyEars2(int bunnies) {
        if(bunnies == 0)
            return 0;
        if(bunnies % 2 == 1)
            return 2 + bunnyEars2(bunnies-1);
        else
            return 3 + bunnyEars2(bunnies-1);
    }


    public int triangle(int rows) {
        if (rows == 0 || rows == 1) return rows;

        return rows + triangle(rows-1);
    }


    public int sumDigits(int n) {
        if (n < 10)
            return n;
        else
            return sumDigits(n/10) + (n%10);
    }


    public int count7(int n) {
        if (n == 7)
            return 1;
        else if (n > 10)
            return count7(n/10) + count7(n%10);
        else
            return 0;
    }


    public int count8(int n) {
        if (n == 8)
            return 1;
        else if (n == 88)
            return 3;
        else if (n > 9999)
            return count8(n/1000) + count8(n%1000);
        else if (n > 99)
            if (n/10 == 88 && n%100 == 88)
                return count8(n/10) + count8(n%100);
            else if (n/10 == 88)
                return count8(n/10);
            else
                return count8(n/100) + count8(n%100);
        else if (n > 9)
            return count8(n/10) + count8(n%10);
        else
            return 0;
    }


    public int powerN(int base, int n) {
        if (n == 0)
            return 1;
        else
            return base * powerN(base, n - 1);
    }


    public int countX(String str) {
        if (str.length() == 0)
            return 0;
        else if (str.charAt(str.length() - 1) == 'x')
            return 1 + countX(str.substring(0, str.length() - 1));
        else
            return countX(str.substring(0, str.length() - 1));
    }


    public int countHi(String str) {
        if (str.length() < 2)
            return 0;
        else if (str.substring(str.length() - 2, str.length()).equals("hi"))
            return 1 + countHi(str.substring(0, str.length() - 1));
        else
            return countHi(str.substring(0, str.length() - 1));
    }


    public String changeXY(String str) {
        String result = "";
        if (str.length() < 1) {
            return str;
        } else if (str.charAt(0) == 'x') {
            result += "y";
            return result + changeXY(str.substring(1));
        } else {
            result += str.charAt(0);
            return result + changeXY(str.substring(1));
        }
    }


    public String changePi(String str) {
        String result = "";
        if (str.length() < 2) {
            return str;
        } else if (str.substring(0,2).equals("pi")) {
            result += "3.14";
            return result + changePi(str.substring(2));
        } else {
            result += str.substring(0,1);
            return result + changePi(str.substring(1));
        }
    }


    public String noX(String str) {
        String result = "";
        if (str.length() < 1) {
            return str;
        } else if (str.charAt(0) == 'x') {
            return noX(str.substring(1));
        } else {
            result += str.charAt(0);
            return result + noX(str.substring(1));
        }
    }


    public boolean array6(int[] nums, int index) {
        if (index == nums.length)
            return false;

        else if (nums[index] == 6)
            return true;
        else
            return array6(nums, index+1);
    }


    public int array11(int[] nums, int index) {
        if (nums.length < 1 || index == nums.length)
            return 0;
        else if (nums[index] == 11)
            return 1 + array11(nums, index+1);
        else
            return array11(nums, index+1);

    }


    public boolean array220(int[] nums, int index) {
        if (nums.length < 2 || index == nums.length-1)
            return false;
        else if (nums[index+1] == nums[index]*10)
            return true;
        else
            return array220(nums, index+1);
    }


    public String allStar(String str) {
        String result = "";
        if (str.length() < 2)
            return str;
        else
            result += str.charAt(0) + "*";
        return result + allStar(str.substring(1));
    }


    public String pairStar(String str) {
        String result = "";
        if (str.length() < 2)
            return str;

        if (str.charAt(0) == str.charAt(1)) {
            result += str.charAt(0) + "*";
            return result + pairStar(str.substring(1));
        } else {
            result += str.charAt(0);
            return result + pairStar(str.substring(1));
        }
    }


    public String endX(String str) {
        String result = "";
        if (str.length() < 2)
            return str;

        result += str.charAt(0);
        if (str.charAt(0) == 'x') {
            return endX(str.substring(1)) + result;
        } else {
            return result + endX(str.substring(1));
        }
    }


    public int countPairs(String str) {
        if (str.length() < 3)
            return 0;
        else if (str.charAt(0) == str.charAt(2))
            return 1 + countPairs(str.substring(1));
        else
            return countPairs(str.substring(1));
    }


    public int countAbc(String str) {
        if (str.length() < 3)
            return 0;
        else if (str.substring(0,3).equals("abc") || str.substring(0,3).equals("aba"))
            return 1 + countAbc(str.substring(1));
        else
            return countAbc(str.substring(1));
    }


    public int count11(String str) {
        if (str.length() < 2)
            return 0;
        else if (str.substring(0,2).equals("11"))
            return 1 + count11(str.substring(2));
        else
            return count11(str.substring(1));
    }


    public String stringClean(String str) {
        String result = "";
        if (str.length() < 2) {
            return str;
        } else if (str.charAt(0) != str.charAt(1)) {
            result += str.charAt(0);
            return result + stringClean(str.substring(1));
        } else {
            return stringClean(str.substring(1));
        }
    }


    public int countHi2(String str) {
        if (str.length() < 2)
            return 0;
        else if (str.substring(str.length() - 2, str.length()).equals("hi"))
            if (str.length() > 2 && str.substring(str.length() - 3, str.length()).equals("xhi"))
                return countHi2(str.substring(0, str.length() - 3));
            else
                return 1 + countHi2(str.substring(0, str.length() - 2));
        else
            return countHi2(str.substring(0, str.length() - 1));
    }


    public String parenBit(String str) {
        return "";
    }


    public boolean nestParen(String str) {
        if (str.length() < 2)
            return true;
        else if (str.charAt(0) != '(' || str.charAt(str.length() - 1) != ')' )
            return false;
        else
            return nestParen(str.substring(1, str.length() - 1));
    }


    public int strCount(String str, String sub) {
        int len = sub.length();
        if (str.length() < len)
            return 0;
        else if (str.substring(0,len).equals(sub))
            return 1 + strCount(str.substring(len), sub);
        else
            return strCount(str.substring(1), sub);
    }



}
