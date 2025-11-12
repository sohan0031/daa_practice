import java.util.*;

class Edge{
    int to;
    int cost;

    public Edge(int to,int cost){
        this.to=to;
        this.cost=cost;
    }
}

public class Assi5 {

    public static int[] findShortestPath(List<List<Edge>> graph,int N,int source,int destination){
        int[] dist=new int[N];
        int[] nextNode=new int[N];

        Arrays.fill(dist,Integer.MAX_VALUE);
        Arrays.fill(nextNode,-1);

        dist[destination]=0;

        for(int i=N-2;i>=0;i--){
            for(Edge e:graph.get(i)){
                if(dist[e.to]!=Integer.MAX_VALUE && e.cost+dist[e.to]<dist[i]){
                    dist[i]=e.cost+dist[e.to];
                    nextNode[i]=e.to;
                }
            }
        }
        return nextNode;

    }

    public static void displayRoute(int source,int[] nextNode){
        int node=source;
        System.out.println("Recommended delivery route: ");
        System.out.print(node);

        while(nextNode[node]!=-1){
            node=nextNode[node];
            System.out.print("-> "+node);
        }
        System.out.println();
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);

        System.out.println("Swiftcart logistics Route Optimization");
        System.out.print("Enter the Total Number of Nodes: ");
        int N=sc.nextInt();

        List<List<Edge>> graph=new ArrayList<>();

        for(int i=0;i<N;i++){
            graph.add(new ArrayList<>());
        }

        System.out.print("Enter the possible routes between the locations: ");
        int E=sc.nextInt();

        System.out.println("Enter the Route details in Format : From To Cost");

        for(int i=0;i<E;i++){
            int u=sc.nextInt();
            int v=sc.nextInt();
            int cost=sc.nextInt();
            graph.get(u).add(new Edge(v,cost));
        }

        System.out.print("Enter the Starting Location ( Start Node ): ");
        int source=sc.nextInt();

        System.out.print("Enter the Destination Location (Destination Point): ");
        int destination=sc.nextInt();

        long startTime=System.nanoTime();

        int[] nextNode = findShortestPath(graph,N,source,destination);

        long endTime=System.nanoTime();

        System.out.println();
        System.out.println("Analyzing Delivery Route....");
        displayRoute(source, nextNode);

        System.out.println("Route Analysis Completed Successfully.");
        System.out.println("Total Computation Time: "+(endTime-startTime)/1_000_000.0+" millisecond");

        System.out.println("SwiftCargo system has Determined the most efficient delivery path.");
        sc.close();
    }
}
