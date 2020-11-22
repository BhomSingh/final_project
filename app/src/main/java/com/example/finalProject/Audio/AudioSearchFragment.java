package com.example.finalProject.Audio;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.androidlabs.R;

public class AudioSearchFragment extends Fragment {

    AudioActivity activity;

    EditText editTextTextPersonName;
    Button SeachButton;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_audio_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        editTextTextPersonName = view.findViewById(R.id.editTextTextPersonName);
        SeachButton = view.findViewById(R.id.btnAudioSearch);

        SeachButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AudioListFragment fragment2 = new AudioListFragment();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frAudio, fragment2);
                fragmentTransaction.commit();

                Bundle bundle = new Bundle();
                bundle.putString("singetName", editTextTextPersonName.getText().toString().trim());
                //set Fragmentclass Arguments
                fragment2.setArguments(bundle);
            }
        });

    }


}
