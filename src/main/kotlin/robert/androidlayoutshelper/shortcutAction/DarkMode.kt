@file:Suppress("UnstableApiUsage", "DialogTitleCapitalization", "SpellCheckingInspection")

package robert.androidlayoutshelper.shortcutAction

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import org.jetbrains.android.sdk.AndroidSdkUtils
import robert.androidlayoutshelper.Commands.darkModeOff
import robert.androidlayoutshelper.Commands.darkModeOn
import robert.androidlayoutshelper.SingleLineReceiver

class DarkMode : AnAction() {

    override fun actionPerformed(event: AnActionEvent) {
        val devices = event.project?.let { project ->
            AndroidSdkUtils.getDebugBridge(project)?.devices
        }

        devices?.forEach { device ->
            device.executeShellCommand("cmd uimode night", SingleLineReceiver { firstLine ->
                if (firstLine == "Night mode: no") {
                    darkModeOn(device)
                } else {
                    darkModeOff(device)
                }
            })
        }
    }
}