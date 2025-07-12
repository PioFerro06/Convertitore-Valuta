package F_Francesco_F_Nicola_C_Felice.convertifacile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView

class ConvertActivity : AppCompatActivity() {

    // Dichiarazione delle variabili della UI
    private lateinit var titleTextView: TextView
    private lateinit var amountTextView: TextView
    private lateinit var amountEditText: EditText
    private lateinit var fromTextView: TextView
    private lateinit var fromCurrencySpinner: Spinner
    private lateinit var toTextView: TextView
    private lateinit var toCurrencySpinner: Spinner
    private lateinit var convertButton: Button
    private lateinit var resultTextView: TextView

    // Tassi di cambio fissi (per scopo didattico)
    // Questi tassi sono relativi a 1 Euro

    private val exchangeRates = mapOf(

        "EUR" to mapOf(
            "USD" to 1.08,
            "JPY" to 170.00,
            "GBP" to 0.85,
            "CHF" to 0.96,
            "EUR" to 1.00
        ),
        "USD" to mapOf(
            "EUR" to 1 / 1.08,
            "JPY" to 170.00 / 1.08,
            "GBP" to 0.85 / 1.08,
            "CHF" to 0.96 / 1.08,
            "USD" to 1.00
        ),
        "JPY" to mapOf(
            "EUR" to 1 / 170.00,
            "USD" to 1.08 / 170.00,
            "GBP" to 0.85 / 170.00,
            "CHF" to 0.96 / 170.00,
            "JPY" to 1.00
        ),
        "GBP" to mapOf(
            "EUR" to 1 / 0.85,
            "USD" to 1.08 / 0.85,
            "JPY" to 170.00 / 0.85,
            "CHF" to 0.96 / 0.85,
            "GBP" to 1.00
        ),
        "CHF" to mapOf(
            "EUR" to 1 / 0.96,
            "USD" to 1.08 / 0.96,
            "JPY" to 170.00 / 0.96,
            "GBP" to 0.85 / 0.96,
            "CHF" to 1.00
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_convert)

        // Inizializzazione delle variabili relative alla UI
        titleTextView = findViewById(R.id.tvTitle)
        amountTextView = findViewById(R.id.tvAmount)
        amountEditText = findViewById(R.id.editTextAmount)
        fromCurrencySpinner = findViewById(R.id.spinnerC1)
        toCurrencySpinner = findViewById(R.id.spinnerC2)
        convertButton =  findViewById(R.id.btnConvert)
        resultTextView = findViewById(R.id.tvResult)
        fromTextView = findViewById(R.id.tvCurrency1)
        toTextView = findViewById(R.id.tvCurrency2)

        // Elenco delle valute da mostrare negli Spinner
        val currencies = arrayOf("USD", "EUR", "JPY", "GBP", "CHF")


        // Adattatore per gli Spinner
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, currencies)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        fromCurrencySpinner.adapter = adapter
        toCurrencySpinner.adapter = adapter

        // Imposta selezioni predefinite
        fromCurrencySpinner.setSelection(currencies.indexOf("EUR")) // Euro come valuta di partenza predefinita
        toCurrencySpinner.setSelection(currencies.indexOf("USD"))  // Dollaro come valuta di arrivo predefinita


        // Click pulsante di conversione
        convertButton.setOnClickListener {
            convertCurrency()
        }

    }// fine Oncreate

    //--------------------------METODI -----------------------------//

    private fun convertCurrency() {
        val amountString = amountEditText.text.toString()
        if (amountString.isEmpty()) {
            resultTextView.text = "Per favore, inserisci un importo."
            return
        }

        val amount = amountString.toDoubleOrNull()
        if (amount == null || amount <= 0) {
            resultTextView.text = "Importo non valido. Inserisci un numero positivo."
            return
        }

        //Nome valuta di partenza
        val fromCurrency = fromCurrencySpinner.selectedItem.toString()

        //Nome valuta di arrivo
        val toCurrency = toCurrencySpinner.selectedItem.toString()

        // Ottieni il tasso di cambio
        val rate = exchangeRates[fromCurrency]?.get(toCurrency)

        //Log.d("RATE DA DIZIONARIO", "$rate")

        if (rate == null) {
            resultTextView.text = "Tasso di cambio non disponibile per questa conversione."
            return
        }

        //Calcolo conversione
        val convertedAmount = amount * rate

        // Formatta il risultato a due cifre decimali
        resultTextView.text = String.format("%.2f %s = %.2f %s", amount, fromCurrency, convertedAmount, toCurrency)
    }



}//fine Classe