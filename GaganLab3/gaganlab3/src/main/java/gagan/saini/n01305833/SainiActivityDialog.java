package gagan.saini.n01305833;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDialogFragment;

public class SainiActivityDialog extends AppCompatDialogFragment {

    public SainiActivityDialog(){

    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {



        final Spinner spinner = (Spinner) getActivity().findViewById(R.id.gaganSpinnerId);

        TextView text_sel = (TextView)spinner.getSelectedView();

        //get the text from the spinner view

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("You Selected")
                .setMessage(getString(R.string.coursemsg)+text_sel.getText())
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

return builder.create();


    }
}
