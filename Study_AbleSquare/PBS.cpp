#include <cstdio>
#include <algorithm>
#include <vector>
using namespace std;
int n, m, q, x, y, z, lo[100010], hi[100010], p[100010], sz[100010], g[100010], rsz[100010];
pair<int, pair<int, int>> edge[100010];
pair<int, int> qry[100010];
vector<vector<int>> vt;
int find(int h){
    return h == p[h] ? h : p[h] = find(p[h]);
}
void merge(int x, int y) {
    x = find(x), y = find(y);
    if (x == y)return;
    p[x] = y;
    sz[y] += sz[x];
}
void init() {
    for (int i = 1; i <= n; i++)
        p[i] = i, sz[i] = 1;
    vt.clear();
    vt.resize(m + 2);
}
int main() {
    scanf("%d%d", &n, &m);
    for (int i = 1; i <= m; i++)
        scanf("%d%d%d", &edge[i].second.first, &edge[i].second.second, &edge[i].first);
    sort(edge + 1, edge + 1 + m);
    scanf("%d", &q);
    for (int i = 0; i < q; i++) {
        scanf("%d%d", &qry[i].first, &qry[i].second);
        lo[i] = 1, hi[i] = m + 1;
    }
    int f = 1;
    while (f) {
        f = 0;
        init();
        for (int i = 0; i < q; i++) {
            if (lo[i] < hi[i]) 
                vt[(lo[i] + hi[i]) >> 1].push_back(i);            
        }
        for (int i = 1; i <= m; i++) {
            merge(edge[i].second.first, edge[i].second.second);
            while (vt[i].size()) {
                f = 1;
                int idx = vt[i].back();
                vt[i].pop_back();
                if (find(qry[idx].first) == find(qry[idx].second)) {
                    hi[idx] = i;
                    g[idx] = edge[i].first;
                    rsz[idx] = sz[find(qry[idx].first)];
                }
                else
                    lo[idx] = i + 1;
            }
        }
    }
    for (int i = 0; i < q; i++) {
        if (lo[i] == m + 1)
            puts("-1");
        else
            printf("%d %d\n", g[i], rsz[i]);
    }
    return 0;
}


출처: http://jason9319.tistory.com/284 [ACM-ICPC 상 탈 사람]