#include <iostream>

using namespace std;

long long fibo(long long input);

int main(void)
{
    long long first = 0, second = 1;
    long long target = 50;
    cout << "fibonacci!" << endl;
    
    for (long long n = 0; n <= target; ++n)
    {
        cout << "F(" << n << ") = " << fibo(n) << std::endl;
    }

    return 0;
}

long long a = 0;
long long b = 1;
long long sum = 1;
long long fibo(long long input)
{
    if (input < 2)
        return input;

     else {
        sum = a + b;
        a = b;
        b = sum;
        return b;
    }
}
