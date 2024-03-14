package robert.androidlayoutshelper.toolWindow.panel

import com.intellij.openapi.project.Project
import org.jetbrains.android.sdk.AndroidSdkUtils
import robert.androidlayoutshelper.Commands.setSystemDisplaySize
import java.awt.FlowLayout
import javax.swing.JButton
import javax.swing.JLabel
import javax.swing.JPanel
import javax.swing.JTextField

object DisplaySizePanel {
    fun addDisplaySizePanel(project: Project): JPanel {
        val displaySizePanel = JPanel(FlowLayout(FlowLayout.LEFT))
        displaySizePanel.add(JLabel("Display size "))

        val buttonList = listOf(
            JButton("reset"),
            JButton("480x800"),
            JButton("720x1280"),
            JButton("1080x1920"),
            JButton("1440x3200")
        )

        buttonList.forEach { button ->
            button.addActionListener { onDisplaySizeButtonClick(project, button.text) }
            displaySizePanel.add(button)
        }

        val textField = JTextField(9)
        textField.toolTipText = "example: 1080x1920"
        displaySizePanel.add(textField)
        val customButton = JButton("Set custom")
        customButton.addActionListener { onDisplaySizeButtonClick(project, textField.text) }
        displaySizePanel.add(customButton)

        return displaySizePanel
    }

    private fun validateDisplaySizeInput(input: String): Boolean {
        return input == "reset" || Regex("^\\d{1,4}x\\d{1,4}$").matches(input)
    }

    private fun onDisplaySizeButtonClick(project: Project, displaySize: String) {
        val devices = AndroidSdkUtils.getDebugBridge(project)?.devices

        if (!devices.isNullOrEmpty()) {
            devices.forEach { device ->
                try {
                    if (validateDisplaySizeInput(displaySize)) {
                        setSystemDisplaySize(device, displaySize)
                    }
                } catch (_: Exception) {
                }
            }
        }
    }
}