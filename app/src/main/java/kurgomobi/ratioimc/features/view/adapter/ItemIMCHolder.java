package kurgomobi.ratioimc.features.view.adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import kurgomobi.ratioimc.R;

/**
 * Created by Dennes on 11/06/16.
 */
public class ItemIMCHolder extends RecyclerView.ViewHolder {

    @Bind(R.id.containerCardView)
    public RelativeLayout containerCardView;

    @Bind(R.id.tWeight)
    public TextView tWeight;

    @Bind(R.id.tHeight)
    public TextView tHeight;

    @Bind(R.id.tResult)
    public TextView tResult;

    public ItemIMCHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);

    }

}
