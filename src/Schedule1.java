import java.util.List;

public class Schedule1 {
    public static void main(String[] args) {
        //调度方法1的调度
        /**
         * pod初始化赋值
         * node初始化赋值
         * 按序调度，选择得分最高的节点进行绑定，失败的节点加入到队列尾部
         * */
        util util = new util();
        //pod初始化，设置pod的一些值
        util.initPod(50);
        util.initNode(5);
        List<Pod> podList = util.podList;
        List<Node> nodeList = util.nodeList;
        //pod依次调度
        int error=0;
        for(int i=0;i<10;i++){
            Node perfectNode = util.getMaxScore1Node(podList.get(i),nodeList);
            if (perfectNode == null){
                podList.add(podList.get(i));
                error++;
                continue;
            }
            else {
                //bind pod to Node
                util.bindPodToNode1(perfectNode,podList.get(i));
            }
        }
        System.out.println("the error:"+error);
        util.printResult(5);

    }
}
