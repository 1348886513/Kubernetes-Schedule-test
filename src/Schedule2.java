import java.util.List;

public class Schedule2 {
    public static void main(String[] args) {
        //第二种调度算法，同时考虑getScore1和getScore2
        util util2 = new util();
        //pod初始化，设置pod的一些值
        util2.initPod(50);
        util2.initNode(5);
        List<Pod> podList = util2.podList;
        List<Node> nodeList = util2.nodeList;
        //pod依次调度
        int error=0;
        for(int i=0;i<50;i++){
            Node perfectNode = util2.getMaxScore2Node(podList.get(i),nodeList);
            if (perfectNode == null){
                podList.add(podList.get(i));
                error++;
            }
            else {
                //bind pod to Node
                util2.bindPodToNode2(perfectNode,podList.get(i));
            }
        }
        System.out.println("the error:"+error);
        util2.printResult(5);
    }
}
