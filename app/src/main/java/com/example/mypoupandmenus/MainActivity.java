package com.example.mypoupandmenus;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.ContextMenu;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.PopupWindow;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
private int checkedItemId;
private ArrayList<Integer> selectedItems;
private AlertDialog alertDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button=findViewById(R.id.button);
        registerForContextMenu(button);
        registerForContextMenu(findViewById(R.id.button2));
//        selectedItems= new ArrayList<>();

        PopupMenu popupMenu= new PopupMenu(MainActivity.this,button);
        MenuInflater menuInflater= popupMenu.getMenuInflater();
        menuInflater.inflate(R.menu.popup_menu, popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.remove:
                        System.out.println("remove");
                        return true;
                    case R.id.hide:
                        System.out.println("hide");
                        return true;
                }
                return false;
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupMenu.show();
            }
        });
    }



    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        switch (v.getId()){
            case R.id.button:
                getMenuInflater().inflate(R.menu.context_menu,menu);
                break;
            case R.id.button2:
                getMenuInflater().inflate(R.menu.context_menu2,menu);
                break;
        }
        super.onCreateContextMenu(menu, v, menuInfo);
    }
    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.copy:
                System.out.println("copy");
                break;
            case R.id.paste:
                System.out.println("paste");
                break;
            case R.id.remove:
                System.out.println("remove");
                break;
            case R.id.hide:
                System.out.println("hide");
                break;

        }
        return super.onContextItemSelected(item);
    }



    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        MenuItem menuItem=menu.findItem(R.id.Settings);
        menuItem.setTooltipText("Show Setting Screen");
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.dark:
                item.setChecked(true);
                break;
            case R.id.light:
                item.setChecked(true);
                break;
            case R.id.Settings:
                System.out.println("setting clicked!");
                break;
            case R.id.Abouts:
                System.out.println("About clicked!");
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    public void showPopupWindow(View v) {
    View popupview = LayoutInflater.from(this).inflate(R.layout.popupwindow_layout,null);
        PopupWindow popupWindow=new PopupWindow(this);
        popupWindow.setContentView(popupview);
        popupWindow.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
//        popupWindow.showAtLocation(findViewById(R.id.container),Gravity.CENTER,0,0);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            popupWindow.showAsDropDown(findViewById(R.id.button),120,0,Gravity.START);
        }
        SeekBar seekBar=popupview.findViewById(R.id.seekBar);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                System.out.println(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                popupWindow.dismiss();
            }
        });
    }

//    public void showCustomDialog(View view) {
//        View dialogview=LayoutInflater.from(this).inflate(R.layout.customdialog,null);
//        EditText editText=dialogview.findViewById(R.id.text_Add_Note);
//        dialogview.findViewById(R.id.button_save).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                System.out.println("Save" + editText.getText().toString());
//                alertDialog.dismiss();
//            }
//        });
//        alertDialog = new AlertDialog.Builder(this)
//                .setView(dialogview)
//                .create();
//        alertDialog.show();
//
//
//    }

//    public void showListOfChoices(View view) {
//        final String[] colors={"RED","GREEN","BLUE","Gray","Cyan"};
//        selectedItems.add(1);
//        selectedItems.add(2);
//        new AlertDialog.Builder(this)
//                .setTitle("Pick your favorites colors")
//                .setMultiChoiceItems(colors, new boolean[]{false, true, false, true, false}, new DialogInterface.OnMultiChoiceClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
//                        if (isChecked){
//                            selectedItems.add(which);
//
//                        }else {
//                            selectedItems.remove(which);
//                        }
//                        System.out.println("Item" + colors[which] + "" + (isChecked ? "checked" : "unchecked"));
//                    }
//                }).setPositiveButton("save", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                System.out.println("save" + checkedItemId);
//            }
//        })
//                .create().show();
//    }



//    public void showSimpleAlertDialog(View view) {
//        DialogInterface.OnClickListener onClickListener=new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//            switch (which){
//                case DialogInterface.BUTTON_POSITIVE:
//                    System.out.println("Ok Clicked!");
//                    break;
//                    case DialogInterface.BUTTON_NEGATIVE:
//                        System.out.println("Cancel Clicked!");
//                        break;
//                        case DialogInterface.BUTTON_NEUTRAL:
//                            System.out.println("neutral Clicked!");
//                            break;
//
//            }
//            }
//        };
//        new AlertDialog.Builder(this)
//         .setTitle("Confirmation")
//         .setMessage("Are your sure ?")
//         .setPositiveButton("Ok", onClickListener)
//         .setNegativeButton("Cancel",onClickListener)
//         .setNeutralButton("Remind me later",onClickListener)
//                .create().show();
//
//    }
//


//    public void showButtonMessage(View view) {
//        Snackbar snackbar=Snackbar.make(findViewById(R.id.container),"Hello World",Snackbar.LENGTH_LONG);
//        snackbar.setAction("ok", new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                System.out.println("Done");
//            }
//        });
//        snackbar.addCallback(new Snackbar.Callback(){
//            @Override
//            public void onShown(Snackbar sb) {
//                super.onShown(sb);
//                System.out.println("Appeared");
//            }
//
//            @Override
//            public void onDismissed(Snackbar transientBottomBar, int event) {
//                super.onDismissed(transientBottomBar, event);
//                System.out.println("Dismissed");
//            }
//        });
//        snackbar.show();
//    }



//    public void showMessage(View view) {
////        int paddingInPixels= (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,16, getResources().getDisplayMetrics());
////        TextView textView=new TextView(this);
////        textView.setText("Hello World!");
////        textView.setBackgroundColor(Color.CYAN);
////        textView.setPadding(paddingInPixels,paddingInPixels,paddingInPixels,paddingInPixels);
//
//        LayoutInflater layoutInflater=LayoutInflater.from(this);
//        View toastView=layoutInflater.inflate(R.layout.toast_layout,null);
//        TextView textView=toastView.findViewById(R.id.text_view_name);
//        textView.setText("Husam");
//        int paddingInPixels= (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,16, getResources().getDisplayMetrics());
//        Toast toast=new Toast(this);
//        toast.setView(toastView);
//        toast.setDuration(Toast.LENGTH_LONG);
//        toast.setGravity(Gravity.BOTTOM,0,paddingInPixels);
//        toast.show();
//
//    }


}