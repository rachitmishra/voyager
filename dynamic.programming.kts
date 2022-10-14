// no. of possible unique paths
fun uniquePaths(m: Int, n: Int) {
    if (m == 1 && n == 1) {
        return 1
    }

    return uniquePaths(m-1, n) uniquePaths(m, n-1)
}
uniquePaths(1, 1)
