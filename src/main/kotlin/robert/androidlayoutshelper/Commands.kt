package robert.androidlayoutshelper

import com.android.ddmlib.IDevice
import com.android.ddmlib.NullOutputReceiver

object Commands {
    fun layoutBoundsOn(device: IDevice) {
        device.executeShellCommand("setprop debug.layout true ; service call activity 1599295570", NullOutputReceiver())
    }

    fun layoutBoundsOff(device: IDevice) {
        device.executeShellCommand("setprop debug.layout false ; service call activity 1599295570", NullOutputReceiver())
    }

    fun darkModeOn(device: IDevice) {
        device.executeShellCommand("cmd uimode night yes", NullOutputReceiver())
    }

    fun darkModeOff(device: IDevice) {
        device.executeShellCommand("cmd uimode night no", NullOutputReceiver())
    }

    fun setSystemFontScale(device: IDevice, scale: String) {
        device.executeShellCommand("settings put system font_scale $scale", NullOutputReceiver())
    }

    fun setSystemDisplaySize(device: IDevice, displaySize: String) {
        device.executeShellCommand("wm size $displaySize", NullOutputReceiver())
    }

    fun setWindowAnimationScale(device: IDevice, animationScale: String) {
        device.executeShellCommand(
            "settings put global window_animation_scale $animationScale",
            NullOutputReceiver()
        )
    }

    fun setTransitionAnimationScale(device: IDevice, animationScale: String) {
        device.executeShellCommand(
            "settings put global transition_animation_scale $animationScale",
            NullOutputReceiver()
        )
    }

    fun setAnimatorDurationScale(device: IDevice, durationScale: String) {
        device.executeShellCommand(
            "settings put global animator_duration_scale $durationScale",
            NullOutputReceiver()
        )
    }

    fun hwuiRenderingBarsOn(device: IDevice) {
        device.executeShellCommand(
            "setprop debug.hwui.profile visual_bars ; service call activity 1599295570",
            NullOutputReceiver()
        )
    }

    fun hwuiRenderingBarsOff(device: IDevice) {
        device.executeShellCommand(
            "setprop debug.hwui.profile off ; service call activity 1599295570",
            NullOutputReceiver()
        )
    }

    fun profileGPURenderingOff(device: IDevice) {
        device.executeShellCommand(
            "setprop debug.hwui.overdraw off ; service call activity 1599295570",
            NullOutputReceiver()
        )
    }

    fun profileGPURenderingOn(device: IDevice) {
        device.executeShellCommand(
            "setprop debug.hwui.overdraw show ; service call activity 1599295570",
            NullOutputReceiver()
        )
    }
}