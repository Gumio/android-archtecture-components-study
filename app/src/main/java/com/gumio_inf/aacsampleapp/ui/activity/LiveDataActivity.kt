package com.gumio_inf.aacsampleapp.ui.activity

import android.arch.lifecycle.*
import android.databinding.DataBindingUtil
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.gumio_inf.aacsampleapp.model.ClockViewModel
import com.gumio_inf.aacsampleapp.R
import com.gumio_inf.aacsampleapp.databinding.ActivityLiveDataBinding
import kotlinx.android.synthetic.main.activity_sensor.*


class LiveDataActivity : AppCompatActivity() {

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

    // ViewModelを使うことでActivityからはLiveDataを隠蔽する
    // lateinit var mClockLiveData: ClockLiveData
    private val mBinding: ActivityLiveDataBinding by lazy {
        DataBindingUtil.setContentView<ActivityLiveDataBinding>(this, R.layout.activity_live_data)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val clockViewModel = ViewModelProviders.of(this).get(ClockViewModel::class.java)
        mBinding.setLifecycleOwner(this)
        mBinding.clockModel = clockViewModel
            // こんな書き方もあるよ程度に
            // clock_text.text = String.format("%02d:%02d", get(Calendar.HOUR_OF_DAY), get(Calendar.MINUTE))

        // viewmodelのとき、画面回転しても値が変わらない
        val dateObserver = Observer<String> {
            mBinding.clockText.text = mBinding.clockModel?.let { it.strTime.value }
        }

        val observer = object : LifecycleObserver {
            @OnLifecycleEvent(Lifecycle.Event.ON_START)
            fun calledWhenOnStart(source: LifecycleOwner) {
                // foreverなら、ライフサイクルにとらわれずに通知を受け取るっぽい
                mBinding.clockModel?.strTime?.observe(source, dateObserver)
            }
            @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
            fun calledWhenOnStop(source: LifecycleOwner) {
                mBinding.clockModel?.strTime?.removeObservers { lifecycle }
            }
        }
        mSensorHandler = SensorHandler(getSystemService(SENSOR_SERVICE) as SensorManager)
        lifecycle.addObserver(mSensorHandler)
        lifecycle.addObserver(observer)
    }

    override fun onDestroy() {
        super.onDestroy()
        lifecycle.removeObserver(mSensorHandler)
    }
}
