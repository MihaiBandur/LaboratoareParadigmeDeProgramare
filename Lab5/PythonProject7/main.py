import  sys
import os
import  random
from datetime import datetime
from PyQt5.QtWidgets import QApplication, QWidget, QVBoxLayout, QPushButton, QTextEdit, QLabel, QFileDialog, QMessageBox, QInputDialog

CITATE_FILE = "citate.txt"
JURNAL_FOLDER = "jurnal_entries"

if not os.path.exists(JURNAL_FOLDER):
    os.makedirs(JURNAL_FOLDER)

def get_random_quote():
    try:
        with open(CITATE_FILE, "r", encoding="utf-8") as f:
             citate = [line.strip() for line in f if line.strip()]
        return random.choice(citate) if citate else "Niciun citat disponibil."
    except FileNotFoundError:
        return "Fisierul cu citate nu exista"


class JournalApp(QWidget):
    def __init__(self):
        super().__init__()
        self.btn_add_quote = None
        self.btn_save = None
        self.btn_load = None
        self.btn_load_quote = None
        self.text_edit = None
        self.quote_label = None
        self.initUI()

    def initUI(self):
        self.setWindowTitle("Jurnal Personal")
        self.setGeometry(100, 100, 600, 400)

        layout = QVBoxLayout ()

        self.quote_label = QLabel(get_random_quote())
        layout.addWidget(self.quote_label)

        self.text_edit = QTextEdit()
        layout.addWidget(self.text_edit)

        self.btn_load = QPushButton("Load Entry")
        self.btn_load.clicked.connect(self.load_entry)
        layout.addWidget(self.btn_load)

        self.btn_save = QPushButton("Save Entry")
        self.btn_save.clicked.connect(self.save_entry)
        layout.addWidget(self.btn_save)

        self.btn_add_quote = QPushButton("Add Quote")
        self.btn_add_quote.clicked.connect(self.add_quote)
        layout.addWidget(self.btn_add_quote)

        self.setLayout(layout)

    def save_entry(self):
        text = self.text_edit.toPlainText().strip()
        if not text:
            QMessageBox.warning(self, "Eroare", "Nu exista text de salvat!")
            return

        filename = os.path.join(JURNAL_FOLDER, f"entry_{datetime.now().strftime('%Y%m%d_%H%M%S')}.txt")
        with open(filename, "w", encoding="utf-8") as f:
                f.write(text)
        QMessageBox.information(self, "Succes", "Intrarea a fost salvata!")
        self.text_edit.clear()

    def load_entry(self):
        file_path, _ = QFileDialog.getOpenFileName(self, "Selectează o intrare", JURNAL_FOLDER, "Text Files (*.txt)")
        if file_path:
            with open(file_path, "r", encoding="utf-8") as f:
                self.text_edit.setText(f.read())

    def add_quote(self):
        new_quote, ok = QFileDialog.getText(self, "Adauga Citat", "Introduceti noul citat:")
        if ok and new_quote.strip():
            with open(CITATE_FILE, "a", encoding="utf-8") as f:
                f.write(new_quote.strip() + "\n")
            QMessageBox.information(self, "Succes", "Citatul a fost adaugat!")
            self.quote_label.setText(get_random_quote())

if __name__ == "__main__":
    app = QApplication(sys.argv)
    window = JournalApp()
    window.show()
    sys.exit(app.exec_())







