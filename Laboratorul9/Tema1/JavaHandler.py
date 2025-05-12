from FileHandler import FileHandler

class JavaHandler(FileHandler):
    def can_handle(self,content):
        return (
            "public class" in content or
            "public static void main" in content or
            "import java." in content
        )

    def get_type(self):
        return "java"
