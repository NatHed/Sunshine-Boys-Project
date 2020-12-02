package gagan.saini.n01305833;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;

import org.w3c.dom.Text;


public class GaHome extends Fragment implements AdapterView.OnItemSelectedListener {

    public GaHome() {

    }


    private DatePicker datePicker;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.ga_home, container, false);


        datePicker = (DatePicker) view.findViewById(R.id.gaganDatePicker);

        //create the spinner object
        final Spinner spinner = (Spinner) view.findViewById(R.id.gaganSpinnerId);
        //create a button object


        final ImageButton submit = (ImageButton) view.findViewById(R.id.gaganimagebtn);

        //handle the  click event
        submit.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                //get the spinner view as text view
                TextView text_sel = (TextView)spinner.getSelectedView();
                //get the text from the spinner view

                openDialog();



                    Toast.makeText(getActivity().getBaseContext(),
                            "Date selected: " + (datePicker.getMonth() + 1) +
                                    "/" + datePicker.getDayOfMonth() +
                                    "/" + datePicker.getYear(),
                            Toast.LENGTH_SHORT).show();

                }

        });

        return view;

    }


    public void openDialog() {
            SainiActivityDialog sainiActivityDialog = new SainiActivityDialog();
            sainiActivityDialog.show(getFragmentManager(), "sainiActivityDialog");
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        String text = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();


    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
