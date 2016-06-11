package kurgomobi.ratioimc.features.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import kurgomobi.ratioimc.R;
import kurgomobi.ratioimc.features.model.ProcedureIMC;
import kurgomobi.ratioimc.features.model.TypePerson;
import kurgomobi.ratioimc.features.view.adapter.GenericAdapter;
import kurgomobi.ratioimc.features.view.adapter.ItemIMCHolder;
import kurgomobi.ratioimc.features.view.presenter.MainPresenter;
import nucleus.factory.RequiresPresenter;
import nucleus.view.NucleusAppCompatActivity;


/**
 * Created by Dennes on 11/06/16.
 */
@RequiresPresenter(MainPresenter.class)
public class MainActivity extends NucleusAppCompatActivity<MainPresenter> {

    private GenericAdapter<ProcedureIMC, ItemIMCHolder> mAdapter;

    @Bind(R.id.rImcs)
    RecyclerView mImcsRecyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mImcsRecyclerView.setLayoutManager(linearLayoutManager);
     }

    public void loadProcedures(List<ProcedureIMC> procedures){

        if(mAdapter == null){
            mAdapter = new GenericAdapter<ProcedureIMC, ItemIMCHolder>(procedures, R.layout.procedure_item, ItemIMCHolder.class) {
                @Override
                protected void populateViewHolder(ItemIMCHolder viewHolder, ProcedureIMC model, int position) {
                    viewHolder.tWeight.setText(String.valueOf(model.getWeight()));
                    viewHolder.tHeight.setText(String.valueOf(model.getHeight()));
                    viewHolder.tResult.setText(String.valueOf(model.getResult()));

                    if(model.getTypePerson().equals(TypePerson.MAN.name())){
                        viewHolder.containerCardView.setBackgroundResource(R.drawable.imageman);
                    }else if(model.getTypePerson().equals(TypePerson.WOMAN.name())) {
                        viewHolder.containerCardView.setBackgroundResource(R.drawable.imagewoman);
                    }else {
                        viewHolder.containerCardView.setBackgroundResource(R.drawable.imagekid);
                    }

                }
            };

            mImcsRecyclerView.setAdapter(mAdapter);
        }else{

            mAdapter.notifyDataSetChanged();
        }
    }

    @OnClick(R.id.fab)
    public void createNewProcedure(View view){
        startActivity(new Intent(this, ProcedureIMCActivity.class));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public void notifyProcedure() {
        mAdapter.notifyDataSetChanged();
    }
}
