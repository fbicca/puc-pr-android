package br.pucpr.appdev.recycler.Model;

import android.content.Context;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class DataStore {

    private static DataStore instance = null;

    private CityDatabase database;
    private List<City> cities;
    private Context context;

    protected DataStore() {}

    public static DataStore sharedInstance() {

        if (instance == null)
            instance = new DataStore();

        return instance;
    }

    public void setContext(Context context) {

        this.context = context;
        database = new CityDatabase(context);
        cities = database.getAll();
//        addCity(new City("Curitiba", 1750000));
//        addCity(new City("São Paulo", 12100000));
//        addCity(new City("Assis Chateaubriand", 34000));
    }

    public void addCity(City city) {

        if (database.addCity(city) > 0)
            cities.add(city);
        else
            Toast.makeText(context, "Deu ruim, rapá!!!", Toast.LENGTH_LONG).show();
    }

    public void removeCity(int position) {

        int count = database.removeCity(cities.get(position));
        if (count > 0) {
            cities.remove(position);
            Toast.makeText(context, count + " cidades removida(s)", Toast.LENGTH_LONG).show();
        }
    }

    public void editCity(City city, int position) {

        database.updateCity(city);
        cities.set(position, city);
    }

    public void clearCities() {

        cities.clear();
    }

    public void clearCitiesList() {

        cities.clear();
    }

    public List<City> findBy(String term) {

        cities = database.findBy(term);
        return cities;

    }

    public List<City> findByNumeric(String population) {

        cities = database.findBy(population);
        return cities;

    }

    public List<City> getCities() {

        return cities;
    }

    public City getCity(int position) {

        return cities.get(position);
    }
}
