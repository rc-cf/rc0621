package programming.demo;

import org.junit.Test;
import programming.demo.exception.InvalidDateException;
import programming.demo.exception.InvalidDiscountException;
import programming.demo.exception.InvalidRentalDayCountException;
import programming.demo.model.RentalAgreement;
import programming.demo.model.Tool;
import programming.demo.model.enumeration.ToolBrand;
import programming.demo.model.enumeration.ToolType;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

import static org.junit.Assert.*;
import static programming.demo.model.enumeration.ToolCode.*;

public class ProgrammingDemoTest {

    // Test 1
    @Test
    public void test1_checkout_shouldThrowInvalidDiscountException_whenDiscountPercentageOver100() {
        Exception actual = new Exception();
        try {
            ProgrammingDemo.checkout(JAKR, 5, 101, LocalDate.of(2015, 9, 3));
        } catch (Exception ex) {
            actual = ex;
        }
        assertEquals(actual.getClass(), InvalidDiscountException.class);
    }

    @Test
    public void checkout_shouldThrowInvalidDiscountException_whenDiscountPercentageLessThan0() {
        Exception actual = new Exception();
        try {
            ProgrammingDemo.checkout(JAKR, 5, -2, LocalDate.of(2015, 9, 3));
        } catch (Exception ex) {
            actual = ex;
        }
        assertEquals(actual.getClass(), InvalidDiscountException.class);
    }

    @Test
    public void checkout_shouldThrowInvalidRentalDayCountException_whenRentalDayCountIsLessThan1() {
        Exception actual = new Exception();
        try {
            ProgrammingDemo.checkout(JAKR, 0, 0, LocalDate.of(2015, 9, 3));
        } catch (Exception ex) {
            actual = ex;
        }
        assertEquals(actual.getClass(), InvalidRentalDayCountException.class);
    }

    // Test 2
    @Test
    public void test2_checkout_shouldNotChargeOnHoliday_andApplyDiscount_whenToolIsLadder() {
        RentalAgreement actual = ProgrammingDemo.checkout(LADW, 3, 10, LocalDate.of(2020, 7, 2));
        RentalAgreement expected =
                RentalAgreement.builder()
                        .chargeDays(3)
                        .dailyRentalCost(1.99)
                        .dueDate(LocalDate.of(2020, 7, 5))
                        .checkoutDate(LocalDate.of(2020, 7, 2))
                        .discountPercent(10)
                        .rentalDayCount(3)
                        .tool(new Tool(ToolType.Ladder, ToolBrand.Werner, LADW))
                        .preDiscountCharge(BigDecimal.valueOf(5.97)
                                .setScale(2, RoundingMode.HALF_UP))
                        .discountAmount(BigDecimal.valueOf(0.60)
                                .setScale(2, RoundingMode.HALF_UP))
                        .finalCharge(BigDecimal.valueOf(5.37)
                                .setScale(2, RoundingMode.HALF_UP))
                        .build();
        assertEquals(expected, actual);
    }

    // Test 3
    @Test
    public void test3_checkout_shouldNotChargeWeekends_andApplyDiscount_whenToolIsChainsaw() {
        RentalAgreement actual = ProgrammingDemo.checkout(CHNS, 5, 25, LocalDate.of(2015, 7, 2));
        RentalAgreement expected =
                RentalAgreement.builder()
                        .dailyRentalCost(1.49)
                        .dueDate(LocalDate.of(2015, 7, 7))
                        .checkoutDate(LocalDate.of(2015, 7, 2))
                        .discountPercent(25)
                        .rentalDayCount(5)
                        .chargeDays(4)
                        .tool(new Tool(ToolType.Chainsaw, ToolBrand.Stihl, CHNS))
                        .preDiscountCharge(BigDecimal.valueOf(5.96)
                                .setScale(2, RoundingMode.HALF_UP))
                        .discountAmount(BigDecimal.valueOf(1.49)
                                .setScale(2, RoundingMode.HALF_UP))
                        .finalCharge(BigDecimal.valueOf(4.47)
                                .setScale(2, RoundingMode.HALF_UP))
                        .build();
        assertEquals(expected, actual);
    }

