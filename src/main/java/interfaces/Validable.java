package interfaces;

import exceptions.ValidationException;

public interface Validable {

    void validate() throws ValidationException;
}
