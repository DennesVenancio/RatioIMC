package kurgomobi.ratioimc.features.view;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.Checked;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import kurgomobi.ratioimc.R;
import kurgomobi.ratioimc.features.model.TypePerson;
import kurgomobi.ratioimc.features.view.presenter.ProcedureIMCPresenter;
import kurgomobi.ratioimc.util.ColoredSnackbar;
import kurgomobi.ratioimc.util.Mask;
import nucleus.factory.RequiresPresenter;
import nucleus.view.NucleusAppCompatActivity;

@RequiresPresenter(ProcedureIMCPresenter.class)
public class ProcedureIMCActivity extends NucleusAppCompatActivity<ProcedureIMCPresenter> implements Validator.ValidationListener {

    @Bind(R.id.containerMain)
    View containerMain;

    @Checked
    @Bind(R.id.rgType)
    RadioGroup typePerson;

    @NotEmpty(messageResId = R.string.errorWeight)
    @Bind(R.id.weight)
    EditText weight;

    @NotEmpty(messageResId = R.string.errorHeight)
    @Bind(R.id.height)
    EditText height;

    private Validator validator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.procedure_imc);
        ButterKnife.bind(this);

        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        typePerson.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){

                    case R.id.rMan :
                        getPresenter().setTypePerson(TypePerson.MAN.name());
                        break;
                    case R.id.rWoman :
                        getPresenter().setTypePerson(TypePerson.WOMAN.name());
                        break;
                    case R.id.rKid :
                        getPresenter().setTypePerson(TypePerson.KID.name());
                        break;
                }

            }
        });

        getPresenter().setTypePerson(TypePerson.MAN.toString());
        validator = new Validator(this);
        validator.setValidationListener(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.calculate)
    public void calculate(View view){
        validator.validate();
    }

    public void showMessageSuccess(int message){
        Snackbar snackbar = Snackbar.make(containerMain, message, Snackbar.LENGTH_SHORT).setCallback(new Snackbar.Callback() {
            @Override
            public void onDismissed(Snackbar snackbar, int event) {
                super.onDismissed(snackbar, event);
                finish();
            }
        });

        ColoredSnackbar.confirm(snackbar, this).show();
    }

    public void showMessageError(int message){
        Snackbar snackbar = Snackbar.make(containerMain, message, Snackbar.LENGTH_SHORT);

        ColoredSnackbar.confirm(snackbar, this).show();
    }

    @Override
    public void onValidationSucceeded() {
        hideKeyboard();
        getPresenter().setHeightAndWeight(Double.valueOf(height.getText().toString()),
                Double.valueOf(weight.getText().toString()));

        getPresenter().calculate();
    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {
        for (ValidationError error : errors) {
            View view = error.getView();
            String message = error.getCollatedErrorMessage(this);

            if (view instanceof EditText) {
                ((EditText) view).setError(message);
            } else {
                Snackbar.make(containerMain, message, Snackbar.LENGTH_SHORT);
            }
        }
    }

    private void hideKeyboard(){
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}
