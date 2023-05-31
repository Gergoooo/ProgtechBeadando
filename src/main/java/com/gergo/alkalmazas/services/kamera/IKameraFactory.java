package com.gergo.alkalmazas.services.kamera;

import com.gergo.alkalmazas.model.Kamera;
import com.gergo.alkalmazas.model.enums.Publisher;

import java.util.List;

public interface IKameraFactory {

    List<Kamera> getSuggestions(Publisher publisher);
    public static IKameraFactory findFactory(boolean isFull){
        if(isFull){
            return new FullKameraFactory();
        }
        else {
            return new WithoutFilterCameraFactory();
        }
    }
}
