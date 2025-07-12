package F_Francesco_F_Nicola_C_Felice.convertifacile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {

    //Variabile per gestire il bottone
    private lateinit var btnStart: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Inizializzare le variabili della UI
        btnStart = findViewById(R.id.btnStart)

        //Gestione click sul bottone
        btnStart.setOnClickListener{

            // Gestisce il passaggio dall'Activity di partenza e quella di arrivo
            var intent = Intent(this, ConvertActivity::class.java)

            //Avvia l'Activity di arrivo
            startActivity(intent)
        }

    }//Fine onCreate

}//Fine classe