package mx.tecnm.tepic.ladm_u2_tarea2_foodplanet

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import mx.tecnm.tepic.ladm_u2_tarea2_foodplanet.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var song : MediaPlayer
    lateinit var start : MediaPlayer
    lateinit var space : MediaPlayer
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        songAsync()

        binding.button.setOnClickListener {
            startAsync()
            callGame()
        }
    }

    fun songAsync() = runBlocking {
        song = MediaPlayer.create(this@MainActivity,R.raw.init)
        launch {
                song.start()
                song.isLooping = true
        }
    }

    fun startAsync() = runBlocking {
        start = MediaPlayer.create(this@MainActivity,R.raw.start)
        space = MediaPlayer.create(this@MainActivity,R.raw.obli)
        launch {
            song.stop()
            start.start()
            delay(1000L)
            space.start()
            space.isLooping = true
        }
    }


    fun callGame(){
        val game = Intent(this, MainActivity2::class.java)
        startActivity(game)
    }
}