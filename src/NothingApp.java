import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class NothingApp {
    public static void main(String[] args) throws IOException {
        // Valid arguments provided.  Assign each argument to a local variable 
        String fileToHack = args[0];
        String encodingToHackWith = args[1];

        String username = "bob";
        String password = "test";

        SecretCredentialThing.data(username, password);

        SecretCredentialThing.data("literal", "string");

        String thing1 = "bob";
        String thing2 = "test";

        SecretCredentialThing.data(thing1, thing2);
        
        HibernateUtil.doSecretQuery("test");

        File hackedFile = new File(fileToHack);

        FileUtils.readLines(hackedFile, encodingToHackWith);
    }
}