
import java.util.*;
public class Schedule3 {
    public static void main(String[] args) {
        //初始化util，设置队列
        util util = new util();
        //pod初始化，设置pod的一些值
        util.initPod(50);
        List<Pod> podList = util.podList;
        //划分优先级队列
        util.DividedPriorityQueue(50);
        //队列、pod
        PriorityQueue<Pod> queue1 = util.queue1;
        PriorityQueue<Pod> queue2 = util.queue2;
        PriorityQueue<Pod> queue3 = util.queue3;
        PriorityQueue<Pod> queue4 = util.queue4;
        //node的初始化
        util.initNode(5);
        List<Node> nodeList = util.nodeList;
        //选择一个pod进行部署，规定部署次数为n次
        int flag = 0;
        Pod pod = null;
        int error = 0;
        for(int i=0;i<50;i++){
            flag = flag%4;
            if(flag==0 && !queue1.isEmpty()) {
                pod = queue1.poll();
            }
            else if (flag==1 && !queue2.isEmpty()){
                pod = queue2.poll();
            }
            else if(flag==2 && !queue3.isEmpty()){
                pod = queue3.poll();
            }
            else if(flag==3 && !queue4.isEmpty()){
                pod = queue4.poll();
            }
            flag++;
            util.calculateScore(pod);
            Node perfectNode = util.getMaxScoreNode(pod);
            if(perfectNode==null){
                //重新插入到原有的队列里
                int podQueue = pod.getQueue();
                double podSocre = pod.getScore();
                pod.setScore(0.5*podSocre);
                if (podQueue == 1) {
                    queue1.add(pod);
                }
                else if(podQueue == 2) {
                    queue2.add(pod);
                }
                else if(podQueue == 3){
                    queue3.add(pod);
                }
                else {
                    queue4.add(pod);
                }
                error++;
            }
            else {
                if(pod !=null){
                    util.bindPodToPerfectNode(pod,perfectNode);
                }
            }

        }
        //error
        System.out.println("the error is: "+error);
        util.printResult(5);

    }
}

