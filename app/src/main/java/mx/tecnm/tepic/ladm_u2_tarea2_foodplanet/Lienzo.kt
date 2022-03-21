package mx.tecnm.tepic.ladm_u2_tarea2_foodplanet

import android.graphics.*
import android.media.MediaPlayer
import android.view.MotionEvent
import android.view.View
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.random.Random

class Lienzo(ce:MainActivity2) : View(ce) {
    var ce = ce
    var conta = 0
    var image = BitmapFactory.decodeResource(this.resources, R.drawable.playing)
    var addH = BitmapFactory.decodeResource(this.resources, R.drawable.hilo)
    var addC = BitmapFactory.decodeResource(this.resources, R.drawable.coroutine)
    var reset = BitmapFactory.decodeResource(this.resources, R.drawable.quit)

    val hilo = HiloAdmin(this,ce)
    lateinit var addcor : MediaPlayer
    lateinit var res : MediaPlayer
    lateinit var addhilo : MediaPlayer

    var Coroutines = Array<Food>(10,{Food(this,true,ce)})

    val corutina = GlobalScope.launch {
        while (true) {
            ce.runOnUiThread {
                invalidate()
            }
            delay(20L)
        }
    }

    override fun onDraw(c: Canvas) {
        super.onDraw(c)
        val p = Paint()
        val src = Rect(0,0,image.width-1,image.height-1)
        val dest = Rect(0,0,this.width,this.height)

        c.drawBitmap(image,src,dest,p)
        val srcH = Rect(0,0,addH.width-1,addH.height-1)
        val destH = Rect(30,20,165,145)
        c.drawBitmap(addH,srcH,destH,p)
        val srcC = Rect(0,0,addC.width-1,addC.height-1)
        val destC = Rect(375,20,515,145)
        c.drawBitmap(addC,srcC,destC,p)
        val srcR = Rect(0,0,reset.width-1,reset.height-1)
        val destR = Rect(730,20,865,145)
        c.drawBitmap(reset,srcR,destR,p)

        hilo.pintar(c)
        for (cc in Coroutines) {
            cc.decendre()
            cc.pintar(c)
        }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                if (event.y <= 145 && event.x <= 165) {
                    addAsync()
                    hilo.start()
                }
                if (event.y <= 145 && event.x >=375 && event.x <= 515) {
                    addHAsync()
                    corutina.start()
                }
                if (event.y <= 145 && event.x >=730 && event.x <= 865) {
                    conta = 0
                    resAsync()
                    hilo.terminarHilo()
                }
            }
            MotionEvent.ACTION_MOVE -> {

            }
            MotionEvent.ACTION_UP -> {

            }
        }
        invalidate()
        return true
    }

    fun addAsync() = runBlocking {
        addcor = MediaPlayer.create(context,R.raw.cor)
        launch {
            addcor.start()
        }
    }

    fun addHAsync() = runBlocking {
        addhilo = MediaPlayer.create(context,R.raw.addhilo)
        launch {
            addhilo.start()
        }
    }

    fun resAsync() = runBlocking {
        res = MediaPlayer.create(context,R.raw.restart)
        launch {
            res.start()
        }
    }
}