import myexceptions.DangerousException;
import org.apache.commons.io.FileUtils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;

public class NothingApp {
    public static void main(String[] args) throws IOException, SQLException {
        // Valid arguments provided.  Assign each argument to a local variable 
        String fileToHack = args[0];
        String encodingToHackWith = args[1];

        String username = "bob";
        String password = "test";

        SecretCredentialThing.data(username, password);

        SecretCredentialThing.data("literal", "string");

        String thing1 = "bob";
        String thing2 = "test";
        
        java.io.BufferedWriter writer = new BufferedWriter(new FileWriter("test"));

        try {
            SecretCredentialThing.data(thing1, thing2);
        } catch (DangerousException ex) {
            writer.write(ex.getMessage());
        }
        
        HibernateUtil.doSecretQuery(args[2]);
        
        HibernateUtil.oldSchool();

        File hackedFile = new File(fileToHack);

        FileUtils.readLines(hackedFile, encodingToHackWith);
    }
}