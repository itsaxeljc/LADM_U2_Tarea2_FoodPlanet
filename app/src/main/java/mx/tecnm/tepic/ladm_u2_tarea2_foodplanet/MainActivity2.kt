package mx.tecnm.tepic.ladm_u2_tarea2_foodplanet

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import mx.tecnm.tepic.ladm_u2_tarea2_foodplanet.databinding.ActivityMain2Binding
import mx.tecnm.tepic.ladm_u2_tarea2_foodplanet.databinding.ActivityMainBinding

class MainActivity2 : AppCompatActivity() {
    lateinit var addhilo : MediaPlayer
    lateinit var addcor : MediaPlayer
    lateinit var res : MediaPlayer
    lateinit var binding: ActivityMain2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(Lienzo(this))

        binding.corAdd.setOnClickListener{
            addAsync()
        }

        binding.hiloAdd.setOnClickListener{
            addHAsync()
        }

        binding.restartBtn.setOnClickListener{
            resAsync()
        }
    }

    fun addAsync() = runBlocking {
        addcor = MediaPlayer.create(this@MainActivity2,R.raw.cor)
        launch {
            addcor.start()
        }
    }

    fun addHAsync() = runBlocking {
        addhilo = MediaPlayer.create(this@MainActivity2,R.raw.addhilo)
        launch {
            addhilo.start()
        }
    }

    fun resAsync() = runBlocking {
        res = MediaPlayer.create(this@MainActivity2,R.raw.restart)
        launch {
            res.start()
        }
    }
}