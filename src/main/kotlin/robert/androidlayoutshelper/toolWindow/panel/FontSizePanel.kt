package robert.androidlayoutshelper.toolWindow.panel

import com.intellij.openapi.project.Project
import org.jetbrains.android.sdk.AndroidSdkUtils
import robert.androidlayoutshelper.Commands.setSystemFontScale
import java.awt.FlowLayout
import javax.swing.JButton
import javax.swing.JLabel
import javax.swing.JPanel
import javax.swing.JTextField

object FontSizePanel {
    private const val startFontSize = 0.80
    private const val endFontSize = 1.6
    private const val step = 0.2

    fun addFontSizePanel(project: Project): JPanel {
        val fontSizePanel = JPanel(FlowLayout(FlowLayout.LEFT))

        fontSizePanel.add(JLabel("Font scale"))

        var currentFontSize = startFontSize
        while (currentFontSize <= endFontSize) {
            val fontSizeText = "%.2f".format(currentFontSize).replace(",", ".")
            val button = JButton(fontSizeText)
            button.addActionListener { onFontButtonClick(project, fontSizeText) }
            fontSizePanel.add(button)
            currentFontSize += step
        }

        val textField = JTextField()
        fontSizePanel.add(textField)
        val customButton = JButton("Set custom")
        customButton.addActionListener { onFontButtonClick(project, textField.text) }
        fontSizePanel.add(customButton)

        return fontSizePanel
    }

    private fun onFontButtonClick(project: Project, fontScale: String) {
        val devices = AndroidSdkUtils.getDebugBridge(project)?.devices

        if (!devices.isNullOrEmpty()) {
            devices.forEach { device ->
                if (validateFontScaleInput(fontScale)) {
                    setSystemFontScale(device, fontScale)
                }
            }
        }
    }

    private fun validateFontScaleInput(input: String): Boolean {
        return try {
            val number = input.toDouble()
            number in 0.1..5.0
        } catch (e: NumberFormatException) {
            false
        }
    }

}