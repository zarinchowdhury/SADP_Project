// ========================================================
// STRATEGY PATTERN
// ========================================================
interface DiscountStrategy {
double apply(double baseAmount);
}


class NoDiscount implements DiscountStrategy {
public double apply(double baseAmount) { return baseAmount; }
}


class PercentageDiscount implements DiscountStrategy {
private final double percent;
public PercentageDiscount(double percent) { this.percent = percent; }
public double apply(double baseAmount) { return baseAmount * (1 - percent); }
}