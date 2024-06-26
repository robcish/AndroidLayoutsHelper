@file:Suppress("SpellCheckingInspection")

package robert.androidlayoutshelper.shortcutAction

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import org.jetbrains.android.sdk.AndroidSdkUtils
import robert.androidlayoutshelper.Commands.layoutBoundsOff
import robert.androidlayoutshelper.Commands.layoutBoundsOn
import robert.androidlayoutshelper.SingleLineReceiver

class LayoutBounds : AnAction() {
    override fun actionPerformed(event: AnActionEvent) {
        val devices = event.project?.let { project ->
            AndroidSdkUtils.getDebugBridge(project)?.devices
        }

        devices?.forEach { device ->
            device.executeShellCommand("getprop debug.layout", SingleLineReceiver { firstLine ->
                if (firstLine.toBoolean()) {
                    layoutBoundsOn(device)
                } else {
                    layoutBoundsOff(device)
                }
            })
        }
    }
}