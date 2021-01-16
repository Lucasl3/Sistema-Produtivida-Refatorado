package src.CustomExceptions;

public class NomeDisponivelException extends Exception{
    private static final long serialVersionUID = -7487531568151780222L;
    
    public NomeDisponivelException(String msg){
        super(msg);
    }
}
