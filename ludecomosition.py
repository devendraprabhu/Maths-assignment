A = [[1, 1, 1],
     [4, 3, -1],
     [3, 5, 3]]
b = [1, 6, 4]


n = 3
L = [[0 for i in range(n)] for j in range(n)]
U = [[0 for i in range(n)] for j in range(n)]


for i in range(n):
    for j in range(i, n):
        U[i][j] = A[i][j] - sum(L[i][k] * U[k][j] for k in range(i))
    for j in range(i, n):
        if i == j:
            L[i][j] = 1
        else:
            L[j][i] = (A[j][i] - sum(L[j][k] * U[k][i] for k in range(i))) / U[i][i]


y = [0] * n
for i in range(n):
    y[i] = b[i] - sum(L[i][j] * y[j] for j in range(i))


x = [0] * n
for i in range(n-1, -1, -1):
    x[i] = (y[i] - sum(U[i][j] * x[j] for j in range(i+1, n))) / U[i][i]


print("L matrix:")
for row in L:
    print(row)
print("\nU matrix:")
for row in U:
    print(row)
print(f"\nAnswer: x = {x[0]}, y = {x[1]}, z = {x[2]}")