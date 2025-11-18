// Implements algebraic operations and the square root function without using 
// the Java operations a + b, a - b, a * b, a / b, a % b, and without calling 
// Math.sqrt. All the functions in this class operate on int values and
// return int values.

public class Algebra {
	public static void main(String args[]) {
	    // Tests some of the operations
	    System.out.println(plus(2,3));   // 2 + 3
	    System.out.println(minus(7,2));  // 7 - 2
   		System.out.println(minus(2,7));  // 2 - 7
 		System.out.println(times(3,4));  // 3 * 4
   		System.out.println(plus(2,times(4,2)));  // 2 + 4 * 2
   		System.out.println(pow(5,3));      // 5^3
   		System.out.println(pow(3,5));      // 3^5
   		System.out.println(div(12,3));   // 12 / 3    
   		System.out.println(div(5,5));    // 5 / 5  
   		System.out.println(div(25,7));   // 25 / 7
   		System.out.println(mod(25,7));   // 25 % 7
   		System.out.println(mod(120,6));  // 120 % 6    
   		System.out.println(sqrt(36));
		System.out.println(sqrt(263169));
   		System.out.println(sqrt(76123));
	}  

    // Returns x1 + x2
    public static int plus(int x1, int x2) {
        int result = x1;
        int count = x2;
        
        if (count < 0) {
            while (count != 0) {
                result--;
                count++;
            }
        } else {
            while (count != 0) {
                result++;
                count--;
            }
        }
        return result;
    }

    // Returns x1 - x2
    public static int minus(int x1, int x2) {
        int result = x1;
        int count = x2;
        
        if (count < 0) {
            while (count != 0) {
                result++;
                count++;
            }
        } else {
            while (count != 0) {
                result--;
                count--;
            }
        }
        return result;
    }

    // Returns x1 * x2
    public static int times(int x1, int x2) {
        int result = 0;
        int count = x2;
        boolean negateResult = false;

        if (x1 < 0) 
		{ 
			x1 = minus(0, x1); 
			negateResult = !negateResult; 
		}
        if (x2 < 0) 
		{ 
			count = minus(0, x2); 
			negateResult = !negateResult; 
		}
        
        int i = 0;
        while (i < count) {
            result = plus(result, x1);
            i++;
        }

        if (negateResult) {
            return minus(0, result);
        }
        return result;
    }

    // Returns x^n (for n >= 0)
    public static int pow(int x, int n) {
        if (n == 0) return 1;
        if (n < 0) return 0; 
        
        int result = x;
        int i = 1;
        while (i < n) {
            result = times(result, x);
            i++;
        }
        return result;
    }

    // Returns the integer part of x1 / x2 
    public static int div(int x1, int x2) {
        if (x2 == 0) return 0; 
        
        int result = 0;
        int absX1 = x1;
        int absX2 = x2;
        boolean negateResult = false;

        if (x1 < 0) 
		{
			absX1 = minus(0, x1);
			negateResult = !negateResult;
		}

        if (x2 < 0) 
		{ 
			absX2 = minus(0, x2); 
			negateResult = !negateResult; 
		}
        
        int current = absX1;

        while (current >= absX2) {
            current = minus(current, absX2);
            result++;
        }

        if (negateResult) {
            return minus(0, result);
        }
        return result;
    }

    // Returns x1 % x2
    public static int mod(int x1, int x2) {
        if (x2 == 0) 
		   return 0;
        
        int divisionResult = div(x1, x2);
        int multiplicationResult = times(divisionResult, x2);
        return minus(x1, multiplicationResult);
    } 

    // Returns the integer part of sqrt(x) 
    public static int sqrt(int x) {
        if (x < 0) return 0;
        
        int i = 0;
        
        while (true) {
            int iPlusOne = plus(i, 1);
            int nextSquared = times(iPlusOne, iPlusOne);

            if (nextSquared < 0 || nextSquared > x) {
                break;
            }
            i++;
        }
        return i;
    }       
}