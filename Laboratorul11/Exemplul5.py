import asyncio

async def print_number(number):
    print(number)

async def main():
    await asyncio.gather(
        *[print_number(number) for number in range(10)]
    )

if __name__ == "__main__":
    asyncio.run(main())
