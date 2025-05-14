import multiprocessing
class ProcesTest(multiprocessing.Process):
    def run(self):
        print(f'am apelat metoda run() in procesul: {self.name}')
        return
if __name__ == '__main__':
    jobs = []
    for i in range(5):
        p = ProcesTest()
        jobs.append(p)
        p.start()
        p.join()
    