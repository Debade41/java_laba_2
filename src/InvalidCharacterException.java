public class InvalidCharacterException extends RuntimeException {

    public InvalidCharacterException(char c) {
        super("Недопустимый символ: '" + c + "'. Разрешены только буквы a..z.");
    }
}