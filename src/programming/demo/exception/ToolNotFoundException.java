package programming.demo.exception;

import programming.demo.model.enumeration.ToolCode;

public class ToolNotFoundException extends RuntimeException {

    public ToolNotFoundException() {
        super("Tool not found, please try another code.");
    }

    public ToolNotFoundException(ToolCode toolCode) {
        super(String.format("Tool %s not found, please try another code.", toolCode.name()));
    }
}
