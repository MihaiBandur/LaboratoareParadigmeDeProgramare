import asyncio


async def gauss(name: str, q):
    n = await q.get()
    s = 0
    for i in range(0, 1 + n):
        s = s + i
    print(f" Task-ul {name}, suma primelor {n} numere este {s}")
    q.task_done()


async def main():
        q = asyncio.queues.Queue()
        v = [10, 12, 15, 20]
        for i in v:
            await q.put(i)
        await asyncio.gather(
            gauss("A", q),
            gauss("B", q),
            gauss("C", q),
            gauss("D", q )
        )
        await q.join()

if __name__ == '__main__':
    asyncio.run(main())