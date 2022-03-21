package mx.tecnm.tepic.ladm_u2_tarea2_foodplanet

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.media.MediaPlayer
import android.view.MotionEvent
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.random.Random

class Food(l:Lienzo,cor:Boolean,context: Context) {
    val l = l
    var x = 0f
    var y = 0f
    var desY = 0f
    var color = Color.WHITE
    var context = context
    var cor = cor
    var hilo = BitmapFactory.decodeResource(context.resources, R.drawable.hil)
    var corutina = BitmapFactory.decodeResource(context.resources, R.drawable.cor)
    lateinit var sonHil : MediaPlayer
    lateinit var sonCor : MediaPlayer

    init {
        x = rand(0,1000)
        y = rand(0,1900)
        desY = rand(1,3)+1
    }

    private fun rand(desde:Int,hasta:Int) : Float{
        return Random.nextInt(desde,hasta).toFloat()
    }

    fun decendre(){
        if(y>l.height){
            y = 0f
        }
        y += desY
    }


    fun pintar(canvas: Canvas){
        var p = Paint()
        p.color = color
        if(cor) canvas.drawBitmap(corutina,x,y,p)
        else canvas.drawBitmap(hilo,x,y,p)
    }
}