package com.benja.restauranteapp.db;


import android.content.Context;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class FoodRepository {

    private final FoodDao foodDao;
    private final Executor executor = Executors.newSingleThreadExecutor();

    public FoodRepository(Context context) {
        AppDatabase db = AppDatabase.getInstance(context);
        this.foodDao = db.foodDao();
    }

    public void insertar(Food food) {
        executor.execute(() -> foodDao.insert(food));
    }

    public void obtenerTodos(Callback<List<Food>> callback) {
        executor.execute(() -> {
            List<Food> lista = foodDao.getAll();
            callback.onResult(lista);
        });
    }

    public interface Callback<T> {
        void onResult(T result);
    }
}
