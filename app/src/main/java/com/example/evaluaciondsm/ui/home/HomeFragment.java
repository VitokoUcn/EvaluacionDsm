package com.example.evaluaciondsm.ui.home;

import static android.content.Context.MODE_PRIVATE;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.evaluaciondsm.R;
import com.example.evaluaciondsm.databinding.FragmentHomeBinding;

import java.util.ArrayList;
import java.util.List;

class Producto {
    String code, description, price;
    public Producto(String codigo, String descripcion, String precio) {
        this.code = codigo;
        this.description = descripcion;
        this.price = precio;
    }

    public String getCodigo() {
        return code;
    }

    public String getDescripcion() {
        return description;
    }

    public String getPrecio() {
        return price;
    }
}
public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;


    TextView txt_code, txt_description, txt_price;

    

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        txt_code = binding.txtCode;
        txt_description = binding.txtDescription;
        txt_price = binding.txtPrice;

        binding.btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                save();
            }
        });

        binding.btnSearchP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                search();
            }
        });

        binding.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                delete();
            }
        });

        binding.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edit();
            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    //Metodo para guardar los productos
    public void save() {


    }

    //Metodo para buscar productos
    public void search() {



    }

    public void delete() {

    }

    public void edit() {

    }

    public void cleanForm() {
    }

}