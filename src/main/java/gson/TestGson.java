package gson;

import test_data.model.LoginCredential;
import test_data.util.DataObjectBuilder;

public class TestGson {

    public static void main(String[] args) {
        LoginCredential[] loginCredentials = DataObjectBuilder.buildDataObject("src/test/java/gson/login.json", LoginCredential[].class);
        //List<LoginCredential> loginCredentialList = Arrays.asList(DataObjectBuilder.buildDataObject("/src/test/java/gson/login.json", LoginCredential[].class));
        //List<LoginCredential> loginCredentialList = DataObjectBuilder.buildDataObject("/src/test/java/gson/login.json", new TypeToken<ArrayList<LoginCredential>>() {});

        for (LoginCredential loginCredential : loginCredentials) {
            System.out.println(loginCredential);
        }
    }
}
