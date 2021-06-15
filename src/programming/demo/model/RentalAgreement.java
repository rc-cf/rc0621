package programming.demo.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class RentalAgreement {
    private static final String dateFormat = "MM/dd/yyyy";

    private final Tool tool;
    private final int rentalDayCount;
    private final LocalDate checkoutDate;
    private final LocalDate dueDate;
    private final double dailyRentalCost;
    private final int chargeDays;
    private final int discountPercent;
    private final BigDecimal discountAmount;
    private final BigDecimal finalCharge;
    private final BigDecimal preDiscountCharge;

    public RentalAgreement(Tool tool,
                           int rentalDayCount,
                           LocalDate checkoutDate,
                           LocalDate dueDate,
                           double dailyRentalCost,
                           int chargeDays,
                           int discountPercent,
                           BigDecimal discountAmount,
                           BigDecimal finalCharge,
                           BigDecimal preDiscountCharge) {

        this.tool = tool;
        this.rentalDayCount = rentalDayCount;
        this.checkoutDate = checkoutDate;
        this.dueDate = dueDate;
        this.dailyRentalCost = dailyRentalCost;
        this.chargeDays = chargeDays;
        this.discountPercent = discountPercent;
        this.discountAmount = discountAmount;
        this.finalCharge = finalCharge;
        this.preDiscountCharge = preDiscountCharge;
    }

    public RentalAgreement(RentalAgreementBuilder builder) {
        this.tool = builder.tool;
        this.rentalDayCount = builder.rentalDayCount;
        this.checkoutDate = builder.checkoutDate;
        this.dueDate = builder.dueDate;
        this.dailyRentalCost = builder.dailyRentalCost;
        this.chargeDays = builder.chargeDays;
        this.discountPercent = builder.discountPercent;
        this.discountAmount = builder.discountAmount;
        this.finalCharge = builder.finalCharge;
        this.preDiscountCharge = builder.preDiscountCharge;
    }

    public static RentalAgreementBuilder builder() {
        return new RentalAgreementBuilder();
    }

    public static class RentalAgreementBuilder {
        private Tool tool;
        private int rentalDayCount;
        private LocalDate checkoutDate;
        private LocalDate dueDate;
        private double dailyRentalCost;
        private BigDecimal preDiscountCharge;
        private int chargeDays;
        private int discountPercent;
        private BigDecimal discountAmount;
        private BigDecimal finalCharge;


        public RentalAgreementBuilder tool(Tool tool) {
            this.tool = tool;
            return this;
        }

        public RentalAgreementBuilder rentalDayCount(int rentalDayCount) {
            this.rentalDayCount = rentalDayCount;
            return this;
        }

        public RentalAgreementBuilder checkoutDate(LocalDate checkoutDate) {
            this.checkoutDate = checkoutDate;
            return this;
        }

        public RentalAgreementBuilder dueDate(LocalDate dueDate) {
            this.dueDate = dueDate;
            return this;
        }

        public RentalAgreementBuilder dailyRentalCost(double dailyRentalCost) {
            this.dailyRentalCost = dailyRentalCost;
            return this;
        }

        public RentalAgreementBuilder chargeDays(int chargeDays) {
            this.chargeDays = chargeDays;
            return this;
        }

        public RentalAgreementBuilder discountPercent(int discountPercent) {
            this.discountPercent = discountPercent;
            return this;
        }

        public RentalAgreementBuilder discountAmount(BigDecimal discountAmount) {
            this.discountAmount = discountAmount;
            return this;
        }

        public RentalAgreementBuilder finalCharge(BigDecimal finalCharge) {
            this.finalCharge = finalCharge;
            return this;
        }

        public RentalAgreementBuilder preDiscountCharge(BigDecimal preDiscountCharge) {
            this.preDiscountCharge = preDiscountCharge;
            return this;
        }

        public RentalAgreement build() {
            return new RentalAgreement(this);
        }
    }

    @Override
    public String toString() {
        return String.format(
                "Tool Code: %s\n" +
                "Tool Type: %s\n" +
                "Tool Brand: %s\n" +
                "Rental Days: %d\n" +
                "Check Out Date: %s\n" +
                "Due Date: %s\n" +
                "Daily Rental Charge: $%.2f\n" +
                "Charge Days: %d\n" +
                "Pre-Discount Charge: $%s\n" +
                "Discount Percent: %d%%\n" +
                "Discount Amount: $%s\n" +
                "Final Charge: $%s\n",
                this.tool.getCode().name(),
                this.tool.getType().name(),
                this.tool.getBrand().name(),
                this.rentalDayCount,
                this.checkoutDate.format(DateTimeFormatter.ofPattern(dateFormat)),
                this.dueDate.format(DateTimeFormatter.ofPattern(dateFormat)),
                this.dailyRentalCost,
                this.chargeDays,
                this.preDiscountCharge.toString(),
                this.discountPercent,
                this.discountAmount.toString(),
                this.finalCharge.toString());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RentalAgreement)) return false;
        RentalAgreement that = (RentalAgreement) o;
        return rentalDayCount == that.rentalDayCount && Double.compare(that.dailyRentalCost, dailyRentalCost) == 0 && chargeDays == that.chargeDays && discountPercent == that.discountPercent && tool.equals(that.tool) && checkoutDate.equals(that.checkoutDate) && dueDate.equals(that.dueDate) && discountAmount.equals(that.discountAmount) && finalCharge.equals(that.finalCharge) && preDiscountCharge.equals(that.preDiscountCharge);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tool, rentalDayCount, checkoutDate, dueDate, dailyRentalCost, chargeDays, discountPercent, discountAmount, finalCharge, preDiscountCharge);
    }
}
