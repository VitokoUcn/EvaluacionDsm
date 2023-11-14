package com.example.evaluaciondsm.ui.home;

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
    ArrayList<Producto> listaProductos = new ArrayList<>();
    

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
        txt_code = binding.txtCode;
        txt_description = binding.txtDescription;
        txt_price = binding.txtPrice;
        
        String codigo = binding.txtCode.getText().toString();
        String descripcion = binding.txtDescription.getText().toString();
        String precio = binding.txtPrice.getText().toString();

        if (!codigo.isEmpty() && !descripcion.isEmpty() && !precio.isEmpty()) {
            Producto producto = new Producto(codigo, descripcion, precio);
            listaProductos.add(producto);
            cleanForm();
            Toast.makeText(getContext(), "Producto registrado", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getContext(), "Debe llenar todos los campos", Toast.LENGTH_SHORT).show();
        }

    }

    //Metodo para buscar productos
    public void search() {
        txt_code = binding.txtCode;
        txt_description = binding.txtDescription;
        txt_price = binding.txtPrice;

        String codigo = binding.txtCode.getText().toString();
        String descripcion = binding.txtDescription.getText().toString();
        String precio = binding.txtPrice.getText().toString();

        if (!codigo.isEmpty()) {
            for (int i = 0; i < listaProductos.size(); i++) {
                if (listaProductos.get(i).getCodigo().equals(codigo)) {
                    txt_description.setText(listaProductos.get(i).getDescripcion());
                    txt_price.setText(listaProductos.get(i).getPrecio());
                    Toast.makeText(getContext(), "Producto encontrado", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "Producto no encontrado", Toast.LENGTH_SHORT).show();
                }
            }
        } else {
            Toast.makeText(getContext(), "Debe ingresar el codigo del producto", Toast.LENGTH_SHORT).show();
        }

    }

    public void delete() {

    }

    public void edit() {

    }

    public void cleanForm() {
    }

}