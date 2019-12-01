package com.eldarovich99.remote_assistant.presentation

import android.os.Bundle
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import com.eldarovich99.remote_assistant.di.Scopes
import com.eldarovich99.remote_assistant.domain.UserRepository
import jp.epson.moverio.H725.DisplayControl
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.Router
import toothpick.Toothpick
import javax.inject.Inject

abstract class BaseFragment : Fragment(){
    @Inject
    lateinit var userRepository: UserRepository

    @Inject
    lateinit var cicerone: Cicerone<Router>

    @Inject
    lateinit var navigator: Navigator

    @Inject
    lateinit var router: Router

    lateinit var displayControl : DisplayControl // TODO inject
   // var sensorManager : SensorManager?=null

    companion object{
        val TYPE_HEADSET_ACCELEROMETER = 0
        val TYPE_CONTROLLER_ACCELEROMETER = 1
        val TYPE_CONTROLLER_MAGNETIC_FIELD = 2
        val TYPE_CONTROLLER_GYROSCOPE = 3
        val TYPE_CONTROLLER_ROTATION_VECTOR = 4
        val TYPE_HEADSET_TAP = 5

        val TAPPED = 1f
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        Toothpick.inject(this, Toothpick.openScopes(Scopes.APP_SCOPE, Scopes.ACTIVITY_SCOPE))
        displayControl = DisplayControl(context)
        super.onCreate(savedInstanceState)
    }

    override fun onPause() {
        cicerone.navigatorHolder.removeNavigator()
        super.onPause()
    }

    override fun onResume() {
        cicerone.navigatorHolder.setNavigator(navigator)

        /*sensorManager = context?.getSystemService(SENSOR_SERVICE) as SensorManager
        if (sensorManager != null) {
            val tap = sensorManager?.getDefaultSensor(TYPE_HEADSET_TAP)
            sensorManager?.registerListener(object : SensorEventListener{
                override fun onAccuracyChanged(p0: Sensor?, p1: Int) {

                }

                override fun onSensorChanged(event: SensorEvent?) {
                    if (event?.sensor?.type == TYPE_HEADSET_TAP) {
                        when (event.values[0]){
                            TAPPED -> TODO()
                        }
                    }
                }

            }, tap, SensorManager.SENSOR_DELAY_NORMAL)
        }*/
        
        super.onResume()
    }

    abstract fun dispatchKeyEvent(event: KeyEvent?)
}