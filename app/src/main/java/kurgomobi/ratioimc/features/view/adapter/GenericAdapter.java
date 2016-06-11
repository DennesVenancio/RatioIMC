package kurgomobi.ratioimc.features.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dennes on 11/06/16.
 */
public abstract class GenericAdapter<T, VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {

    List<T> models;
    int modelLayout;
    Class<VH> modelViewHolder;

    public GenericAdapter(List<T> models, int modelLayout, Class<VH> viewHolderClass) {
        this.models = models;
        this.modelLayout = modelLayout;
        this.modelViewHolder = viewHolderClass;
    }

    protected GenericAdapter() {
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewGroup view = (ViewGroup) LayoutInflater.from(parent.getContext()).inflate(modelLayout, parent, false);
        try {
            Constructor<VH> constructor = modelViewHolder.getConstructor(View.class);
            return constructor.newInstance(view);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public T getItem(int position){
        return models.get(position);
    }

    public void cleanup() {
        models.clear();
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    @Override
    public long getItemId(int position) {
        return models.get(position).hashCode();
    }

    @Override
    public void onBindViewHolder(VH viewHolder, int position) {
        T model = getItem(position);
        populateViewHolder(viewHolder, model, position);
    }

    protected void populateViewHolder(VH viewHolder, T model, int position){
        populateViewHolder(viewHolder, model);
    }

    protected void populateViewHolder(VH viewHolder, T model) {

    }

    public void updateListItem(List<T> items) {
        models.addAll(items);
        notifyDataSetChanged();
    }

    public void updateList(List<T> items) {
        models = new ArrayList<>();
        models.addAll(items);
        notifyDataSetChanged();
    }

    public void restartListItems(List<T> items) {
        models.clear();
        models = new ArrayList<>(items);
        notifyDataSetChanged();
    }

    public void addItem(List<T> items, T item, int index) {
        notifyItemInserted(index);
    }

    public void removeItem(T chat, int index) {
        models.remove(index);
        notifyItemRemoved(index);
    }

    public void updateItem(T chat, int index) {
        notifyDataSetChanged();
    }
}
