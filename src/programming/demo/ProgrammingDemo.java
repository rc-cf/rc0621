package programming.demo;

import programming.demo.exception.InvalidDiscountException;
import programming.demo.exception.InvalidRentalDayCountException;
import programming.demo.model.RentalAgreement;
import programming.demo.model.enumeration.ToolCode;
import programming.demo.service.RentalService;

import java.time.LocalDate;

public class ProgrammingDemo {

    private static final RentalService rentalService = new RentalService();

    public static void main(String[] args) {
//        System.out.println(checkout(LADW, 3, 10, LocalDate.of(2020, 7, 2)));
//        System.out.println(checkout(CHNS, 5, 25, LocalDate.of(2015, 7, 2)));
//        System.out.println(checkout(LADW, 6, 0, LocalDate.of(2020, 7, 2)));
//        System.out.println(ProgrammingDemo.checkout(JAKR, 9, 0, LocalDate.of(2015, 7, 2)));
//        System.out.println(checkout(JAKR, 9, 0, LocalDate.of(2015, 7, 2)));
    }

    public static RentalAgreement checkout(ToolCode toolCode, int rentalDayCount, int discountPercent, LocalDate checkoutDate) {
        if (discountPercent < 0 || discountPercent > 100) {
            throw new InvalidDiscountException();
        }

        if (rentalDayCount < 1) {
            throw new InvalidRentalDayCountException();
        }

        return rentalService.buildRentalAgreement(toolCode, rentalDayCount, discountPercent, checkoutDate);
    }


}