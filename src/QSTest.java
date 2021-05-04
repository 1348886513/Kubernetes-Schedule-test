import java.util.List;

public class QSTest {
    public static void main(String[] args) {
        util util = new util();
        //pod初始化，设置pod的一些值
        util.initPod(50);
        List<Pod> podList = util.podList;
        int CPU_total=0,MEM_total=0;
        for(int i=0;i<50;i++){
            CPU_total += podList.get(i).getCPU_avg();
            MEM_total += podList.get(i).getMEM_avg();
        }
        int CPU_avg = CPU_total/50;
        int MEM_avg = MEM_total/50;
//        for(int i=0;i<podList.size();i++){
//            double res = util.getQsScore(podList.get(i),CPU_avg,MEM_avg);
//            System.out.println("the QS is: "+1/res);
//        }
        util.DividedPriorityQueue(50);
        System.out.println(util.queue1.size());
        System.out.println(util.queue2.size());
        System.out.println(util.queue3.size());
        System.out.println(util.queue4.size());

        while (!util.queue3.isEmpty()){
            System.out.print(util.queue3.poll().getScore()+" ");
        }
    }
}
