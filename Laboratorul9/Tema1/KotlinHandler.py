from FileHandler import FileHandler

class KotlinHandler(FileHandler):
    def can_handle(self,content):
        return (
            "fun main(" in content or
            "import kotlin" in content or
            "when" in content
        )
    def get_type(self):
        return "kotlin"
