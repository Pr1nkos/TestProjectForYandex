package exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class DeliveryException extends Exception{
    public DeliveryException(String message) {
        super(message);
    }
}
