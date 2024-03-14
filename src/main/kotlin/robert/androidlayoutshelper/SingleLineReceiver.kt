package robert.androidlayoutshelper

import com.android.ddmlib.MultiLineReceiver

class SingleLineReceiver(val processFirstLine: (response: String) -> Unit) : MultiLineReceiver() {
    private var cancelled = false

    override fun isCancelled(): Boolean = cancelled

    override fun processNewLines(lines: Array<out String>?) {
        lines?.getOrNull(0)?.let { firstLine ->
            processFirstLine(firstLine)
            cancelled = true
        }
    }
}