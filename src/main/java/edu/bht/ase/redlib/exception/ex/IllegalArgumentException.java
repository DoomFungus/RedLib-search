package edu.bht.ase.redlib.exception.ex;

import edu.bht.ase.redlib.exception.codes.ExceptionCodes;

public class IllegalArgumentException extends AbstractException {

    public IllegalArgumentException(ExceptionCodes code) {
        super(code);
    }

    public IllegalArgumentException(ExceptionCodes code, Object... formatArgs) {
        super(code, formatArgs);
    }
}
