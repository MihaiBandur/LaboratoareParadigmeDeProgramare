from Subject import RealSubject
from Proxy import Proxy, LoadBalancer
import time

if __name__ == "__main__":
    print("=== Testare Proxy cu Cache ===")
    serviciu_real = RealSubject()
    proxy = Proxy(serviciu_real)

    url = "https://example.com"
    print(proxy.request(url))
    print(proxy.request(url))

    print("\n=== Testare Load Balancer ===")
    balancer = LoadBalancer()
    for i in range(12):
        print(f"Cerere {i+1}: {balancer.check_request_rate()}")
        time.sleep(0.1)
