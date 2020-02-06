package cz.jirix.simplelistapp.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import cz.jirix.simplelistapp.R
import kotlinx.android.synthetic.main.loading_button.view.*

/**
 * A button with a built in progressBar / loader
 */
class LoadingButton : FrameLayout {
    private var isLoading = false
    private var onClickListener: OnClickListener? = null

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init()
    }

    constructor(
        context: Context,
        attrs: AttributeSet,
        defStyle: Int
    ) : super(context, attrs, defStyle) {
        init()
    }

    private fun init() {
        LayoutInflater.from(context).inflate(R.layout.loading_button, this, true)
        isLoading = false
        loading_btn?.setOnClickListener { view ->
            if (!isLoading) {
                onClickListener?.onClick(view)
            }
        }
    }

    override fun setOnClickListener(listener: OnClickListener) {
        onClickListener = listener
    }

    fun showProgress(show: Boolean) {
        isLoading = show
        loading_btn?.visibility = if (show) View.GONE else View.VISIBLE
        loading_progress.visibility = if (show) View.VISIBLE else View.GONE
    }
}
