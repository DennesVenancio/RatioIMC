package kurgomobi.ratioimc.util;

import com.google.firebase.iid.FirebaseInstanceId;

/**
 * Created by dennes on 11/06/16.
 */
public class RatioIMC {

    private static RatioIMC instance;

    private static String userToken;

    public static void initInstance(){
        if(instance == null){
            instance = new RatioIMC();
        }
    }

    public static RatioIMC getInstance(){
        return instance;
    }


    public static String getUserToken() {
        return FirebaseInstanceId.getInstance().getToken();
    }

    public static void setUserToken(String userToken) {
        RatioIMC.userToken = userToken;
    }
}
