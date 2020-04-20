package com.example.basic_geo_quiz_app.ui.register;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.basic_geo_quiz_app.data.RegistrationRepository;
import com.example.basic_geo_quiz_app.data.model.userRecord;

public class RegisterViewModel extends ViewModel {

    private MutableLiveData<userRecord> userRecordMutableLiveData = new MutableLiveData<>();

    public RegisterViewModel(RegistrationRepository registration) {

    }


}
