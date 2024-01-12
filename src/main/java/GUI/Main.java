package GUI;

public class Main {

    public void main (){
        IdAndPassword idAndPasswords = new IdAndPassword();
        Login loginPage = new Login(idAndPasswords.getLoginInfo());
    }

    public static void main(String[] args) {

        IdAndPassword idAndPasswords = new IdAndPassword();
        Login loginPage = new Login(idAndPasswords.getLoginInfo());


    }
}
