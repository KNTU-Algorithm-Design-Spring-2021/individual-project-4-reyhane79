import java.util.*;

class Post {
  public static void main(String[] args) {
    Scanner sc= new Scanner(System.in);
    System.out.println("Enter N:");
    int n= sc.nextInt();
    System.out.println("Enter K:");
    int k= sc.nextInt();
    System.out.println("Enter wights:");
    List<Double> list = new ArrayList<>();
    for (int i =0; i < n; i++)
      list.add(sc.nextDouble());
    Collections.sort(list);
    PriorityQueue<Double> pq = new PriorityQueue<>();
    for (int i=0; i < k; i++)
      pq.add((double) 0);
    double maxNumberInQueue = (double) 0;
    for (int i=n-1; i >= 0; i--){
      double now = list.get(i);
      double tmp = pq.peek();
      pq.poll();
      tmp += now;
      if (tmp > maxNumberInQueue)
        maxNumberInQueue = tmp;
      pq.add(tmp);
    }
    System.out.println(maxNumberInQueue);
  }
}
