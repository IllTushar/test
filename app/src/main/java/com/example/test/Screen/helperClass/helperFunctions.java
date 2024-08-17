package com.example.test.Screen.helperClass;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatEditText;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;

import com.example.test.R;
import com.example.test.RoomDB.Entity.entityClass;
import com.example.test.Screen.Adapter.UserListAdapter;
import com.example.test.ViewModel.viewModelClass;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


import java.util.*;


public class helperFunctions {
    Context context;

    public helperFunctions(Context context) {
        this.context = context;
    }

    public void createUser(FloatingActionButton createUser, viewModelClass userViewModel) {
        createUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCustomDialog(userViewModel);
            }
        });
    }

    private void showCustomDialog(viewModelClass userViewModel) {
        Dialog dialog = new Dialog(context, R.style.CustomDialogTheme);
        dialog.setContentView(R.layout.custom_dialog_layout);

        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);

        AppCompatEditText name = dialog.findViewById(R.id.userName);
        AppCompatEditText age = dialog.findViewById(R.id.userAge);
        TextView dob = dialog.findViewById(R.id.userDOB);
        AppCompatEditText address = dialog.findViewById(R.id.userAddress);
        TextView done = dialog.findViewById(R.id.done);
        ImageView close = dialog.findViewById(R.id.close);
        // Set DOB
        dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the current date
                final Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                // Initialize DatePickerDialog
                DatePickerDialog datePickerDialog = new DatePickerDialog(context,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                // Set the selected date in the TextView
                                dob.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
                            }
                        }, year, month, day);

                // Show the DatePickerDialog
                datePickerDialog.show();
            }
        });

        //Close Dialog box
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        //Save Data
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the data from the EditTexts
                String userName = name.getText().toString().trim();
                String Age = age.getText().toString().trim();

                String userDOB = dob.getText().toString().trim();
                String userAddress = address.getText().toString().trim();

                // Check if any field is empty
                if (userName.isEmpty() || userDOB.isEmpty() || userAddress.isEmpty() || Age.isEmpty()) {
                    // Display a message or handle empty fields
                    Toast.makeText(context, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                    return;
                } else if (userDOB.equals("DOB")) {
                    Toast.makeText(context, "Please fill DOB", Toast.LENGTH_SHORT).show();
                    return;
                }

                try {
                    int userAge = Integer.parseInt(Age);
                    // Create a new User object
                    entityClass user = new entityClass(userName, userAge, userDOB, userAddress);
                    // Insert the user into the database using ViewModel
                    userViewModel.insert(user);
                    dialog.dismiss();
                } catch (NullPointerException e) {
                    Toast.makeText(context, "Please enter a valid age", Toast.LENGTH_SHORT).show();
                }


            }
        });

        dialog.show();
    }

    public void showDetails(RecyclerView userlist, viewModelClass userViewModel) {
        UserListAdapter adpater = new UserListAdapter(context);
        userlist.setAdapter(adpater);
        userViewModel.getAllUsers().observe((LifecycleOwner) context, new Observer<List<entityClass>>() {
            @Override
            public void onChanged(List<entityClass> entityClasses) {
                adpater.setUsers(entityClasses);
            }
        });
    }


}
