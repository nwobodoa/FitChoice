package com.example.fitchoices;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SetupViewModel extends ViewModel {
   private final MutableLiveData<String> whyWeAsk;

   public SetupViewModel(){
       whyWeAsk = new MutableLiveData<>();
   }

   public void updateWhyWeAsk(String message) {
       whyWeAsk.postValue(message);
   }

   public LiveData<String> getWhyWeAsk() {
       return  whyWeAsk;
   }
}
