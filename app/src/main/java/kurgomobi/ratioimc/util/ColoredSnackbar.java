package kurgomobi.ratioimc.util;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;

import kurgomobi.ratioimc.R;

public class ColoredSnackbar {

    private static final int red = 0xfff44336;
    private static final int green = 0xff4caf50;
    private static final int blue = 0xff2195f3;
    private static final int orange = 0xffffc107;

    private static View getSnackBarLayout(Snackbar snackbar) {
        if (snackbar != null) {
            return snackbar.getView();
        }
        return null;
    }

    private static Snackbar colorSnackBar(Snackbar snackbar, int colorId) {
        View snackBarView = getSnackBarLayout(snackbar);
        if (snackBarView != null) {
            snackBarView.setBackgroundColor(colorId);
            final TextView tv = (TextView) snackBarView.findViewById(android.support.design.R.id.snackbar_text);
            tv.setTextSize(TypedValue.COMPLEX_UNIT_PX, tv.getResources().getDimension(R.dimen.snackbar_textsize));
        }

        return snackbar;
    }

    public static Snackbar info(Snackbar snackbar, Context context) {
        return colorSnackBar(snackbar, blue);
    }

    public static Snackbar warning(Snackbar snackbar, Context context) {
        return colorSnackBar(snackbar, context.getResources().getColor(R.color.colorAccent));
    }

    public static Snackbar confirm(Snackbar snackbar, Context context) {
        return colorSnackBar(snackbar, context.getResources().getColor(R.color.colorPrimary));
    }
}