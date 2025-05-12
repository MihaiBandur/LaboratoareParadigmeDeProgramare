from FileHandler import FileHandler

class PythonHandler(FileHandler):
    def can_handle(self,content):
        return (
            "def" in content or
            "import" in content or
            "from" in content
        )

    def get_type(self):
        return "python"
