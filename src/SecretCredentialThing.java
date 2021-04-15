public class SecretCredentialThing {
    public static void data(String a, String b) {
        doSomethingWithCreds(a, b);
    }
    
    public static void doSomethingWithCreds(String username, String password) {
        System.out.println(username + " " + password);
    }
}
