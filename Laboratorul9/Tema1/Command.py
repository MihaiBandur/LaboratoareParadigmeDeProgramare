import subprocess

class Command:
    @staticmethod
    def execute(file_type, content):
        if file_type == "python":
            return subprocess.run(["python3", "-c", content], capture_output = True, text = True)
        elif file_type == "bash":
            return subprocess.run(["bash", "-c", content], capture_output = True, text = True)
        elif file_type == "kotlin":
            with open("temp.kt", "w") as f:
                f.write(content)
            result = subprocess.run(["kotlinc", "temp.kt", "-include-runtime", "-d", "temp.jar"] +
                                    ["&&", ".java", "-jar", "temp.jar"],
                                    capture_output=True, text=True, shell=True)
            subprocess.run(["rm", "temp.kt", "temp.jar"])
            return result
        elif file_type == "java":
            class_name = None
            for line in content.split('\n'):
                if "public class" in line:
                    class_name = line.split('public class')[1].split()[0].strip()
                    break
                if not class_name:
                    return None
                with open(f"{class_name}.java", "w") as f:
                    f.write(content)
                subprocess.run(["javac", f"{class_name}.java"])
                result = subprocess.run(["java",class_name], capture_output=True, text=True)
                subprocess.run(["rm", f"{class_name}.java", f"{class_name}.class"])
                return result
        else:
            return None
