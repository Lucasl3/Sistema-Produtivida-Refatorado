package src.CustomExceptions;

public class TituloDisponivelException extends Exception{
    private static final long serialVersionUID = 2062837697564787818L;
    
    public TituloDisponivelException(String msg){
        super(msg);
    }
}
