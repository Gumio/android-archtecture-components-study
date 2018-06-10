package com.gumio_inf.aacsampleapp.ui.activity

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.OnLifecycleEvent
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.gumio_inf.aacsampleapp.R
import kotlinx.android.synthetic.main.activity_sensor.*

class SensorActivity : AppCompatActivity() {
    private lateinit var mSensorHandler: SensorHandler

    inner class SensorHandler(private val mSensorManager: SensorManager): SensorEventListener, LifecycleObserver {
        private var mAccelerometer: Sensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)

        override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        }

        override fun onSensorChanged(event: SensorEvent?) {
            if (event?.sensor?.type == Sensor.TYPE_ACCELEROMETER) {
                val sensorX = event.values[0]
                val sensorY = event.values[1]
                val sensorZ = event.values[2]

                val strTmp = "加速度センサー\n" + " X: " + sensorX + "\n" + " Y: " + sensorY + "\n" + " Z: " + sensorZ;
                Log.d("onSensorChanged", strTmp)
                acceleText.text = strTmp
            }
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
        fun register() {
            Log.d("SensorActivity", "登録しました")
            mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL)
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
        fun unregister() {
            Log.d("SensorActivity", "解除しました")
            mSensorManager.unregisterListener(this)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sensor)

        mSensorHandler = SensorHandler(getSystemService(SENSOR_SERVICE) as SensorManager)
        lifecycle.addObserver(mSensorHandler)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("SensorActivity", "Lifecycleから解除しました")
        lifecycle.removeObserver(mSensorHandler)
    }
}
