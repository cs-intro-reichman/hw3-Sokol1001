// Computes the periodical payment necessary to pay a given loan.
public class LoanCalc {
    
    static double epsilon = 0.001; 	// Approximation accuracy
    static int iterationCounter; 	// Number of iterations 
    
    // Gets the loan data and computes the periodical payment.
    // Expects to get three command-line arguments: loan amount (double),
    // interest rate (double, as a percentage), and number of payments (int). 	
    public static void main(String[] args) { 		
        // Gets the loan data
        double loan = Double.parseDouble(args[0]);
        double rate = Double.parseDouble(args[1]);
        int n = Integer.parseInt(args[2]);
        System.out.println("Loan = " + loan + ", interest rate = " + rate + "%, periods = " + n);

        // Computes the periodical payment using brute force search
        System.out.print("\nPeriodical payment, using brute force: ");
        System.out.println((int) bruteForceSolver(loan, rate, n, epsilon));
        System.out.println("number of iterations: " + iterationCounter);

        // Computes the periodical payment using bisection search
        System.out.print("\nPeriodical payment, using bi-section search: ");
        System.out.println((int) bisectionSolver(loan, rate, n, epsilon));
        System.out.println("number of iterations: " + iterationCounter);
    }

    // Computes the ending balance of a loan, given the loan amount, the periodical
    // interest rate (as a percentage), the number of periods (n), and the periodical payment.
    private static double endBalance(double loan, double rate, int n, double payment) { 
        double balance = loan;
        double interestMultiplier = 1.0 + (rate / 100.0);
        int i = 0;
        
        while (i < n) {
            balance = balance - payment;
            balance = balance * interestMultiplier;
            i++;
        }
        return balance;
    }
    
    // Uses sequential search to compute an approximation of the periodical payment
    // that will bring the ending balance of a loan close to 0.
    // Given: the sum of the loan, the periodical interest rate (as a percentage),
    // the number of periods (n), and epsilon, the approximation's accuracy
    // Side effect: modifies the class variable iterationCounter.
    public static double bruteForceSolver(double loan, double rate, int n, double epsilon) {
        iterationCounter = 0;
        double g = loan / n;
        
        while (endBalance(loan, rate, n, g) > 0) {
            g = g + epsilon;
            iterationCounter++;
        }
        return g;
    }
    
    // Uses bisection search to compute an approximation of the periodical payment 
    // that will bring the ending balance of a loan close to 0.
    // Given: the sum of the loan, the periodical interest rate (as a percentage),
    // the number of periods (n), and epsilon, the approximation's accuracy
    // Side effect: modifies the class variable iterationCounter.
    public static double bisectionSolver(double loan, double rate, int n, double epsilon) { 	
        iterationCounter = 0;
        
        // Initial L: payment that is too low (f(L) > 0)
        double L = loan / n;
        
        // Initial H: payment that is too high (f(H) < 0). Using loan * 2 for a safe upper bound.
        double H = loan * 2.0; 

        double g = (L + H) / 2.0;
        
        while ((H - L) > epsilon) {
            iterationCounter++;
            g = (L + H) / 2.0;

            if (endBalance(loan, rate, n, g) > 0) {
                // g is too low, the solution is between g and H.
                L = g;
            } else {
                // g is too high, the solution is between L and g.
                H = g;
            }
        }
        return g;
    }
}