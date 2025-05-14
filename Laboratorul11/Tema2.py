import subprocess

def main():
        c = input("Introduceti o comanda bash care are pipeuri, ex: ls -l ")
        commands = c.split("|")

        proces_trecut = None

        print(commands)

        proces = []
        for cmd in commands:
                cv = cmd.strip().split()

                if proces_trecut is None:
                        proc = subprocess.Popen(cv, stdout=subprocess.PIPE)
                else:
                        proc = subprocess.Popen(cv, stdin=proces_trecut.stdout, stdout=subprocess.PIPE)
                proces_trecut = proc
        output, err = proces_trecut.communicate()
        print(f" Resultatul este: {output.decode()} cu eroarea: {err}")

if __name__ == '__main__':
        main()