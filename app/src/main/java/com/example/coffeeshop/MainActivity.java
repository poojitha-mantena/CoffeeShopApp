package com.example.coffeeshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {
    TextView quantityTextView;
    int numberOfCakes=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void increment(View view) {
        if(numberOfCakes==10){
            Toast.makeText(this,"You cannot have more than 10 cakes",Toast.LENGTH_SHORT).show();
            return;
        }
        numberOfCakes=numberOfCakes+1;
        display(numberOfCakes);
    }
    public void decrement(View view) {
        if(numberOfCakes==1){
            Toast.makeText(this,"You cannot have less than 1 cake",Toast.LENGTH_SHORT).show();
            return;
        }
        numberOfCakes=numberOfCakes-1;
        display(numberOfCakes);
    }

    public void submitOrder(View view) {
        EditText nameField= (EditText) findViewById(R.id.name_field);
        String name= nameField.getText().toString();

        CheckBox wippedCreamCheckBox= (CheckBox) findViewById(R.id.wipped_cream_checkBox);
        boolean hasWippedCream=wippedCreamCheckBox.isChecked();

        CheckBox chocolateCheckBox= (CheckBox) findViewById(R.id.chocolate_checkBox);
        boolean hasChocolate= chocolateCheckBox.isChecked();

        int price = calculatePrice(hasWippedCream,hasChocolate);
        String priceMessage=createOrderSummary(name,price,hasWippedCream,hasChocolate);
        displayMessage(priceMessage);
    }

    private int calculatePrice(boolean addWippedCream, boolean addChocolate) {
        int basePrice=5;
        if(addWippedCream){
            basePrice +=1;
        }
        if(addChocolate){
            basePrice +=2;
        }
        return numberOfCakes * basePrice;
    }
    private String createOrderSummary(String name,int price, boolean butterscotch, boolean chocolate){
        String priceMessage="Name: "+name;
        priceMessage += "\nAdd Butterscotch? "+butterscotch;
        priceMessage += "\nAdd Chocolate? "+chocolate;
        priceMessage += "\nQuantity: "+numberOfCakes;
        priceMessage += "\nTotal: $" +price;
        priceMessage += "\nThank you!";
        return priceMessage;
    }

    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.orderSummary_text_view);
        orderSummaryTextView.setText(message);
    }

    private void display(int number){
        quantityTextView =findViewById(R.id.quantity_text_view);
        quantityTextView.setText(""+number);
    }

}
