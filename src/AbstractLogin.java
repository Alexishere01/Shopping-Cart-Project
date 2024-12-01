package src;

public abstract class AbstractLogin {
    abstract void authenticate();
    abstract void getUserInput();
    abstract void checkUserType();
    public final void completeLogin(){
        getUserInput();
        authenticate();
        checkUserType();
    }
}
