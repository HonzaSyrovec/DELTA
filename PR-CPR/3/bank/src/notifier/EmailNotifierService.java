package notifier;

public class EmailNotifierService  implements NotifierSeervice{
    public void notify(string message){
        System.out.println("EMAIL: " + message);
    }
}
