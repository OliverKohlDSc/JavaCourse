var BigDecimal = Java.type('java.math.BigDecimal');

function calculate (income, interest) {
    var result = new BigDecimal(income).multiply(new BigDecimal(interest)).divide ( new BigDecimal ("100"), 2, BigDecimal.ROUND_HALF_EVEN);
    return result.toPlainString();
}

// Should print 177509536812209646641661.41
print(calculate(1241325432253214134526324,14.3))