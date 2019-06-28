package utils;

import com.google.gson.Gson;
import test_DataTypes.Passenger;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class JsonDataReader {
    private final String customerFilePath = FileReaderManager.getInstance().getConfigReader().getTestDataResourcePath();
    private List<Passenger> passengerList;

    public JsonDataReader(String resourceName) {
        passengerList = getCustomerDataAsList(resourceName);
    }

    private List<Passenger> getCustomerDataAsList(String resourceName) {
        Gson gson = new Gson();
        BufferedReader bufferReader = null;
        try {
            bufferReader = new BufferedReader(new FileReader(customerFilePath + resourceName));
            Passenger[] passengers = gson.fromJson(bufferReader, Passenger[].class);
            return Arrays.asList(passengers);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Json file not found at path : " + customerFilePath);
        } finally {
            try {
                if (bufferReader != null) bufferReader.close();
            } catch (IOException ignore) {
            }
        }
    }

    public final Passenger getCustomerInformationByName(String customerName) {
        for (Passenger passenger : passengerList) {
            if (passenger.firstName.equalsIgnoreCase(customerName)) return passenger;
        }
        return null;
    }

}
