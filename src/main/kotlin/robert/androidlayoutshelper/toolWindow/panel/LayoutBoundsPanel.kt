package robert.androidlayoutshelper.toolWindow.panel

import com.intellij.openapi.project.Project
import org.jetbrains.android.sdk.AndroidSdkUtils
import robert.androidlayoutshelper.Commands
import java.awt.FlowLayout
import javax.swing.JButton
import javax.swing.JLabel
import javax.swing.JPanel

object LayoutBoundsPanel {
    fun addLayoutBoundsPanel(project: Project): JPanel {
        val layoutBoundsPanel = JPanel(FlowLayout(FlowLayout.LEFT))
        layoutBoundsPanel.add(JLabel("Show layout bounds "))

        val onButton = JButton("on")
        onButton.addActionListener { onButtonClick(project, true) }
        layoutBoundsPanel.add(onButton)

        val offButton = JButton("off")
        offButton.addActionListener { onButtonClick(project, false) }
        layoutBoundsPanel.add(offButton)

        return layoutBoundsPanel
    }

    private fun onButtonClick(project: Project, show: Boolean) {
        val devices = AndroidSdkUtils.getDebugBridge(project)?.devices

        if (!devices.isNullOrEmpty()) {
            devices.forEach { device ->
                try {
                    if (show) {
                        Commands.layoutBoundsOn(device)
                    } else {
                        Commands.layoutBoundsOff(device)
                    }
                } catch (_: Exception) {
                }
            }
        }
    }
}