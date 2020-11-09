package Graphing;

import java.math.BigDecimal;

public class Combination {

    static BigDecimal nCr(long n, final long m) {
        BigDecimal r = BigDecimal.valueOf(1);
        if (2 * m > n) nCr(n, n - m);
        for (int i = 1; i <= m; n--, i++) {
            r = (r.multiply(BigDecimal.valueOf(n))).divide(BigDecimal.valueOf(i));
        }
        return (r);
    }
}
