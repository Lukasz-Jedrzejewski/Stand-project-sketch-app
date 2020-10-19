package com.legion.standprojectapp.service;

import com.byteowls.jopencage.model.JOpenCageLatLng;

public interface JOpenCageService {

    JOpenCageLatLng getCoordinates(String street, String city);
}
