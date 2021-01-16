package src.CustomExceptions;

public class StringVaziaException extends Exception{

    private static final long serialVersionUID = -156001684518148931L;
    
    public StringVaziaException(String msg){
        super(msg);
    }

}
