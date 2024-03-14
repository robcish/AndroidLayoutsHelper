package robert.androidlayoutshelper.toolWindow.panel

import com.intellij.openapi.project.Project
import org.jetbrains.android.sdk.AndroidSdkUtils
import robert.androidlayoutshelper.Commands
import java.awt.FlowLayout
import javax.swing.JButton
import javax.swing.JLabel
import javax.swing.JPanel

object HwuiRenderingBarsPanel {
    fun addHwuiRenderingBarsPanel(project: Project): JPanel {
        val renderingBarsPanel = JPanel(FlowLayout(FlowLayout.LEFT))
        renderingBarsPanel.add(JLabel("HWUI Rendering Bars "))

        val onButton = JButton("on")
        onButton.addActionListener { onButtonClick(project, true) }
        renderingBarsPanel.add(onButton)

        val offButton = JButton("off")
        offButton.addActionListener { onButtonClick(project, false) }
        renderingBarsPanel.add(offButton)

        return renderingBarsPanel
    }

    private fun onButtonClick(project: Project, show: Boolean) {
        val devices = AndroidSdkUtils.getDebugBridge(project)?.devices

        if (!devices.isNullOrEmpty()) {
            devices.forEach { device ->
                try {
                    if (show) {
                        Commands.hwuiRenderingBarsOn(device)
                    } else {
                        Commands.hwuiRenderingBarsOff(device)
                    }
                } catch (_: Exception) {
                }
            }
        }
    }
}