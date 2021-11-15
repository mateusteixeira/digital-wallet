package br.com.dwallet.model;

public class OperationErrorHelper {

    public static OperationError withIdUser(String idUser) {
        return OperationError.builder().idUser(idUser).build();
    }

}
