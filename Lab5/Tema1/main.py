import sys
import os
import ctypes
import sysv_ipc
from PyQt5.QtWidgets import QApplication, QWidget, QVBoxLayout, QHBoxLayout, QLineEdit, QPushButton, QFileDialog, QMessageBox, QTextEdit

def convert_to_html(file_path):
    try:
        with open(file_path, 'r', encoding='utf-8') as file:
            lines = file.readlines()

        if not lines:
            return None

        title = lines[0].strip() if lines else "Untitled"
        paragraphs = [f'<p>{line.strip()}</p>' for line in lines[1:] if line.strip()]

        html_content = f"""
        <html>
        <head><title>{title}</title></head>
        <body>
        <h1>{title}</h1>
        {''.join(paragraphs)}
        </body>
        </html>
        """

        output_file = os.path.splitext(file_path)[0] + ".html"
        with open(output_file, 'w', encoding='utf-8') as file:
            file.write(html_content)

        return html_content, output_file
    except Exception as e:
        return str(e), None


def send_to_c_program(message):
    try:
        key = sysv_ipc.ftok("message_queue_name", ord('B'))
        message_queue = sysv_ipc.MessageQueue(key, sysv_ipc.IPC_CREAT)
        message_queue.send(message.encode(), True,1)
        QMessageBox.information(None,"Succes", "Mesajul a fost trimis cu succes catre programul c")
    except Exception as e:
        QMessageBox.warning(None, "Eroare", f"Eșec la trimiterea mesajului: {e}")


class HTMLConverter(QWidget):
    def __init__(self):
        super().__init__()
        self.send_button = None
        self.upload_button = None
        self.button_layout = None
        self.html_output = None
        self.html_layout = None
        self.layout = None
        self.browse_button = None
        self.input_layout = None
        self.text_input = None
        self.InitUI()

    def InitUI(self):
        self.setWindowTitle("HTML Converter")
        self.setGeometry(100, 100, 500, 300)

        self.layout = QVBoxLayout()

        self.input_layout = QHBoxLayout()

        self.text_input = QLineEdit(self)
        self.text_input.setPlaceholderText("/path/to/a/text/file")
        self.input_layout.addWidget(self.text_input)

        self.browse_button = QPushButton("Browse", self)
        self.browse_button.clicked.connect(self.browse_file)
        self.input_layout.addWidget(self.browse_button)

        self.layout.addLayout(self.input_layout)

        self.html_output = QTextEdit(self)
        self.html_output.setPlaceholderText("HTML result")
        self.html_output.setReadOnly(True)
        self.layout.addWidget(self.html_output)

        self.button_layout = QHBoxLayout()

        self.upload_button = QPushButton("Convert to HTML", self)
        self.upload_button.clicked.connect(self.convert_file)
        self.button_layout.addWidget(self.upload_button)

        self.send_button = QPushButton('Send to C program', self)
        self.send_button.clicked.connect(self.send_message)
        self.button_layout.addWidget(self.send_button)

        self.layout.addLayout(self.button_layout)

        self.setLayout(self.layout)

    def browse_file(self):
        options = QFileDialog.Options()
        file_name, _ = QFileDialog.getOpenFileName(self, "Select a text file", "", "Text Files (*.txt);;All Files (*)", options=options)
        if file_name:
            self.text_input.setText(file_name)

    def convert_file(self):
        file_path = self.text_input.text().strip()
        if not os.path.isfile(file_path):
            QMessageBox.warning(self, "Error", "Fișierul specificat nu există!")
            return

        result, output_file = convert_to_html(file_path)
        if output_file:
            self.html_output.setText(result)
            QMessageBox.information(self, "Succes", f"Fișierul HTML a fost salvat: {output_file}")
        else:
            QMessageBox.warning(self, "Eroare", f"Conversia a eșuat: {result}")

    def send_message(self):
        message = self.html_output.toPlainText().strip()
        if not message:
            QMessageBox.warning(self, "Eroare", "Nu există conținut HTML de trimis!")
            return
        send_to_c_program(message)


if __name__ == '__main__':
    app = QApplication(sys.argv)
    window = HTMLConverter()
    window.show()
    sys.exit(app.exec_())
