import sys
import sqlite3
import random
from datetime import datetime
from PyQt5.QtWidgets import QApplication, QWidget, QVBoxLayout, QPushButton, QTextEdit, QLabel, QFileDialog, QMessageBox, QInputDialog

citate_db = "citate.db"
jurnal_db = "jurnal.db"

def init_databases():
    conn = sqlite3.connect(citate_db)
    cursor = conn.cursor()
    cursor.execute("""
        CREATE TABLE IF NOT EXISTS citate (
            id INTEGER PRIMARY KEY AUTOINCREMENT,
            text TEXT NOT NULL
        )
    """)
    conn.commit()
    conn.close()

    conn = sqlite3.connect(jurnal_db)
    cursor = conn.cursor()
    cursor.execute("""
        CREATE TABLE IF NOT EXISTS jurnal (
            id INTEGER PRIMARY KEY AUTOINCREMENT,
            data TEXT NOT NULL,
            continut TEXT NOT NULL
        )
    """)
    conn.commit()
    conn.close()

init_databases()

def get_random_quote():
    conn = sqlite3.connect(citate_db)
    cursor = conn.cursor()
    cursor.execute("SELECT text FROM citate")
    citate = [row[0] for row in cursor.fetchall()]
    conn.close()
    return random.choice(citate) if citate else "Niciun citat disponibil."

class JournalApp(QWidget):
    def __init__(self):
        super().__init__()
        self.initUI()

    def initUI(self):
        self.setWindowTitle("Jurnal Personal")
        self.setGeometry(100, 100, 600, 400)

        layout = QVBoxLayout()

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

        conn = sqlite3.connect(jurnal_db)  # Fixed: Use jurnal_db instead of citate_db
        cursor = conn.cursor()
        cursor.execute("INSERT INTO jurnal (data, continut) VALUES (?, ?)",
                       (datetime.now().strftime('%Y-%m-%d %H:%M:%S'), text))
        conn.commit()
        conn.close()

        QMessageBox.information(self, "Succes", "Intrarea a fost adaugata!")
        self.text_edit.clear()

    def load_entry(self):
        conn = sqlite3.connect(jurnal_db)
        cursor = conn.cursor()
        cursor.execute("SELECT id, data FROM jurnal ORDER BY id DESC")
        entries = cursor.fetchall()
        conn.close()

        if not entries:
            QMessageBox.warning(self, "Eroare", "Nu exista intrari in jurnal!")
            return

        items = [f"{entry[0]} - {entry[1]}" for entry in entries]
        selected, ok = QInputDialog.getItem(self, "Selectează o intrare", "Alege o intrare:", items, 0, False)

        if ok and selected:
            entry_id = int(selected.split(" - ")[0])
            conn = sqlite3.connect(jurnal_db)
            cursor = conn.cursor()
            cursor.execute("SELECT continut FROM jurnal WHERE id = ?", (entry_id,))
            entry = cursor.fetchone()
            conn.close()

            if entry:
                self.text_edit.setText(entry[0])

    def add_quote(self):
        new_quote, ok = QInputDialog.getText(self, "Adauga Citat", "Introduceti noul citat:")

        if ok and new_quote.strip():
            conn = sqlite3.connect(citate_db)
            cursor = conn.cursor()  # Fixed: Use cursor instead of conn.execute()
            cursor.execute("INSERT INTO citate (text) VALUES (?)", (new_quote.strip(),))
            conn.commit()
            conn.close()

            QMessageBox.information(self, "Succes", "Citatul a fost adaugat!")
            self.quote_label.setText(get_random_quote())

if __name__ == "__main__":
    app = QApplication(sys.argv)
    window = JournalApp()
    window.show()
    sys.exit(app.exec_())
