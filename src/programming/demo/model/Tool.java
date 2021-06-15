package programming.demo.model;


import programming.demo.model.enumeration.ToolBrand;
import programming.demo.model.enumeration.ToolCode;
import programming.demo.model.enumeration.ToolType;

import java.util.Objects;

public class Tool {
    private final ToolType type;
    private final ToolBrand brand;
    private final ToolCode code;
    private final ChargeInformation chargeInformation;


    public Tool(ToolType type, ToolBrand brand, ToolCode code) {
        this.type = type;
        this.brand = brand;
        this.code = code;
        this.chargeInformation = new ChargeInformation(type);
    }

    public ToolBrand getBrand() {
        return brand;
    }

    public ToolCode getCode() {
        return code;
    }

    public ToolType getType() {
        return type;
    }

    public ChargeInformation getChargeInformation() {
        return this.chargeInformation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tool)) return false;
        Tool tool = (Tool) o;
        return type == tool.type && brand == tool.brand && code == tool.code && chargeInformation.equals(tool.chargeInformation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, brand, code, chargeInformation);
    }
}
