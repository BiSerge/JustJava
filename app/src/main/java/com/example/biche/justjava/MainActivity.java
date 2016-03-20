package com.example.biche.justjava;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
        String priceMessage = createOrderSummary(price, hasWippedCream, hasChocolate, nameString);

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto: "));
        intent.putExtra(intent.EXTRA_SUBJECT, getString(R.string.order_summary_email_subject, nameString));
        intent.putExtra(intent.EXTRA_TEXT, priceMessage);
        if (intent.resolveActivity(getPackageManager()) != null){
            startActivity(intent);
        }
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
        String priceMessage = getString(R.string.order_summary_name, nameUser);
        priceMessage += "\n " + getString(R.string.order_summary_whipped_cream, addWhipedCream);
        priceMessage += "\n " + getString(R.string.order_summary_chocolate, addChocolate);
        priceMessage += "\n " + getString(R.string.order_summary_quantity, quantity);
        priceMessage += "\n " + getString(R.string.order_summary_price, price);
        NumberFormat.getCurrencyInstance().format(price);
        priceMessage += "\n " + getString(R.string.thank_you);
        return  priceMessage;
    }

    public void increment(View view) {
        if (quantity == 100){
            // Show an error message as a toast
            Toast.makeText(this, "You cannot have more than 100 coffees", Toast.LENGTH_SHORT).show();
            // Exit this method early because there's nothing left to do
            return;
        }
        quantity += 1;
        display(quantity);
    }

    public void decrement(View view) {
        if (quantity == 1){
            // Show an error message as a toast
            Toast.makeText(this, "You cannot have less than 1 coffee", Toast.LENGTH_SHORT).show();
            // Exit this method early because there's nothing left to do
            return;
        }
        quantity -= 1;
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
}
