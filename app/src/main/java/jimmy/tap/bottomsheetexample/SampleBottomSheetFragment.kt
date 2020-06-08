package jimmy.tap.bottomsheetexample

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.transition.Transition
import androidx.transition.TransitionInflater
import androidx.transition.TransitionManager
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.sample_bottom_sheet.*

/**
 *
 * Created by Mario Gamal on 6/7/20
 * Copyright © 2020 Tap Payments. All rights reserved.
 *
 */
class SampleBottomSheetFragment : BottomSheetDialogFragment() {

    private var isFragmentAdded = false
    private var bottomSheetLayout: FrameLayout? = null
    private lateinit var bottomSheetDialog: BottomSheetDialog

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.sample_bottom_sheet, container, false)

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        bottomSheetDialog = super.onCreateDialog(savedInstanceState) as BottomSheetDialog
        bottomSheetDialog.setOnShowListener {
            bottomSheetLayout =
                bottomSheetDialog.findViewById(com.google.android.material.R.id.design_bottom_sheet)
        }
        return bottomSheetDialog
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bottomSheetDialog.behavior.state = BottomSheetBehavior.STATE_EXPANDED
        initFragmentTransition()
    }

    private fun initFragmentTransition() {
        val fragment = PlaceHolderFragment()

        add_remove_fragment.setOnClickListener {

            if (isFragmentAdded) {
                bottomSheetLayout?.let { layout ->
                    val removeTransition: Transition =
                        TransitionInflater.from(context)
                            .inflateTransition(R.transition.remove_fragment)
                    TransitionManager.beginDelayedTransition(layout, removeTransition)
                }

                childFragmentManager
                    .beginTransaction()
                    .remove(fragment)
                    .commit()

            } else {
                bottomSheetLayout?.let { layout ->
                    layout.post {
                        val addTransition: Transition =
                            TransitionInflater.from(context)
                                .inflateTransition(R.transition.add_fragment)
                        TransitionManager.beginDelayedTransition(layout, addTransition)
                    }
                }
                childFragmentManager
                    .beginTransaction()
                    .add(R.id.fragment_container, fragment)
                    .commit()
            }
            isFragmentAdded = !isFragmentAdded
        }
    }

}