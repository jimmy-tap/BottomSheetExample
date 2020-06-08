package jimmy.tap.bottomsheetexample

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import company.tap.tapuilibrary.DialogConfigurations
import company.tap.tapuilibrary.TapBottomDialogInterface

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun showBottomSheet(view: View) {
        val bottomSheet = SampleBottomSheetFragment()
        bottomSheet.arguments = getArguments()
        bottomSheet.show(supportFragmentManager, null)
    }

    private fun getArguments(): Bundle {
        val arguments = Bundle()
        arguments.putFloatArray(DialogConfigurations.Corners, floatArrayOf(50f, 50f, 0f, 0f))
        arguments.putInt(DialogConfigurations.Color, Color.WHITE)
        arguments.putBoolean(DialogConfigurations.Cancelable, true)
        arguments.putFloat(DialogConfigurations.Dim, 0.75f)
        return arguments
    }

}
