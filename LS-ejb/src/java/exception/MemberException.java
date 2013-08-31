package exception;

public class MemberException extends Exception {

    private int exception=0;
    private static final int HAS_FINE=1;
    public MemberException(){}
    public MemberException(int exception){
        setException(exception);
    }
    public int getException(){
        return exception;
    }

    public void setException(int exception){
        this.exception=exception;
    }

    public static int getHAS_FINE(){
        return HAS_FINE;
    }
}
