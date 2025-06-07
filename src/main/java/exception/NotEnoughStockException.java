package exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class NotEnoughStockException extends Exception{
    public NotEnoughStockException(String message) {
        super(message);
    }
}
