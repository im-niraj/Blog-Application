package blogapp.Exceptions;

public class BlogException extends RuntimeException{
    public BlogException(){
        
    }
    public BlogException(String message){
        super(message);
    }
}
