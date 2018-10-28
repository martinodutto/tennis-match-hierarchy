package interfaces;

import exceptions.ValidationException;

/**
 * Generic contract for classes requiring validation.
 */
public interface Validable {

    /**
     * Throws a {@link ValidationException} when validation is not passed.
     *
     * @throws ValidationException Validation failed.
     */
    void validate() throws ValidationException;
}
