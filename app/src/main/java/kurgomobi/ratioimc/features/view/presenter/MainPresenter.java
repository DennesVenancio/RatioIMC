package kurgomobi.ratioimc.features.view.presenter;

import android.os.Bundle;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import kurgomobi.ratioimc.features.model.ProcedureIMC;
import kurgomobi.ratioimc.features.view.MainActivity;
import kurgomobi.ratioimc.util.RatioIMC;
import nucleus.presenter.RxPresenter;

/**
 * Created by dennes on 10/06/16.
 */
public class MainPresenter extends RxPresenter<MainActivity> {

    private List<ProcedureIMC> procedureIMCs;

    @Override
    protected void onCreate(Bundle savedState) {
        super.onCreate(savedState);

        procedureIMCs = new ArrayList<>();
    }

    public void loadProcedures(){
        if(RatioIMC.getUserToken() != null) {
            DatabaseReference ref = FirebaseDatabase.getInstance().getReference("procedureIMCs").child(RatioIMC.getUserToken());

            ref.addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                    if(!existProcedure(dataSnapshot.getKey())) {
                        if(getView() != null) {
                            if (s == null) {
                                ProcedureIMC procedureIMC = dataSnapshot.getValue(ProcedureIMC.class);
                                procedureIMC.setId(dataSnapshot.getKey());

                                procedureIMCs.add(procedureIMC);

                                getView().loadProcedures(procedureIMCs);

                            } else if (!s.equals(dataSnapshot.getKey())) {
                                ProcedureIMC procedureIMC = dataSnapshot.getValue(ProcedureIMC.class);
                                procedureIMC.setId(dataSnapshot.getKey());

                                procedureIMCs.add(procedureIMC);

                                getView().notifyProcedure();
                            }
                        }
                    }
                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {

                }

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }
    }

    @Override
    protected void onTakeView(MainActivity mainActivity) {
        super.onTakeView(mainActivity);

        loadProcedures();

        if(!procedureIMCs.isEmpty())
            if(getView() != null)
                getView().notifyProcedure();
    }

    private boolean existProcedure(String key){
        boolean existProcedure = false;

        for(ProcedureIMC procedureIMC : procedureIMCs){
            if(procedureIMC.getId().equals(key))
                existProcedure = true;
        }

        return existProcedure;
    }
}
