package com.example.chaitany.justjava;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class  MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    int numberOfCoffees=1;


    public void price(int n){

    }
    /**
     * This method is called when the order button is clicked.
     */
    public void increment(View view){

        numberOfCoffees+=1;
        CheckBox wc = findViewById(R.id.whipped_cream);
        CheckBox c = findViewById(R.id.chocolate);
        boolean whipped_cream,choco;
        if(wc.isChecked())
            whipped_cream=true;
        else
            whipped_cream=false;
        if(c.isChecked())
            choco=true;
        else
            choco=false;
        displayPrice(calculatePrice(numberOfCoffees,50,whipped_cream,choco));
        display(numberOfCoffees);


    }
    public void checkbox(View v){
        CheckBox wc = findViewById(R.id.whipped_cream);
        CheckBox c = findViewById(R.id.chocolate);
        boolean whipped_cream,choco;
        if(wc.isChecked())
            whipped_cream=true;
        else
            whipped_cream=false;
        if(c.isChecked())
            choco=true;
        else
            choco=false;
                displayPrice(calculatePrice(numberOfCoffees,50,whipped_cream,choco));
    }
    public void decrement(View view){
        CheckBox wc = findViewById(R.id.whipped_cream);
        CheckBox c = findViewById(R.id.chocolate);
        boolean whipped_cream,choco;
        if(numberOfCoffees==0)
            numberOfCoffees=0;
        else
            numberOfCoffees-=1;
        display(numberOfCoffees);
        if(wc.isChecked())
            whipped_cream=true;
        else
            whipped_cream=false;
        if(c.isChecked())
            choco=true;
        else
            choco=false;
        displayPrice(calculatePrice(numberOfCoffees,50,whipped_cream,choco));

    }
    public void submitOrder(View view) {

        display(numberOfCoffees);
        CheckBox wc = findViewById(R.id.whipped_cream);
        CheckBox c = findViewById(R.id.chocolate);
        EditText name = findViewById(R.id.Name);
        String name1 = name.getText().toString();
        boolean whipped_cream,choco;
        if(wc.isChecked())
            whipped_cream=true;
        else
            whipped_cream=false;
        if(c.isChecked())
            choco=true;
        else
            choco=false;
        String[] arr = new String[1];
        arr[0] = "chaitany.pandiya@gmail.com";

        String temp = createOrderSummary(numberOfCoffees,calculatePrice(numberOfCoffees,50,whipped_cream,choco),name1,whipped_cream,choco);
//        displayPrice(temp);
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_EMAIL, arr);
        intent.putExtra(Intent.EXTRA_SUBJECT, "Coffee order for "+name1);
        intent.putExtra(Intent.EXTRA_TEXT,(temp));
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }

    }
    private int calculatePrice(int quantity,int cost,boolean wc,boolean choco){
        int price=0;
        if(wc)
            price+=10;
        if(choco)
            price+=20;
        price+=cost*quantity;
        return price;
    }

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(message);
    }
    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }
    private void displayPrice(int x) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText("Rs "+x);
    }
    private String createOrderSummary(int quantity,int price,String name,boolean whipped_cream,boolean choco){

        return("Name: "+name+"\nQuantity: "+quantity+"\nAdded Whipped Cream: "+whipped_cream+"\nAdded Chocolate: "+choco+"\nTotal: Rs"+price);

    }
}