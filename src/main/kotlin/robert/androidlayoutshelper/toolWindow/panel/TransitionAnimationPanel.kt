package robert.androidlayoutshelper.toolWindow.panel

import com.intellij.openapi.project.Project
import org.jetbrains.android.sdk.AndroidSdkUtils
import robert.androidlayoutshelper.Commands.setTransitionAnimationScale
import java.awt.FlowLayout
import javax.swing.JButton
import javax.swing.JLabel
import javax.swing.JPanel
import javax.swing.JTextField

object TransitionAnimationPanel {
    fun addTransitionAnimationScalePanel(project: Project): JPanel {
        val transitionAnimationScalePanel = JPanel(FlowLayout(FlowLayout.LEFT))
        transitionAnimationScalePanel.add(JLabel("Transition animation scale "))

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
            button.addActionListener { onTransitionAnimationScaleButtonClick(project, button.text) }
            transitionAnimationScalePanel.add(button)
        }

        val textField = JTextField(5)
        textField.toolTipText = "example: 0.5, max = 100"
        transitionAnimationScalePanel.add(textField)
        val customButton = JButton("Set custom")
        customButton.addActionListener { onTransitionAnimationScaleButtonClick(project, textField.text) }
        transitionAnimationScalePanel.add(customButton)

        return transitionAnimationScalePanel
    }

    private fun validateInput(input: String): Boolean {
        return try {
            val number = input.toDouble()
            number in 0.0..100.0
        } catch (e: NumberFormatException) {
            false
        }
    }

    private fun onTransitionAnimationScaleButtonClick(project: Project, animationScale: String) {
        val devices = AndroidSdkUtils.getDebugBridge(project)?.devices

        if (!devices.isNullOrEmpty()) {
            devices.forEach { device ->
                try {
                    if (validateInput(animationScale)) {
                        setTransitionAnimationScale(device, animationScale)
                    }
                } catch (_: Exception) {
                }
            }
        }
    }
}