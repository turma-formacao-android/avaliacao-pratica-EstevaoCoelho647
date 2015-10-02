package br.com.cast.turmaformacao.agenda.model.http;


import android.util.Log;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import br.com.cast.turmaformacao.agenda.model.entities.Address;


public final class CepService {

    private static final String URL = "http://correiosapi.apphb.com/cep/";

    private CepService() {

    }

    public static Address getAddressByCep(String zipCode) {
        Address address = null;

        try {
            java.net.URL url = new URL(URL + zipCode);

            final HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            int responseCode = conn.getResponseCode();
            Log.i("getAddresByCep", "Codigo de retorno da requisição de cep: " + responseCode);

            if (responseCode == HttpURLConnection.HTTP_OK) {
                InputStream inputStream = conn.getInputStream();

                ObjectMapper objectMapper = new ObjectMapper();
                address = objectMapper.readValue(inputStream, Address.class);
            }
        } catch (Exception e) {
            Log.e(CepService.class.getName(), e.getMessage());
        }


        return address;
    }
}