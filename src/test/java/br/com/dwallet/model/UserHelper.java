package br.com.dwallet.model;

public class UserHelper {

    public static User user() {
        return User.builder().build();
    }

    public static User userWithDocument(String document) {
        return User.builder().document(document).build();
    }

}
