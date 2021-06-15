package programming.demo.model;

import programming.demo.model.enumeration.ToolType;

import java.util.Objects;

public class ChargeInformation {

    private final double LADDER_CHARGE = 1.99;
    private final double CHAINSAW_CHARGE = 1.49;
    private final double JACKHAMMER_CHARGE = 2.99;

    private final ToolType toolType;

    public ChargeInformation(ToolType toolType) {
        this.toolType = toolType;
    }

    public double getDailyCharge() {
        switch (this.toolType) {
            case Ladder:
                return LADDER_CHARGE;
            case Chainsaw:
                return CHAINSAW_CHARGE;
            case Jackhammer:
                return JACKHAMMER_CHARGE;
            default:
                throw new RuntimeException();
        }
    }

    public boolean hasWeekdayCharge() {
        return true;
    }

    public boolean hasWeekendCharge() {
        switch (this.toolType) {
            case Ladder:
                return true;
            case Chainsaw:
            case Jackhammer:
                return false;
            default:
                throw new RuntimeException();
        }
    }

    public boolean hasHolidayCharge() {
        switch (this.toolType) {
            case Ladder:
            case Jackhammer:
                return false;
            case Chainsaw:
                return true;
            default:
                throw new RuntimeException();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ChargeInformation)) return false;
        ChargeInformation that = (ChargeInformation) o;
        return toolType == that.toolType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(toolType);
    }
}
