package kurgomobi.ratioimc;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import kurgomobi.ratioimc.util.RatioIMC;

/**
 * Created by dennes on 11/06/16.
 */
public class MyFirebaseInstanceIdService extends FirebaseInstanceIdService {

    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();

        RatioIMC.setUserToken(FirebaseInstanceId.getInstance().getToken());
    }
}
