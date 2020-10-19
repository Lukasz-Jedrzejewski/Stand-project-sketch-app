package com.legion.standprojectapp.service.serviceImpl;

import com.byteowls.jopencage.JOpenCageGeocoder;
import com.byteowls.jopencage.model.JOpenCageForwardRequest;
import com.byteowls.jopencage.model.JOpenCageLatLng;
import com.byteowls.jopencage.model.JOpenCageResponse;
import com.legion.standprojectapp.service.JOpenCageService;

public class JOpenCageServiceImpl implements JOpenCageService {
    @Override
    public JOpenCageLatLng getCoordinates(String street, String city) {
        JOpenCageGeocoder geocoder = new JOpenCageGeocoder("4a83bf1d08dc45479e8370def4ce0ce3");
        JOpenCageForwardRequest request = new JOpenCageForwardRequest(street, city);
        request.setLanguage("Polish");

        JOpenCageResponse response = geocoder.forward(request);
        return response.getFirstPosition();
    }
}
