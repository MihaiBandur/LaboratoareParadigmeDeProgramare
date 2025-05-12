from FileHandler import FileHandler

class BashHandler(FileHandler):
    def can_handle(self,content):
        return (
            content.startswith("#!/bin/bash") or
            "if [" in content or
            "$(" in content or
            "from" in content
        )

    def get_type(self):
        return "bash"
