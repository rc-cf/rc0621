package programming.demo.service;

import programming.demo.exception.InvalidDateException;
import programming.demo.exception.ToolNotFoundException;
import programming.demo.model.ChargeInformation;
import programming.demo.model.RentalAgreement;
import programming.demo.model.Tool;
import programming.demo.model.enumeration.ToolBrand;
import programming.demo.model.enumeration.ToolCode;
import programming.demo.model.enumeration.ToolType;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.DayOfWeek;
import java.time.LocalDate;

public class RentalService {

    public RentalAgreement buildRentalAgreement(ToolCode toolCode, int rentalDayCount, int discountPercent, LocalDate checkoutDate) {
        RentalAgreement.RentalAgreementBuilder builder = new RentalAgreement.RentalAgreementBuilder();
        Tool tool = getToolByToolCode(toolCode);
        int chargeDays = calculateChargeDays(tool, checkoutDate, rentalDayCount);
        BigDecimal preDiscountCharge = calculatePreDiscountCharge(chargeDays, tool.getChargeInformation().getDailyCharge());
        BigDecimal discountAmount = calculateDiscountAmount(discountPercent, preDiscountCharge);

        return builder
                .tool(tool)
                .rentalDayCount(rentalDayCount)
                .discountPercent(discountPercent)
                .checkoutDate(checkoutDate)
                .dueDate(calculateDueDate(checkoutDate, rentalDayCount))
                .dailyRentalCost(tool.getChargeInformation().getDailyCharge())
                .chargeDays(chargeDays)
                .preDiscountCharge(preDiscountCharge)
                .discountAmount(discountAmount)
                .finalCharge(calculateFinalCharge(preDiscountCharge, discountAmount))
                .build();
    }

    private Tool getToolByToolCode(ToolCode toolCode) {
        switch (toolCode) {
            case JAKR:
                return new Tool(ToolType.Jackhammer, ToolBrand.Ridgid, toolCode);
            case CHNS:
                return new Tool(ToolType.Chainsaw, ToolBrand.Stihl, toolCode);
            case JAKD:
                return new Tool(ToolType.Jackhammer, ToolBrand.DeWalt, toolCode);
            case LADW:
                return new Tool(ToolType.Ladder, ToolBrand.Werner, toolCode);
            default:
                throw new ToolNotFoundException(toolCode);
        }
    }

    private LocalDate calculateDueDate(LocalDate checkoutDate, int rentalDayCount) {
        return checkoutDate.plusDays(rentalDayCount);
    }

    private int calculateChargeDays(Tool tool, LocalDate checkoutDate, int rentalDayCount) {
        ChargeInformation chargeInformation = tool.getChargeInformation();
        int chargeDays = rentalDayCount + 1;

        if (!chargeInformation.hasWeekendCharge()) {
            chargeDays -= getWeekendDayCount(checkoutDate, rentalDayCount);
        }

        if (!chargeInformation.hasHolidayCharge()) {
            chargeDays -= getHolidayDayCount(checkoutDate, rentalDayCount);
        }

        return chargeDays;
    }

    private BigDecimal calculatePreDiscountCharge(int chargeDays, double dailyCharge) {
        return new BigDecimal(chargeDays * dailyCharge)
                .setScale(2, RoundingMode.HALF_UP);
    }

    private BigDecimal calculateDiscountAmount(int discountPercent, BigDecimal preDiscountCharge) {
        return preDiscountCharge.multiply(new BigDecimal(discountPercent / 100.00))
                .setScale(2, RoundingMode.HALF_UP);
    }

    private BigDecimal calculateFinalCharge(BigDecimal preDiscountCharge, BigDecimal discountAmount) {
        return preDiscountCharge.subtract(discountAmount)
                .setScale(2, RoundingMode.HALF_UP);
    }

    private int getWeekendDayCount(LocalDate checkoutDate, int rentalDayCount) {
        return (int) checkoutDate.datesUntil(checkoutDate.plusDays(rentalDayCount + 1))
                .filter(date -> date.getDayOfWeek() == DayOfWeek.SATURDAY || date.getDayOfWeek() == DayOfWeek.SUNDAY)
                .count();
    }

    private int getHolidayDayCount(LocalDate checkoutDate, int rentalDayCount) {
        LocalDate laborDay = getLaborDay(checkoutDate.getYear());
        LocalDate independenceDay = getIndependenceDay(checkoutDate.getYear());

        return (int) checkoutDate.datesUntil(checkoutDate.plusDays(rentalDayCount + 1))
                .filter(date -> date.equals(laborDay) || date.equals(independenceDay))
                .count();
    }

    private LocalDate getLaborDay(int year) {
        LocalDate septemberFirst = LocalDate.of(year, 9, 1);
        LocalDate septemberEighth = LocalDate.of(year, 9, 8);

        return septemberFirst.datesUntil(septemberEighth)
                .filter(date -> date.getDayOfWeek() == DayOfWeek.MONDAY).findFirst()
                .orElseThrow(InvalidDateException::new);
    }

    private LocalDate getIndependenceDay(int year) {
        LocalDate julyFourth = LocalDate.of(year, 7, 4);

        if (julyFourth.getDayOfWeek() == DayOfWeek.SATURDAY) {
            return julyFourth.minusDays(1);
        } else if (julyFourth.getDayOfWeek() == DayOfWeek.SUNDAY) {
            return julyFourth.plusDays(1);
        } else {
            return julyFourth;
        }
    }

}