    // Test 4
    @Test
    public void test4_checkout_shouldNotChargeWeekendsNorHolidays_whenToolIsJackhammer() {
        RentalAgreement actual = ProgrammingDemo.checkout(JAKD, 6, 0, LocalDate.of(2020, 9, 3));
        RentalAgreement expected =
                RentalAgreement.builder()
                        .dailyRentalCost(2.99)
                        .dueDate(LocalDate.of(2020, 9, 9))
                        .checkoutDate(LocalDate.of(2020, 9, 3))
                        .discountPercent(0)
                        .rentalDayCount(6)
                        .chargeDays(4)
                        .tool(new Tool(ToolType.Jackhammer, ToolBrand.DeWalt, JAKD))
                        .preDiscountCharge(BigDecimal.valueOf(11.96)
                                .setScale(2, RoundingMode.HALF_UP))
                        .discountAmount(BigDecimal.valueOf(0.00)
                                .setScale(2, RoundingMode.HALF_UP))
                        .finalCharge(BigDecimal.valueOf(11.96)
                                .setScale(2, RoundingMode.HALF_UP))
                        .build();
        assertEquals(expected, actual);
    }

    // Test 5
    @Test
    public void test5_checkout_shouldNotChargeWeekendsNorHolidays_whenToolIsJackhammer() {
        RentalAgreement actual = ProgrammingDemo.checkout(JAKR, 9, 0, LocalDate.of(2015, 7, 2));
        RentalAgreement expected =
                RentalAgreement.builder()
                        .dailyRentalCost(2.99)
                        .dueDate(LocalDate.of(2015, 7, 11))
                        .checkoutDate(LocalDate.of(2015, 7, 2))
                        .discountPercent(0)
                        .rentalDayCount(9)
                        .chargeDays(6)
                        .tool(new Tool(ToolType.Jackhammer, ToolBrand.Ridgid, JAKR))
                        .preDiscountCharge(BigDecimal.valueOf(17.94)
                                .setScale(2, RoundingMode.HALF_UP))
                        .discountAmount(BigDecimal.valueOf(0.00)
                                .setScale(2, RoundingMode.HALF_UP))
                        .finalCharge(BigDecimal.valueOf(17.94)
                                .setScale(2, RoundingMode.HALF_UP))
                        .build();
        assertEquals(expected, actual);
    }

    // Test 6
    @Test
    public void test6_shouldNotChargeWeekendsNorHolidays_andApplyDiscount_whenToolIsJackhammer() {
        RentalAgreement actual = ProgrammingDemo.checkout(JAKR, 4, 50, LocalDate.of(2020, 7, 2));
        RentalAgreement expected =
                RentalAgreement.builder()
                        .dailyRentalCost(2.99)
                        .dueDate(LocalDate.of(2020, 7, 6))
                        .checkoutDate(LocalDate.of(2020, 7, 2))
                        .discountPercent(50)
                        .rentalDayCount(4)
                        .chargeDays(2)
                        .tool(new Tool(ToolType.Jackhammer, ToolBrand.Ridgid, JAKR))
                        .preDiscountCharge(BigDecimal.valueOf(5.98)
                                .setScale(2, RoundingMode.HALF_UP))
                        .discountAmount(BigDecimal.valueOf(2.99)
                                .setScale(2, RoundingMode.HALF_UP))
                        .finalCharge(BigDecimal.valueOf(2.99)
                                .setScale(2, RoundingMode.HALF_UP))
                        .build();
        assertEquals(expected, actual);
    }

    @Test
    public void rentalAgreement_toString_shouldPrintOutCorrectFormat() {
        RentalAgreement agreement = ProgrammingDemo.checkout(JAKR, 20, 10, LocalDate.of(2020, 7, 2));
        assertEquals("Tool Code: JAKR\n" +
                "Tool Type: Jackhammer\n" +
                "Tool Brand: Ridgid\n" +
                "Rental Days: 20\n" +
                "Check Out Date: 07/02/2020\n" +
                "Due Date: 07/22/2020\n" +
                "Daily Rental Charge: $2.99\n" +
                "Charge Days: 14\n" +
                "Pre-Discount Charge: $41.86\n" +
                "Discount Percent: 10%\n" +
                "Discount Amount: $4.19\n" +
                "Final Charge: $37.67\n", agreement.toString());
    }

}