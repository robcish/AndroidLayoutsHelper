package robert.androidlayoutshelper.toolWindow.panel

import com.intellij.openapi.project.Project
import org.jetbrains.android.sdk.AndroidSdkUtils
import robert.androidlayoutshelper.Commands.setAnimatorDurationScale
import java.awt.FlowLayout
import javax.swing.JButton
import javax.swing.JLabel
import javax.swing.JPanel
import javax.swing.JTextField

object AnimatorDurationPanel {
    fun addAnimatorDurationPanel(project: Project): JPanel {
        val animatorDurationPanel = JPanel(FlowLayout(FlowLayout.LEFT))
        animatorDurationPanel.add(JLabel("Animator duration "))

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
            button.addActionListener { onAnimatorDurationScaleButtonClick(project, button.text) }
            animatorDurationPanel.add(button)
        }

        val textField = JTextField(5)
        textField.toolTipText = "example: 0.5, max = 100"
        animatorDurationPanel.add(textField)
        val customButton = JButton("Set custom")
        customButton.addActionListener { onAnimatorDurationScaleButtonClick(project, textField.text) }
        animatorDurationPanel.add(customButton)

        return animatorDurationPanel
    }

    private fun validateInput(input: String): Boolean {
        return try {
            val number = input.toDouble()
            number in 0.0..100.0
        } catch (e: NumberFormatException) {
            false
        }
    }

    private fun onAnimatorDurationScaleButtonClick(project: Project, animationScale: String) {
        val devices = AndroidSdkUtils.getDebugBridge(project)?.devices

        if (!devices.isNullOrEmpty()) {
            devices.forEach { device ->
                try {
                    if (validateInput(animationScale)) {
                        setAnimatorDurationScale(device, animationScale)
                    }
                } catch (_: Exception) {
                }
            }
        }
    }
}