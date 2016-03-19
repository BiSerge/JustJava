package com.example.biche.justjava;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    int quantity = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view)
    {
        CheckBox wippedCreamCheckBox = (CheckBox)findViewById(R.id.whipped_cream_checkbox);
        boolean hasWippedCream = wippedCreamCheckBox.isChecked();

        CheckBox chocolateCheckBox = (CheckBox)findViewById(R.id.chocolate_checkBox);
        boolean hasChocolate = chocolateCheckBox.isChecked();

        EditText nameEditText = (EditText) findViewById(R.id.editTextName);
        String nameString = nameEditText.getText().toString();

        int price = calculatePrice(hasWippedCream, hasChocolate);
        displayMessage(createOrderSummary(price, hasWippedCream, hasChocolate, nameString));
        //displayMessage(price);
    }

    /**
     * Calculates the price of the order.
     *
     //* @param quantity is the number of cups of coffee ordered
     */
    private int calculatePrice(boolean addWippedCream, boolean addChocolate) {
        int basePrice = 5;
        if (addWippedCream){
            basePrice += 1;
        }

        if (addChocolate){
            basePrice += 2;
        }

        return quantity * basePrice;
    }

    private String createOrderSummary(int price, boolean addWhipedCream, boolean addChocolate, String nameUser)
    {
        String priceMessage = "Name: " + nameUser;
        priceMessage += "\n Add whipped cream?: " + addWhipedCream;
        priceMessage += "\n Add chocolate?: " + addChocolate;
        priceMessage += "\n Quantity: " + quantity;
        priceMessage += "\n Total: $" + price;
        priceMessage += "\n Thank you!";
        return  priceMessage;
    }

    public void increment(View view) {
        quantity = quantity + 1;
        display(quantity);
    }

    public void decrement(View view) {
        quantity = quantity - 1;
        display(quantity);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(
                R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }
}
