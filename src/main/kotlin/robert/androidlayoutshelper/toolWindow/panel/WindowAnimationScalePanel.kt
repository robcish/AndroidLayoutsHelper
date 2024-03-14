package robert.androidlayoutshelper.toolWindow.panel

import com.intellij.openapi.project.Project
import org.jetbrains.android.sdk.AndroidSdkUtils
import robert.androidlayoutshelper.Commands.setWindowAnimationScale
import java.awt.FlowLayout
import javax.swing.JButton
import javax.swing.JLabel
import javax.swing.JPanel
import javax.swing.JTextField

object WindowAnimationScalePanel {
    fun addWindowAnimationScalePanel(project: Project): JPanel {
        val windowAnimationScalePanel = JPanel(FlowLayout(FlowLayout.LEFT))
        windowAnimationScalePanel.add(JLabel("Window animation scale "))

        val buttonList = listOf(
            JButton("0"),
            JButton("0.2"),
            JButton("0.5"),
            JButton("0.75"),
            JButton("1"),
            JButton("2"),
            JButton("5")
        )

        buttonList.forEach { button ->
            button.addActionListener { onWindowAnimationScaleButtonClick(project, button.text) }
            windowAnimationScalePanel.add(button)
        }

        val textField = JTextField(5)
        textField.toolTipText = "example: 0.5, max = 100"
        windowAnimationScalePanel.add(textField)
        val customButton = JButton("Set custom")
        customButton.addActionListener { onWindowAnimationScaleButtonClick(project, textField.text) }
        windowAnimationScalePanel.add(customButton)

        return windowAnimationScalePanel
    }

    private fun validateInput(input: String): Boolean {
        return try {
            val number = input.toDouble()
            number in 0.0..100.0
        } catch (e: NumberFormatException) {
            false
        }
    }

    private fun onWindowAnimationScaleButtonClick(project: Project, animationScale: String) {
        val devices = AndroidSdkUtils.getDebugBridge(project)?.devices

        if (!devices.isNullOrEmpty()) {
            devices.forEach { device ->
                try {
                    if (validateInput(animationScale)) {
                        setWindowAnimationScale(device, animationScale)
                    }
                } catch (_: Exception) {
                }
            }
        }
    }
}