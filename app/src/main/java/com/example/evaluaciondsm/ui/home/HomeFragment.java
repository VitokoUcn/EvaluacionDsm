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
    SQLiteDatabase db;

    TextView txt_code, txt_description, txt_price;

    

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        db = getActivity().openOrCreateDatabase("products.db", MODE_PRIVATE, null);
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
        String code = txt_code.getText().toString();
        String description = txt_description.getText().toString();
        String price = txt_price.getText().toString();

        if (code.isEmpty() || description.isEmpty() || price.isEmpty()) {
            Toast.makeText(getActivity(), "Debe llenar todos los campos", Toast.LENGTH_SHORT).show();
        } else {
            ContentValues fila = new ContentValues();
            fila.put("code", code);
            fila.put("description", description);
            fila.put("price", price);

            db.insert("products", null, fila);
            Toast.makeText(getActivity(), "Producto registrado", Toast.LENGTH_SHORT).show();
            cleanForm();
        }
    }

    //Metodo para buscar productos
    public void search() {
        String code = txt_code.getText().toString();
        if (code.isEmpty()) {
            Toast.makeText(getActivity(), "Debe ingresar un codigo", Toast.LENGTH_SHORT).show();
        } else {
            Cursor fila = db.rawQuery("select description, price from products where code = " + code, null);
            if (fila.moveToFirst()) {
                txt_description.setText(fila.getString(0));
                txt_price.setText(fila.getString(1));
            } else {
                Toast.makeText(getActivity(), "No existe el producto", Toast.LENGTH_SHORT).show();
                cleanForm();
            }

        }
    }

    public void delete() {
        String code = txt_code.getText().toString();
        if (code.isEmpty()) {
            Toast.makeText(getActivity(), "Debe ingresar un codigo", Toast.LENGTH_SHORT).show();
        } else {
            Cursor fila = db.rawQuery("select description, price from products where code = " + code, null);
            if (fila.moveToFirst()) {
                db.delete("products", "code = " + code, null);
                Toast.makeText(getActivity(), "Producto eliminado", Toast.LENGTH_SHORT).show();
                cleanForm();
            } else {
                Toast.makeText(getActivity(), "No existe el producto", Toast.LENGTH_SHORT).show();
                cleanForm();
            }
        }

    }

    public void edit() {
        String code = txt_code.getText().toString();
        String description = txt_description.getText().toString();
        String price = txt_price.getText().toString();

        if (code.isEmpty() || description.isEmpty() || price.isEmpty()) {
            Toast.makeText(getActivity(), "Debe llenar todos los campos", Toast.LENGTH_SHORT).show();
        } else {
            Cursor fila = db.rawQuery("select description, price from products where code = " + code, null);
            if (fila.moveToFirst()) {
                ContentValues register = new ContentValues();
                register.put("description", description);
                register.put("price", price);
                db.update("products", register, "code = " + code, null);
                Toast.makeText(getActivity(), "Producto actualizado", Toast.LENGTH_SHORT).show();
                cleanForm();
            } else {
                Toast.makeText(getActivity(), "No existe el producto", Toast.LENGTH_SHORT).show();
                cleanForm();
            }
        }

    }

    public void cleanForm(){
        txt_code.setText("");
        txt_description.setText("");
        txt_price.setText("");
    }

}