package kurgomobi.ratioimc.features.view.presenter;

import android.os.Bundle;
import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import kurgomobi.ratioimc.R;
import kurgomobi.ratioimc.features.model.ProcedureIMC;
import kurgomobi.ratioimc.features.view.ProcedureIMCActivity;
import kurgomobi.ratioimc.util.RatioIMC;
import nucleus.presenter.RxPresenter;

/**
 * Created by dennes on 10/06/16.
 */
public class ProcedureIMCPresenter extends RxPresenter<ProcedureIMCActivity>{

    private ProcedureIMC procedureIMC;

    @Override
    protected void onCreate(Bundle savedState) {
        super.onCreate(savedState);


        procedureIMC = new ProcedureIMC();
    }

    public void setTypePerson(String typePerson) {
        procedureIMC.setTypePerson(typePerson);
    }

    public void setHeightAndWeight(double height, double weight){
        procedureIMC.setHeight(height);
        procedureIMC.setWeight(weight);
    }

    public void calculate() {
        procedureIMC.calculateImc();

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("procedures")
                .child(RatioIMC.getUserToken());

        ref.push().setValue(procedureIMC).addOnSuccessListener(getView(), new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                getView().showMessageSuccess(R.string.messageSuccess);
            }
        }).addOnFailureListener(getView(), new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                getView().showMessageError(R.string.messageError);
            }
        });
    }
}
