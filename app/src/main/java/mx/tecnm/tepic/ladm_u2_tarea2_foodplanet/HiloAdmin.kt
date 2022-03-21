package mx.tecnm.tepic.ladm_u2_tarea2_foodplanet

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.widget.TextView
import kotlinx.coroutines.delay

class HiloAdmin(l :Lienzo, ce : MainActivity2) : Thread() {
    private var ejecutar = true
    var l = l
    var ce = ce
    var Hilos = Array<Food>(30,{Food(l,false,ce)})
    override fun run() {
        while (ejecutar) {
            l.invalidate()
            sleep(20)
        }
    }

    fun terminarHilo() {
        ejecutar = false
    }

    fun pintar(c: Canvas){
        val c = c
        for (cc in Hilos) {
            var p = Paint()
            cc.decendre()
            cc.pintar(c)
        }
    }

}