package org.ars.example.function;

public class Function1 {

    public static interface Client {
        String get( String id);
    }

    /*
     * public Client getClient() {
     * // return clientId -> return clientId == 1 ? "Y" : "N";
     * return new Client();
     * }
     */

    public static void main( String[] args) {
        try {
            Client obj = ( id) -> {
                return id.concat( id);
            };
            System.out.println( obj.get( "1"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
